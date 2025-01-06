package com.example.academy.repository.mysql;

import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import com.example.academy.domain.StudentCourse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

//    List<StudentCourse> findByStudent(Member student);

    StudentCourse findByStudent(Member student);

    Optional<StudentCourse> findById(Long id);

    List<StudentCourse> findAllById(Long id);

    Optional<StudentCourse> findByStudentIdAndCourseId(Long id, Long id1);

    StudentCourse findByStudentId(Long id);

    List<StudentCourse> findByCourseId(Long id);

    List<StudentCourse> findByCourse(Course course);

    List<StudentCourse> findByCourse_Id(Long courseId);
}
