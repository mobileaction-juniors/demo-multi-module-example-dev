package co.mobileaction.example.worker.service;
import co.mobileaction.example.common.dto.UserRequestDto;
public interface IUserRequestHandlerService
{
    void executeMessage(UserRequestDto request);
}
