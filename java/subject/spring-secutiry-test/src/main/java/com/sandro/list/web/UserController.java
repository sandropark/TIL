package com.sandro.list.web;

import com.sandro.list.domain.AccountType;
import com.sandro.list.service.UserService;
import com.sandro.list.web.request.UserSaveRequest;
import com.sandro.list.web.response.UserListResponse;
import com.sandro.list.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    // save
    @PostMapping
    public UserResponse saveUser(@RequestBody UserSaveRequest request) {
        return UserResponse.of(userService.save(request.toEntity()));
    }

    // list : page
    @GetMapping
    public Page<UserListResponse> list(@PageableDefault Pageable pageable) {
        return userService.findUsers(pageable);
    }

    // addRole
    @PatchMapping("/{accountId}/type")
    public UserResponse updateRole(@PathVariable String accountId, @RequestParam AccountType accountType) {
        System.out.println("accountType = " + accountType);
        return UserResponse.of(userService.updateRole(accountId, accountType));
    }

    // removeRole
    @DeleteMapping("/{accountId}/type")
    public UserResponse removeRole(@PathVariable String accountId) {
        return UserResponse.of(userService.deleteRole(accountId));
    }

}
