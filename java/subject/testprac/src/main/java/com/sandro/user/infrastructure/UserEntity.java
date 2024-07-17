package com.sandro.user.infrastructure;

import com.sandro.user.domain.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public static UserEntity from(User user) {
        return new UserEntity(user.getId(), user.getName());
    }

    public User toModel() {
        return User.builder().id(id).name(name).build();
    }
}
