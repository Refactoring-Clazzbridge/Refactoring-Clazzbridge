package com.example.academy.dto.course;

import lombok.Data;

@Data
public class CourseTitleDTO {

  private String courseTitle;

  public CourseTitleDTO(String courseTitle) {
    this.courseTitle = courseTitle;
  }
}
