package co.mobileaction.example.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "cities")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "lat")
    private float lat;

    @Column (name = "lon")
    private float lon;
}
