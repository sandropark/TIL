package com.sandro.list.web;

import com.sandro.list.config.SecurityConfig;
import com.sandro.list.domain.AccountType;
import com.sandro.list.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    @Autowired MockMvc mvc;

    UserDetails guest() { return User.of("0", "게스트", "1"); }
    UserDetails myUser() { return User.of("1", "김유저", "1", AccountType.ROLE_USER); }
    UserDetails admin() { return User.of("2", "김어드", "1", AccountType.ROLE_ADMIN); }

    @DisplayName("글 작성은 역할이 있는 유저만 가능하다.")
    @Test
    void test1() throws Exception {
        mvc.perform(post("/articles").with(user(guest()))).andExpect(status().is4xxClientError()).andDo(print());
        mvc.perform(post("/articles").with(user(myUser()))).andExpect(status().isOk());
        mvc.perform(post("/articles").with(user(admin()))).andExpect(status().isOk());
    }

    @DisplayName("글 작성은 역할이 있는 유저만 가능하다.")
    @Test
    void test2() throws Exception {
        mvc.perform(post("/articles").header("Authentication", "")).andExpect(status().is4xxClientError()).andDo(print());
        mvc.perform(post("/articles").header("Authentication", "Realtor 1")).andExpect(status().isOk()).andDo(print());
        mvc.perform(post("/articles").header("Authentication", "Lessor 1")).andExpect(status().isOk());
        mvc.perform(post("/articles").header("Authentication", "Lessee 1")).andExpect(status().isOk());
    }

}