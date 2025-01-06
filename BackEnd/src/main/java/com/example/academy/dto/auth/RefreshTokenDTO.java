package com.example.academy.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenDTO {

  String value;

  public RefreshTokenDTO() {
  }

  public RefreshTokenDTO(String value) {
    this.value = value;
  }
}
