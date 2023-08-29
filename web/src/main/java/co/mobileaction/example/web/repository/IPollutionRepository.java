package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.City;
import co.mobileaction.example.web.model.Pollution;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IPollutionRepository extends JpaRepository<Pollution, Long>
{

    List<Pollution> findAllByCityAndDate(City city, LocalDate date);

    Boolean existsByCityAndDate(City city, LocalDate date);
}
