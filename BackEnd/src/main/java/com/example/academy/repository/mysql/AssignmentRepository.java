package com.example.academy.repository.mysql;

import com.example.academy.domain.Assignment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> getAssignmentsByCourseId(Long courseId);

    List<Assignment> findAllByCourseId(Long courseId);
}
