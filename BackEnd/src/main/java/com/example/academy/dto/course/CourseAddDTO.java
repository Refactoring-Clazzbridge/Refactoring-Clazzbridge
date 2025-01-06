package com.example.academy.dto.course;

import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CourseAddDTO {

  private String classroomName;
  private String title;
  private String description;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;
  private String layoutImageUrl;

  public CourseAddDTO( String classroomName, String title, String description,
      LocalDate startDate, LocalDate endDate, String layoutImageUrl) {
    this.classroomName = classroomName;
    this.title = title;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.layoutImageUrl = layoutImageUrl;
  }
}
