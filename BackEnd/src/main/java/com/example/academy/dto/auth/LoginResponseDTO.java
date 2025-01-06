package com.example.academy.dto.auth;


import javax.servlet.http.Cookie;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    private String accessToken;
    private Cookie refreshTokenCookie;
    private AuthResponseDTO authResponseDTO;

    public LoginResponseDTO(AuthResponseDTO authResponseDTO) {
        this.authResponseDTO = authResponseDTO;
    }

    public LoginResponseDTO(String accessToken, Cookie refreshTokenCookie,
        AuthResponseDTO authResponseDTO) {
        this.accessToken = accessToken;
        this.refreshTokenCookie = refreshTokenCookie;
        this.authResponseDTO = authResponseDTO;
    }
}
