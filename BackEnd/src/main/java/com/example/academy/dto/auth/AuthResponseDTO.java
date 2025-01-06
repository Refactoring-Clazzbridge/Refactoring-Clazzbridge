package com.example.academy.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {

    private Long id; // 사용자 고유 ID
    private String name; // 사용자 이름
    private String email; // 사용자 이메일
    private String memberType; // 사용자 유형
    private String gitUrl; // Git URL
    private String profileImageUrl; // 프로필 이미지 URL

}
