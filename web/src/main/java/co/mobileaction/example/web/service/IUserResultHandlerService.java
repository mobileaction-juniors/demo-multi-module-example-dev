package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;

/**
 * @author Doga Elif Konuk
 * @date 17.07.2024
 * @time 15:15
 */
public interface IUserResultHandlerService {
    void executeMessage(UserDto userDto);
}
