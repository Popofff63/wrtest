package ru.pdsonline.wrtest.service;

import ru.pdsonline.wrtest.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {
    void save(SubscriptionDto dto);
    void delete(String id);
    List<SubscriptionDto> findTopThree();
}
