package co.mobileaction.example.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:41
 */
@Data
@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post
{
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;
}
