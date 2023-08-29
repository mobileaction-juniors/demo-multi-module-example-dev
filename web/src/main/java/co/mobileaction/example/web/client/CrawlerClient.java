package co.mobileaction.example.web.client;

import co.mobileaction.example.web.dto.*;
import co.mobileaction.example.web.model.City;
import co.mobileaction.example.web.repository.ICityRepository;
import co.mobileaction.example.web.service.ICityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlerClient implements ICrawlerClient
{
    private static final String APP_ID = "ce615b45850cd320186740aa1d646eda";
    public static final String API_CITY_URL = "http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=5&appid=%s";
    private final IHttpRequestExecutor httpRequestExecutor;

    @Override
    public CityDto fetchCity(String cityName)
    {
        String url = String.format(API_CITY_URL, cityName, APP_ID);
        List<CityDto> cityDtoList = Arrays.stream(httpRequestExecutor.executeGetRequest(url, CityDto[].class)).toList();
        return cityDtoList.get(0);
    }
}
