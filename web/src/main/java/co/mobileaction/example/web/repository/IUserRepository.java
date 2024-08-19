package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

}
