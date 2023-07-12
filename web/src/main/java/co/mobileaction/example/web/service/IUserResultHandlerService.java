package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;

public interface IUserResultHandlerService {

    void executeMessage(UserDto userDto);

    User convertFrom(UserDto userDto);
}
