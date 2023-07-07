package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:45
 */
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT p.userId FROM Post p")
    List<Long> findAllDistinctUserIds();

}
