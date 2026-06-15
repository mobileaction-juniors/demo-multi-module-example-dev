package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService
{
    List<UserDto> findUsers(Pageable pageable);

    void saveUser(User user);
}
