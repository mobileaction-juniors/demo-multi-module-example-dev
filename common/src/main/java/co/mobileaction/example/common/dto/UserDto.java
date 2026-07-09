package co.mobileaction.example.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Yunus Gunay
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDto(
        Long id,
        String name,
        String username,
        String email
)
{
}
