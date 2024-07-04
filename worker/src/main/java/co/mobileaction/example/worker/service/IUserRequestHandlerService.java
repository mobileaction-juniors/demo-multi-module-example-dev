package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserQueueDto;

/**
 * @author sa
 * @date 17.05.2021
 * @time 16:56
 */
public interface IUserRequestHandlerService
{
    void executeMessage(UserQueueDto request);
}
