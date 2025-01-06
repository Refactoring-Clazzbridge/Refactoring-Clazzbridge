package com.example.academy.service;

import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import com.example.academy.domain.StudentCourse;
import com.example.academy.domain.Submission;
import com.example.academy.dto.member.CustomUserDetails;
import com.example.academy.dto.member.StudentDTO;
import com.example.academy.dto.studentCourse.StudentCourseResponseDTO;
import com.example.academy.enums.MemberRole;
import com.example.academy.exception.common.NotFoundException;
import com.example.academy.mapper.member.MemberResponseMapper;
import com.example.academy.mapper.studentCourse.StudentCourseMapper;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.MemberRepository;
import com.example.academy.repository.mysql.StudentCourseRepository;
import com.example.academy.repository.mysql.SubmissionRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;
    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;
    private final SubmissionRepository submissionRepository;
    private final AuthService authService;

    public StudentCourseService(StudentCourseRepository studentCourseRepository,
        CourseRepository courseRepository,
        SubmissionRepository submissionRepository,
        MemberRepository memberRepository,
        AuthService authService) {
        this.studentCourseRepository = studentCourseRepository;
        this.courseRepository = courseRepository;
        this.memberRepository = memberRepository;
        this.authService = authService;
        this.submissionRepository = submissionRepository;
    }


    public Long getCourseId() {
        CustomUserDetails user = authService.getAuthenticatedUser();

        return studentCourseRepository.findByStudentId(user.getUserId()).getCourse().getId();
    }

    public StudentCourseResponseDTO getStudentCourseId() {
        CustomUserDetails user = authService.getAuthenticatedUser();

        if (user.getUserType().equals(MemberRole.ROLE_TEACHER)) {
            Course course = courseRepository.findByInstructor_Id(user.getUserId())
                .orElseThrow(() -> new NotFoundException("배정된 강의가 없습니다."));

            return StudentCourseMapper.toDto(course);
        }

        StudentCourse studentCourse = studentCourseRepository.findByStudentId(user.getUserId());

        return StudentCourseMapper.toDto(studentCourse);
    }

    public List<StudentDTO> studentsWithSubmissions(Long assignmentId) {
        CustomUserDetails user = authService.getAuthenticatedUser();

        Course course = courseRepository.findByInstructor_Id(user.getUserId())
            .orElseThrow(() -> new NotFoundException("배정된 강의가 없습니다."));

        // 해당 강의(courseId)에 등록된 모든 수강생의 StudentCourse 엔터티 리스트를 조회
        List<StudentCourse> studentCourses = studentCourseRepository.findByCourse_Id(
            course.getId());

        // 최종 결과를 담을 리스트
        List<StudentDTO> studentDTOs = new ArrayList<>();

        // 각 StudentCourse 엔터티를 StudentDTO로 매핑하고 과제 제출 상태를 확인
        for (StudentCourse studentCourse : studentCourses) {
            // Member 정보를 StudentDTO로 매핑
            StudentDTO studentDTO = MemberResponseMapper.toStudentDTO(studentCourse.getStudent());

            // 각 수강생의 과제 제출 상태를 확인하여 설정
            Optional<Submission> submission = submissionRepository.findByIdStudentCourseIdAndIdAssignmentId(
                studentCourse.getId(), assignmentId);

            if (submission.isPresent()) {
                studentDTO.setSubmitted(true);
                studentDTO.setContent(submission.get().getContent());
                studentDTO.setSubmissionDate(submission.get().getSubmissionDate());
            }

            // studentDTO를 리스트에 추가
            studentDTOs.add(studentDTO);
        }

        return studentDTOs;
    }


}
