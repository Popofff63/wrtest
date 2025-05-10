package ru.pdsonline.wrtest.component;

import org.springframework.stereotype.Component;
import ru.pdsonline.wrtest.dto.SubscriptionDto;
import ru.pdsonline.wrtest.entity.SubscriptionEntity;

@Component
public class SubscriptionMapper {
    public SubscriptionEntity toEntity(SubscriptionDto dto){
        var e = new SubscriptionEntity();
        e.setId(dto.id());
        e.setName(dto.name());
        e.setRating(dto.rating());
        return e;
    }
    public SubscriptionDto toDto(SubscriptionEntity entity){
        var d = new SubscriptionDto(entity.getId(), entity.getName(), entity.getRating());
        return d;
    }
}

