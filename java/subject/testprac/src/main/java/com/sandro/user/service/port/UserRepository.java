package com.sandro.user.service.port;

import com.sandro.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findByName(String name);
}
