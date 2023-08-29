package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.City;

public interface ICityService
{
    void fetchAndSaveCity(String name);
    void saveCity(City city);
    City findCityByName(String name);

}
