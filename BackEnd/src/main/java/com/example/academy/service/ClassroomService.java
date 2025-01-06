package com.example.academy.service;

import com.example.academy.domain.Classroom;
import com.example.academy.domain.Course;
import com.example.academy.dto.classroom.AddClassroomDTO;
import com.example.academy.dto.classroom.ClassroomNameDTO;
import com.example.academy.dto.classroom.GetClassroomDTO;
import com.example.academy.dto.classroom.UpdateClassroomDTO;
import com.example.academy.repository.mysql.ClassroomRepository;
import com.example.academy.repository.mysql.CourseRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {


  private final ClassroomRepository classroomRepository;
  private final CourseRepository courseRepository;

  public ClassroomService(ClassroomRepository classroomRepository,
      CourseRepository courseRepository) {
    this.classroomRepository = classroomRepository;
    this.courseRepository = courseRepository;
  }

  public void addClassroom(AddClassroomDTO addClassroomDTO) {
    String name =  addClassroomDTO.getName();
    Classroom classroom = new Classroom();
    classroom.setName(name);
    classroom.setIsOccupied(false);

    classroomRepository.save(classroom);
  }

  public Classroom updateClassroom(UpdateClassroomDTO updateClassroomDTO) {

    Long id = updateClassroomDTO.getId();
    String name = updateClassroomDTO.getName();


    Optional<Classroom> classroom = classroomRepository.findById(id);
    if (classroom.isEmpty()) {
      throw new NoSuchElementException("해당 강의실이 없습니다.");
    }
    Classroom newClassroom = classroom.get();

    newClassroom.setId(id);
    newClassroom.setName(name);

    return classroomRepository.save(newClassroom);
  }

  public List<ClassroomNameDTO> getClassroomName(){
    List<ClassroomNameDTO> classroomNameDTOS =  classroomRepository.findAll().stream()
        .map(classroom -> new ClassroomNameDTO(classroom.getName())) // ClassroomNameDTO로 변환
        .collect(Collectors.toList()); // List로 수집
    return classroomNameDTOS;
  }

  public List<Classroom> getAllClassroom(){
    LocalDate now = LocalDate.now();
    List<Classroom> classrooms = classroomRepository.findAll();
    List<Course> courses =   courseRepository.findAll();
    for (Classroom classroom : classrooms) {
      classroom.setIsOccupied(false);
      classroomRepository.save(classroom);
    }

    for (Course cours2 : courses) {
      if (now.isBefore(cours2.getEndDate()) && now.isAfter(cours2.getStartDate()) ) {
        // 현재 < 종료,  현재 > 시작
        cours2.getClassroom().setIsOccupied(true);
      }
      courseRepository.save(cours2);
    }
    return classroomRepository.findAll();
  }

  public Optional<Classroom> getClassroom(Long id)  {
    Optional<Classroom> classroom =classroomRepository.findById(id);
    if (!classroom.isPresent()) {
      return Optional.empty();
    }
    return classroomRepository.findById(id);
  }


  public void deleteClassroom(Long id) throws Exception{
    try {
      classroomRepository.deleteById(id);
    } catch (Exception e) {
      throw new Exception("잘못된 삭제입니다.");
    }
  }
}
