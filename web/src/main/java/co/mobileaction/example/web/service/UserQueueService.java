package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.web.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class UserQueueService implements IUserQueueService{
    private final AmqpTemplate userRequestQueueTemplate;
    private final IPostRepository postRepository;   //distinct userIdleri burdan çekiyoruz

    @Override
    public void sendUserRequestForDistinctUserIds(){
         postRepository.findDistinctUserIds()
            .forEach(userId -> userRequestQueueTemplate.convertAndSend(new UserQueueRequestDto(userId)));
    }
}

/**
 * 1. Post tablosuna bak → distinct userId'leri al → [1, 2, 3]
2. Her userId için kuyruğa mesaj at → "userId=1'i çek", "userId=2'yi çek"...
Çekme işlemini o yapmıyor — sadece kuyruğa "çek" mesajı atıyor. Asıl çekmeyi Worker yapacak.
 */