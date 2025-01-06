package com.example.academy.service;

import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import com.example.academy.domain.StudentCourse;
import com.example.academy.domain.Vote;
import com.example.academy.domain.VoteOption;
import com.example.academy.domain.VoteResponse;
import com.example.academy.dto.member.CustomUserDetails;
import com.example.academy.dto.vote.AddVoteDTO;
import com.example.academy.dto.vote.DoVoteDTO;
import com.example.academy.dto.vote.GetAllVoteDTO;
import com.example.academy.dto.vote.GetVoteDTO;
import com.example.academy.dto.vote.GetVoteInfoDTO;
import com.example.academy.dto.vote.GetVoteInfoDTO.VoteOptionInfo;
import com.example.academy.enums.MemberRole;
import com.example.academy.exception.common.NotFoundException;
import com.example.academy.exception.post.PostBadRequestException;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.MemberRepository;
import com.example.academy.repository.mysql.StudentCourseRepository;
import com.example.academy.repository.mysql.VoteOptionRepository;
import com.example.academy.repository.mysql.VoteRepository;
import com.example.academy.repository.mysql.VoteResponseRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javax.persistence.NonUniqueResultException;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

  private final VoteRepository voteRepository;
  private final CourseRepository courseRepository;
  private final MemberRepository memberRepository;
  private final VoteOptionRepository voteOptionRepository;
  private final VoteResponseRepository voteResponseRepository;
  private final StudentCourseRepository studentCourseRepository;

  // 로그인된 유저 정보에 접근할 수 있는 서비스
  private final AuthService authService;

  public VoteService(VoteRepository voteRepository, CourseRepository courseRepository,
      AuthService authService, MemberRepository memberRepository,
      VoteOptionRepository voteOptionRepository, StudentCourseRepository studentCourseRepository,
      VoteResponseRepository voteResponseRepository) {
    this.voteRepository = voteRepository;
    this.courseRepository = courseRepository;
    this.authService = authService;
    this.memberRepository = memberRepository;
    this.voteOptionRepository = voteOptionRepository;
    this.studentCourseRepository = studentCourseRepository;
    this.voteResponseRepository = voteResponseRepository;
  }

  public void addVote(AddVoteDTO addVoteDTO) {
    CustomUserDetails user = authService.getAuthenticatedUser();

    Member member = memberRepository.findById(user.getUserId())
        .orElseThrow(PostBadRequestException::new);

    if (!member.getMemberType().getType().equalsIgnoreCase(MemberRole.ROLE_TEACHER.toString())) {
      throw new PostBadRequestException("강사만 투표를 추가할 수 있습니다.");
    }

    // 강사 강의 중복 방지
    Course course;
    List<Course> courses = courseRepository.findByInstructor(member);
    if (courses.isEmpty()) {
      throw new NotFoundException("담당 과정이 존재하지 않습니다.");
    } else if (courses.size() != 1) {
      throw new PostBadRequestException("강사가 2개 이상의 강의를 담당하고 있습니다.");
    } else {
      course = courses.get(0);
    }

    // Vote 객체 생성
    Vote vote = new Vote();
    vote.setCourse(course);
    vote.setTitle(addVoteDTO.getTitle());
    vote.setDescription(addVoteDTO.getDescription());
    vote.setStartDate(addVoteDTO.getStartDate());
    vote.setEndDate(addVoteDTO.getEndDate());
    vote.setIsExpired(false);

    // Vote_option 객체 생성
    List<VoteOption> voteOptions = new ArrayList<>();
    for (String optionText : addVoteDTO.getOptionText()) { // 수정: List<String>을 사용
      VoteOption option = new VoteOption();
      option.setOptionText(optionText);
      option.setVote(vote);

      voteOptions.add(option);
    }

    voteRepository.save(vote);
    voteOptionRepository.saveAll(voteOptions);
  }

  public GetVoteInfoDTO getVoteInfo(Long id) {
    // 투표 정보 조회
    Vote vote = voteRepository.findById(id)
        .orElseThrow(NotFoundException::new);

    // 투표 정보 상세조회용 DTO 생성
    GetVoteInfoDTO getVoteInfoDTO = new GetVoteInfoDTO();

    // 투표 정보 상세조회 설정 위한 데이터 조회

    // 응답 종류 출력
    List<VoteResponse> voteResponsesForVote = voteResponseRepository.findByVote(vote);

    List<VoteOption> voteOptions = voteOptionRepository.findByVote(vote);
    List<StudentCourse> studentCourses = studentCourseRepository.findByCourseId(
        vote.getCourse().getId());

    getVoteInfoDTO.setVoteOptionInfoList(new ArrayList<>());
    List<VoteOptionInfo> voteOptionInfos = getVoteInfoDTO.getVoteOptionInfoList();

    // 기존 데이터로부터 상세 조회 기본 데이터 설정
    getVoteInfoDTO.setVoteTitle(vote.getTitle());
    getVoteInfoDTO.setDescription(vote.getDescription());
    getVoteInfoDTO.setEndDate(vote.getEndDate());
    getVoteInfoDTO.setIsExpired(vote.getIsExpired());

    // 기존 데이터를 가공하여 진행률, 투표 인원 계산
    getVoteInfoDTO.setProgressRate(
        (Math.round((voteResponsesForVote.size() * 1.0 / studentCourses.size()) * 100)) + "%");
    getVoteInfoDTO.setCurrentParticipants(
        voteResponsesForVote.size() + "/" + studentCourses.size());

    int count = 0;
    // 투표 옵션별 투표 결과 조회
    for (int i = 0; i < voteOptions.size(); i++) {
      count = 0;
      for (VoteResponse voteResponse : voteResponsesForVote) {
        if (voteOptions.get(i).getOptionText()
            .equals(voteResponse.getVoteOption().getOptionText())) {
          count++;
        }
      }
      voteOptionInfos.add(new VoteOptionInfo());
      List<VoteResponse> voteResponses = voteResponseRepository.findByVoteOptionId(
          voteOptions.get(i).getId());
      voteOptionInfos.get(i).setOptionText(voteOptions.get(i).getOptionText());
      voteOptionInfos.get(i)
          .setOccupancyRate(
              Math.round(count * 1.0 / voteResponsesForVote.size() * 100) + "%");
      voteOptionInfos.get(i).setVotes(String.valueOf(count));

      Optional<VoteOption> voteOption = voteOptionRepository.findByVoteIdAndOptionText(vote.getId(), voteOptions.get(i).getOptionText());
      voteOption.get().getId();

      getVoteInfoDTO.getVoteOptionInfoList().get(i).setRank(voteOption.get().getId());
    }


    // 투표 결과 설정
    return getVoteInfoDTO;
  }

  public void doVote(DoVoteDTO doVoteDTO) {
    System.out.println("Received DoVoteDTO: " + doVoteDTO);
    try {
      CustomUserDetails user = authService.getAuthenticatedUser();

      Member member = memberRepository.findById(user.getUserId())
          .orElseThrow(PostBadRequestException::new);
      System.out.println("멤버" + member);

      Vote vote = voteRepository.findById(doVoteDTO.getVoteId())
          .orElseThrow(NotFoundException::new);

      if (vote.getIsExpired()) {
        throw new PostBadRequestException("만료된 투표입니다.");
      }

      // 투표 중복 방지
      List<VoteResponse> voteResponses = voteResponseRepository.findByVote(vote);
      for (VoteResponse voteResponse : voteResponses) {
        if (voteResponse.getStudentCourse().getStudent().getId().equals(member.getId())) {
          throw new PostBadRequestException("이미 투표한 학생입니다.");
        }
      }

      // 투표 옵션 확인
      VoteOption voteOption = voteOptionRepository.findById(doVoteDTO.getVoteOptionId())
          .orElseThrow(NotFoundException::new);

      // 투표 응답 객체 생성
      VoteResponse voteResponse = new VoteResponse();
      voteResponse.setVote(vote);
      voteResponse.setStudentCourse(studentCourseRepository.findByStudent(member));
      System.out.println("스튜던트 코스" + studentCourseRepository.findByStudent(member));
      voteResponse.setVoteOption(voteOption);

      voteResponseRepository.save(voteResponse);
    } catch (NonUniqueResultException e) {
      throw new PostBadRequestException("투표 중복 오류입니다.");
    }
  }

  public List<GetAllVoteDTO> getAllVote() {
    LocalDateTime now = LocalDateTime.now();
    List<Vote> votes = voteRepository.findAllWithCourseAndInstructor();

    CustomUserDetails user = authService.getAuthenticatedUser();

    Member member = memberRepository.findById(user.getUserId())
        .orElseThrow(PostBadRequestException::new);

    // 상태 업데이트
    for (Vote vote : votes) {
      boolean isExpired = now.isAfter(vote.getEndDate()) || now.isBefore(vote.getStartDate());
      vote.setIsExpired(isExpired);
      voteRepository.save(vote);
    }

    List<GetAllVoteDTO> getAllVoteDTOS = new ArrayList<>();
    if(member.getMemberType().getType().equalsIgnoreCase(String.valueOf(MemberRole.ROLE_TEACHER))) {
       Optional<Course> course = courseRepository.findByInstructor_Id(member.getId());
      List<Vote> votes1 =  voteRepository.findByCourse(course.get());
      for (Vote vote : votes1) {
        getAllVoteDTOS.add(new GetAllVoteDTO(vote.getId(), vote.getCourse().getTitle()
            , vote.getTitle(), vote.getDescription()
            , vote.getStartDate(), vote.getEndDate(), vote.getIsExpired()));
      }
    } else if (member.getMemberType().getType().equalsIgnoreCase(String.valueOf(MemberRole.ROLE_STUDENT))) {
      StudentCourse studentCourse = studentCourseRepository.findByStudent(member);
      List<Vote> votes2 =  voteRepository.findByCourse(studentCourse.getCourse());
      for (Vote vote : votes2) {
        getAllVoteDTOS.add(new GetAllVoteDTO(vote.getId(), vote.getCourse().getTitle()
            , vote.getTitle(), vote.getDescription()
            , vote.getStartDate(), vote.getEndDate(), vote.getIsExpired()));
      }
    } else {
      for (Vote vote : votes) {
        getAllVoteDTOS.add(new GetAllVoteDTO(vote.getId(), vote.getCourse().getTitle()
            , vote.getTitle(), vote.getDescription()
            , vote.getStartDate(), vote.getEndDate(), vote.getIsExpired()));
      }
    }
    //투표 시작날짜가 현재보다 빠르고 종료날짜가 느린 값들은 Ture 아니면 false;
    return getAllVoteDTOS.stream().sorted(Comparator.comparing(GetAllVoteDTO::getId).reversed()).toList();
  }

  public GetVoteDTO getVote(Long id) {
    Optional<Vote> votes = voteRepository.findById(id);
    Vote vote = votes.get();
    List<VoteOption> voteOption = voteOptionRepository.findByVote(vote);
    List<String> options = new ArrayList<>();
    for (VoteOption option : voteOption) {
      options.add(option.getOptionText());
    }

    GetVoteDTO getVoteDTO = new GetVoteDTO(vote.getId(), vote.getCourse().getTitle(),
        vote.getTitle(), vote.getDescription(), vote.getStartDate(),
        vote.getEndDate(), vote.getIsExpired(), options);

    return getVoteDTO;
  }


  public void deleteVote(Long id) throws Exception {
    try {
      voteRepository.deleteById(id);
    } catch (Exception e) {
      throw new Exception("잘못된 삭제입니다.");
    }
  }
}
