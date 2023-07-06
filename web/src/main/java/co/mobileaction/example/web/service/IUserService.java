package co.mobileaction.example.web.service;

import co.mobileaction.example.web.exception.AlreadyExistException;
import co.mobileaction.example.web.exception.NotFoundException;
import co.mobileaction.example.web.model.User;

/**
 * @author elif
 * @date 05.07.2023
 * @time 14.36
 */
public interface IUserService
{
    void saveUser(User user) throws AlreadyExistException;

    User getUserReference(Long id) throws NotFoundException;

    boolean userExist(Long id);
}
