package co.mobileaction.example.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
//bu userın istenmeyen (adrs vs) kısımlarını ignorelamak için
public class UserDto
{
    private Long id;
    private String name;
    private String username;
    private String email;
}

//workerın webe gönderdiği sonuç--> id name username email gönderiyı
