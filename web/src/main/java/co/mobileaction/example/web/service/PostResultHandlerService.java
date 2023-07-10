package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.web.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:39
 */
@Service
@RequiredArgsConstructor
public class PostResultHandlerService implements IPostResultHandlerService
{
    private final IPostService postService;

    @Override
    public void executeMessage(PostDto postDto)
    {
        postService.savePost(convertFrom(postDto));
    }

    private Post convertFrom(PostDto postDto)
    {
        return Post.builder()
                .id(postDto.getId())
                .userId(postDto.getUserId())
                .title(postDto.getTitle())
                .body(postDto.getBody())
                .build();
    }
}
