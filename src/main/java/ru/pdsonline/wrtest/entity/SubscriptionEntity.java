package ru.pdsonline.wrtest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="subscriptions")
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "rating")
    private double rating;
    @ManyToMany(mappedBy = "subscriptions")
    private Set<UserEntity> users;
    public void addUser(UserEntity userEntity){
        users.add(userEntity);
        userEntity.getSubscriptions().add(this);
    }
    public void removeUser(UserEntity userEntity){
        users.remove(userEntity);
        userEntity.getSubscriptions().remove(this);
    }}
