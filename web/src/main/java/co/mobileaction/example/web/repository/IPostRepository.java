package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:45
 */
@Transactional
@Repository
public interface IPostRepository extends JpaRepository<Post, Long>
{
    List<Post> findAllByUserId(Long userId);

    @Query("select distinct p.userId from Post p")
    List<Long> getDistinctIds();

}
