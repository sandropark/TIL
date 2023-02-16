package com.sandro.list.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "users")
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String accountId;
    private String nickname;
    private String password;
    private boolean quit;
    @Enumerated(EnumType.STRING) private AccountType accountType;

    @CreatedDate @Column(updatable = false) private LocalDateTime createdAt;
    @LastModifiedDate private LocalDateTime modifiedAt;

    private User(String accountId, String nickname, String password, AccountType accountType) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.password = password;
        this.accountType = accountType;
    }

    public static User of(String accountId, String nickname, String password, AccountType accountType) {
        return new User(accountId, nickname, password, accountType);
    }

    public static User of(String accountId, String nickname, String password) {
        return new User(accountId, nickname, password, AccountType.ROLE_GUEST);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(accountType.name()));
    }
    @Override public String getUsername() { return accountId; }
    @Override public boolean isAccountNonExpired() { return !quit; }
    @Override public boolean isAccountNonLocked() { return !quit; }
    @Override public boolean isCredentialsNonExpired() { return !quit; }
    @Override public boolean isEnabled() { return !quit; }

    public void updateAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void deleteAccountType() {
        this.accountType = null;
    }
}
