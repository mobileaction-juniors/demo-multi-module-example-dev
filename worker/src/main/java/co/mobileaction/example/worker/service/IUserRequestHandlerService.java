package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.QueueUserDto;


public interface IUserRequestHandlerService {

    void executeUser(QueueUserDto request);
}
