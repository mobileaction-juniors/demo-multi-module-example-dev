package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long>{

    List<User> findAllById(Long id);

    @Query("SELECT DISTINCT userId FROM Post")
    List<Long> findDistinctUserIDs();

}
