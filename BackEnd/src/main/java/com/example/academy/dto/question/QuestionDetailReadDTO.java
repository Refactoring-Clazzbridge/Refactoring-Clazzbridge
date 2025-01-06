package com.example.academy.dto.question;

import com.example.academy.dto.answer.AnswerReadDTO;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class QuestionDetailReadDTO {

  private Long id;
  private String studentName;
  private Long studentId;  // 작성자 ID 추가
  private Long courseId;
  private String content;
  private boolean isRecommended;
  private boolean isSolved;
  private Date createdAt;

  private List<AnswerReadDTO> answers;

}
