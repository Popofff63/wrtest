package ru.pdsonline.wrtest.service;

import ru.pdsonline.wrtest.dto.SubscriptionDto;
import ru.pdsonline.wrtest.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto save(UserDto dto);
    UserDto getById(String id);
    List<SubscriptionDto> getUserSubscriptions(String id);
    UserDto update(String id, UserDto dto);
    void delete(String id);
    void addSubscription(String userId, SubscriptionDto dto);
    void delSubscription(String userId, String subId);
}
