package com.example.academy.service;


import com.example.academy.domain.Member;
import com.example.academy.dto.member.MemberProfileUpdateDTO;
import com.example.academy.repository.mysql.MemberRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public Optional<Member> getUserProfileById(Long id) {
    return memberRepository.findById(id);
  }

  public void createOrUpdateUserProfile(MemberProfileUpdateDTO updateDTO) {
    // 비밀번호가 null이 아니고 변경된 경우에만 암호화
    Member member = memberRepository.getById(updateDTO.getId());

    if (updateDTO.getPassword() != null && !updateDTO.getPassword().isEmpty()) {
      // 비밀번호 암호화
      String encodedPassword = passwordEncoder.encode(updateDTO.getPassword());
      member.setPassword(encodedPassword);
    }
    member.setEmail(updateDTO.getEmail());
    member.setBio(updateDTO.getBio());
    member.setGitUrl(updateDTO.getGitUrl());
    member.setPhone(updateDTO.getPhone());

    memberRepository.save(member);
  }

  // 비밀번호를 확인하는 메서드
  public boolean checkPassword(Long userId, String inputPassword) {
    Optional<Member> userOptional = getUserProfileById(userId);

    if (userOptional.isPresent()) {
      Member user = userOptional.get();

      return passwordEncoder.matches(inputPassword, user.getPassword());
    }

    return false; // 사용자가 없을 경우 false 반환
  }
}