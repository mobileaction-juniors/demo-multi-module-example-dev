package co.mobileaction.example.common.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PostCountDto
{
    private String name;
    private Long postCount;
}
