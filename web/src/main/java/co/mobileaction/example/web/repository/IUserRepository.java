package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mehmet Akif Sahin
 * @date 03.07.2024
 * @time 13:35
 */
public interface IUserRepository extends JpaRepository<User, Long>
{
}
