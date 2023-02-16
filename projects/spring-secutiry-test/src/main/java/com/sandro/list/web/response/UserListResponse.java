package com.sandro.list.web.response;

import com.sandro.list.domain.AccountType;
import com.sandro.list.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserListResponse {
    private String accountId;
    private AccountType accountType;

    public static UserListResponse from(User user) {
        return new UserListResponse(user.getAccountId(), user.getAccountType());
    }
}
