package com.example.academy.controller;

import com.example.academy.dto.assignment.AssignmentRequestDTO;
import com.example.academy.dto.assignment.AssignmentResponseDTO;
import com.example.academy.service.AssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }


    @Operation(summary = "모든 과제 리스트 반환", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("")
    public ResponseEntity<List<AssignmentResponseDTO>> getAllAssignments() {
        List<AssignmentResponseDTO> assignment = assignmentService.getAllAssignments();

        return ResponseEntity.ok().body(assignment);
    }

    @Operation(summary = "강의별 과제 리스트 반환", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AssignmentResponseDTO>> getAssignmentsByCourseId(
        @PathVariable(value = "courseId") Long courseId) {
        List<AssignmentResponseDTO> assignment = assignmentService.getAssignmentsByCourseId(
            courseId);

        return ResponseEntity.ok().body(assignment);
    }

    @Operation(summary = "로그인된 사용자의 강의 과제 리스트 반환", security = {
        @SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/me")
    public ResponseEntity<List<AssignmentResponseDTO>> getAssignmentsForCurrentUser() {
        List<AssignmentResponseDTO> assignment = assignmentService.getAssignmentsForCurrentUser();

        return ResponseEntity.ok().body(assignment);
    }


    @Operation(summary = "과제 생성", security = {@SecurityRequirement(name = "bearerAuth")})
    @PostMapping("")
    public ResponseEntity<AssignmentResponseDTO> createAssignment(
        @RequestBody AssignmentRequestDTO assignmentRequestDTO) {

        AssignmentResponseDTO assignment = assignmentService.createAssignment(assignmentRequestDTO);

        return ResponseEntity.ok().body(assignment);
    }

    @Operation(summary = "과제 삭제", security = {@SecurityRequirement(name = "bearerAuth")})
    @DeleteMapping("/{assignmentId}")
    public HttpStatus deleteAssignment(@PathVariable(value = "assignmentId") Long assignmentId) {

        assignmentService.deleteAssignment(assignmentId);

        return HttpStatus.OK;
    }


}
