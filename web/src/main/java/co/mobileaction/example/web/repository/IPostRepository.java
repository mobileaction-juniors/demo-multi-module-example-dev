package co.mobileaction.example.web.repository;

import co.mobileaction.example.common.dto.IUserId;
import co.mobileaction.example.common.dto.PostCountDto;
import co.mobileaction.example.web.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:45
 */

@Transactional
public interface IPostRepository extends JpaRepository<Post, Long>
{
    public final JdbcTemplate jdbcTemplate = null;
    List<Post> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);

    @Query(value = "select distinct p.userId from Post p")
    List<Long> findDistinctUserIds();

    @Query("select count (distinct p.title) from Post p")
    int countNumberOfUniqueTitles();

    @Query(nativeQuery = true, value = "select distinct user_id as userId from posts")
    List<IUserId> findDistinctUserIdsNative();

    @Query( value = "select new co.mobileaction.example.common.dto.PostCountDto(u.name, count(p.id)) " +
            "from User u " +
            "inner join Post p on u.id = p.userId " +
            "where u.name like 'C%' " +
            "group by u.name")
    List<PostCountDto> findAllPostsOfUsersK();
}
