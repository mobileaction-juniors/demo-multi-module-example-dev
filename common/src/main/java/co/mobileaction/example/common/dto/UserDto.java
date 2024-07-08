package co.mobileaction.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Mehmet Akif Sahin
 * @date 03.07.2024
 * @time 10:51
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto
{
    private Long id;
    private String name;
    private String username;
    private String email;
}
