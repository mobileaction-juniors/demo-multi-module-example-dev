package co.mobileaction.example.common.dto;

import lombok.Builder;

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:10
 */
@Builder
public record UserDto(Long id,
                      String name,
                      String username,
                      String phone,
                      String email,
                      String website)
{

}
