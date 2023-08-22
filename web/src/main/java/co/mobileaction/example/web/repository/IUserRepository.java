package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Transactional
@Repository
public interface IUserRepository extends JpaRepository<User, Long>{

    List<User> findAll();

}
