package ru.pdsonline.wrtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pdsonline.wrtest.dto.SubscriptionDto;
import ru.pdsonline.wrtest.dto.UserDto;
import ru.pdsonline.wrtest.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto){
        UserDto userDto = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserDto dto){
        return ResponseEntity.ok(userService.update(id, dto));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // User Subscriptions
    @PostMapping(value = "/{id}/subscriptions")
    public ResponseEntity<Void> addSubscription(@PathVariable String id, @RequestBody SubscriptionDto dto){
        userService.addSubscription(id, dto);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionDto>>getUserSubscriptions(@PathVariable String id){
        return ResponseEntity.ok(userService.getUserSubscriptions(id));
    }
    @DeleteMapping(value = "/{userid}/subscriptions/{subid}")
    public ResponseEntity<Void> delSubscription(@PathVariable String userid, @PathVariable String subid){
        userService.delSubscription(userid, subid);
        return ResponseEntity.noContent().build();
    }
}
