package co.mobileaction.example.web.client;

import co.mobileaction.example.web.dto.CityDto;
import co.mobileaction.example.web.dto.PollutionApiResultDto;
import co.mobileaction.example.web.dto.PollutionDto;
import co.mobileaction.example.web.dto.PollutionValuesDto;

import java.util.List;

public interface ICrawlerClient
{
    CityDto fetchCity(String cityName);
}
