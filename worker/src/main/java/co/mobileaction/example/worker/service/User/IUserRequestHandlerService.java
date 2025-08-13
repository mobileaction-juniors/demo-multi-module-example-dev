package co.mobileaction.example.worker.service.User;

import co.mobileaction.example.common.dto.QueueRequestDto;

/**
 * @author sa
 * @date 17.05.2021
 * @time 16:56
 */
public interface IUserRequestHandlerService
{
    void executeMessage(QueueRequestDto request);
}
