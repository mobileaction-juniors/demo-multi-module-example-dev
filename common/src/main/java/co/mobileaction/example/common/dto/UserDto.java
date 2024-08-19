package co.mobileaction.example.common.dto;

import lombok.*;

/**
 * @author sa
 * @date 17.05.2021
 * @time 13:38
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto
{
    @Setter
    private Long id;
    @Setter
    private String name;
    @Setter
    private String username;
    @Setter
    private String email;
    @Setter
    private String phone;
    @Setter
    private String website;

}