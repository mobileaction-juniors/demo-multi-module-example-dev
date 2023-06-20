package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.PostDto;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:39
 */
public interface IPostResultHandlerService
{
    void executeMessage(PostDto postDto);
}
