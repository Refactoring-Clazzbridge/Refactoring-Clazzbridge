package com.example.academy.mapper.answer;

import com.example.academy.domain.Answer;
import com.example.academy.domain.Member;
import com.example.academy.domain.Question;
import com.example.academy.dto.answer.AnswerCreateDTO;
import com.example.academy.dto.answer.AnswerReadDTO;
import com.example.academy.dto.answer.AnswerUpdateDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnswerMapper {

  AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

  // Answer 엔티티를 AnswerReadDTO로 변환
  @Mapping(source = "teacher.id", target = "teacherId")
  @Mapping(source = "teacher.name", target = "teacherName")
  @Mapping(source = "createdAt", target = "createdAt")
  AnswerReadDTO answerToAnswerReadDTO(Answer answer);

  // List<Answer>를 List<AnswerReadDTO>로 변환
  List<AnswerReadDTO> answersToAnswerReadDTOs(List<Answer> answers);

  // AnswerCreateDTO를 Answer 엔티티로 변환
  @Mapping(target = "id", ignore = true)
  @Mapping(source = "question", target = "question")
  @Mapping(source = "teacher", target = "teacher")
  @Mapping(source = "answerCreateDTO.content", target = "content")
  Answer answerCreateDTOToAnswer(AnswerCreateDTO answerCreateDTO, Question question, Member teacher);

  // AnswerUpdateDTO를 Answer 엔티티로 변환
  @Mapping(target = "question", ignore = true)
  @Mapping(target = "teacher", ignore = true)
  @Mapping(target = "createdAt", ignore = true) // createdAt 필드 무시
  Answer answerUpdateDTOToAnswer(AnswerUpdateDTO answerUpdateDTO);
}