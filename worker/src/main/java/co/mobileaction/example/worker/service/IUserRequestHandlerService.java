package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.QueueRequestDto;
import co.mobileaction.example.common.dto.UserRequestDto;

/**
 * @author elif
 * @date 05.07.2023
 * @time 17.00
 */
public interface IUserRequestHandlerService
{
    void executeMessage(UserRequestDto request);
}
