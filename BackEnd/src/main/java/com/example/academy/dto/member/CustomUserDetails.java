package com.example.academy.dto.member;

import com.example.academy.domain.Member;
import com.example.academy.enums.MemberRole;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private final Member user;

    public CustomUserDetails(Member user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
            new SimpleGrantedAuthority(user.getMemberType().getType()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName(); // UserDetails의 요구사항으로 userName을 반환합니다.
    }

    public Long getUserId() {
        return user.getId(); //  id를 반환합니다.
    }

    // 사용자 타입 체크용
    public MemberRole getUserType() {
        return MemberRole.valueOf(user.getMemberType().getType());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
