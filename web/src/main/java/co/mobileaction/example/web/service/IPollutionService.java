package co.mobileaction.example.web.service;

import co.mobileaction.example.web.dto.AnswerDto;
import co.mobileaction.example.web.dto.CategoriesDto;
import co.mobileaction.example.web.dto.PollutionApiResultDto;
import co.mobileaction.example.web.model.City;
import co.mobileaction.example.web.model.Pollution;

import java.time.LocalDate;

public interface IPollutionService
{
    AnswerDto getResults(String cityName, LocalDate startDate, LocalDate endDate);
}
