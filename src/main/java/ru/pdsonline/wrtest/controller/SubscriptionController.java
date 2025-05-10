package ru.pdsonline.wrtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pdsonline.wrtest.dto.SubscriptionDto;
import ru.pdsonline.wrtest.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    @GetMapping(value = "/top")
    public ResponseEntity<List<SubscriptionDto>> getTop(){
        return ResponseEntity.ok(subscriptionService.findTopThree());
    }

}
