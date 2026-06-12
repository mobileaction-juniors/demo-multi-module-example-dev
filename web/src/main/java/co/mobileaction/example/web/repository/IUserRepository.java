package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author serkankorkut
 * @date 12.06.2026
 * @time 14:20
 */
public interface IUserRepository extends JpaRepository<User, Long>
{
}
