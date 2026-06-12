package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;

/**
 * @author serkankorkut
 * @date 12.06.2026
 * @time 14:40
 */
public interface IUserRequestHandlerService
{
    void executeMessage(UserQueueRequestDto request);
}
