package com.example.academy.service;

import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import com.example.academy.domain.Question;
import com.example.academy.domain.StudentCourse;
import com.example.academy.dto.answer.AnswerReadDTO;
import com.example.academy.dto.member.CustomUserDetails;
import com.example.academy.dto.question.QuestionCreateDTO;
import com.example.academy.dto.question.QuestionDetailReadDTO;
import com.example.academy.dto.question.QuestionReadDTO;
import com.example.academy.dto.question.QuestionToggleRecommendedDTO;
import com.example.academy.dto.question.QuestionUpdateDTO;
import com.example.academy.enums.MemberRole;
import com.example.academy.exception.post.PostBadRequestException;
import com.example.academy.exception.post.PostEmptyException;
import com.example.academy.exception.post.PostNotFoundException;
import com.example.academy.mapper.question.QuestionMapper;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.MemberRepository;
import com.example.academy.repository.mysql.QuestionRepository;
import com.example.academy.repository.mysql.StudentCourseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionService {

  private final QuestionRepository questionRepository;
  private final MemberRepository memberRepository;
  private final AuthService authService;
  private final StudentCourseRepository studentCourseRepository;
  private final CourseRepository courseRepository;
  private final AnswerService answerService;
  private final QuestionMapper questionMapper = QuestionMapper.INSTANCE;

  @Autowired
  public QuestionService(QuestionRepository questionRepository, MemberRepository memberRepository,
      AuthService authService,
      StudentCourseRepository studentCourseRepository, CourseRepository courseRepository,
      AnswerService answerService) {
    this.questionRepository = questionRepository;
    this.memberRepository = memberRepository;
    this.authService = authService;
    this.studentCourseRepository = studentCourseRepository;
    this.courseRepository = courseRepository;
    this.answerService = answerService;
  }

  public List<QuestionReadDTO> getAllQuestions() {
    CustomUserDetails user = authService.getAuthenticatedUser();

    // 매니저인 경우 모든 질문 리스트 반환
    if (user.getUserType().equals(MemberRole.ROLE_ADMIN)) {
      return questionRepository.findAll().stream()
          .map(question -> {
            // 답변이 있는지 확인하여 isSolved 설정
            boolean isSolved = question.getAnswers() != null && !question.getAnswers().isEmpty();
            return questionMapper.questionToQuestionReadDTO(question, isSolved);
          })
          .collect(Collectors.toList());
    }

    // 교사나 학생인 경우는 해당 강의의 질문만 반환 (기존 로직)
    // ... 기존 코드 ...

    return new ArrayList<>(); // 권한이 없는 경우 빈 리스트 반환
  }


  public QuestionDetailReadDTO getQuestionDetailById(Long id) {
    Question question = questionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 질문입니다 ID: " + id));

    // 답변이 있는지 확인하여 isSolved 값 설정
    boolean isSolved = question.getAnswers() != null && !question.getAnswers().isEmpty();

    // 연관된 답변도 조회
    List<AnswerReadDTO> answers = answerService.getAnswersByQuestionId(id);

    // isSolved 값을 함께 전달
    return questionMapper.questionToQuestionDetailReadDTO(question, isSolved, answers);
  }

  public QuestionReadDTO createQuestion(QuestionCreateDTO questionCreateDTO) {
    CustomUserDetails user = authService.getAuthenticatedUser();

    Member student = memberRepository.findById(user.getUserId())
        .orElseThrow(PostBadRequestException::new);

    Course course = studentCourseRepository.findByStudentId(user.getUserId()).getCourse();

    StudentCourse studentCourse = studentCourseRepository.findByStudentIdAndCourseId(
            student.getId(), course.getId())
        .orElseThrow(() -> new RuntimeException("회원이 수강"));

    Question newQuestion = questionMapper.questionCreateDTOToQuestion(questionCreateDTO,
        studentCourse);
    Question savedQuestion = questionRepository.save(newQuestion);

    return questionMapper.questionToQuestionReadDTO(savedQuestion, false);
  }

  public QuestionReadDTO updateQuestion(QuestionUpdateDTO questionUpdateDTO) {
    Question existingQuestion = questionRepository.findById(questionUpdateDTO.getId())
        .orElseThrow(() -> new RuntimeException("Question not found"));

    existingQuestion.updateContent(questionUpdateDTO.getContent());
    questionRepository.save(existingQuestion);

    boolean isSolved = !existingQuestion.getAnswers().isEmpty();
    return questionMapper.questionToQuestionReadDTO(existingQuestion, isSolved);
  }

  public QuestionReadDTO recommendQuestion(
      QuestionToggleRecommendedDTO questionToggleRecommendedDTO) {
    Question existingQuestion = questionRepository.findById(questionToggleRecommendedDTO.getId())
        .orElseThrow();

    existingQuestion.toggleRecommended(questionToggleRecommendedDTO.isRecommended());
    questionRepository.save(existingQuestion);

    boolean isSolved = !existingQuestion.getAnswers().isEmpty();
    return questionMapper.questionToQuestionReadDTO(existingQuestion, isSolved);
  }

  public void deleteQuestion(List<Long> ids) {
    if (ids.isEmpty()) {
      throw new PostEmptyException();
    }

    for (Long id : ids) {
      Question deletedQuestion = questionRepository.findById(id)
          .orElseThrow(() -> new PostNotFoundException(id));
      questionRepository.delete(deletedQuestion);
    }
  }

  public List<QuestionDetailReadDTO> getQuestionsByCourseId(Long courseId) {
    List<Question> questions = questionRepository.findByStudentCourse_Course_Id(courseId);

    return questions.stream()
        .map(question -> {
          StudentCourse studentCourse = question.getStudentCourse();

          // 답변이 있는지 확인하여 isSolved 설정
          boolean isSolved = question.getAnswers() != null && !question.getAnswers().isEmpty();

          return new QuestionDetailReadDTO(
              question.getId(),
              studentCourse.getStudent().getName(),   // 학생 이름
              studentCourse.getStudent().getId(),
              studentCourse.getCourse().getId(),      // 강의 ID (title 대신 id로 변경)
              question.getContent(),
              question.isRecommended(),
              isSolved,
              question.getCreatedAt(),
              question.getAnswers().stream()
                  .map(answer -> new AnswerReadDTO(
                      answer.getId(),
                      answer.getContent(),
                      answer.getTeacher() != null ? answer.getTeacher().getName() : null,
                      answer.getTeacher() != null ? answer.getTeacher().getId() : null,
                      answer.getCreatedAt()
                  ))
                  .collect(Collectors.toList())
          );
        })
        .collect(Collectors.toList());
  }

}
