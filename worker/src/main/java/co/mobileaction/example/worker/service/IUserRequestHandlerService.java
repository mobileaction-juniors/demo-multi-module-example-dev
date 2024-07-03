package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;

/**
 * @author Mehmet Akif Sahin
 * @date 03.07.2024
 * @time 09:57
 */
public interface IUserRequestHandlerService
{
    void executeMessage(UserQueueRequestDto request);
}
