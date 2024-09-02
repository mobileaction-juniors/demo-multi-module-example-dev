package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.QueueRequestUserDto;

public interface IUserRequestHandlerService {

    void executeMessage(QueueRequestUserDto request);
}
