package com.example.academy.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerCreateDTO {

  private Long questionId;
  private Long teacherId;
  private String content;

}
