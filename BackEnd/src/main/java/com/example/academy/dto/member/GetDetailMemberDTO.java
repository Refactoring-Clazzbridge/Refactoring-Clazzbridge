package com.example.academy.dto.member;

import com.example.academy.domain.AvatarImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetDetailMemberDTO {

    private Long id;
    private String memberId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String memberType;
    private String avatarImage;
    private String courseTitle;

    public GetDetailMemberDTO() {
    }

    public GetDetailMemberDTO(Long id, String memberId, String password, String name, String email,
        String phone, String memberType, AvatarImage avatarImage, String courseTitle) {
        this.id = id;
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.memberType = memberType;
        this.avatarImage = avatarImage.getAvatarImageUrl();
        this.courseTitle = courseTitle;
    }
}