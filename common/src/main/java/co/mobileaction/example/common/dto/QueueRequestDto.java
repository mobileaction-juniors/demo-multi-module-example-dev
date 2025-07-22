package co.mobileaction.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

/**
 * @author sa
 * @date 17.05.2021
 * @time 13:49
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueRequestDto {
    private Long postId;
    private Long userId;

    public static QueueRequestDto forUser(Long userId) {
        return new QueueRequestDto(null, userId);
    }

    public static QueueRequestDto forPost(Long postId) {
        return new QueueRequestDto(postId, null);
    }
}
