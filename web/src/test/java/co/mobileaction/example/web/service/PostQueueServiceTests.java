package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Mehmet Akif Sahin
 * @date 08.07.2024
 * @time 11:25
 */

@ExtendWith(MockitoExtension.class)
class PostQueueServiceTests
{
    @InjectMocks
    private PostQueueService queueService;

    @Mock(name = "requestQueueTemplate")
    private AmqpTemplate requestQueueTemplate;

    @Test
    void sendPostRequestForAllItems()
    {
        queueService.sendPostRequestForAllItems();

        verify(requestQueueTemplate, times(100)).convertAndSend(any(QueueRequestDto.class));
    }
}
