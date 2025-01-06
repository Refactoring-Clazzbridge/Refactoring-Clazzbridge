package com.example.academy.controller.qna;

import com.example.academy.dto.question.QuestionCreateDTO;
import com.example.academy.dto.question.QuestionDetailReadDTO;
import com.example.academy.dto.question.QuestionReadDTO;
import com.example.academy.dto.question.QuestionToggleRecommendedDTO;
import com.example.academy.dto.question.QuestionUpdateDTO;
import com.example.academy.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/qnas/questions")
public class QuestionController {

  private QuestionService questionService;

  @Autowired
  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  //요청이 들어왔을 때 처리해야 한다
  // 기능

  /**
   * 게시판 접근 - 목록을 리턴 *
   * <p>
   * 질문 생성 (수강) *
   * <p>
   * 질문에 접근 (id) (수강) * - 질문 데이터를 리턴
   * <p>
   * 질문 수정 (수강) *
   * <p>
   * 질문 삭제 (수강) *
   * <p>
   * 질문에 접근 (id) (강사님) *
   * <p>
   * 질문 수정 (강사) *
   * <p>
   * 질문 삭제 (강사) *
   * <p>
   * 질문 개추 (강사) *
   * <p>
   * 질문 답변 완료 (강사) *
   * <p>
   */
//
//  //Question CRUD
//  @GetMapping
//  public ResponseEntity<Map<String, Object>> getQuestionsByPage(@RequestParam("page") int page) {
//    Pageable pageable = PageRequest.of(page - 1, 10);  // 페이지 당 10개의 질문을 가져오기 위해 Pageable 설정
//
//    Page<QuestionReadDTO> questionsPage = questionService.getPageQuestions(pageable);
//
//    // 질문 목록과 총 페이지 수를 담는 응답 데이터를 구성
//    Map<String, Object> response = new HashMap<>();
//    response.put("questions", questionsPage.getContent());  // 현재 페이지의 질문 목록
//    response.put("totalPages", questionsPage.getTotalPages());  // 총 페이지 수
//
//    return ResponseEntity.ok(response);  // 응답을 반환
//  }
  @Operation(summary = "전체 질문 리스트 반환", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping
  public ResponseEntity<List<QuestionReadDTO>> getAllQuestions() {
    List<QuestionReadDTO> questions = questionService.getAllQuestions();
    return ResponseEntity.ok(questions);
  }

  @Operation(summary = "특정 강의의 질문 리스트 반환", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping("/course/{courseId}")
  public ResponseEntity<List<QuestionDetailReadDTO>> getQuestionsByCourseId(@PathVariable Long courseId) {
    List<QuestionDetailReadDTO> questions = questionService.getQuestionsByCourseId(courseId);
    return ResponseEntity.ok(questions);
  }

  @Operation(summary = "ID로 질문 상세 조회", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping("/{id}")
  public ResponseEntity<QuestionDetailReadDTO> getQuestionDetailById(@PathVariable("id") Long id) {
    QuestionDetailReadDTO questionDetailReadDTO = questionService.getQuestionDetailById(id);
    return ResponseEntity.ok(questionDetailReadDTO);
  }

  @Operation(summary = "질문 생성", security = {@SecurityRequirement(name = "bearerAuth")})
  @PostMapping()
  public ResponseEntity<QuestionReadDTO> createQuestion(
      @RequestBody QuestionCreateDTO createQuestionDTO) {
    QuestionReadDTO newQuestionReadDTO = questionService.createQuestion(createQuestionDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(newQuestionReadDTO);
  }

  @Operation(summary = "질문 변경", security = {@SecurityRequirement(name = "bearerAuth")})
  @PutMapping()
  public ResponseEntity<QuestionReadDTO> updateQuestion(
      @RequestBody QuestionUpdateDTO questionUpdateDTO) {
    QuestionReadDTO updatedQuestionReadDTO = questionService.updateQuestion(questionUpdateDTO);
    return ResponseEntity.status(HttpStatus.OK).body(updatedQuestionReadDTO);
  }

  @Operation(summary = "질문 삭제", security = {@SecurityRequirement(name = "bearerAuth")})
  @DeleteMapping()
  public ResponseEntity<?> deleteQuestion(@RequestBody List<Long> ids) {
    questionService.deleteQuestion(ids);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "질문 추천 상태 변경", security = {@SecurityRequirement(name = "bearerAuth")})
  @PutMapping("/recommended")
  public ResponseEntity<QuestionReadDTO> toggleQuestionRecommend(
      @RequestBody QuestionToggleRecommendedDTO questionToggleRecommendedDTO) {
    QuestionReadDTO updatedQuestionReadDTO = questionService.recommendQuestion(
        questionToggleRecommendedDTO);
    return ResponseEntity.status(HttpStatus.OK).body(updatedQuestionReadDTO);
  }

}