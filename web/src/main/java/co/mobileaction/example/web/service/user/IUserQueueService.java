package co.mobileaction.example.web.service.user;

import java.util.List;

public interface IUserQueueService
{
    void sendUserRequestForAllItems(List<Long> userIds);
}
