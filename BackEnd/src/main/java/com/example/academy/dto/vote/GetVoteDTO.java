package com.example.academy.dto.vote;

import com.example.academy.domain.VoteOption;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class GetVoteDTO {

  private Long id;
  private String courseTitle;
  private String title;
  private String description;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime startDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime endDate;
  private Boolean isExpired;
  private List<String> optionText;

  public GetVoteDTO() {
  }

  public GetVoteDTO(Long id, String courseTitle, String title, String description,
      LocalDateTime startDate, LocalDateTime endDate, Boolean isExpired, List<String> optionText) {
    this.id = id;
    this.courseTitle = courseTitle;
    this.title = title;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.isExpired = isExpired;
    this.optionText = optionText;
  }
}
