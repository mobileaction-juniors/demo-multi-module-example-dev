package co.mobileaction.example.web.model;

import co.mobileaction.example.common.dto.UserDto;
import co.mobileaction.example.web.model.dto.CreateUserCmd;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    public static User fromDto(UserDto userDto) {
        return User.builder()
                .id(userDto.id())
                .name(userDto.name())
                .username(userDto.username())
                .email(userDto.email())
                .build();
    }

    public static User fromDto(CreateUserCmd createUserCmd) {
        return User.builder()
                .name(createUserCmd.name())
                .username(createUserCmd.username())
                .email(createUserCmd.email())
                .build();
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getUsername(), user.getEmail());
    }
}
