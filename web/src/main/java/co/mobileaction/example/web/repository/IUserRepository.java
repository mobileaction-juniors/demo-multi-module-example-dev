package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Doga Elif Konuk
 * @date 17.07.2024
 * @time 15:15
 */
public interface IUserRepository extends JpaRepository<User, Long> {
}
