package com.example.academy.controller;


import com.example.academy.dto.course.CourseAddDTO;
import com.example.academy.dto.course.CourseTitleDTO;
import com.example.academy.dto.course.CourseUpdateDTO;
import com.example.academy.dto.course.GetCourseDTO;
import com.example.academy.dto.member.StudentDTO;
import com.example.academy.dto.course.SelectCourseDTO;
import com.example.academy.service.CourseService;
import com.example.academy.service.StudentCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private final CourseService courseService;
    private final StudentCourseService studentCourseService;

    public CourseController(CourseService courseService,
        StudentCourseService studentCourseService) {
        this.courseService = courseService;
        this.studentCourseService = studentCourseService;
    }


    @Operation(summary = "강의명 전체 조회", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/title")
    public ResponseEntity<List<?>> getCourseTitle() {
        List<CourseTitleDTO> title = courseService.getCourseTitle();
        return ResponseEntity.ok(title);
    }

    @Operation(summary = "강의에 속한 수강생 리스트 및 특정 과제 제출 여부 조회", security = {
        @SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/students/{assignmentId}")
    public ResponseEntity<List<StudentDTO>> studentsWithSubmissions(
        @PathVariable(value = "assignmentId") Long assignmentId) {
        List<StudentDTO> students = studentCourseService.studentsWithSubmissions(assignmentId);
        return ResponseEntity.ok(students);
    }

    @Operation(summary = "강의 전체 조회", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping
    public ResponseEntity<List<GetCourseDTO>> getAllCourse() {
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @Operation(summary = "강의 조회", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        try {
            List<GetCourseDTO> getCourseDTOS = courseService.getCourse(id);
            return ResponseEntity.ok(getCourseDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "강의 시간 조회", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("time/{title}")
    public ResponseEntity<?> getTime(@PathVariable String title) {
        return ResponseEntity.ok(courseService.getTime(title));
    }


    @PostMapping
    @Operation(summary = "강의 추가", security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<?> addCourse(@RequestBody CourseAddDTO courseAddDTO) {
        try {
            courseService.addCourse(courseAddDTO);
            return ResponseEntity.ok().body("추가 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    @Operation(summary = "강의 변경", security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<?> updateCourse(@RequestBody CourseUpdateDTO courseUpdateDTO) {
        try {
            courseService.updateCourse(courseUpdateDTO);
            return ResponseEntity.ok().body("변경 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("{id}")
    @Operation(summary = "강의 삭제", security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok("삭제완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 강의 삭제 실패");
        }
    } // 여기 괄호 추가됨

    @Operation(summary = "강의실 강의 선택")
    @GetMapping("/select")
    public ResponseEntity<List<SelectCourseDTO>> SeatAllCourse() {
        return ResponseEntity.ok(courseService.seatAllCourse());
    }

    @Operation(summary = "강의 번호 반환")
    @GetMapping("/teacher")
    public ResponseEntity<Long> getTeacherByCourseId() {
        return ResponseEntity.ok(courseService.getTeacherByCourseId());
    }

    @Operation(summary = "학생의 강의 번호 반환")
    @GetMapping("/student")
    public ResponseEntity<Long> getStudentCourseId() {
        return ResponseEntity.ok(courseService.getStudentCourseId());
    }

    @Operation(summary = "수강생 및 강사 강의 번호 반환")
    @GetMapping("/userCourseId")
    public ResponseEntity<Long> getCourseIdForUser() {
        return ResponseEntity.ok(courseService.getCourseIdForUser());
    }
}