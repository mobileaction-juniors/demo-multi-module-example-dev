package co.mobileaction.example.web.dto;

import co.mobileaction.example.web.model.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PollutionDto
{
    private Long id;
    private City city;
    private LocalDate date;
    private String co;
    private String so2;
    private String o3;
}
