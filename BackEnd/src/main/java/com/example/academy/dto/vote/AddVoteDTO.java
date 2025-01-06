package com.example.academy.dto.vote;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class AddVoteDTO {

  private String title;
  private String description;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime startDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime endDate;
  private List<String> optionText;

  public AddVoteDTO(String title, String description, LocalDateTime startDate,
      LocalDateTime endDate, List<String> optionText) {
    this.title = title;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.optionText = optionText;
  }
}
