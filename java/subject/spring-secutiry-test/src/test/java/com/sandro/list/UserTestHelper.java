package com.sandro.list;

import com.sandro.list.domain.AccountType;
import com.sandro.list.domain.User;
import com.sandro.list.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class UserTestHelper {

    private final UserRepository userRepository;

    public User createUser(String name) {
        return userRepository.saveAndFlush(User.of(name, "san", "1"));
    }

    public User createUser(String name, AccountType accountType) {
        return userRepository.saveAndFlush(User.of(name, "san", "1", accountType));
    }

    public void assertUser(User user, String name) {
        assertThat(user.getId()).isNotNull();
        assertThat(user.getCreatedAt()).isNotNull();
        assertThat(user.getModifiedAt()).isNotNull();
        assertThat(user.isQuit()).isFalse();
        assertThat(user.getAccountId()).isEqualTo(name);
        assertThat(user.getPassword()).isEqualTo("1");
        assertThat(user.getNickname()).isEqualTo("san");
    }

    public void assertUser(User user, String name, AccountType accountType) {
        assertThat(user.getId()).isNotNull();
        assertThat(user.getCreatedAt()).isNotNull();
        assertThat(user.getModifiedAt()).isNotNull();
        assertThat(user.isQuit()).isFalse();
        assertThat(user.getAccountId()).isEqualTo(name);
        assertThat(user.getAccountType()).isEqualTo(accountType);
        assertThat(user.getPassword()).isEqualTo("1");
        assertThat(user.getNickname()).isEqualTo("san");
    }

}
