package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.QueueRequestDto;


/**
 * @author sa
 * @date 17.05.2021
 * @time 16:56
 */
public interface IPostRequestHandlerService
{
    void executeMessage(QueueRequestDto request);



}
