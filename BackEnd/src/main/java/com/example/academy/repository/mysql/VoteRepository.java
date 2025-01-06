package com.example.academy.repository.mysql;

import com.example.academy.domain.Course;
import com.example.academy.domain.Vote;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

  Optional<Vote> findByTitle(String title); // 투표 제목으로 조회

  List<Vote> findByCourse(Course course);

  @Query("SELECT v FROM Vote v JOIN FETCH v.course c JOIN FETCH c.instructor")
  List<Vote> findAllWithCourseAndInstructor();

  boolean existsByTitle(String title);

  List<Vote> findAll();

}
