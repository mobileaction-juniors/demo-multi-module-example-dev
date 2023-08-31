package co.mobileaction.example.web.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto
{
    private String city;
    private List<ResultDto> results;
}
