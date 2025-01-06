package com.example.academy.dto.classroom;


import lombok.Data;

@Data
public class ClassroomNameDTO {
  private String classroomName;

  public ClassroomNameDTO(String classroomName) {
    this.classroomName = classroomName;
  }
}
