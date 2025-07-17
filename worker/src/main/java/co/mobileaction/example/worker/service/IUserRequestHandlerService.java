package co.mobileaction.example.worker.service;
import co.mobileaction.example.common.dto.UserCrawlRequestDto;

public interface IUserRequestHandlerService {
    void executeMessage(UserCrawlRequestDto request);
}