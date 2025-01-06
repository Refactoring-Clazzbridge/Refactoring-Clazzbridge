package com.example.academy.dto.studentCourse;

import lombok.Builder;
import lombok.Data;

@Data
public class StudentCourseResponseDTO {

    private Long id;
    private Long courseId;
    private String courseTitle;

    @Builder
    public StudentCourseResponseDTO(Long id, Long courseId, String courseTitle) {
        this.id = id;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
    }
}
