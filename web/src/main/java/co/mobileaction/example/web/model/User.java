package co.mobileaction.example.web.model;

import co.mobileaction.example.common.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "user")
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

    public static User from(co.mobileaction.example.common.dto.UserCrawlRequestDto dto) {
        return User.builder()
                .id(dto.getId())
                .build();
    }

    public static User from(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();
    }
}