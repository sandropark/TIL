package com.sandro.list.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static com.sandro.list.domain.AccountType.ROLE_ADMIN;
import static com.sandro.list.domain.AccountType.ROLE_USER;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .formLogin(config ->
                        config
                                .loginPage("/login")
                                .failureForwardUrl("/login?error=true")
                )
                .authorizeHttpRequests(auth ->
                        auth
                                .mvcMatchers(HttpMethod.GET, "/user").hasAnyAuthority(ROLE_ADMIN.name(), ROLE_USER.name())
                                .mvcMatchers(HttpMethod.GET, "/admin").hasAuthority(ROLE_ADMIN.name())
                                .mvcMatchers(HttpMethod.GET, "/users").hasAuthority(ROLE_ADMIN.name())
                                .mvcMatchers(HttpMethod.PATCH, "/users/*/type").hasAuthority(ROLE_ADMIN.name())
                                .mvcMatchers(HttpMethod.DELETE, "/users/*/type").hasAuthority(ROLE_ADMIN.name())

                                .mvcMatchers(HttpMethod.POST, "/articles").hasAnyAuthority(ROLE_ADMIN.name(), ROLE_USER.name())

                                .mvcMatchers("/login").permitAll()
                                .mvcMatchers("/").authenticated()
                )
                .build();
    }

//    @Bean
//    public UserDetailsService UserDetailsService(UserRepository userRepository) {
//        return username -> userRepository
//                .findByAccountId(username)
//                .orElseThrow(() -> new UsernameNotFoundException("유저가 존재하지 않습니다. username = " + username));
//    }

}
