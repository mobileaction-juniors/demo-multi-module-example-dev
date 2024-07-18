package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:45
 */
public interface IPostRepository extends JpaRepository<Post, Long>
{
    List<Post> findAllByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM posts WHERE user_id = :userId", nativeQuery = true)
    void deleteById(Long userId);

    @Query("SELECT DISTINCT p.userId FROM Post p")
    Set<Long> findDistinctUserIds();
}
