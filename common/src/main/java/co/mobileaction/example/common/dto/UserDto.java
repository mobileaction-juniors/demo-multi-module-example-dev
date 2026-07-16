package co.mobileaction.example.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * User crawl results intentionally exclude remote contact and nested details.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto
{
    private Long id;
    private String name;
    private String username;
}
