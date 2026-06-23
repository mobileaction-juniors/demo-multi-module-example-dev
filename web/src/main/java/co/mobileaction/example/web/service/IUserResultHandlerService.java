package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:09
 */
public interface IUserResultHandlerService
{
    void executeMessage(UserDto userDto);
}
