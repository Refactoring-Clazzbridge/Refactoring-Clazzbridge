package com.example.academy.dto.member;

import com.example.academy.domain.AvatarImage;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateDTO {

  private Long id;
  @NotBlank(message = "memberId cannot be blank")
  private String memberId;
  @NotBlank(message = "Password cannot be blank")
  private String password;
  @NotBlank(message = "name cannot be blank")
  private String name;
  private String email;
  private String phone;
  private String memberType;
  private AvatarImage avatarImage;
  private String courseTitle;

  public MemberUpdateDTO() {
  }

  public MemberUpdateDTO(Long id, String memberId, String password, String name, String email,
      String phone, String memberType, AvatarImage avatarImage, String courseTitle) {
    this.id = id;
    this.memberId = memberId;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.memberType = memberType;
    this.avatarImage = avatarImage;
    this.courseTitle = courseTitle;
  }
}