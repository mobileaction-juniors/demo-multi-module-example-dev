package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.model.User;
import co.mobileaction.example.web.repository.IPostRepository;
import co.mobileaction.example.web.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final IUserRepository userRepository;

    private final IPostRepository postRepository;

    @Override
    public void saveUser(User user)
    {
        userRepository.save(user);
    }


    @Override
    public List<Long> getAllDistinctIds()
    {
        return postRepository.getAllDistinctIds();
    }



}
