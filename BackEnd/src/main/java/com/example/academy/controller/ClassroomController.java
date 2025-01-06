package com.example.academy.controller;


import com.example.academy.domain.Classroom;
import com.example.academy.dto.classroom.AddClassroomDTO;
import com.example.academy.dto.classroom.ClassroomNameDTO;
import com.example.academy.dto.classroom.UpdateClassroomDTO;
import com.example.academy.service.ClassroomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

  @Autowired
  private ClassroomService classroomService;

  @GetMapping("/name")
  @Operation(summary = "강의실명 전체 조회", security = {@SecurityRequirement(name = "bearerAuth")})
  public List<ClassroomNameDTO> getClassroomName(){
    List<ClassroomNameDTO> classroomNameDTOS = classroomService.getClassroomName();
    return classroomNameDTOS;
  }

  @PostMapping
  @Operation(summary = "강의실 추가", security = {@SecurityRequirement(name = "bearerAuth")})
  public ResponseEntity<String> addClassroom(@RequestBody AddClassroomDTO addClassroomDTO){
    classroomService.addClassroom(addClassroomDTO);
    return ResponseEntity.ok("추가완료");
  }

  @GetMapping
  @Operation(summary = "강의실 전체 조회", security = {@SecurityRequirement(name = "bearerAuth")})
  public ResponseEntity<List<?>> getAllClassroom(){
    List<Classroom> name = classroomService.getAllClassroom();
    return ResponseEntity.ok(name);
  }

  @GetMapping("{id}")
  @Operation(summary = "강의실 조회", security = {@SecurityRequirement(name = "bearerAuth")})
  public ResponseEntity<Optional<?>> getClassroom(@PathVariable Long id){
    Optional<Classroom> name = classroomService.getClassroom(id);
    if (name.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(name);
  }

  @DeleteMapping("{id}")
  @Operation(summary = "강의실 삭제", security = {@SecurityRequirement(name = "bearerAuth")})
  public ResponseEntity<?> deleteClassroom(@PathVariable Long id){
    try {
      classroomService.deleteClassroom(id);
      return ResponseEntity.ok("삭제 성공");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PutMapping
  @Operation(summary = "강의실 변경", security = {@SecurityRequirement(name = "bearerAuth")})
  public ResponseEntity<?> updateClassroom(@RequestBody UpdateClassroomDTO updateClassroomDTO){
    try {
      classroomService.updateClassroom(updateClassroomDTO);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당하는 강의실이 없습니다.");
    }
    return ResponseEntity.ok(updateClassroomDTO);
  }

}
