package co.mobileaction.example.common.dto;

import lombok.*;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class UserCrawlResultDto 
{
    private Long id;       
    private String name;
    private String username;
}
