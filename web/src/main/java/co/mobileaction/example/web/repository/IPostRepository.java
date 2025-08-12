package co.mobileaction.example.web.repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import co.mobileaction.example.web.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:45
 */
public interface IPostRepository extends JpaRepository<Post, Long>
{
   @Modifying
   @Transactional
   void deleteByUserId(Long userId);
    List<Post> findAllByUserId(Long userId);
}
