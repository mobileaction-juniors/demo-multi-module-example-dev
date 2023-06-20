package co.mobileaction.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author sa
 * @date 17.05.2021
 * @time 13:38
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto
{
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
