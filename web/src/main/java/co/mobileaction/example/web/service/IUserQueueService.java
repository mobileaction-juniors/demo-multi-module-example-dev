package co.mobileaction.example.web.service;

import java.util.Set;

public interface IUserQueueService {
    void sendUserRequests(Set<Long> userIds);
}
