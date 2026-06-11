package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
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
    @Query("SELECT DISTINCT p.userId FROM Post p")
    List<Long> findDistinctUserIds();

}



/**
 * 1. Sen → POST /api/admin/queue/posts isteği atıyorsun

2. AdminController → PostQueueService'i çağırıyor
   "1'den 100'e kadar her postId için kuyruga mesaj at"

3. Kuyrukta 100 mesaj birikiyor:
   [postId:1] [postId:2] [postId:3] ... [postId:100]

4. Worker kuyruğu dinliyor, mesajları tek tek alıyor
   postId:1 geldi → jsonplaceholder.typicode.com/posts/1 adresine git, veriyi çek

5. Worker çektiği veriyi "sonuç kuyruğuna" koyuyor
   [post verisi] → result queue

6. Web sonuç kuyruğunu dinliyor
   Veri geldi → DB'ye kaydet

 */