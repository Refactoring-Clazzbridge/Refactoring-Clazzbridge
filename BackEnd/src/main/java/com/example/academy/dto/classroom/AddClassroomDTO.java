package com.example.academy.dto.classroom;

import lombok.Data;

@Data
public class AddClassroomDTO {

  private String name; //강의실 명


  public AddClassroomDTO(String name) {
    this.name = name;
  }
  public AddClassroomDTO() {
  }

}
