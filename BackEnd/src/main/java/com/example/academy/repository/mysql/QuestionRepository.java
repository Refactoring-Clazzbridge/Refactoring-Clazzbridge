package com.example.academy.repository.mysql;

import com.example.academy.domain.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository를 상속받아 ToDoRepository 구현하기 JPA (Java Persistence API) 자바 객체를 관계형 데이터 베이스에 영속적으로
 * 저장하고 조회할 수 있는 ORM 기술에 대한 표준 명세 SQL 쿼리를 작성하지 않고도 객체를 통해 데이터베이스를 조작할 수 있으며, 객체 지향적인 코드 작성과 유지 보수성
 * 향상에 도움이 됨 복잡한 JDBC 코드를 작성하지 않아도 간단하게 DB와의 데이터 접근 작업을 처리할 수 있음
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {


  List<Question> findByStudentCourse_Course_Id(Long courseId);
}
