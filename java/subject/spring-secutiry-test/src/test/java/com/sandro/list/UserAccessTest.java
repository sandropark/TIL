package com.sandro.list;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandro.list.config.SecurityConfig;
import com.sandro.list.domain.AccountType;
import com.sandro.list.domain.User;
import com.sandro.list.service.UserService;
import com.sandro.list.web.SecurityMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest
public class UserAccessTest {

    @Autowired MockMvc mvc;
    @MockBean UserService userService;
    @Autowired ObjectMapper mapper;
    UserDetails guest() { return User.of("0", "게스트", "1"); }
    UserDetails myUser() { return User.of("1", "김유저", "1", AccountType.ROLE_USER); }
    UserDetails admin() { return User.of("2", "김어드", "1", AccountType.ROLE_ADMIN); }

    @DisplayName("user 로 user 페이지를 접근할 수 있다.")
    @Test
    void userAccessUserPage() throws Exception {
        String response = mvc.perform(get("/user")
                        .with(user(myUser())))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        SecurityMessage message = mapper.readValue(response, SecurityMessage.class);
        assertThat(message.getMessage()).isEqualTo("user page");
    }

    @DisplayName("user 로 admin 페이지를 접근할 수 없다.")
    @Test
    void userCantAccessAdminPage() throws Exception {
        mvc.perform(get("/admin")
                        .with(user(myUser())))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @DisplayName("amin 은 모든 페이지를 접근할 수 있다.")
    @Test
    void adminAccessEveryPages() throws Exception {
        String response = mvc.perform(get("/user")
                        .with(user(admin())))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        SecurityMessage message = mapper.readValue(response, SecurityMessage.class);
        assertThat(message.getMessage()).isEqualTo("user page");

        response = mvc.perform(get("/admin")
                        .with(user(admin())))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        message = mapper.readValue(response, SecurityMessage.class);
        assertThat(message.getMessage()).isEqualTo("admin page");
    }

    @DisplayName("login 페이지는 아무나 접근할 수 있다.")
    @Test
    void loginPage() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("root 페이지는 아무나 접근할 수 없다. 로그인 화면으로 redirect 된다.")
    @Test
    void rootPage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        mvc.perform(get("/").with(user(guest()))).andExpect(status().isOk());
        mvc.perform(get("/").with(user(myUser()))).andExpect(status().isOk());
        mvc.perform(get("/").with(user(admin()))).andExpect(status().isOk());
    }

}
