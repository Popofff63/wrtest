package ru.pdsonline.wrtest.dto;

import java.util.UUID;

public record UserDto(UUID id, String userName, String name, String lastName) {}
