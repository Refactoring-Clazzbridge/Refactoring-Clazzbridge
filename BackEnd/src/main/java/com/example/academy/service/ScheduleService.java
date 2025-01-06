package com.example.academy.service;

import com.example.academy.domain.Course;
import com.example.academy.domain.Schedule;
import com.example.academy.dto.schedule.ScheduleAddDTO;
import com.example.academy.dto.schedule.ScheduleListDTO;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.ScheduleRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;
  private final CourseRepository courseRepository;


  public ScheduleService(ScheduleRepository scheduleRepository,
      CourseRepository courseRepository) {
    this.scheduleRepository = scheduleRepository;
    this.courseRepository = courseRepository;
  }

  public List<ScheduleListDTO> getScheduleAll() {
    List<Schedule> schedules = scheduleRepository.findAll();

    // Schedule 리스트를 ScheduleAddDTO 리스트로 변환
    return schedules.stream().map(schedule -> {
      // 강의실 정보 가져오기
      Course course = schedule.getCourse();
      Long courseId = (course != null) ? course.getId() : null; // course가 null일 때 null 처리

      // courseId가 null인 경우 "Unknown" 처리
      String courseName = (courseId != null) ? courseRepository.findById(courseId)
          .map(Course::getTitle)
          .orElse("전체 일정")
          : "전체 일정";

      // ScheduleAddDTO로 변환
      ScheduleListDTO scheduleListDTO = new ScheduleListDTO();
      scheduleListDTO.setId(schedule.getId());
      scheduleListDTO.setCourseTitle(courseName);
      scheduleListDTO.setEventTitle(schedule.getEventTitle());
      scheduleListDTO.setStartDate(schedule.getStartDate());
      scheduleListDTO.setEndDate(schedule.getEndDate());
      scheduleListDTO.setDescription(schedule.getDescription());

      return scheduleListDTO;
    }).collect(Collectors.toList());
  }



  public Optional<ScheduleListDTO> getScheduleById(Long id) {
    Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);

    if (!scheduleOptional.isPresent()) {
      return Optional.empty();
    }

    Schedule schedule = scheduleOptional.get();

    Long courseId = schedule.getCourse().getId();
    Optional<Course> courseOptional = courseRepository.findById(courseId);

    if (!courseOptional.isPresent()) {
      return Optional.empty();
    }

    Course course = courseOptional.get();
    String courseTitle = course.getTitle(); // 강의실 명 추출

    ScheduleListDTO scheduleListDTO = new ScheduleListDTO();
    scheduleListDTO.setId(id);
    scheduleListDTO.setCourseTitle(courseTitle);
    scheduleListDTO.setEventTitle(schedule.getEventTitle());
    scheduleListDTO.setStartDate(schedule.getStartDate());
    scheduleListDTO.setEndDate(schedule.getEndDate());
    scheduleListDTO.setDescription(schedule.getDescription());

    return Optional.of(scheduleListDTO);
  }


  public void addSchedule(ScheduleAddDTO schedule) {

    String courseTitle = schedule.getCourseTitle();
    Schedule data = new Schedule();
    Optional<Course> optionalCourse = courseRepository.findByTitle(courseTitle);
    // courseName 찾기
    if (optionalCourse.isEmpty()) {
      if (courseTitle.equals("전체 일정")) {
        // 전체 일정의 경우 새로운 ID를 설정
        System.out.println(scheduleRepository.findLastId());
        data.setId(scheduleRepository.findLastId() + 1000L);
      } else {
        // courseName를 찾지 못하면 예외 발생
        throw new IllegalArgumentException("CourseName not found: " + courseTitle);
      }
    } else {
      // Course가 존재할 경우에만 CourseId 설정
      Course courseId = optionalCourse.get(); // Optional 값 추출
      data.setCourse(courseId);
    }

    String eventTitle = schedule.getEventTitle(); // 일정 제목
    LocalDateTime startDate = schedule.getStartDate(); // 일정 시작 날짜
    LocalDateTime endDate = schedule.getEndDate(); // 일정 종료 날짜
    String description = schedule.getDescription(); // 일정 설명
    if (endDate.isBefore(startDate)) {
      throw new IllegalArgumentException("종료 날짜는 시작 날짜보다 이후여야 합니다.");
    }

    data.setEventTitle(eventTitle);
    data.setStartDate(startDate);
    data.setEndDate(endDate);
    data.setDescription(description);

    scheduleRepository.save(data);
  }

  public void updateSchedule(ScheduleListDTO schedule) {
    String courseTitle = schedule.getCourseTitle();
    Course course = null;
    // classroomRepository에서 courseName 강의실 조회
    Optional<Course> courseOptional = courseRepository.findByTitle(courseTitle);
    if (!courseOptional.isPresent() && !courseTitle.equals("전체 일정")) {
      throw new RuntimeException("해당 이름의 강의실을 찾을 수 없습니다.");
    } else if (courseTitle.equals("전체 일정")) {

    } else {
      course = courseOptional.get(); // 강의 ID 추출
    }

    String eventTitle = schedule.getEventTitle(); // 일정 제목
    LocalDateTime startDate = schedule.getStartDate(); // 일정 시작 날짜
    LocalDateTime endDate = schedule.getEndDate(); // 일정 종료 날짜
    String description = schedule.getDescription(); // 일정 설명
    if (endDate.isBefore(startDate)) {
      throw new IllegalArgumentException("종료 날짜는 시작 날짜보다 이후여야 합니다.");
    }

    Long scheduleId = schedule.getId(); // 일정 번호

    // scheduleRepository에서 일정 조회
    Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
    if (!scheduleOptional.isPresent()) {
      throw new RuntimeException("해당 ID의 일정을 찾을 수 없습니다.");
    }

    Schedule data = scheduleOptional.get(); // Optional에서 Schedule 추출

    // Schedule 데이터를 업데이트
    data.setCourse(course);
    data.setEventTitle(eventTitle);
    data.setStartDate(startDate);
    data.setEndDate(endDate);
    data.setDescription(description);

    // 변경된 데이터를 저장
    scheduleRepository.save(data);
  }

  public void deleteSchedule(Long id) {
    scheduleRepository.deleteById(id);

  }
}
