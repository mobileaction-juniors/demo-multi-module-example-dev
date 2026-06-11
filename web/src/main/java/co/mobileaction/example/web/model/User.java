package co.mobileaction.example.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//DB de users tablosu oluşturmak için UserDto taşıma aracıydı 
//kuyrukta gidiyo geliyo ama nu DB ye kaydettiğimiz nesne
//kaydetme işleminin IUserServicd + userServicete yapıyoruz
@Data
@Entity //bu 1 DB tablosu diyo
@Table(name = "users")
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

    @Column(name = "email")
    private String email;
}

/**
 * CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    name VARCHAR,
    username VARCHAR,
    email VARCHAR
)
 */

