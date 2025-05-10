package ru.pdsonline.wrtest.component;

import org.springframework.stereotype.Component;
import ru.pdsonline.wrtest.dto.UserDto;
import ru.pdsonline.wrtest.entity.UserEntity;

@Component
public class UserMapper {
    public UserEntity toEntity(UserDto dto){
        var e = new UserEntity();
        e.setId(dto.id());
        e.setUserName(dto.userName());
        e.setName(dto.name());
        e.setLastName(dto.lastName());
        return e;
    }
    public UserDto toDto(UserEntity entity){
        var d = new UserDto(entity.getId(), entity.getUserName(), entity.getName(), entity.getLastName());
        return d;
    }
}
