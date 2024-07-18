package co.mobileaction.example.worker.service;
import co.mobileaction.example.common.dto.UserQueueRequestDto;

/**
 * @author Doga Elif Konuk
 * @date 17.07.2024
 * @time 15:15
 */
public interface IUserRequestHandlerService {
    void executeMessage(UserQueueRequestDto userRequest);
}
