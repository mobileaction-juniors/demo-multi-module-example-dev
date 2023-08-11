package co.mobileaction.example.web.model;

import co.mobileaction.example.common.dto.AddressDto;
import co.mobileaction.example.common.dto.CompanyDto;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column (name = "name")
    private String name;
    @Column (name = "username")
    private String username;
    @Column (name = "email")
    private String email;
    @Column (name = "phone")
    private String phone;
    @Column (name = "website")
    private String website;
}
