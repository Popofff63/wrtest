package ru.pdsonline.wrtest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pdsonline.wrtest.entity.UserEntity;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}
