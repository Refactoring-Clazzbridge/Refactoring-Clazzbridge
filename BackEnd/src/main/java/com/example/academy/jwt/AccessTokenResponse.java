package com.example.academy.jwt;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccessTokenResponse {


  private String accessToken;

  public AccessTokenResponse(String accessToken) {
    this.accessToken = accessToken;
  }

}
