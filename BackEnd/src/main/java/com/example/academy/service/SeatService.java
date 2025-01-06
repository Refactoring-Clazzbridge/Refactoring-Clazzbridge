package com.example.academy.service;

import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import com.example.academy.domain.Seat;
import com.example.academy.dto.member.CustomUserDetails;
import com.example.academy.dto.member.MemberDTO;
import com.example.academy.dto.seat.SeatUpdateDTO;
import com.example.academy.dto.seat.SeatListDTO;
import com.example.academy.exception.common.NotFoundException;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.MemberTypeRepository;
import com.example.academy.repository.mysql.SeatRepository;
import com.example.academy.repository.mysql.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatService {

  private final SeatRepository seatRepository;
  private final MemberRepository memberRepository;
  private final MemberTypeRepository memberTypeRepository;
  private final CourseRepository courseRepository;
  private final AuthService authService;

  private static final String TEACHER_SEAT_NUMBER = "T";

  @Autowired
  public SeatService(SeatRepository seatRepository, MemberRepository memberRepository,
      MemberTypeRepository memberTypeRepository, CourseRepository courseRepository,
      AuthService authService) {
    this.seatRepository = seatRepository;
    this.memberRepository = memberRepository;
    this.authService = authService;
    this.memberTypeRepository = memberTypeRepository;
    this.courseRepository = courseRepository;
  }

  public List<SeatListDTO> getAllSeat() {
    List<Seat> seatList = seatRepository.findAll();
    List<SeatListDTO> seatListDTO = new ArrayList<>();

    // 좌석을 정렬하고 리스트에 추가
    seatList.stream()
        .filter(seat -> !TEACHER_SEAT_NUMBER.equals(seat.getSeatNumber()))
        .sorted((s1, s2) -> {
          String seatNumber1 = s1.getSeatNumber();
          String seatNumber2 = s2.getSeatNumber();

          // 문자와 숫자 부분 분리
          String prefix1 = seatNumber1.replaceAll("\\d", "");
          String prefix2 = seatNumber2.replaceAll("\\d", "");
          int number1 = Integer.parseInt(seatNumber1.replaceAll("\\D", ""));
          int number2 = Integer.parseInt(seatNumber2.replaceAll("\\D", ""));

          int prefixComparison = prefix1.compareTo(prefix2);
          return prefixComparison != 0 ? prefixComparison : Integer.compare(number1, number2);
        })
        .forEach(seat -> seatListDTO.add(convertSeatToDTO(seat)));

    return seatListDTO;
  }

  @Transactional
  public Optional<SeatListDTO> assignSeatToMember(SeatUpdateDTO seatUpdateDTO) {
    CustomUserDetails user = authService.getAuthenticatedUser();
    Member member = memberRepository.findById(user.getUserId())
        .orElseThrow(() -> new NotFoundException("해당 회원이 없습니다."));

    Optional<Seat> occupiedSeat = seatRepository.findByMemberId(member.getId());
    if (occupiedSeat.isPresent()) {
      // 이미 좌석을 점유 중일 경우 빈 결과 반환
      return Optional.empty();
    }

    Seat seat = seatRepository.findById(seatUpdateDTO.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found"));

    // 좌석에 회원 정보와 온라인 상태 설정
    seat.setMember(member);
    seat.setIsOnline(true);
    Seat savedSeat = seatRepository.save(seat);

    return Optional.of(convertSeatToDTO(savedSeat));
  }

  public SeatListDTO removeMemberFromSeat(Long seatId) {
    Seat seat = seatRepository.findById(seatId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found"));

    // 좌석에서 회원 정보와 온라인 상태 해제
    seat.setMember(null);
    seat.setIsOnline(false);
    return convertSeatToDTO(seatRepository.save(seat));
  }

  @Transactional
  public SeatListDTO assignTeacherSeat(String memberId) {
    Member member = memberRepository.findByMemberId(memberId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

    if (!"ROLE_TEACHER".equals(member.getMemberType().getType())) {
      throw new IllegalStateException("강사만 사용 가능합니다.");
    }

    return assignOrUpdateTeacherSeat(member);
  }

  private SeatListDTO assignOrUpdateTeacherSeat(Member teacher) {
    Seat teacherSeat = seatRepository.findBySeatNumber(TEACHER_SEAT_NUMBER)
        .orElseGet(() -> {
          Seat newSeat = new Seat();
          newSeat.setSeatNumber(TEACHER_SEAT_NUMBER);
          newSeat.setIsExist(true);
          newSeat.setIsOnline(false);
          return seatRepository.save(newSeat);
        });

    // 기존 강사의 좌석 재설정
    if (teacherSeat.getMember() != null) {
      Member oldTeacher = teacherSeat.getMember();
      oldTeacher.setMemberType(memberTypeRepository.findByType("ROLE_STUDENT")
          .orElseThrow(() -> new IllegalArgumentException("ROLE_STUDENT 타입이 없습니다.")));
      memberRepository.save(oldTeacher);
      teacherSeat.setMember(null);
    }

    teacherSeat.setMember(teacher);
    Seat savedTeacherSeat = seatRepository.save(teacherSeat);
    return convertSeatToDTO(savedTeacherSeat);
  }

  @Transactional
  public List<SeatListDTO> registerSeats(int seatCount, Long courseId) {
    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new EntityNotFoundException("Course not found"));

    List<Seat> existingSeats = seatRepository.findByCourse(course);
    seatRepository.deleteAll(existingSeats);  // 기존 좌석 삭제

    List<SeatListDTO> createdSeats = new ArrayList<>();
    for (int i = 1; i <= seatCount; i++) {
      Seat seat = new Seat();
      seat.setSeatNumber(String.valueOf(i));
      seat.setIsExist(true);
      seat.setIsOnline(false);
      seat.setCourse(course);
      Seat savedSeat = seatRepository.save(seat);
      createdSeats.add(convertSeatToDTO(savedSeat));
    }

    return createdSeats;
  }

  @Transactional
  public void deleteAllSeatsByCourse(Long courseId) {
    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new EntityNotFoundException("Course not found"));

    List<Seat> seats = seatRepository.findByCourse(course);
    seatRepository.deleteAll(seats);  // 좌석 삭제
  }

  public List<SeatListDTO> getSeatsByCourse(Long courseId) {
    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new NotFoundException("Course not found"));

    List<Seat> seats = seatRepository.findByCourse(course);
    return seats.stream()
        .map(this::convertSeatToDTO)
        .collect(Collectors.toList());
  }

  public Optional<SeatListDTO> getSeatStatusByMemberId(Long memberId) {
    Optional<Seat> seat = seatRepository.findByMemberId(memberId);
    if (seat.isPresent()){
      return seat.map(this::convertSeatToDTO);
    }else{
      return Optional.empty();
    }
  }


  public void setSeatOnlineForCurrentUser(Member member) {
    Optional<Seat> seat = seatRepository.findByMember(member);

    seat.ifPresent(updateSeat -> {
      updateSeat.setIsOnline(true);
      seatRepository.save(updateSeat);  // 변경사항 저장
    });
  }

  public void setSeatOfflineForCurrentUser(Member member) {
    Optional<Seat> seat = seatRepository.findByMember(member);

    if (seat.isPresent()) {
      Seat updateSeat = seat.get();
      updateSeat.setIsOnline(false);  // 좌석 상태를 오프라인으로 설정
      seatRepository.save(updateSeat);  // 변경사항 저장
    }
  }

  private SeatListDTO convertSeatToDTO(Seat seat) {
    Member member = seat.getMember();
    MemberDTO memberDTO = null;

    if (member != null) {
      memberDTO = new MemberDTO(
          member.getId(),
          member.getMemberId(),
          member.getName(),
          member.getEmail(),
          member.getPhone(),
          member.getMemberType(),
          member.getAvatarImage() != null ? member.getAvatarImage().getAvatarImageUrl() : null,
          member.getGitUrl(),
          member.getBio()
      );
    }
    return new SeatListDTO(seat.getId(), seat.getSeatNumber(), seat.getIsExist(), seat.getIsOnline(), memberDTO);
  }

  public void updateOnlineStatus(Long seatId, boolean isOnline) {
    Seat seat = seatRepository.findById(seatId)
        .orElseThrow(() -> new EntityNotFoundException("Seat not found with id: " + seatId));
    seat.setIsOnline(isOnline);
    seatRepository.save(seat);
  }
}