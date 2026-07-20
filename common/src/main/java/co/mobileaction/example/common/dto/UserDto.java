package co.mobileaction.example.common.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto
{
    private Long id;
    private String username;
    private String email;
    private String name;
}
