package com.example.academy.jwt;

import com.example.academy.domain.Member;
import com.example.academy.domain.MemberType;
import com.example.academy.dto.member.CustomUserDetails;
import com.example.academy.repository.mysql.MemberTypeRepositoy;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;

  private MemberTypeRepositoy memberTypeRepositoy;

  // JwtUtil 클래스를 사용하기 위해 필드로 주입받는 생성자


  public JwtFilter(JwtUtil jwtUtil, MemberTypeRepositoy memberTypeRepositoy) {
    this.jwtUtil = jwtUtil;
    this.memberTypeRepositoy = memberTypeRepositoy;
  }

  // doFilterInternal: HTTP 요청이 올 때마다 실행되는 필터 메소드
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // 1. 요청 헤더에서 Authorization 값을 가져옴
    String authorization = request.getHeader("Authorization");

    // 2. Authorization 헤더가 없거나 Bearer 토큰으로 시작하지 않으면 필터를 통과시키고 아무 작업도 하지 않음
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      System.out.println("JWTFilter . authorization : " + authorization);
      filterChain.doFilter(request, response);

      return;  // 필터링 중단 후 요청을 다음 필터로 전달
    }

    // 3. Authorization 헤더에서 "Bearer " 이후의 토큰 값 추출
    String token = authorization.split(" ")[1];
    System.out.println("token : " + token);
    // 4. 토큰이 만료되었는지 확인

    if (jwtUtil.isExpired(token)) {
      System.out.println("token expired\n");
      filterChain.doFilter(request, response);

      return;  // 토큰이 만료되었다면 요청을 다음 필터로 전달
    }

    // 5. 토큰에서 사용자 id와 역할 추출
    Long userId = jwtUtil.getUserId(token);
    String role = jwtUtil.getRole(token);
    System.out.println("role = " + role);

    // 6. UserList 객체 생성 및 사용자 이름, 역할 설정 (임시 비밀번호로 설정)
    Member member = new Member();
    Optional<MemberType> memberTypeOptional = memberTypeRepositoy.findByType(role.toUpperCase());

    if (memberTypeOptional.isPresent()) {
      MemberType memberType = memberTypeOptional.get();
      member.setId(userId);
      member.setPassword("temppassword");  // 임시 비밀번호
      member.setMemberType(memberType);
    } else {
      // 예외 처리 또는 기본 MemberType 설정
      throw new IllegalArgumentException("MemberType not found for role: " + role);
    }
    // 7. CustomUserDetails 객체 생성, 이 객체는 Spring Security에서 사용하는 사용자 정보 객체
    CustomUserDetails customUserDetails = new CustomUserDetails(member);

    // 8. 인증 토큰 생성 (customUserDetails로 인증 객체 생성) - 비밀번호는 null로 설정
    Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null,
        customUserDetails.getAuthorities());

    // 9. SecurityContextHolder에 인증 객체 설정 (이후 SecurityContextHolder를 통해 인증 정보 사용 가능)
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // 10. 필터 체인 계속 실행 (다음 필터로 요청을 넘김)
    filterChain.doFilter(request, response);

    System.out.println("필터 종료");
  }
}
