package com.example.academy.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMemberForChatDTO {

  private Long id;
  private String memberId;
  private String name;
  private String avatarImage;
  private String role;
  private String courseId;

}