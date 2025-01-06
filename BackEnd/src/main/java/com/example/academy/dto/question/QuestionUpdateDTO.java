package com.example.academy.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionUpdateDTO {

  private String content;
  private Long id;

}
