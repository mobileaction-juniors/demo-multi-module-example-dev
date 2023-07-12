package co.mobileaction.example.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yavuzselimsaribas
 * @date 11.07.2023
 */

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;


    @Column(name = "email")
    private String email;


    @Column(name = "phone")
    private String phone;



}
