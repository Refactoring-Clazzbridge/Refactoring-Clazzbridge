package com.example.academy.dto.course;

import java.time.LocalDate;
import lombok.Data;

@Data
public class GetCourseDTO {

  private Long id;
  private String instructor;
  private String classroomName;
  private String title;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private String layoutImageUrl;

  public GetCourseDTO() {
  }

  public GetCourseDTO(Long id, String instructor, String classroomName, String title,
      String description, LocalDate startDate, LocalDate endDate, String layoutImageUrl) {
    this.id = id;
    this.instructor = instructor;
    this.classroomName = classroomName;
    this.title = title;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.layoutImageUrl = layoutImageUrl;
  }
}
