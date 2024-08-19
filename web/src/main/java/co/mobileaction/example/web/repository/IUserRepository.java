package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface IUserRepository extends JpaRepository<User, Long>{}