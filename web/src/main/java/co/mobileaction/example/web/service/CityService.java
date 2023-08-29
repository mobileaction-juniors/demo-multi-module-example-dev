package co.mobileaction.example.web.service;

import co.mobileaction.example.web.client.ICrawlerClient;
import co.mobileaction.example.web.dto.CityDto;
import co.mobileaction.example.web.model.City;
import co.mobileaction.example.web.repository.ICityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

public class CityService implements ICityService
{
    private final ICityRepository cityRepository;

    private final ICrawlerClient crawlerClient;

    @Override
    public void fetchAndSaveCity(String name)
    {
        if(!cityRepository.existsByName(name))
        {
            CityDto cityDto = crawlerClient.fetchCity(name);
            log.info("The city {} is fetched from the API", name);
            City city = City.builder()
                    .name(name)
                    .lat(cityDto.getLat())
                    .lon(cityDto.getLon())
                    .build();
            saveCity(city);
        }
    }

    @Override
    public void saveCity(City city)
    {
        cityRepository.save(city);
    }

    @Override
    public City findCityByName(String name)
    {
        return cityRepository.findCityByName(name);
    }
}
