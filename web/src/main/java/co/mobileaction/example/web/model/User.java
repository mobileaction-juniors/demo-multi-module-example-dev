package co.mobileaction.example.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 16:49
 */
@Data
@Entity
@Table(name = "user_details")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User
{
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;
}
