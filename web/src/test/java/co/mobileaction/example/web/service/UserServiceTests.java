package co.mobileaction.example.web.service;


import co.mobileaction.example.web.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql("/data/users.sql")
public class UserServiceTests {

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void queueAllDistinctUserIds()
    {
        postService.queueUniqueUserIds();
    }



}
