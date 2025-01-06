package com.example.academy.dto.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectCourseDTO {

  private Long id;
  private String courseTitle;

  public SelectCourseDTO(Long id, String courseTitle) {
    this.id = id;
    this.courseTitle = courseTitle;
  }
}
