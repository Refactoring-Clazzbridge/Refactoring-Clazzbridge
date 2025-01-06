
package com.example.academy.controller;

import com.example.academy.dto.schedule.ScheduleAddDTO;
import com.example.academy.dto.schedule.ScheduleListDTO;
import com.example.academy.service.ScheduleService;
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
@RequestMapping("/api/schedule")
public class ScheduleController {

  @Autowired
  private ScheduleService scheduleService;

  @Operation(summary = "모든 일정 리스트 반환", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping
  public ResponseEntity<List<ScheduleListDTO>> getAllSchedules() {
    List<ScheduleListDTO> scheduleList = scheduleService.getScheduleAll();

    if (scheduleList.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT)
          .body(scheduleList); // 데이터가 없으면 204 No Content
    }

    return ResponseEntity.status(HttpStatus.OK).body(scheduleList);
  }

  @Operation(summary = "선택한 일정 정보 반환", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping("/{id}")
  public ResponseEntity<ScheduleListDTO> getScheduleById(@PathVariable("id") Long id) {
    Optional<ScheduleListDTO> scheduleAddDTOOptional = scheduleService.getScheduleById(id);

    if (scheduleAddDTOOptional.isPresent()) {
      // 값이 있을 때는 200 OK와 함께 ScheduleAddDTO 반환
      return ResponseEntity.status(HttpStatus.OK).body(scheduleAddDTOOptional.get());
    } else {
      // 값이 없을 때는 404 Not Found 반환
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }


  @Operation(summary = "일정 정보 추가", security = {@SecurityRequirement(name = "bearerAuth")})
  @PostMapping
  public ResponseEntity<?> addSchedule(@RequestBody ScheduleAddDTO addDTO) {
    try {
      scheduleService.addSchedule(addDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(addDTO);
    } catch (IllegalArgumentException e) {
      // Classroom을 찾지 못한 경우
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      // 그 외의 예외 처리
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("An error occurred: " + e.getMessage());
    }
  }

  @Operation(summary = "일정 정보 변경", security = {@SecurityRequirement(name = "bearerAuth")})
  @PutMapping
  public ResponseEntity<ScheduleListDTO> updateSchedule(@RequestBody ScheduleListDTO updateDTO) {
    System.out.println("업데이트");
    scheduleService.updateSchedule(updateDTO);
    return ResponseEntity.status(HttpStatus.OK).body(updateDTO);
  }

  @Operation(summary = "일정 정보 삭제", security = {@SecurityRequirement(name = "bearerAuth")})
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {
    try {
      scheduleService.deleteSchedule(id);
      return ResponseEntity.ok("ok"); // 성공 시 200 OK와 함께 "ok" 반환
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: 일정 삭제 실패"); // 실패 시 404 응답
    }
  }
}

