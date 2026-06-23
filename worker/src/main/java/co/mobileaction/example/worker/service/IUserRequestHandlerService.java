package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;

/**
 * @author berkturk
 * @date 22.06.2026
 * @time 17:32
 */
public interface IUserRequestHandlerService
{
    void executeMessage(UserQueueRequestDto request);
}
