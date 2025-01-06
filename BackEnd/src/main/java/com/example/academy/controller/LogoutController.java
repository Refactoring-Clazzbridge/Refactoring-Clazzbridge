package com.example.academy.controller;

import com.example.academy.service.LogoutService;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logout")
public class LogoutController {

  private final LogoutService logoutService;

  public LogoutController(LogoutService logoutService) {
    this.logoutService = logoutService;
  }

  @PostMapping
  public ResponseEntity<String> logout(@RequestBody Map<String, String> request) {
    String memberIdStr = request.get("memberId");
    Long memberId = Long.parseLong(memberIdStr); // 문자열을 Long으로 변환
    System.out.println("로그아웃 요청을 받았습니다: " + memberId);
    logoutService.logout(memberId);
    return ResponseEntity.ok("로그아웃 완료되었습니다.");
  }
}

