package co.mobileaction.example.web.repository;

import co.mobileaction.example.web.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:45
 */
public interface IPostRepository extends JpaRepository<Post, Long>
// JpaRepository bize CRUD işlemlerini hazır olarak sunar. save, findAll, deleteById gibi metotları kullanabiliriz.
// post = hangi tbablo ile çalışıyoruz, long = o tablonun primary key tipi
{
    List<Post> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId); //-->DELETE FROM posts WHERE user_id = ? SQL üretiyo
    //spring uygulama ayağa kalktığımda bu metodu görecek ve deleteAllByUserId isimli bir metot oluşturacak.
    //  Bu metot user_id'ye göre silme işlemi yapacak.
    //napması gerektipini isimden anlıyo delete + AllBy + UserId
}
//PostRepository i Spring yazdı biz sadece interface i tanımladık. 
// Spring uygulama ayağa kalktığında bu interface i görüp onun bir implementasyonunu oluşturacak ve bize verecek.

/**
 * PostController   ← HTTP isteği buraya gelir
    ↓
PostService      ← iş mantığı burada
    ↓
IPostRepository  ← veritabanı işlemleri burada
    ↓
Veritabanı
 */
