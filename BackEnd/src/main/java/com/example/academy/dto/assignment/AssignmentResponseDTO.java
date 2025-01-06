package com.example.academy.dto.assignment;

import com.example.academy.domain.Member;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class AssignmentResponseDTO {

    private Long assignmentId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Long courseId;
    private String courseName;

    @Builder
    public AssignmentResponseDTO(Long assignmentId, String title, String description,
        LocalDate dueDate,
        Long courseId, String courseName) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.courseId = courseId;
        this.courseName = courseName;
    }
}
