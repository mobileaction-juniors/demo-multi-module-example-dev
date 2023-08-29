package co.mobileaction.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PollutionElementsDto
{
    float co;
    float so2;
    float o3;
}
