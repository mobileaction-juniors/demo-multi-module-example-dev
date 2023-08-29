package co.mobileaction.example.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.mobileaction.example.web.model.City;
public interface ICityRepository extends JpaRepository<City, Long>
{
    City findCityByName(String name);
    City findCityByLatAndLon(float lat, float lon);

    Boolean existsByName(String name);
}
