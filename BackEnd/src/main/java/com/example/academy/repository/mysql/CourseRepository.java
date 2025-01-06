package com.example.academy.repository.mysql;

import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByInstructor(Member instructor);
    
    Optional<Course> findByTitle(String title);

    boolean existsByTitle(String title);

    Optional<Course> findByInstructor_Id(Long userId);

  Optional<Course> findByInstructorId(Long userId);

}
