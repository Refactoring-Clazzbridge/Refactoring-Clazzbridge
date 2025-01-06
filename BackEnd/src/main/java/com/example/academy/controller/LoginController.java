package com.example.academy.controller;

import com.example.academy.dto.auth.LoginRequestDTO;
import com.example.academy.dto.auth.LoginResponseDTO;
import com.example.academy.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

  private final LoginService loginService;

  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping
  @Operation(summary = "사용자 로그인")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO req) {

    return loginService.login(req.getMemberId(), req.getPassword());
  }

}
