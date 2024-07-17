package com.sandro.user.infrastructure;

import com.sandro.user.domain.User;
import com.sandro.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        return jpaUserRepository.save(UserEntity.from(user)).toModel();
    }

    @Override
    public Optional<User> findByName(String name) {
        return jpaUserRepository.findByName(name).map(UserEntity::toModel);
    }
}
