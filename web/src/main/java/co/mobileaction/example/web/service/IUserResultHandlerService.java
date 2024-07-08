package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;

/**
 * @author Mehmet Akif Sahin
 * @date 03.07.2024
 * @time 11:05
 */
public interface IUserResultHandlerService
{
    public void executeMessage(UserDto userDto);
}
