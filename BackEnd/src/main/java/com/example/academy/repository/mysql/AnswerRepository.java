package com.example.academy.repository.mysql;


import com.example.academy.domain.Answer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
  List<Answer> findByQuestionId(Long questionId);

}
