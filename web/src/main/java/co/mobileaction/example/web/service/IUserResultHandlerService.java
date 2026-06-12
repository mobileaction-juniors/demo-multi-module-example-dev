package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;

/**
 * @author serkankorkut
 * @date 12.06.2026
 * @time 14:32
 */
public interface IUserResultHandlerService
{
    void executeMessage(UserDto userDto);
}
