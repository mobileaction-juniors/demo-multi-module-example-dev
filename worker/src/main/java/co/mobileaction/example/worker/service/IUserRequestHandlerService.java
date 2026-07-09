package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;

/**
 * @author Yunus Gunay
 */
public interface IUserRequestHandlerService
{
    void executeMessage(UserQueueRequestDto request);
}
