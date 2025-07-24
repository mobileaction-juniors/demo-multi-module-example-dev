package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.QueueRequestDto;

public interface IUserRequestHandlerService
{
    void executeMessage(QueueRequestDto request);
}