package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;

/**
 * @author Yunus Gunay
 */
public interface IUserResultHandlerService
{
    void executeMessage(UserDto userDto);
}
