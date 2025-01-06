package com.example.academy.controller;

import com.example.academy.dto.member.StudentDTO;
import com.example.academy.dto.studentCourse.StudentCourseResponseDTO;
import com.example.academy.service.StudentCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/studentCourses")
public class studentCourseController {

    private final StudentCourseService studentCourseService;

    public studentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @Operation(summary = "사용자 강의 아이디 반환", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("")
    public ResponseEntity<Long> getCoureseId() {
        Long courseId = studentCourseService.getCourseId();

        return ResponseEntity.ok().body(courseId);
    }
    

    @Operation(summary = "과제 삭제", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/studentCourseId")
    public ResponseEntity<StudentCourseResponseDTO> getStudentCourseId() {
        StudentCourseResponseDTO studentCourseResponseDTO = studentCourseService.getStudentCourseId();

        return ResponseEntity.ok().body(studentCourseResponseDTO);
    }

}
