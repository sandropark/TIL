package com.sandro.list;

import com.sandro.list.config.JpaAuditingConfig;
import com.sandro.list.domain.AccountType;
import com.sandro.list.domain.User;
import com.sandro.list.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(JpaAuditingConfig.class)
@DataJpaTest
public class UserAuthorityJpaTest {

    @Autowired UserRepository userRepository;
    UserTestHelper testHelper;

    @BeforeEach
    void setUp() {
        testHelper = new UserTestHelper(userRepository);
    }

    @DisplayName("1. 사용자를 생성한다.")
    @Test
    void test1() throws Exception {
        String name = "san";
        testHelper.createUser(name);
        List<User> userList = userRepository.findAll();

        assertThat(userList).hasSize(1);
        testHelper.assertUser(userList.get(0), name);
    }

    @DisplayName("2. authority 를 부여한다.")
    @Test
    void test3() throws Exception {
        String name = "san";
        AccountType accountType = AccountType.ROLE_USER;
        User user = testHelper.createUser(name, accountType);
        User savedUser = userRepository.findByAccountId(user.getAccountId()).get();

        testHelper.assertUser(savedUser, name, accountType);
    }

}
