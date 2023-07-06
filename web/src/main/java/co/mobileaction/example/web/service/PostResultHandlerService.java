package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.web.exception.NotFoundException;
import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.model.User;
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
    private final IUserService userService;

    @Override
    public void executeMessage(PostDto postDto) throws NotFoundException
    {
        postService.savePost(convertFrom(postDto));
    }

    private Post convertFrom(PostDto postDto) throws NotFoundException
    {
        User user = userService.getUserReference(postDto.getUserId());

        return Post.builder()
                .id(postDto.getId())
                .user(user)
                .title(postDto.getTitle())
                .body(postDto.getBody())
                .build();
    }
}
