package co.mobileaction.example.web.service;

import java.util.List;

public interface IUserQueueService
{
    void sendRequestForDistinctUsers(List<Long> userIds);
}
