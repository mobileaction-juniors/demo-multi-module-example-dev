package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
     
}

//burda DB ye sorgu atıyoryz 

/**
 * User için özel bir sorgu yazmıyoruz — sadece "kaydet" diyeceğiz. O da hazır:
userRepository.save(user)  // JpaRepository'den geliyor, biz yazmıyoruz
 */

