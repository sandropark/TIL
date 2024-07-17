package com.sandro.list.web.request;

import com.sandro.list.domain.AccountType;
import com.sandro.list.domain.User;
import lombok.Setter;

@Setter
public class UserSaveRequest {
    private String accountId;
    private String password;
    private String nickname;
    private AccountType accountType;

    protected UserSaveRequest() {}

    public User toEntity() {
        return User.of(accountId, nickname, password, accountType);
    }
}
