package com.example.academy.controller.qna;

import com.example.academy.dto.answer.AnswerCreateDTO;
import com.example.academy.dto.answer.AnswerReadDTO;
import com.example.academy.dto.answer.AnswerUpdateDTO;
import com.example.academy.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/qnas/answers")
public class AnswerController {


  private final AnswerService answerService;

  @Autowired
  public AnswerController(AnswerService answerService) {
    this.answerService = answerService;
  }

  @Operation(summary = "질문에 관한 답변 리스트 반환", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping("/question/{questionId}")
  public ResponseEntity<List<AnswerReadDTO>> getAnswersByQuestionId(@PathVariable Long questionId) {
    List<AnswerReadDTO> answers = answerService.getAnswersByQuestionId(questionId);
    return ResponseEntity.ok(answers);
  }

  @Operation(summary = "답변 등록", security = {@SecurityRequirement(name = "bearerAuth")})
  @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')") // 교사와 관리자 접근 가능
  @PostMapping
  public ResponseEntity<AnswerReadDTO> createAnswer(@RequestBody AnswerCreateDTO answerCreateDTO) {
    AnswerReadDTO answerReadDTO = answerService.createAnswer(answerCreateDTO);
    return ResponseEntity.ok(answerReadDTO);
  }

  @Operation(summary = "답변 수정", security = {@SecurityRequirement(name = "bearerAuth")})
  @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')") // 교사와 관리자 접근 가능
  @PutMapping
  public ResponseEntity<AnswerReadDTO> updateAnswer(@RequestBody AnswerUpdateDTO answerUpdateDTO) {
    AnswerReadDTO updatedAnswer = answerService.updateAnswer(answerUpdateDTO);
    return ResponseEntity.ok(updatedAnswer);
  }

  @Operation(summary = "답변 삭제", security = {@SecurityRequirement(name = "bearerAuth")})
  @PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')") // 교사와 관리자 접근 가능
  @DeleteMapping("/{answerId}")
  public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
    answerService.deleteAnswer(answerId);
    return ResponseEntity.noContent().build();
  }
}
