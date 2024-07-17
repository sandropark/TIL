package com.sandro;

import com.sandro.mock.FakeUserRepository;
import com.sandro.user.domain.User;
import com.sandro.user.infrastructure.JpaUserRepository;
import com.sandro.user.infrastructure.UserEntity;
import com.sandro.user.service.port.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DBContractTest {

    @Autowired
    UserRepository userRepository;
    UserRepository fakeUserRepository = new FakeUserRepository();

    @DisplayName("user를 save하면 ID가 생성된다.")
    @Test
    void save() throws Exception {
        // Given
        User user = new User("Sandro");
        assertThat(user.getId()).isNull();

        // When
        User realSaved = userRepository.save(user);
        User fakeSaved = fakeUserRepository.save(user);

        // Then
        assertThat(realSaved.getId()).isNotNull();
        assertThat(fakeSaved.getId()).isNotNull();
    }

    @DisplayName("해당 이름을 가진 사용자 객체를 반환한다.")
    @Test
    void findByName() throws Exception {
        // Given
        User user = new User("Sandro");
        userRepository.save(user);
        fakeUserRepository.save(user);

        // When
        Optional<User> realSaved = userRepository.findByName("Sandro");
        Optional<User> fakeSaved = fakeUserRepository.findByName("Sandro");

        // Then
        assertThat(realSaved).isPresent();
        assertThat(realSaved.get().getName()).isEqualTo("Sandro");
        assertThat(fakeSaved).isPresent();
        assertThat(fakeSaved.get().getName()).isEqualTo("Sandro");
    }

}
