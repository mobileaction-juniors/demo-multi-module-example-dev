package co.mobileaction.example.web.model;

import co.mobileaction.example.common.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
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

    public static User fromDto(UserDto dto)
    {
        if (dto == null) {
            return null;
        }
        
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .username(dto.getUsername())
                .build();
    }

    public UserDto toDto()
    {
        return UserDto.builder()
                .id(this.id)
                .name(this.name)
                .username(this.username)
                .build();
    }
}