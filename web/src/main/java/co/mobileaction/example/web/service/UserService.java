package co.mobileaction.example.web.service;

import co.mobileaction.example.web.exception.AlreadyExistException;
import co.mobileaction.example.web.exception.NotFoundException;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elif
 * @date 05.07.2023
 * @time 14.37
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService
{
    private final IUserRepository userRepository;

    @Override
    public void saveUser(User user) throws AlreadyExistException
    {
        // check if user already exists
        boolean userAlreadyExists = userExist(user.getId());

        if (userAlreadyExists)
        {
            throw new AlreadyExistException("user");
        }

        userRepository.save(user);
    }

    @Override
    public User getUserReference(Long id) throws NotFoundException
    {
        // check if user already exists
        boolean userExists = userExist(id);

        if (!userExists)
        {
            throw new NotFoundException("user", "id", id);
        }

        return userRepository.getReferenceById(id);
    }

    @Override
    public boolean userExist(Long id)
    {
        return userRepository.existsById(id);
    }
}
