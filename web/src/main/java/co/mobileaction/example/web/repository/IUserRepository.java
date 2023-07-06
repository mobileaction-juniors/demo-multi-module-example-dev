package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author elif
 * @date 05.07.2023
 * @time 14.38
 */
public interface IUserRepository extends JpaRepository<User, Long>
{
    boolean existsById(Long id);
    User getReferenceById(Long id);
}
