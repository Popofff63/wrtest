package ru.pdsonline.wrtest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name="username", unique = true, nullable = false)
    private String userName;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_subscription",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "subscription_id", referencedColumnName = "id"))
    private Set<SubscriptionEntity> subscriptions;
    public void addSubscription(SubscriptionEntity subscription){
        subscriptions.add(subscription);
        subscription.getUsers().add(this);
    }
    public void removeSubscription(SubscriptionEntity subscription){
        subscriptions.remove(subscription);
        subscription.getUsers().remove(this);
    }
}
