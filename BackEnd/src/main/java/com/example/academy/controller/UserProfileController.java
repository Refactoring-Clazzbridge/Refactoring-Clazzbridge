package com.example.academy.controller;


import com.example.academy.domain.Member;
import com.example.academy.dto.auth.LoginRequestDTO;
import com.example.academy.dto.auth.LoginResponseDTO;
import com.example.academy.dto.member.MemberProfileUpdateDTO;
import com.example.academy.service.LoginService;
import com.example.academy.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userlist")
public class UserProfileController {

  @Autowired
  private UserProfileService userProfileService;
  @Autowired
  private LoginService loginService;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;


  @GetMapping("/{userId}")
  @Operation(summary = "유저 조회")
  public ResponseEntity<Member> getUserProfileById(@PathVariable("userId") Long id) {
    Optional<Member> userProfile = userProfileService.getUserProfileById(id);

    return userProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  @Operation(summary = "비밀번호 체크")
  public ResponseEntity<Map<String, Boolean>> checkPassword(
      @RequestBody Map<String, String> request) {
    String inputPassword = request.get("password"); // 입력된 비밀번호
    System.out.println("inputPassword = " + inputPassword);
    String beforeId = request.get("userId");
    System.out.println("beforeId = " + beforeId);
    Long id = Long.valueOf(beforeId);
    System.out.println("id = " + id);
    // User ID는 예시로 1L을 사용 (실제 로그인한 사용자의 ID를 사용해야 함)
    boolean passwordMatch = userProfileService.checkPassword(id, inputPassword);

    System.out.println("passwordMatch = " + passwordMatch);
    Map<String, Boolean> response = new HashMap<>();
    response.put("success", passwordMatch); // 비밀번호가 일치하면 true, 아니면 false
    return ResponseEntity.ok(response); // 결과를 반환
  }

  @PutMapping
  @Operation(summary = "유저 정보 변경")
  public ResponseEntity<String> updateUserProfile(@RequestBody MemberProfileUpdateDTO updateDTO) {
    try {
      userProfileService.createOrUpdateUserProfile(updateDTO);
      return ResponseEntity.ok().body("회원 정보 업데이트 성공");
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity.badRequest().body("회원 정보 업데이트 실패: " + e.getMessage());
    } catch (RuntimeException e) {
      // 예외 발생 시, 에러 메시지와 함께 400 Bad Request 응답 반환
      return ResponseEntity.badRequest().body("회원 정보 업데이트 실패: " + e.getMessage());
    }
  }

  @PostMapping("update")
  @Operation(summary = "사용자 로그인")
  public ResponseEntity<LoginResponseDTO> update(@RequestBody LoginRequestDTO loginRequestDTO) {

    return loginService.updateUserInfo(loginRequestDTO.getMemberId(), loginRequestDTO.getPassword());
  }
}