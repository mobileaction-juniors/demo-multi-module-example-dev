package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;
import co.mobileaction.example.web.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class UserQueueService implements  IUserQueueService{
    private final AmqpTemplate requestQueueUserTemplate;
    private final IUserRepository userRepo;

    @Override
    public void sendUserRequestForAllItems()
    {
        List<Long> tmp = userRepo.findDistinctUserIDs();

        for (Long x : tmp){
         requestQueueUserTemplate.convertAndSend(new UserQueueRequestDto(x));
        }

    }


}
