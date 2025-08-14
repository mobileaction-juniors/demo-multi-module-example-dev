package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.dto.CreateUserCmd;
import co.mobileaction.example.web.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService
{
    User saveUser(CreateUserCmd createUserCmd);
    List<User> findUsers(Pageable pageable);
}
