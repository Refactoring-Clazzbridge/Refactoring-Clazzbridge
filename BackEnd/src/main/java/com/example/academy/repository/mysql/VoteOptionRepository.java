package com.example.academy.repository.mysql;

import com.example.academy.domain.Vote;
import com.example.academy.domain.VoteOption;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteOptionRepository extends JpaRepository<VoteOption, Long> {

  Optional<VoteOption> findByOptionText(String optionText); // 투표로 조회

  List<VoteOption> findByVote(Vote vote);
  Optional<VoteOption> findByVoteIdAndOptionText(Long voteId, String optionText);

  boolean existsByOptionText(String optionText);

  List<VoteOption> findAll();

}
