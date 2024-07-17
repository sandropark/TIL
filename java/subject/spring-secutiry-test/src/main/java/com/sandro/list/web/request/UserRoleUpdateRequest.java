package com.sandro.list.web.request;

import com.sandro.list.domain.AccountType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRoleUpdateRequest {
    private String accountId;
    private AccountType accountType;

    protected UserRoleUpdateRequest() {}
}
