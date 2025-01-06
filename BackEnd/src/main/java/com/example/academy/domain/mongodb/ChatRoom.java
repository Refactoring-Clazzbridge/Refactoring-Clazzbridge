package com.example.academy.domain.mongodb;

import java.util.List;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {

  @Id
  private String id;

  private String name;
  private List<String> userIds;

}
