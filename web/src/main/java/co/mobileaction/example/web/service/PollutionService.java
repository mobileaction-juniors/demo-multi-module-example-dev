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
import java.util.ArrayList;
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
    private static final String API_POLLUTION_URL = "http://api.openweathermap.org/data/2.5/air_pollution/history?lat=%f&lon=%f&start=%s&end=%s&appid=%s";
    private static final String APP_ID = "ce615b45850cd320186740aa1d646eda";
    private static final Long SECONDS_IN_ONE_DAY = 86400L;
    private static final Long UNIX_TIME_27_11_2020 = 1606424400L;

    private void fetchAndSavePollution(City city, Long start, Long end)
    {
        PollutionApiResultDto result = fetchPollution(city.getName(), start, end);

        int index = 0;
        float sumCo = 0;
        float sumO3 = 0;
        float sumSo2 = 0;
        for (PollutionValuesDto pollutionValuesDto : result.getList())
        {
            sumCo += pollutionValuesDto.getComponents().getCo();
            sumO3 += pollutionValuesDto.getComponents().getO3();
            sumSo2 += pollutionValuesDto.getComponents().getSo2();
            index++;
            if(index == 24)
            {
                float avgCo = sumCo / 24;
                float avgSo2 = sumSo2 / 24;
                float avgO3 = sumO3 / 24;
                LocalDate date = Instant.ofEpochSecond(pollutionValuesDto.getDt()).atOffset(ZoneOffset.UTC).toLocalDate();
                Pollution pollution = Pollution.builder()
                        .city(city)
                        .date(date)
                        .co(avgCo)
                        .so2(avgSo2)
                        .o3(avgO3)
                        .build();

                pollutionRepository.save(pollution);

                sumCo = 0;
                sumO3 = 0;
                sumSo2 = 0;
                index = 0;
            }
        }
    }

    public PollutionApiResultDto fetchPollution(String cityName, Long start, Long end)
    {
        cityService.fetchAndSaveCity(cityName);
        City city = cityService.findCityByName(cityName);
        String url = String.format(API_POLLUTION_URL, city.getLat(), city.getLon(), start, end, APP_ID);
        return httpRequestExecutor.executeGetRequest(url, PollutionApiResultDto.class);
    }

    @Override
    public AnswerDto getResults(String cityName, LocalDate startDate, LocalDate endDate) throws DateOutOfRangeException
    {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setCity(cityName);
        answerDto.setResults(new ArrayList<>());
        cityService.fetchAndSaveCity(cityName);
        City city = cityService.findCityByName(cityName);

        List<LocalDate> allDates = startDate.datesUntil(endDate).collect(Collectors.toList());
        allDates.add(endDate);
        for (LocalDate date : allDates)
        {
            if (!pollutionRepository.existsByCityAndDate(city, date))
            {
                Long currentDay = date.atStartOfDay().toInstant(ZoneOffset.UTC).getEpochSecond();
                Long nextDay = currentDay + SECONDS_IN_ONE_DAY - 1;
                fetchAndSavePollution(city, currentDay, nextDay);
                log.info("Pollution data is fetched from the API");
            }
            else
            {
                log.info("Pollution data is fetched from the database");
            }
            if(!(date.atStartOfDay().toInstant(ZoneOffset.UTC).getEpochSecond() > UNIX_TIME_27_11_2020))
            {
                log.warn("The dates you required includes an invalid date, i.e. a date before 2020-11-27!");
                throw new DateOutOfRangeException("Invalid date");
            }
            else
            {
                CategoriesDto categoriesDto = determineCategories(city, date);
                answerDto.getResults().add(new ResultDto(date, categoriesDto));
            }
        }
        return answerDto;
    }

    private CategoriesDto determineCategories(City city, LocalDate date)
    {
        if(!pollutionRepository.existsByCityAndDate(city, date))
        {
            return null;
        }

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
        String o3Value = PollutionUtils.determineO3Value(avgO3);
        String so2Value = PollutionUtils.determineSo2Value(avgSo2);

        return new CategoriesDto(coValue, o3Value, so2Value);
    }
}
