package co.mobileaction.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

//web workera su userId yi çek diye mesaj gönderiyo
public class UserQueueRequestDto {
    private Long userId;
}

//webin workera gönderdiği mesaj yani userID yi çekmesini istiyo

