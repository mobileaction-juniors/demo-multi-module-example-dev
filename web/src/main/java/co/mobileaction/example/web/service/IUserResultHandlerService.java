package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.UserCrawlRequestDto;
import co.mobileaction.example.web.model.User;

public interface IUserResultHandlerService {
    void executeMessage(UserCrawlRequestDto userDto);
    User convertFrom(UserCrawlRequestDto userDto);
}
