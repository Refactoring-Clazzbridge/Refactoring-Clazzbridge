package com.example.academy.dto.question;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionReadDTO {

  private Long id;
  private String studentName;
  private String content;
  private boolean isRecommended;
  private boolean isSolved;
  private Date createdAt;
}
