package co.mobileaction.example.web.service;

import co.mobileaction.example.web.client.IHttpRequestExecutor;
import co.mobileaction.example.web.dto.*;
import co.mobileaction.example.web.exception.DateOutOfRangeException;
import co.mobileaction.example.web.model.City;
import co.mobileaction.example.web.model.Pollution;
import co.mobileaction.example.web.repository.IPollutionRepository;
import co.mobileaction.example.web.util.PollutionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class PollutionService implements IPollutionService
{
    private final IPollutionRepository pollutionRepository;
    private final ICityService cityService;
    private final IHttpRequestExecutor httpRequestExecutor;
    public static final String API_POLLUTION_URL = "http://api.openweathermap.org/data/2.5/air_pollution/history?lat=%f&lon=%f&start=%s&end=%s&appid=%s";
    private static final String APP_ID = "ce615b45850cd320186740aa1d646eda";
    @Override
    public void fetchAndSavePollution(String cityName, Long start, Long end)
    {

        PollutionApiResultDto result = fetchPollution(cityName, start, end);
        cityService.fetchAndSaveCity(cityName);
        City city = cityService.findCityByName(cityName);
        for (PollutionValuesDto pollutionValuesDto : result.getList())
        {
            if(pollutionValuesDto.getDt() > 1606424400)
            {
                LocalDate date = Instant.ofEpochSecond(pollutionValuesDto.getDt()).atZone(ZoneId.systemDefault()).toLocalDate();
                Pollution pollution = Pollution.builder()
                        .city(city)
                        .date(date)
                        .co(pollutionValuesDto.getComponents().getCo())
                        .so2(pollutionValuesDto.getComponents().getSo2())
                        .o3(pollutionValuesDto.getComponents().getO3())
                        .build();

                pollutionRepository.save(pollution);
            }
        }
    }

    @Override
    public PollutionApiResultDto fetchPollution(String cityName, Long start, Long end)
    {
        cityService.fetchAndSaveCity(cityName);
        City city = cityService.findCityByName(cityName);
        String url = String.format(API_POLLUTION_URL, city.getLat(), city.getLon(), start, end, APP_ID);
        return httpRequestExecutor.executeGetRequest(url, PollutionApiResultDto.class);
    }

    @Override
    public AnswerDto getResults(String cityName, Long start, Long end)
    {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setCity(cityName);
        cityService.fetchAndSaveCity(cityName);
        City city = cityService.findCityByName(cityName);
        if(start == null && end == null)
        {
            end = Instant.now().getEpochSecond();
            start = end - (7 * 86400);
        }
        LocalDate startDate = Instant.ofEpochSecond(start).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = Instant.ofEpochSecond(end).atZone(ZoneId.systemDefault()).toLocalDate();
        List<LocalDate> allDates = startDate.datesUntil(endDate).collect(Collectors.toList());
        allDates.add(endDate);
        for (LocalDate date : allDates)
        {
            if (!pollutionRepository.existsByCityAndDate(city, date))
            {
                Long currentDay = date.toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.MIN);
                Long nextDay = currentDay + 86400;
                fetchAndSavePollution(cityName, currentDay, nextDay);
                log.info("Pollution data is fetched from the API");
            }
            else
            {
                log.info("Pollution data is fetched from the database");
            }
            if(date.toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.MIN) > 1606424400)
            {
                CategoriesDto categoriesDto = determineCategories(city, date);
                answerDto.getResults().add(new ResultDto(date, categoriesDto));
            }
            else
            {
                throw new DateOutOfRangeException("Required date is out of range, i.e. before 2020-11-27");
            }
        }
        return answerDto;
    }

    @Override
    public CategoriesDto determineCategories(City city, LocalDate date)
    {
        if(pollutionRepository.existsByCityAndDate(city, date))
        {
            List<Pollution> pollutionList = pollutionRepository.findAllByCityAndDate(city, date);
            float sumCo = 0;
            float sumO3 = 0;
            float sumSo2 = 0;
            for(Pollution p : pollutionList)
            {
                sumCo += p.getCo();
                sumO3 += p.getO3();
                sumSo2 = p.getSo2();
            }

            int size = pollutionList.size();
            float avgCo = sumCo / size;
            float avgO3 = sumO3 / size;
            float avgSo2 = sumSo2 / size;

            String coValue = PollutionUtils.determineCoValue(avgCo);
            String o3Value = PollutionUtils.determineCoValue(avgO3);
            String so2Value = PollutionUtils.determineCoValue(avgSo2);

            return new CategoriesDto(coValue, o3Value, so2Value);
        }
        return null;
    }
}
