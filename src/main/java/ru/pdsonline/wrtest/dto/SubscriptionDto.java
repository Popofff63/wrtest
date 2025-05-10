package ru.pdsonline.wrtest.dto;

import java.util.UUID;

public record SubscriptionDto(UUID id, String name, Double rating) {}
