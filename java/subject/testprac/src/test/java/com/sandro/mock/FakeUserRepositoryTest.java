package com.sandro.mock;

import com.sandro.user.domain.User;
import com.sandro.user.service.port.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class FakeUserRepositoryTest {

    @DisplayName("id가 없는 객체를 저장하면 id가 생긴다.")
    @Test
    void save() throws Exception {
        // Given
        UserRepository fakeUserRepository = new FakeUserRepository();
        User user = new User("Sandro");
        assertThat(user.getId()).isNull();

        // When
        User saved = fakeUserRepository.save(user);

        // Then
        assertThat(saved.getId()).isNotNull();
    }

    @Nested
    class FindByName {
        @DisplayName("이름이 같은 user 객체를 반환한다.")
        @Test
        void name() throws Exception {
            // Given
            UserRepository fakeUserRepository = new FakeUserRepository();
            User user = new User("Coco");
            User user2 = new User("Sandro");
            fakeUserRepository.save(user);
            fakeUserRepository.save(user2);

            // When
            Optional<User> foundUser = fakeUserRepository.findByName("Sandro");

            // Then
            assertThat(foundUser).isPresent();
            assertThat(foundUser.get().getName()).isEqualTo("Sandro");
        }

        @DisplayName("해당 이름의 사용자가 없다면 Optional.empty()를 반환한다.")
        @Test
        void noName() throws Exception {
            // Given
            UserRepository fakeUserRepository = new FakeUserRepository();
            User user = new User("Coco");
            fakeUserRepository.save(user);

            // When
            Optional<User> foundUser = fakeUserRepository.findByName("Sandro");

            // Then
            assertThat(foundUser).isEmpty();
        }
    }
}