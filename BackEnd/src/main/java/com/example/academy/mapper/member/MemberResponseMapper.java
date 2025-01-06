package com.example.academy.mapper.member;

import com.example.academy.domain.Member;
import com.example.academy.domain.StudentCourse;
import com.example.academy.dto.auth.AuthResponseDTO;
import com.example.academy.dto.member.StudentDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MemberResponseMapper {

    public static AuthResponseDTO toDTO(Member member) {
        return AuthResponseDTO.builder()
            .id(member.getId())
            .email(member.getEmail())
            .name(member.getName())
            .memberType(member.getMemberType().getType())
            .gitUrl(member.getGitUrl())
            .profileImageUrl(member.getAvatarImage().getAvatarImageUrl())
            .build();
    }

    public static StudentDTO toStudentDTO(Member member) {
        return StudentDTO.builder()
            .avatarImage(member.getAvatarImage().getAvatarImageUrl())
            .name(member.getName())
            .build();
    }

    public static List<StudentDTO> toStudentDTOList(List<Member> members) {
        return members.stream()
            .map(member -> toStudentDTO(member))
            .collect(Collectors.toList());
    }


}