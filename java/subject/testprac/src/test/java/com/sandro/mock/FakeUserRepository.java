package com.sandro.mock;

import com.sandro.user.domain.User;
import com.sandro.user.service.port.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeUserRepository implements UserRepository {

    private final List<User> store = new ArrayList<>();
    private Long autoId = 0L;

    @Override
    public User save(User user) {
        if (user.getId() == null)
            user = User.builder().id(++autoId).name(user.getName()).build();
        store.add(user);
        return user;
    }

    @Override
    public Optional<User> findByName(String name) {
        return store.stream().filter(user -> user.getName().equals(name)).findFirst();
    }
}
