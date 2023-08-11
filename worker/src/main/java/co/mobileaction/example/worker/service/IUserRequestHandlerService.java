package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.UserQueueRequestDto;

public interface IUserRequestHandlerService
{
    void executeMessage(UserQueueRequestDto request);
}
