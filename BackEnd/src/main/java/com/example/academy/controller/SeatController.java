package com.example.academy.controller;


import com.example.academy.dto.seat.SeatManagementDTO;
import com.example.academy.dto.seat.SeatUpdateDTO;
import com.example.academy.dto.seat.SeatListDTO;
import com.example.academy.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/seat")
public class SeatController {

  private final SeatService seatService;

  @Autowired
  public SeatController(SeatService seatService) {
    this.seatService = seatService;
  }

  @Operation(summary = "모든 좌석 리스트 반환")
  @GetMapping("/")
  public ResponseEntity<List<SeatListDTO>> getAllSeat() {
    List<SeatListDTO> seatListDTO = seatService.getAllSeat();
    return ResponseEntity.ok(seatListDTO);
  }

  @Operation(summary = "멤버 등록", description = "특정 좌석에 멤버 등록")
  @PutMapping("/assign")
  public ResponseEntity<?> assignSeat(@RequestBody SeatUpdateDTO seatUpdateDTO) {

    System.out.println("SeatUpdateDTO: " + seatUpdateDTO);
    System.out.println("Assigning seat to member: " + seatUpdateDTO.getMemberId());

    Optional<SeatListDTO> result = seatService.assignSeatToMember(seatUpdateDTO); // seatDTO 전체를 전달
    return result.map(seat -> ResponseEntity.ok("좌석이 성공적으로 배정되었습니다."))
        .orElse(ResponseEntity.status(HttpStatus.CONFLICT)
            .body("이 회원은 이미 다른 좌석을 점유하고 있습니다."));
  }

  @Operation(summary = "멤버 해제", description = "특정 좌석 멤버 해제")
  @DeleteMapping("/{id}")
  public ResponseEntity<SeatListDTO> removeMemberFromSeat(@PathVariable Long id) {
    SeatListDTO updatedSeat = seatService.removeMemberFromSeat(id);
    return ResponseEntity.ok(updatedSeat);
  }

  @Operation(summary = "좌석 생성", description = "매니저가 좌석을 생성합니다.")
  @PostMapping("/register")
  public ResponseEntity<List<SeatListDTO>> registerSeats(@RequestParam int seatCount, @RequestParam Long courseId) {
    List<SeatListDTO> createdSeats = seatService.registerSeats(seatCount, courseId);
    return ResponseEntity.ok(createdSeats);
  }

  @Operation(summary = "좌석 삭제", description = "모든 좌석을 삭제합니다.")
  @DeleteMapping("/deleteAllSeats")
  public ResponseEntity<String> deleteAllSeats(@RequestParam Long courseId) {
    seatService.deleteAllSeatsByCourse(courseId);
    return ResponseEntity.ok("모든 좌석이 성공적으로 삭제되었습니다.");
  }

  @Operation(summary = "특정 강의의 좌석 리스트 반환")
  @GetMapping("/course/{courseId}")
  public ResponseEntity<List<SeatListDTO>> getSeatsByCourse(@PathVariable Long courseId) {
    List<SeatListDTO> seats = seatService.getSeatsByCourse(courseId);
    return ResponseEntity.ok(seats);
  }

  @Operation(summary = "특정 멤버의 좌석 상태 반환")
  @GetMapping("/status/{memberId}")
  public ResponseEntity<SeatListDTO> getSeatStatusByMemberId(@PathVariable Long memberId) {
    SeatListDTO seatStatus = seatService.getSeatStatusByMemberId(memberId).orElse(new SeatListDTO());

    return ResponseEntity.ok().body(seatStatus);
  }

  @Operation(summary = "학생의 온라인 상태 업데이트", description = "좌석 ID로 학생의 온라인 상태를 업데이트합니다.")
  @PutMapping("/status/{seatId}")
  public ResponseEntity<String> updateStudentOnlineStatus(@PathVariable Long seatId, @RequestBody Map<String, Boolean> onlineStatus) {
    boolean isOnline = onlineStatus.get("isOnline");
    seatService.updateOnlineStatus(seatId, isOnline);
    return ResponseEntity.ok("학생의 온라인 상태가 업데이트되었습니다.");
  }
}

