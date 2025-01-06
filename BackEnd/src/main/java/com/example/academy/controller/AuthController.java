package com.example.academy.controller;

import com.example.academy.dto.auth.RefreshTokenDTO;
import com.example.academy.jwt.AccessTokenResponse;
import com.example.academy.jwt.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth") // 이 컨트롤러의 모든 요청 URL은 "/auth"로 시작합니다.
public class AuthController {

  @Autowired
  private JwtUtil jwtUtil; // JwtUtil을 자동으로 주입받습니다.

  @PostMapping("/refresh")
  @Operation(summary = "새로고침")
  public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDTO refreshToken) {

    // refreshToken이 null이거나 유효하지 않은 경우
    if (refreshToken == null || !jwtUtil.validateToken(refreshToken.getValue())) {
      // 유효하지 않은 refresh token에 대한 응답을 반환합니다.
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
    }

    try {
      // 유효한 refreshToken을 사용하여 새로운 access token을 생성합니다.
      String newAccessToken = jwtUtil.refreshAccessToken(refreshToken.getValue());
      // 새로운 access token을 AccessTokenResponse 객체에 담아 응답으로 반환합니다.
      return ResponseEntity.ok(new AccessTokenResponse(newAccessToken));
    } catch (Exception e) {
      // access token 생성 중 예외가 발생하면 오류 응답을 반환합니다.
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body("Error generating new access token");
    }
  }
  }
