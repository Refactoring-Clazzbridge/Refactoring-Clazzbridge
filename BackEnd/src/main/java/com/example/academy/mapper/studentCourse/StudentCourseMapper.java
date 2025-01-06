package com.example.academy.mapper.studentCourse;

import com.example.academy.domain.Course;
import com.example.academy.domain.StudentCourse;
import com.example.academy.dto.studentCourse.StudentCourseResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentCourseMapper {

    public static StudentCourseResponseDTO toDto(StudentCourse studentCourse) {
        if (studentCourse == null) {
            return null;
        }
        return StudentCourseResponseDTO.builder()
            .id(studentCourse.getId())
            .courseId(studentCourse.getCourse().getId())
            .courseTitle(studentCourse.getCourse().getTitle())
            .build();
    }

    public static StudentCourseResponseDTO toDto(Course course) {
        if (course == null) {
            return null;
        }
        return StudentCourseResponseDTO.builder()
            .courseId(course.getId())
            .courseTitle(course.getTitle())
            .build();
    }

}
