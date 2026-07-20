package co.mobileaction.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto
{
    private Long id;
    private String username;
    private String email;
    private String phone;
}
