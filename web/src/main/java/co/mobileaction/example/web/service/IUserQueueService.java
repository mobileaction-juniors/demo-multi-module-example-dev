package co.mobileaction.example.web.service;

import java.util.Set;

/**
 * @author Doga Elif Konuk
 * @date 17.07.2024
 * @time 15:15
 */
public interface IUserQueueService
{
    void sendRequestForDistinctUsers(Set<Long> userIds);
}
