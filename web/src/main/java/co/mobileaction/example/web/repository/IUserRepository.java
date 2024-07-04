package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author elif ece can
 * @date 04.07.2024
 * @time 17:45
 */

@Transactional
@Repository
public interface IUserRepository extends JpaRepository<User, Long>
{
    List<User> findAll();
}
