package co.mobileaction.example.worker.service;

/**
 * @author sa
 * @date 17.05.2021
 * @time 16:56
 */
public interface IPostRequestHandlerService
{
    void executeMessage(QueueRequestDto request);
}
