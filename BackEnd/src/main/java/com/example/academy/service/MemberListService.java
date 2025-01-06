package com.example.academy.service;


import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import com.example.academy.domain.StudentCourse;
import com.example.academy.dto.member.GetChatDetailMemberDTO;
import com.example.academy.dto.member.GetDetailMemberDTO;
import com.example.academy.dto.member.GetMemberDTO;
import com.example.academy.dto.member.GetMemberForChatDTO;
import com.example.academy.dto.vote.GetAllVoteDTO;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.MemberRepository;
import com.example.academy.repository.mysql.StudentCourseRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberListService {

  @Autowired
  private final MemberRepository memberRepository;
  private final StudentCourseRepository studentCourseRepository;
  private final CourseRepository courseRepository;


  public MemberListService(MemberRepository memberRepository,
      StudentCourseRepository studentCourseRepository, CourseRepository courseRepository) {
    this.memberRepository = memberRepository;
    this.studentCourseRepository = studentCourseRepository;
    this.courseRepository = courseRepository;
  }

  public String getCheckRole(Long id) {
    String type = memberRepository.findById(id).get().getMemberType().getType();
    if (type.equals("ROLE_STUDENT")) {
      StudentCourse studentCourse = studentCourseRepository.findByStudent(
          memberRepository.findById(id).get());
      return studentCourse.getCourse().getTitle();
    } else {
      List<Course> courseTitle = courseRepository.findByInstructor(
          memberRepository.findById(id).get());
      return courseTitle.get(0).getTitle();
    }
  }

  public GetDetailMemberDTO getMemberWithCourseInfo(Long memberId) {
    // Member 조회
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new NoSuchElementException("Member not found with ID: " + memberId));

    String courseTitle = "";
    if (member.getMemberType().getType().equals("ROLE_STUDENT")) {
      // Member가 수강한 StudentCourse 조회
      StudentCourse studentCourse = studentCourseRepository.findByStudent(member);
      courseTitle =
          studentCourse.getCourse().getTitle() != null ? studentCourse.getCourse().getTitle() : "";
    } else if (member.getMemberType().getType().equals("ROLE_TEACHER")) {
      List<Course> courses = courseRepository.findByInstructor(member);
      courseTitle = courses.stream()
          .findFirst()  // 여러 개일 경우 첫 번째 코스를 선택
          .map(Course -> Course.getTitle())  // Course 이름 가져오기
          .orElse("");
    } else {
      // 멤버 타입이 STUDENT 또는 TEACHER가 아니면 예외 발생
      throw new UnsupportedOperationException("Unsupported member type for ID: " + memberId);
    }

    // GetMemberDTO 생성 및 값 설정
    GetDetailMemberDTO getDetailMemberDTO = new GetDetailMemberDTO(
        member.getId(),
        member.getMemberId(),
        member.getPassword(),
        member.getName(),
        member.getEmail(),
        member.getPhone(),
        member.getMemberType().getType(),
        member.getAvatarImage(),
        courseTitle
    );

    return getDetailMemberDTO;
  }



  public GetChatDetailMemberDTO getMemberForChat(Long memberId) {
    // Member 조회
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new NoSuchElementException("Member not found with ID: " + memberId));

    String memberName = member.getName();
    String avatarImageUrl = member.getAvatarImage().getAvatarImageUrl();

    return new GetChatDetailMemberDTO(memberName,
        avatarImageUrl);
  }


  public List<GetMemberDTO> getAllMembersWithCourses() {
    List<Member> members = memberRepository.findAll(); // 전체 멤버 조회
    List<GetMemberDTO> memberDTOs = new ArrayList<>();
    String courseTitle;
    // 각 멤버에 대해 코스 정보 조회 및 DTO로 변환
    for (Member member : members) {
      if (member.getMemberType().getType().equals("ROLE_STUDENT")) {
        StudentCourse studentCourse = studentCourseRepository.findByStudent(member);

        // 수강 코스의 이름을 가져오거나, 없으면 빈 문자열 반환
        courseTitle =
            studentCourse.getCourse().getTitle() != null ? studentCourse.getCourse().getTitle()
                : "";
      } else if (member.getMemberType().getType().equals("ROLE_TEACHER")) {
        List<Course> courses = courseRepository.findByInstructor(member);

        // 첫 번째 수강 코스의 이름을 가져오거나, 없으면 빈 문자열 반환
        courseTitle = courses.stream()
            .findFirst()  // 여러 개일 경우 첫 번째 코스를 선택
            .map(studentCourse -> studentCourse.getTitle())
            .orElse("");
      } else {
        continue;
      }
      // GetMemberDTO 생성 및 값 설정
      GetMemberDTO dto = new GetMemberDTO(
          member.getId(),
          member.getMemberId(),
          member.getPassword(),
          member.getName(),
          member.getEmail(),
          member.getPhone(),
          member.getMemberType().getType(),
          member.getAvatarImage(),
          courseTitle
      );

      // DTO 리스트에 추가
      memberDTOs.add(dto);
    }

    return memberDTOs.stream().sorted(Comparator.comparing(GetMemberDTO::getId).reversed()).toList();
  }

  public List<GetMemberForChatDTO> getAllMembers() {
    List<Member> members = memberRepository.findAll(); // 전체 멤버 조회
    List<GetMemberForChatDTO> memberDTOs = new ArrayList<>();

    // 각 멤버에 대해 코스 정보 조회 및 DTO로 변환
    for (Member member : members) {
      Long courseId = null;
      if (Objects.equals(member.getMemberType().getType(), "ROLE_STUDENT")) {
        StudentCourse studentCourse = studentCourseRepository.findByStudentId(member.getId());
        courseId = studentCourse.getId();
      } else if (Objects.equals(member.getMemberType().getType(), "ROLE_TEACHER")) {
        if (courseRepository.findByInstructorId(member.getId()).isPresent()) {
          Course course = courseRepository.findByInstructorId(member.getId()).get();
          courseId = course.getId();
        } else {
          courseId = 0L;
        }
      } else if (Objects.equals(member.getMemberType().getType(), "ROLE_ADMIN")) {
        courseId = 0L;
      }

      // GetMemberForChatDTO 생성 및 값 설정
      GetMemberForChatDTO dto = new GetMemberForChatDTO(
          member.getId(),
          member.getMemberId(),
          member.getName(),
          member.getAvatarImage().getAvatarImageUrl(),
          member.getMemberType().getType(),
          courseId.toString()
      );

      // DTO 리스트에 추가
      memberDTOs.add(dto);
    }

    return memberDTOs;
  }

  public void deleteMember(Long id) {
    memberRepository.deleteById(id);
  }
}
