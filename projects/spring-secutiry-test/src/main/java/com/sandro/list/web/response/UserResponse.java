package com.sandro.list.web.response;

import com.sandro.list.domain.AccountType;
import com.sandro.list.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private String accountId;
    private AccountType accountType;
    public static UserResponse of(User user) {
        return new UserResponse(user.getAccountId(), user.getAccountType());
    }
}
