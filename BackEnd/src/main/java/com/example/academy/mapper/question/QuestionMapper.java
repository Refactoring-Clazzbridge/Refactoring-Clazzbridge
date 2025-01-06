package com.example.academy.mapper.question;

import com.example.academy.domain.Question;
import com.example.academy.domain.StudentCourse;
import com.example.academy.dto.answer.AnswerReadDTO;
import com.example.academy.dto.question.QuestionCreateDTO;
import com.example.academy.dto.question.QuestionDetailReadDTO;
import com.example.academy.dto.question.QuestionReadDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionMapper {

  QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);




  // Domain to DTO - QuestionReadDTO로 변환 (기본적인 질문 정보)
  @Mapping(source = "question.id", target = "id")
  @Mapping(source = "question.studentCourse.student.name", target = "studentName")
  @Mapping(source = "question.recommended", target = "isRecommended")
  @Mapping(source = "isSolved", target = "isSolved")  // isSolved 파라미터를 매핑
  QuestionReadDTO questionToQuestionReadDTO(Question question, boolean isSolved);

  // DTO to Domain - Question 엔티티로 변환 (질문 생성 시)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "aiAnswer", ignore = true)
  @Mapping(target = "recommended", ignore = true)
  @Mapping(target = "answers", ignore = true)
  @Mapping(source = "studentCourse", target = "studentCourse")
  Question questionCreateDTOToQuestion(QuestionCreateDTO questionCreateDTO, StudentCourse studentCourse);

  // Domain to DTO - QuestionDetailReadDTO로 변환 (질문 상세 조회 시)
  @Mapping(source = "question.id", target = "id")
  @Mapping(source = "question.studentCourse.student.name", target = "studentName")
  @Mapping(source = "question.studentCourse.student.id", target = "studentId")
  @Mapping(source = "question.studentCourse.course.id", target = "courseId")
  @Mapping(source = "question.content", target = "content")
  @Mapping(source = "question.recommended", target = "isRecommended")
  @Mapping(source = "isSolved", target = "isSolved")  // 추가된 isSolved 파라미터를 매핑
  @Mapping(source = "question.createdAt", target = "createdAt")
  @Mapping(target = "answers", source = "answers")
  QuestionDetailReadDTO questionToQuestionDetailReadDTO(Question question, boolean isSolved, List<AnswerReadDTO> answers);
}
