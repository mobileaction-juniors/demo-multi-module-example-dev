package co.mobileaction.example.web.service;

import co.mobileaction.example.web.dto.AnswerDto;
import co.mobileaction.example.web.dto.CategoriesDto;
import co.mobileaction.example.web.dto.PollutionApiResultDto;
import co.mobileaction.example.web.model.City;
import co.mobileaction.example.web.model.Pollution;

import java.time.LocalDate;

public interface IPollutionService
{
    void fetchAndSavePollution(String cityName, Long start, Long end);
    PollutionApiResultDto fetchPollution(String cityName, Long start, Long end);

    AnswerDto getResults(String cityName, Long start, Long end);
    CategoriesDto determineCategories(City city, LocalDate date);
}
