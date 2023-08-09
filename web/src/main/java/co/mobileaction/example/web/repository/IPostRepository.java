package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:45
 */
public interface IPostRepository extends JpaRepository<Post, Long>
{
    List<Post> findAllByUserId(Long userId);

    @Modifying
    @Query(value = "DELETE FROM posts WHERE user_id = :userId", nativeQuery = true)
    void deletePostsByUserId(Long userId);
}
