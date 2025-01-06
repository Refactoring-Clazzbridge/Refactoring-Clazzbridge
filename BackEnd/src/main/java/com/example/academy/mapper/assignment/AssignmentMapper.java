package com.example.academy.mapper.assignment;

import com.example.academy.domain.Assignment;
import com.example.academy.domain.Post;
import com.example.academy.dto.assignment.AssignmentResponseDTO;
import com.example.academy.dto.post.PostResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper {

    public static AssignmentResponseDTO toDto(Assignment assignment) {
        if (assignment == null) {
            return null;
        }
        return AssignmentResponseDTO.builder()
            .assignmentId(assignment.getId())
            .description(assignment.getDescription())
            .courseId(assignment.getCourse().getId())
            .courseName(assignment.getCourse().getTitle())
            .dueDate(assignment.getDueDate())
            .title(assignment.getTitle())
            .build();
    }

    public static List<AssignmentResponseDTO> toDtoList(List<Assignment> assignments) {

        return assignments.stream()
            .map(assignment -> toDto(assignment))
            .collect(Collectors.toList());
    }

}
