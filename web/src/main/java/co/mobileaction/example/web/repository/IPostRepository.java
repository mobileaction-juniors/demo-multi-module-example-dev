package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface IPostRepository extends JpaRepository<Post, Long>
{
    List<Post> findAllByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Post p WHERE p.userId = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT DISTINCT p.userId FROM Post p")
    Set<Long> findDistinctUserIds();

}
