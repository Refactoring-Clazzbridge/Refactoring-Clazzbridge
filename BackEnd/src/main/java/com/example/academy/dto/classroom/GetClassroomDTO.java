package com.example.academy.dto.classroom;


import lombok.Data;

@Data
public class GetClassroomDTO {


  private Long id;

  private String name; //강의실 명

  private boolean isOccupied; //점유 여부

  public GetClassroomDTO(Long id, String name, boolean isOccupied) {
    this.id = id;
    this.name = name;
    this.isOccupied = isOccupied;
  }
}
