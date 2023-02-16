package com.sandro.list.service;

import com.sandro.list.domain.AccountType;
import com.sandro.list.domain.User;
import com.sandro.list.repository.UserRepository;
import com.sandro.list.web.response.UserListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public Page<UserListResponse> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserListResponse::from);
    }

    @Transactional
    public User updateRole(String accountId, AccountType accountType) {
        User user = findUserByAccountId(accountId);
        user.updateAccountType(accountType);
        return user;
    }

    @Transactional
    public User deleteRole(String accountId) {
        User user = findUserByAccountId(accountId);
        user.deleteAccountType();
        return user;
    }

    private User findUserByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UsernameNotFoundException("유저가 존재하지 않습니다. accountId = " + accountId));
    }

}
