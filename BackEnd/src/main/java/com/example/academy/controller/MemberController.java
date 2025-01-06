package com.example.academy.controller;

import com.example.academy.dto.member.GetChatDetailMemberDTO;
import com.example.academy.dto.member.GetDetailMemberDTO;
import com.example.academy.dto.member.GetMemberDTO;
import com.example.academy.dto.member.GetMemberForChatDTO;
import com.example.academy.dto.member.MemberSignUpDTO;
import com.example.academy.dto.member.MemberUpdateDTO;
import com.example.academy.service.MemberListService;
import com.example.academy.service.MemberManageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("/api/user")
public class MemberController {

  private final MemberListService memberListService;
  private final MemberManageService memberManageService;

  public MemberController(MemberListService memberListService,
      MemberManageService memberManageService) {
    this.memberListService = memberListService;
    this.memberManageService = memberManageService;
  }

  @GetMapping("/check/{userId}")
  @Operation(summary = "권한 확인", security = {@SecurityRequirement(name = "bearerAuth")})
  public ResponseEntity<?> roleCheck(@PathVariable Long userId) {
    return ResponseEntity.ok(memberListService.getCheckRole(userId));
  }

  @PostMapping
  @Operation(summary = "회원 등록", security = {@SecurityRequirement(name = "bearerAuth")})
  public ResponseEntity<String> signUp(@RequestBody MemberSignUpDTO memberSignUpDTO) {
    try {
      memberManageService.signUp(memberSignUpDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body("회원 등록 완료");
    } catch (DataIntegrityViolationException e) { // 중복 값 등으로 인한 DB 제약 조건 위반
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    } catch (Exception e) { // 그 외의 일반적인 예외 처리
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생");
    }
  }

  @Operation(summary = "회원 조회", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping("/{id}")
  public ResponseEntity<GetDetailMemberDTO> getMemberWithCourseInfo(@PathVariable Long id) {
    GetDetailMemberDTO memberDTO = memberListService.getMemberWithCourseInfo(id);
    return ResponseEntity.ok(memberDTO);
  }

  @Operation(summary = "전체 회원 조회", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping
  public ResponseEntity<List<GetMemberDTO>> getAllMembersWithCoursesInfo() {
    List<GetMemberDTO> memberDTOs = memberListService.getAllMembersWithCourses();
    return ResponseEntity.ok(memberDTOs);
  }

  @Operation(summary = "관리자 포함 전체 회원 조회", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping("/all")
  public ResponseEntity<List<GetMemberForChatDTO>> getAllMembers() {
    List<GetMemberForChatDTO> memberDTOs = memberListService.getAllMembers();
    return ResponseEntity.ok(memberDTOs);
  }

  @Operation(summary = "회원 변경", security = {@SecurityRequirement(name = "bearerAuth")})
  @PutMapping
  public ResponseEntity<String> updateMember(@RequestBody MemberUpdateDTO updateDTO) {
    try {
      memberManageService.updateMember(updateDTO);
      return ResponseEntity.ok().body("회원 정보 업데이트 성공");
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity.badRequest().body("회원 정보 업데이트 실패: " + e.getMessage());
    } catch (RuntimeException e) {
      // 예외 발생 시, 에러 메시지와 함께 400 Bad Request 응답 반환
      return ResponseEntity.badRequest().body("회원 정보 업데이트 실패: " + e.getMessage());
    }
  }

  @Operation(summary = "회원 삭제", security = {@SecurityRequirement(name = "bearerAuth")})
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteMembers(@PathVariable Long id) {
    memberListService.deleteMember(id);
    return ResponseEntity.ok().body("삭제 완료");
  }

  @Operation(summary = "채팅용 회원 조회", security = {@SecurityRequirement(name = "bearerAuth")})
  @GetMapping("/chat/{id}")
  public ResponseEntity<GetChatDetailMemberDTO> getMemberForChat(@PathVariable Long id) {
    GetChatDetailMemberDTO memberDTO = memberListService.getMemberForChat(id);
    return ResponseEntity.ok(memberDTO);
  }
}


