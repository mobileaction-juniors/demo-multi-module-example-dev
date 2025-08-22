package co.mobileaction.example.web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter
public class User
{
    @Id
    private Long id;
    private String name;
    private String username;
}
