package com.example.academy.dto.submission;


import lombok.Builder;
import lombok.Data;

@Data
public class SubmissionRequestDTO {

    private Long assignmentId;
    private Long studentCourseId;
    private String content;
    private String fileUrl;


    @Builder
    public SubmissionRequestDTO(Long assignmentId, Long studentCourseId, String content,
        String fileUrl) {
        this.assignmentId = assignmentId;
        this.studentCourseId = studentCourseId;
        this.content = content;
        this.fileUrl = fileUrl;
    }
}
