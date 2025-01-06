package com.example.academy.service;

import com.example.academy.domain.Member;
import com.example.academy.dto.member.CustomUserDetails;
import com.example.academy.repository.mysql.MemberRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public CustomUserDetailService(MemberRepository memberRepository,
      PasswordEncoder passwordEncoder) {
    this.memberRepository = memberRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Member> userListOptional = memberRepository.findByName(username);

    // Optional에서 값 꺼내기
    Member member = userListOptional.orElseThrow(() ->
        new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

    return new CustomUserDetails(member);
  }
}
