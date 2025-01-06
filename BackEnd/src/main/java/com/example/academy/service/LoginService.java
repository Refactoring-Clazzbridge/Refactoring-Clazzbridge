package com.example.academy.service;


import com.example.academy.domain.Member;
import com.example.academy.dto.auth.LoginResponseDTO;
import com.example.academy.jwt.JwtUtil;
import com.example.academy.mapper.member.MemberResponseMapper;
import com.example.academy.repository.mysql.MemberRepository;
import java.util.Optional;
import javax.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    private static final long ACCESS_TOKEN_EXPIRATION = 1000L * 60 * 15; // 15분
    private static final long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 10080; // 150분


    @Autowired
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public LoginService(MemberRepository memberRepository,
        BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<LoginResponseDTO> login(String memberId, String password) {
        // 사용자 이름으로 사용자 정보를 검색합니다.
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        // 사용자가 존재하는 경우
        if (member.isPresent()) {
            Long userId = member.get().getId(); // 이 부분을 isPresent() 확인 후에 위치시킵니다.
            System.out.println("User found: " + member.get().getMemberId()); // 디버깅을 위한 로그

            // 비밀번호가 일치하는지 확인합니다.
            if (passwordEncoder.matches(password, member.get().getPassword())) {
                // 비밀번호가 일치하면 JWT 토큰을 생성합니다. - 72초
                String accessToken = jwtUtil.createJWT(userId,
                    member.get().getMemberType().getType(),
                    ACCESS_TOKEN_EXPIRATION);
                System.out.println("accessToken = " + accessToken);

                // 인증된 사용자에 대한 Refresh Tok en 생성 - 7200000 - 7200초
                String refreshToken = jwtUtil.createRefreshJWT(userId,
                    member.get().getMemberType().getType(),
                    REFRESH_TOKEN_EXPIRATION);
                Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
                // 클라이언트 JavaScript에서 접근 불가
                refreshTokenCookie.setSecure(false);
                // HTTPS에서만 전송 = true(HTTPS 환경에서 권장)
                refreshTokenCookie.setPath("/");       // 쿠키가 유효한 경로
                //refreshTokenCookie.setMaxAge(2);  // 2분 유효
                // 쿠키를 응답에 추가
                System.out.println("refreshTokenCookie = " + refreshTokenCookie);

                // LoginResponseDTO 객체를 생성하고 반환합니다.
                LoginResponseDTO response = new LoginResponseDTO(accessToken, refreshTokenCookie,
                    MemberResponseMapper.toDTO(member.get()));
                return ResponseEntity.ok(response);
            } else {
                System.out.println("Password does not match"); // 비밀번호 불일치 로그
            }
        } else {
            System.out.println("Member not found"); // 사용자 미발견 로그
        }
        // 로그인 실패 시 빈 Optional을 반환합니다.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 사용자 미발견 시 404 상태 코드 반환
    }

    public ResponseEntity<LoginResponseDTO> updateUserInfo(String memberId, String password) {
        // 사용자 이름으로 사용자 정보를 검색합니다.
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        // 사용자가 존재하는 경우
        if (member.isPresent()) {
            Long userId = member.get().getId(); // 이 부분을 isPresent() 확인 후에 위치시킵니다.
            System.out.println("User found: " + member.get().getMemberId()); // 디버깅을 위한 로그

            // 비밀번호가 일치하는지 확인합니다.
            if (passwordEncoder.matches(password, member.get().getPassword())) {

                LoginResponseDTO response = new LoginResponseDTO(MemberResponseMapper.toDTO(member.get()));
                return ResponseEntity.ok(response);
            } else {
                System.out.println("Password does not match"); // 비밀번호 불일치 로그
            }
        } else {
            System.out.println("Member not found"); // 사용자 미발견 로그
        }
        // 로그인 실패 시 빈 Optional을 반환합니다.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 사용자 미발견 시 404 상태 코드 반환
    }
}
