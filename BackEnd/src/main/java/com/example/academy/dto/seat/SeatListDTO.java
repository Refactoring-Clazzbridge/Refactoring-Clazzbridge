package com.example.academy.dto.seat;

import com.example.academy.dto.member.MemberDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SeatListDTO {

  private Long id;
  private String seatNumber;
  private Boolean isExist = true;
  private Boolean isOnline = false;
  private MemberDTO member;

  public SeatListDTO() {
  }

  public SeatListDTO(Long id, String seatNumber, Boolean isExist, Boolean isOnline, MemberDTO member) {
    this.id = id;
    this.seatNumber = seatNumber;
    this.isExist = isExist;
    this.isOnline = isOnline;
    this.member = member;
  }
}
