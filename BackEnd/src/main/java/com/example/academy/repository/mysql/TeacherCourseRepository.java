package com.example.academy.repository.mysql;

import com.example.academy.domain.Member;
import com.example.academy.domain.TeacherCourse;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Long> {

  Optional<TeacherCourse> findByTeacher(Member teacher);


}
