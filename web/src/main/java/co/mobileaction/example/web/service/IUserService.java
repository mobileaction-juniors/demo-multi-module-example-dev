package co.mobileaction.example.web.service;

import co.mobileaction.example.web.model.Post;
import co.mobileaction.example.web.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    void saveUser(User user);

}

//dto sadece veri taşıyor service iş yapıyor 

/** 
 * Worker → UserDto gönderir (sadece veri)
    ↓
Web alır → UserService.saveUser() çağırır (iş mantığı)
    ↓
UserRepository → DB'ye kaydeder

 */

/**
 * Web     → "userId=1'i çek" mesajını kuyruğa atar
Worker  → kuyruğu dinler, jsonplaceholder'a gider, veriyi çeker, sonucu kuyruğa atar
Web     → sonucu alır, DB'ye kaydeder
 */

/**
 * web/     → Spring web uygulaması, controller, service, repository
worker/  → ayrı uygulama, sadece veri çeker
common/  → ikisi de kullanır, DTO'lar burada

 */
