package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.PostDto;
import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.exception.AlreadyExistException;

/**
 * @author elif
 * @date 05.07.2023
 * @time 17.15
 */
public interface IUserResultHandlerService
{
    void executeMessage(UserDto userDto) throws AlreadyExistException;
}
