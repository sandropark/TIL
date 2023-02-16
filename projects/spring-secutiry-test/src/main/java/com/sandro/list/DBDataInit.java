package com.sandro.list;

import com.sandro.list.domain.AccountType;
import com.sandro.list.domain.User;
import com.sandro.list.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DBDataInit {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        userRepository.save(User.of("user", "김유저", passwordEncoder.encode("1"), AccountType.ROLE_USER));
        userRepository.save(User.of("admin", "박드민", passwordEncoder.encode("1"), AccountType.ROLE_ADMIN));
    }

}

