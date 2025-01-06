package com.example.academy.repository.mysql;

import com.example.academy.domain.StudentCourse;
import com.example.academy.domain.Submission;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByIdAssignmentId(Long assignmentId);

    Optional<Submission> findByIdAssignmentIdAndIdStudentCourseId(Long assignmentId,
        Long studentCourseId);

    List<Submission> findByStudentCourse(StudentCourse studentCourses);

    Optional<Submission> findByStudentCourseIdAndAssignmentId(Long studentCourseId,
        Long assignmentId);

    Optional<Submission> findByIdStudentCourseIdAndIdAssignmentId(Long studentCourseId,
        Long assignmentId);

}
