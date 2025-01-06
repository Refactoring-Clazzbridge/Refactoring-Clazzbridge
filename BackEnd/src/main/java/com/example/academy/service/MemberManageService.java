package com.example.academy.service;


import com.example.academy.domain.AvatarImage;
import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import com.example.academy.domain.StudentCourse;
import com.example.academy.dto.member.MemberSignUpDTO;
import com.example.academy.dto.member.MemberUpdateDTO;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.MemberRepository;
import com.example.academy.repository.mysql.MemberTypeRepositoy;
import com.example.academy.repository.mysql.ProfileImageRepository;
import com.example.academy.repository.mysql.StudentCourseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberManageService {

  private final MemberRepository memberRepository;
  private final ProfileImageRepository profileImageRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final CourseRepository courseRepository;
  private final StudentCourseRepository studentCourseRepository;
  private final MemberTypeRepositoy memberTypeRepositoy;

  public MemberManageService(MemberRepository memberRepository,
      ProfileImageRepository profileImageRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
      CourseRepository courseRepository, StudentCourseRepository studentCourseRepository,
      MemberTypeRepositoy memberTypeRepositoy) {
    this.memberRepository = memberRepository;
    this.profileImageRepository = profileImageRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.courseRepository = courseRepository;
    this.studentCourseRepository = studentCourseRepository;
    this.memberTypeRepositoy = memberTypeRepositoy;
  }

  public ResponseEntity<String> signUp(MemberSignUpDTO memberSignUpDTO) {

    String memberId = memberSignUpDTO.getMemberId();
    String password = memberSignUpDTO.getPassword();
    String name = memberSignUpDTO.getName();
    String email = memberSignUpDTO.getEmail();
    String phone = memberSignUpDTO.getPhone();
    String memberType = memberSignUpDTO.getMemberType();
    String title = memberSignUpDTO.getCourseTitle();

    boolean isExistMember = memberRepository.existsByMemberId(memberId);
    boolean isExistEmail = memberRepository.existsByEmail(email);
    boolean isExistCourseTitle = courseRepository.existsByTitle(title);

    if (isExistMember || isExistEmail || !isExistCourseTitle) {
      String errorMessage = "";
      if (isExistMember) {
        errorMessage += "memberId 중복. ";
      }
      if (isExistEmail) {
        errorMessage += "email 중복. ";
      }
      if (!isExistCourseTitle) {
        errorMessage += "강의명 없음. ";
      }
      System.out.println(errorMessage);
      throw new DataIntegrityViolationException(errorMessage);
    }

    Member data = new Member();

    data.setMemberId(memberId);
    data.setPassword(bCryptPasswordEncoder.encode(password));
    data.setName(name);
    data.setEmail(email);
    data.setPhone(phone);
    data.setMemberType(memberTypeRepositoy.findByType(memberType).get());
    // ProfileImage 테이블의 총 레코드 수 가져오기
    long count = profileImageRepository.count();

    // 1부터 count까지의 랜덤 ID 생성
    int randomId = (int) (Math.random() * count) + 1;

    // 랜덤 ID에 해당하는 ProfileImage 가져오기
    AvatarImage avatarImage = profileImageRepository.findById((long) randomId)
        .orElseThrow(() -> new RuntimeException("AvtarImage not found"));

    // member 객체에 profileImage 설정
    data.setAvatarImage(avatarImage);

    Member saveMember = memberRepository.save(data);

    Long id = saveMember.getId();

    // 과정명 추가
    // 수강생은 student_course에 데이터 추가
    if (memberType.equals("ROLE_STUDENT")) {
      Optional<Course> course = courseRepository.findByTitle(title);
      StudentCourse studentCourse = new StudentCourse();

      studentCourse.setCourse(course.get());
      studentCourse.setStudent(saveMember);

      studentCourseRepository.save(studentCourse);

      // 강사는 Course에 데이터 업데이트
    } else {
      Optional<Course> course = courseRepository.findByTitle(title);
      course.get().setInstructor(saveMember);
      courseRepository.save(course.get());
    }

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body("회원가입 성공"); // 성공 메시지 반환
  }

  public ResponseEntity<String> updateMember(MemberUpdateDTO memberUpdateDTO) {
    // 기존 회원 데이터를 id로 조회
    Member member = memberRepository.findById(memberUpdateDTO.getId())
        .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));

    // 본인을 제외한 memberId와 email 중복 체크
    boolean isExistMember = memberRepository.existsByMemberIdAndIdNot(
        memberUpdateDTO.getMemberId(), member.getId());

    //memberUpdateDTO.getEmail()을 갖는 값이 없나 찾는데 meber.getId를 갖는 레코드는 제외
    boolean isExistEmail = memberRepository.existsByEmailAndIdNot(memberUpdateDTO.getEmail(),
        member.getId());

    if (isExistMember || isExistEmail) {
      String errorMessage = "";
      if (isExistMember) {
        errorMessage += "memberId 중복. ";
      }
      if (isExistEmail) {
        errorMessage += "email 중복. ";
      }

      System.out.println(errorMessage);
      throw new DataIntegrityViolationException(errorMessage);
    }
    // DTO의 값으로 기존 데이터를 업데이트
    member.setMemberId(memberUpdateDTO.getMemberId());
    if (!memberUpdateDTO.getPassword().equals("") && !memberUpdateDTO.getPassword().isEmpty()) {
      member.setPassword(bCryptPasswordEncoder.encode(memberUpdateDTO.getPassword())); // 비밀번호 인코딩
    }
    member.setName(memberUpdateDTO.getName());
    member.setEmail(memberUpdateDTO.getEmail());
    member.setPhone(memberUpdateDTO.getPhone());

    member.setMemberType(memberTypeRepositoy.findByType(memberUpdateDTO.getMemberType()).get());

    String title = memberUpdateDTO.getCourseTitle();
    // 과정명 업데이트 (수강생인 경우)
    if (memberUpdateDTO.getMemberType().equals("ROLE_STUDENT")) {
      StudentCourse studentCourse = studentCourseRepository.findByStudent(member);
      if (studentCourse != null) {
        // 학생이 수강하는 과정이 있을 경우 업데이트
        Optional<Course> course = courseRepository.findByTitle(title); // 입력한 과정명 조회
        Course course1 = course.get(); // Optional 값 갖고오기
        studentCourse.setCourse(course1); // 스튜던트 코스 변경
        studentCourseRepository.save(studentCourse); // 업데이트된 과정 저장
      } else {
        throw new RuntimeException("해당 과정명을 찾을 수 없습니다.");
      }
    } else if (memberUpdateDTO.getMemberType().equals("ROLE_TEACHER")) {
      // 강사인 경우 과정 정보 업데이트
      Optional<Course> course = courseRepository.findByTitle(title); // 입력한 강의 정보
      if (course.isPresent() && course.get().getInstructor() != null) {
        if (course.get().getInstructor().getMemberId() != memberUpdateDTO.getMemberId()) {
          throw new RuntimeException("해당 과정명은 이미 배정된 강사가 있습니다.");
        }
      }

      if (course.isPresent()) { // 코스 값이 존재하면 true
        List<Course> course1 = courseRepository.findByInstructor(member);

        if (!course1.isEmpty()) { // 기존 강의가 존재하는 경우
          Course beforeCourse = course1.get(0); // 기존 강사의 강의 정보
          Course afterCourse = course.get(); // 입력한 강의 정보

          if (beforeCourse.getTitle().equals(afterCourse.getTitle())) {
            System.out.println("동일 강의");
          } else {
            afterCourse.setInstructor(member); // 강사 정보 업데이트
            beforeCourse.setInstructor(null); // 기존 강의에서 강사 정보 제거
            courseRepository.save(beforeCourse);
            courseRepository.save(afterCourse); // 업데이트된 과정 저장
          }
        } else {
          // 기존 강의가 없을 때 강사 정보만 업데이트
          Course afterCourse = course.get(); // 입력한 강의 정보
          afterCourse.setInstructor(member);
          courseRepository.save(afterCourse);
        }
      } else {
        throw new RuntimeException("해당 과정명을 찾을 수 없습니다.");
      }

    }

    // 업데이트된 회원 정보 저장
    memberRepository.save(member);

    return ResponseEntity.status(HttpStatus.OK).body("회원 정보 업데이트 성공");
  }


}










