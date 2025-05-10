package ru.pdsonline.wrtest.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pdsonline.wrtest.component.SubscriptionMapper;
import ru.pdsonline.wrtest.component.UserMapper;
import ru.pdsonline.wrtest.dto.SubscriptionDto;
import ru.pdsonline.wrtest.dto.UserDto;
import ru.pdsonline.wrtest.entity.SubscriptionEntity;
import ru.pdsonline.wrtest.entity.UserEntity;
import ru.pdsonline.wrtest.exception.NotFoundException;
import ru.pdsonline.wrtest.repository.SubscriptionRepository;
import ru.pdsonline.wrtest.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    UserMapper userMapper;
    SubscriptionMapper subscriptionMapper;
    UserRepository repository;
    SubscriptionRepository subscriptionRepository;
    @Override
    public UserDto save(UserDto dto) {
        UserEntity entity = repository.save(userMapper.toEntity(dto));
        log.info("Add user - {}", entity);
        return userMapper.toDto(entity);
    }

    @Override
    public UserDto getById(String id) {
        Optional<UserEntity> entityOptional = repository.findById(UUID.fromString(id));
        return entityOptional.map(userMapper::toDto).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public UserDto update(String id, UserDto dto) {
        var entity = userMapper.toEntity(dto);
        entity.setId(UUID.fromString(id));
        return userMapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(UUID.fromString(id));
        log.info("User({}) was deleted", id);
    }

    @Override
    public List<SubscriptionDto> getUserSubscriptions(String id) {
        List<SubscriptionDto> subscriptions = new ArrayList<>();
        repository.findById(UUID.fromString(id)).ifPresent(u -> u.getSubscriptions().stream().map(subscriptionMapper::toDto).forEach(subscriptions::add));
        return subscriptions;
    }

    @Override
    public void addSubscription(String userId, SubscriptionDto dto) {
        SubscriptionEntity subscription = subscriptionRepository.findById(dto.id()).orElseThrow(() -> new NotFoundException("Subscription not found"));
        UserEntity user = repository.findById(UUID.fromString(userId)).orElseThrow(() -> new NotFoundException("User not found"));
        user.addSubscription(subscription);
        repository.save(user);
        log.info("Subscription({}) was added for user({})", subscription.getId(), user.getId());
    }

    @Override
    public void delSubscription(String userId, String subId) {
        SubscriptionEntity subscription = subscriptionRepository.findById(UUID.fromString(subId)).orElseThrow(() -> new NotFoundException("Subscription not found"));
        UserEntity user = repository.findById(UUID.fromString(userId)).orElseThrow(() -> new NotFoundException("User not found"));
        user.removeSubscription(subscription);
        repository.save(user);
        log.info("Subscription({}) was delete for user({})", subId, userId);
    }
}
