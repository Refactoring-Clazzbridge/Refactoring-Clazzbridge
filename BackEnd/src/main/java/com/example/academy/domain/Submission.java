package com.example.academy.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "submission")
// 과제 제출 정보 (학생)
public class Submission {

    @EmbeddedId
    private SubmissionId id;

    @MapsId("assignmentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @MapsId("studentCourseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_course_id", nullable = false)
    private StudentCourse studentCourse;

    @NotNull
    @Lob
    @Column(name = "content", columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content;

    @Size(max = 255)
    @Column(name = "submission_url")
    private String submissionUrl;

    @NotNull
    @Column(name = "submission_date", nullable = false)
    private LocalDate submissionDate;

}