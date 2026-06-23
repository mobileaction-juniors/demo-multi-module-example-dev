package co.mobileaction.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:10
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
    private String phone;
    private String email;
    private String website;
}
