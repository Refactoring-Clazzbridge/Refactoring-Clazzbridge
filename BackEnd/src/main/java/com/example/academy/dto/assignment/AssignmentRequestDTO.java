package com.example.academy.dto.assignment;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AssignmentRequestDTO {

    private Long courseId;
    private String title;
    private String description;
    private String dueDate;

    @Builder
    public AssignmentRequestDTO(Long courseId, String title, String description,
        String dueDate) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
}
