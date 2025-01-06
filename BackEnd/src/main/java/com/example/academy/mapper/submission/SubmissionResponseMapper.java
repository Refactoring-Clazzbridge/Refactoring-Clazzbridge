package com.example.academy.mapper.submission;


import com.example.academy.domain.Submission;
import com.example.academy.dto.submission.SubmissionResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SubmissionResponseMapper {

    public static SubmissionResponseDTO toDto(Submission submission) {
        if (submission == null) {
            return null;
        }
        return SubmissionResponseDTO.builder()
            .assignmentId(submission.getId().getAssignmentId())
            .studentCourseId(submission.getId().getStudentCourseId())
            .content(submission.getContent())
            .submissionUrl(submission.getSubmissionUrl())
            .submissionDate(submission.getSubmissionDate())
            .build();
    }

    public static List<SubmissionResponseDTO> toDtoList(List<Submission> submissions) {
        if (submissions.isEmpty()) {
            return null;
        }
        return submissions.stream()
            .map(submission -> toDto(submission))
            .collect(Collectors.toList());
    }

}
