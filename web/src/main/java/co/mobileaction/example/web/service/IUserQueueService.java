package co.mobileaction.example.web.service;

import java.util.List;

/**
 * @author Mehmet Akif Sahin
 * @date 03.07.2024
 * @time 09:13
 */
public interface IUserQueueService
{
    void sendUserRequests(List<Long> userIds);
}
