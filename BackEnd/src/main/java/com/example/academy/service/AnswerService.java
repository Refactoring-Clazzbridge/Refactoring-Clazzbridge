package com.example.academy.service;

import com.example.academy.domain.Answer;
import com.example.academy.domain.Member;
import com.example.academy.domain.Question;
import com.example.academy.dto.answer.AnswerCreateDTO;
import com.example.academy.dto.answer.AnswerReadDTO;
import com.example.academy.dto.answer.AnswerUpdateDTO;
import com.example.academy.mapper.answer.AnswerMapper;
import com.example.academy.repository.mysql.AnswerRepository;
import com.example.academy.repository.mysql.MemberRepository;
import com.example.academy.repository.mysql.QuestionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnswerService {

  private final AnswerRepository answerRepository;
  private final QuestionRepository questionRepository;
  private final MemberRepository memberRepository;
  private final AnswerMapper answerMapper = AnswerMapper.INSTANCE;

  @Autowired
  public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository,
      MemberRepository memberRepository) {
    this.answerRepository = answerRepository;
    this.questionRepository = questionRepository;
    this.memberRepository = memberRepository;
  }

  public List<AnswerReadDTO> getAnswersByQuestionId(Long questionId) {
    List<Answer> answers = answerRepository.findByQuestionId(questionId);
    return answerMapper.answersToAnswerReadDTOs(answers);
  }

  public AnswerReadDTO createAnswer(AnswerCreateDTO answerCreateDTO) {
    if (answerCreateDTO.getQuestionId() == null || answerCreateDTO.getTeacherId() == null) {
      throw new IllegalArgumentException("질문 ID나 교사 ID가 null일 수 없습니다.");
    }

    Question question = questionRepository.findById(answerCreateDTO.getQuestionId())
        .orElseThrow(() -> new RuntimeException("Question not found"));
    Member teacher = memberRepository.findById(answerCreateDTO.getTeacherId())
        .orElseThrow(() -> new RuntimeException("Teacher not found"));

    Answer answer = answerMapper.answerCreateDTOToAnswer(answerCreateDTO, question, teacher);
    answerRepository.save(answer);

    return answerMapper.answerToAnswerReadDTO(answer);
  }

  public AnswerReadDTO updateAnswer(AnswerUpdateDTO answerUpdateDTO) {
    Answer answer = answerRepository.findById(answerUpdateDTO.getId())
        .orElseThrow(() -> new RuntimeException("Answer not found"));

    answer.setContent(answerUpdateDTO.getContent());
    answerRepository.save(answer);

    return answerMapper.answerToAnswerReadDTO(answer);
  }

  public void deleteAnswer(Long answerId) {
    answerRepository.deleteById(answerId);
  }
}
