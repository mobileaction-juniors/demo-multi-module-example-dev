package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.QueueUserRequestDto;

public interface IUserRequestHandlerService
{
    void executeMessage(QueueUserRequestDto request);
}
