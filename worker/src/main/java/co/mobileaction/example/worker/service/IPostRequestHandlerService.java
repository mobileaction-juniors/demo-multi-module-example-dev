package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.QueueRequestDto;

public interface IPostRequestHandlerService
{
    void executeMessage(QueueRequestDto request);
}
