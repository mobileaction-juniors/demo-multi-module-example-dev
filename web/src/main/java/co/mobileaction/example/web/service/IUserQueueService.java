package co.mobileaction.example.web.service;

import java.util.List;

/**
 * @author Doga Elif Konuk
 * @date 17.07.2024
 * @time 15:15
 */
public interface IUserQueueService
{
    void sendRequestForDistinctUsers(List<Long> userIds);
}
