package ru.pdsonline.wrtest.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import ru.pdsonline.wrtest.dto.SubscriptionDto;
import ru.pdsonline.wrtest.service.SubscriptionService;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitializingBeanImpl implements InitializingBean {
    private final SubscriptionService subscriptionService;
    private final List<String> services = new ArrayList<>();

    public InitializingBeanImpl(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
        services.add("YouTube Premium");
        services.add("VK Музыка");
        services.add("Яндекс.Плюс");
        services.add("Netflix");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(subscriptionService.findTopThree().isEmpty()){
            createDefaultSubscription();
        }
    }
    private void createDefaultSubscription(){
        services.forEach(s -> {
            var d = new SubscriptionDto(null, s, randomRating());
            subscriptionService.save(d);
        });
    }
    private Double randomRating(){
        return Math.floor(Math.random() * 5 * 100) / 100;
    }
}
