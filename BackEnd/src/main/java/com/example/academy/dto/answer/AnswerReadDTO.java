package com.example.academy.dto.answer;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerReadDTO {

  private Long id;
  private String content;
  private String teacherName;
  private Long teacherId; // 추가
  private Date createdAt;
}
