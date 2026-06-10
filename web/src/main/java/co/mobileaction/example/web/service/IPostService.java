package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:46
 */
public interface IPostService
{
    void savePost(Post post);

    List<Post> findPosts(Pageable pageable);

    List<Post> findAllPostsOfUser(Long userId);

    void deletePost(Long postId);

    void deleteAllPostsByUserId(Long userId); //bu metodu ekledik ama sadece tanumlama şeklinde yine isimden SQL üretiyo
    // ama IPostRepository Spring DAta JPA üzerinden çalışıyodu (extends JpaRepository<Post, Long>)  o yüzden otomatik dolduruyodu bu 
    //bizim yazdığımız 1 interface dolayısıyla Spring burayı tanımıyor
    //so body i biz yazcaz
}

//IPostService = ne yapılacak (tanım)
// PostService = nasıl yapılacak (gerçekleştirim)

//Controllerı private final IPostService postService; ile IPostService e bağladık bu sayede controller 
//sadece şu metotlar var biliyor ama nasıl çalıştığını bilmiyor. 
// Bu sayede controller ile service birbirinden bağımsız çalışıyor.
