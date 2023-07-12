package co.mobileaction.example.web.service;

import java.util.List;

/**
 * @author sa
 * @date 17.05.2021
 * @time 17:57
 */
public interface IPostQueueService
{
    void sendPostRequestForAllItems();

    boolean sendAllUniqueUserIds(List<Long> userIds);
}
