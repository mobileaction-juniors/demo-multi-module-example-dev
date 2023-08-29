package co.mobileaction.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CityDto
{
    private String name;
    private float lat;
    private float lon;
}