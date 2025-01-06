package com.example.academy.dto.submission;


import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
public class SubmissionResponseDTO {

    private Long assignmentId;
    private Long studentCourseId;
    private String content;
    private String submissionUrl;
    private boolean submitted = false;
    private LocalDate submissionDate;


    @Builder
    public SubmissionResponseDTO(Long assignmentId, Long studentCourseId, String content,
        String submissionUrl, LocalDate submissionDate) {
        this.assignmentId = assignmentId;
        this.studentCourseId = studentCourseId;
        this.content = content;
        this.submissionUrl = submissionUrl;
        this.submissionDate = submissionDate;
    }
}
