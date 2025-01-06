package com.example.academy.service;

import com.example.academy.domain.Member;
import com.example.academy.repository.mysql.MemberRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

  private final SeatService seatService;
  private final MemberRepository memberRepository;

  public LogoutService(SeatService seatService, MemberRepository memberRepository) {
    this.seatService = seatService;
    this.memberRepository = memberRepository;
  }

  public void logout(Long memberId) {
    Optional<Member> member = memberRepository.findById(memberId); // findById로 Long ID 검색

    // 로그 추가: 해당 사용자가 존재하는지 확인
    if (member.isPresent()) {
      System.out.println("로그아웃 사용자: " + memberId);
      seatService.setSeatOfflineForCurrentUser(member.get());
    } else {
      System.out.println("사용자를 찾을 수 없습니다: " + memberId);
    }
  }
}
