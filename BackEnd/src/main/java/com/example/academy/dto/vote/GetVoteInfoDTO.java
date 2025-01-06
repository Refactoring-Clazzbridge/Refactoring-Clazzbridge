package com.example.academy.dto.vote;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetVoteInfoDTO {

  private String voteTitle;
  private String description;
  private LocalDateTime endDate;
  private Boolean isExpired;

  // ex) 50.0%
  private String progressRate;
  // ex) 10/20
  private String currentParticipants;
  private String result;

  private List<VoteOptionInfo> voteOptionInfoList;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class VoteOptionInfo {

    private String optionText;
    private String occupancyRate;
    private String votes;
    private Long rank;
  }
}
