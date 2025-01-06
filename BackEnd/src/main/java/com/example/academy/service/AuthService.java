package com.example.academy.service;

import com.example.academy.dto.member.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // 이 메소드 사용하면 현재 로그인된 사용자 정보를 가져올 수 있음
    public CustomUserDetails getAuthenticatedUser() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
    }

//    ex :
//    CustomUserDetails user = authService.getAuthenticatedUser();
//
//    Member member = memberRepository.findById(user.getUserId()).get();
}
