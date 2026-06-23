package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:07
 */
public interface IUserRepository extends JpaRepository<User, Long>
{

}
