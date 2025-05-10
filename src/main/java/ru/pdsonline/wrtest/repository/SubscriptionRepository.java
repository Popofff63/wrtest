package ru.pdsonline.wrtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pdsonline.wrtest.entity.SubscriptionEntity;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {

    List<SubscriptionEntity> findFirst3ByOrderByRatingDesc();
}
