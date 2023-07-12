package co.mobileaction.example.web.repository;
import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long>
{


}
