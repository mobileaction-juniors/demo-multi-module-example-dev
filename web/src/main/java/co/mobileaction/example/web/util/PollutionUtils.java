package co.mobileaction.example.web.util;

import co.mobileaction.example.web.dto.CategoriesDto;
import co.mobileaction.example.web.model.City;
import co.mobileaction.example.web.repository.IPollutionRepository;

import java.time.LocalDate;

public class PollutionUtils
{
    public static final String[] CITIES = {"London", "Barcelona", "Ankara", "Tokyo", "Mumbai"};

    public static float roundToOneDecimal(float value)
    {
        return (float) Math.round(value * 10) / 10;
    }

    public static String determineCoValue(float coValue)
    {
        float value = roundToOneDecimal(coValue);
        if(value < 1.1)
            return "Good";
        else if(value < 2.1)
            return "Satisfactory";
        else if(value < 10.0)
            return "Moderate";
        else if(value < 17.0)
            return "Poor";
        else if(value < 34.0)
            return "Severe";
        else
            return "Hazardous";
    }

    public static String determineO3Value(float coValue)
    {
        float value = roundToOneDecimal(coValue);
        if(value < 51.0)
            return "Good";
        else if(value < 101.0)
            return "Satisfactory";
        else if(value < 169.0)
            return "Moderate";
        else if(value < 209.0)
            return "Poor";
        else if(value < 748.0)
            return "Severe";
        else
            return "Hazardous";
    }

    public static String determineSo2Value(float coValue)
    {
        float value = roundToOneDecimal(coValue);
        if(value < 41.0)
            return "Good";
        else if(value < 81.0)
            return "Satisfactory";
        else if(value < 381.0)
            return "Moderate";
        else if(value < 801.0)
            return "Poor";
        else if(value < 1800.0)
            return "Severe";
        else
            return "Hazardous";
    }

}
