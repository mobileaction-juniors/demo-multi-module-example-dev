package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:45
 */
public interface IPostRepository extends JpaRepository<Post, Long>
{

    List<Post> findAllByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Post p WHERE p.userId = :userId")
    void deletePostByUserId(@Param("userId") Long userId);
}
