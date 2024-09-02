package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public void saveUser(User user) {

        userRepository.save(user);

    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);

    }
}
