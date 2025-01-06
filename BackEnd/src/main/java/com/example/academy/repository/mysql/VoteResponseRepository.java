package com.example.academy.repository.mysql;

import com.example.academy.domain.StudentCourse;
import com.example.academy.domain.Vote;
import com.example.academy.domain.VoteResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteResponseRepository extends JpaRepository<VoteResponse, Long> {

  List<VoteResponse> findByStudentCourse(StudentCourse studentCourse); // 투표로 조회

  List<VoteResponse> findByVote(Vote vote);

  List<VoteResponse> findByVoteOptionId(Long id);

  VoteResponse findByStudentCourseAndVote(StudentCourse studentCourse, Vote vote);

  List<VoteResponse> findAll();

}
