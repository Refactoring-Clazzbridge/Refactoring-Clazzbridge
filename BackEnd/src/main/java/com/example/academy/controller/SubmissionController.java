package com.example.academy.controller;

import com.example.academy.dto.submission.SubmissionCheckRequestDTO;
import com.example.academy.dto.submission.SubmissionRequestDTO;
import com.example.academy.dto.submission.SubmissionResponseDTO;
import com.example.academy.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @Operation(summary = "과제 제출", security = {@SecurityRequirement(name = "bearerAuth")})
    @PostMapping("")
    public ResponseEntity<SubmissionResponseDTO> submitAssignment(
        @ModelAttribute SubmissionRequestDTO submissionRequestDTO,
        @RequestParam(value = "file", required = false) MultipartFile file) {
        SubmissionResponseDTO submission = submissionService.submitAssignment(
            submissionRequestDTO, file);
        return ResponseEntity.ok().body(submission);
    }

    @Operation(summary = "모든 과제 제출리스트 조회", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("")
    public ResponseEntity<List<SubmissionResponseDTO>> getAllSubmission() {
        List<SubmissionResponseDTO> response = submissionService.getAllSubmission();
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "특정 과제 제출 조회", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/{assignmentId}/student/{studentCourseId}")
    public ResponseEntity<SubmissionResponseDTO> getSubmission(@PathVariable Long assignmentId,
        @PathVariable Long studentCourseId) {
        SubmissionResponseDTO response = submissionService.getSubmission(assignmentId,
            studentCourseId);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "특정 과제 제출 리스트 조회", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/{assignmentId}")
    public ResponseEntity<List<SubmissionResponseDTO>> getSubmissions(
        @PathVariable Long assignmentId) {
        List<SubmissionResponseDTO> response = submissionService.getSubmissions(assignmentId);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "특정 과제 제출 체크", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/check")
    public ResponseEntity<SubmissionResponseDTO> checkSubmission(
        @RequestParam Long studentCourseId,
        @RequestParam Long assignmentId) {

        SubmissionResponseDTO responseDTO = submissionService.hasSubmitted(studentCourseId,
            assignmentId);
        return ResponseEntity.ok().body(responseDTO);
    }


}
