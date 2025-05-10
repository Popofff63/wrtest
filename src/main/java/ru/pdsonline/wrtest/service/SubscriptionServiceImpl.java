package ru.pdsonline.wrtest.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.pdsonline.wrtest.component.SubscriptionMapper;
import ru.pdsonline.wrtest.dto.SubscriptionDto;
import ru.pdsonline.wrtest.repository.SubscriptionRepository;

import java.util.List;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{
    SubscriptionRepository repository;
    SubscriptionMapper mapper;
    @Override
    public void save(SubscriptionDto dto) {
        repository.save(mapper.toEntity(dto));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<SubscriptionDto> findTopThree() {
         return repository.findFirst3ByOrderByRatingDesc().stream().map(mapper::toDto).toList();
    }
}
