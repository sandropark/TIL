package com.sandro.list.web;

import com.sandro.list.config.SecurityConfig;
import com.sandro.list.domain.AccountType;
import com.sandro.list.domain.User;
import com.sandro.list.service.UserService;
import com.sandro.list.web.response.UserListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired MockMvc mvc;
    @MockBean UserService userService;
    User noRole() { return User.of("0", "김익명", "1"); }
    User myUser() { return User.of("1", "김유저","1", AccountType.ROLE_USER); }
    User admin() { return User.of("2", "박어드", "1", AccountType.ROLE_ADMIN); }

    @DisplayName("리스트 접근시 권한이 있어야 한다.")
    @Test
    void userList() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/users"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @DisplayName(" admin은 user 리스트에 접근할 수 있다.")
    @Test
    void userList2() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/users")
                        .with(user(admin())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName(" user 리스트는 페이징 되어 내려온다.")
    @Test
    void userList3() throws Exception {
        // Given
        when(userService.findUsers(any(Pageable.class))).thenReturn(getUserListResponse());

        // When
        mvc.perform(get("/users")
                        .with(user(admin())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value("1"))
                .andExpect(jsonPath("$.totalElements").value("2"))
                .andExpect(jsonPath("$.number").value("0"))
                .andDo(print());

        // Then
        then(userService).should().findUsers(any(Pageable.class));
    }

    private Page<UserListResponse> getUserListResponse() {
        return new PageImpl<>(List.of(
                new UserListResponse("user", AccountType.ROLE_USER),
                new UserListResponse("admin", AccountType.ROLE_ADMIN)));
    }

    @DisplayName("admin 은 사용자에게 role 을 부여할 수 있다.")
    @Test
    void setRole() throws Exception {
        // Given
        User user = myUser();
        AccountType roleUser = AccountType.ROLE_USER;
        String accountId = user.getAccountId();
        given(userService.updateRole(accountId, roleUser)).willReturn(user);

        // When
        mvc.perform(patch(String.format("/users/%s/type?accountType=%s", accountId, AccountType.ROLE_USER.name()))
                        .with(user(admin())))
                    .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(accountId))
                .andExpect(jsonPath("$.accountType").value(AccountType.ROLE_USER.name()))
                .andDo(print());

        // Then
        then(userService).should().updateRole(accountId, roleUser);
    }

    @DisplayName("admin 은 사용자의 role 을 삭제할 수 있다.")
    @Test
    void removeRole() throws Exception {
        // Given
        User user = noRole();
        String accountId = user.getAccountId();
        given(userService.deleteRole(accountId)).willReturn(user);

        // When
        mvc.perform(delete(String.format("/users/%s/type", accountId))
                        .with(user(admin())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(accountId))
                .andExpect(jsonPath("$.accountType").isEmpty())
                .andDo(print());

        // Then
        then(userService).should().deleteRole(accountId);
    }

}