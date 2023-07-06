package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.exception.AlreadyExistException;
import co.mobileaction.example.web.exception.NotFoundException;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:39
 */
public interface IPostResultHandlerService
{
    void executeMessage(PostDto postDto) throws NotFoundException;
}
