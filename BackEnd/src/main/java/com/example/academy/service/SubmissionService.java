package com.example.academy.service;

import com.example.academy.domain.Assignment;
import com.example.academy.domain.StudentCourse;
import com.example.academy.domain.Submission;
import com.example.academy.domain.SubmissionId;
import com.example.academy.dto.submission.SubmissionCheckRequestDTO;
import com.example.academy.dto.submission.SubmissionRequestDTO;
import com.example.academy.dto.submission.SubmissionResponseDTO;
import com.example.academy.exception.assignment.AssignmentDeadlineException;
import com.example.academy.exception.common.NotFoundException;
import com.example.academy.mapper.submission.SubmissionResponseMapper;
import com.example.academy.repository.mysql.AssignmentRepository;
import com.example.academy.repository.mysql.StudentCourseRepository;
import com.example.academy.repository.mysql.SubmissionRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
public class SubmissionService {


    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentCourseRepository studentCourseRepository;

    public SubmissionService(SubmissionRepository submissionRepository,
        AssignmentRepository assignmentRepository,
        StudentCourseRepository studentCourseRepository) {
        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    public List<SubmissionResponseDTO> getAllSubmission() {
        return SubmissionResponseMapper.toDtoList(submissionRepository.findAll());
    }

    // 과제 제출 메서드
    @Transactional
    public SubmissionResponseDTO submitAssignment(SubmissionRequestDTO submissionRequestDTO,
        MultipartFile file) {

        String fileUrl = null;

        Long assignmentId = submissionRequestDTO.getAssignmentId();
        Long studentCourseId = submissionRequestDTO.getStudentCourseId();

        // 과제 찾기
        Assignment assignment = assignmentRepository.findById(assignmentId)
            .orElseThrow(() -> new NotFoundException("해당 과제를 찾을 수 없습니다."));

        // 마감 기한 확인
        LocalDate dueDate = assignment.getDueDate(); // 마감일 가져오기
        if (dueDate != null && LocalDate.now().isAfter(dueDate)) {
            throw new AssignmentDeadlineException("과제 제출 기한이 지났습니다.");
        }

        // 학생 수강 정보 찾기
        StudentCourse studentCourse = studentCourseRepository.findById(studentCourseId)
            .orElseThrow(() -> new NotFoundException("해당 학생의 수강 정보를 찾을 수 없습니다."));

        // 수강생이 제출하는 과제가 현재 수강 중인 강의의 과제인지 확인
        if (!assignment.getCourse().getId().equals(studentCourse.getCourse().getId())) {
            throw new NotFoundException("이 과제는 현재 수강 중인 강의의 과제가 아닙니다.");
        }

        // SubmissionId 생성
        SubmissionId submissionId = new SubmissionId();
        submissionId.setAssignmentId(assignmentId);
        submissionId.setStudentCourseId(studentCourseId);

        // 과제 제출 정보 생성
        Submission submission = new Submission();
        submission.setId(submissionId);
        submission.setAssignment(assignment);
        submission.setStudentCourse(studentCourse);
        submission.setContent(submissionRequestDTO.getContent());
        submission.setSubmissionUrl(fileUrl); // null일 수 있음
        submission.setSubmissionDate(LocalDate.now());

        submissionRepository.save(submission);

        return SubmissionResponseMapper.toDto(submission);
    }

    public SubmissionResponseDTO getSubmission(Long assignmentId, Long studentCourseId) {
        Submission submission = submissionRepository
            .findByIdAssignmentIdAndIdStudentCourseId(assignmentId, studentCourseId)
            .orElseThrow(() -> new NotFoundException("과제 제출 데이터가 없습니다."));

        return SubmissionResponseMapper.toDto(submission);
    }

    public List<SubmissionResponseDTO> getSubmissions(Long assignmentId) {
        if (assignmentId == null) {
            throw new NotFoundException("과제 번호가 없습니다.");
        }

        return SubmissionResponseMapper
            .toDtoList(submissionRepository
                .findByIdAssignmentId(assignmentId));
    }

    public SubmissionResponseDTO hasSubmitted(Long assignmentId, Long studentCourseId) {
        Optional<Submission> submission = submissionRepository
            .findByIdStudentCourseIdAndIdAssignmentId(studentCourseId, assignmentId);

        SubmissionResponseDTO responseDTO = null;
        if (submission.isPresent()) {
            responseDTO = SubmissionResponseMapper.toDto(submission.get());
        }

        if (responseDTO != null) {
            responseDTO.setSubmitted(true);
        }

        return responseDTO;
    }

}
