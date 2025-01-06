package com.example.academy.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@Embeddable
public class SubmissionId implements Serializable {

    private static final long serialVersionUID = -8173060769844765609L;
    @NotNull
    @Column(name = "assignment_id", nullable = false)
    private Long assignmentId;

    @NotNull
    @Column(name = "student_course_id", nullable = false)
    private Long studentCourseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        SubmissionId entity = (SubmissionId) o;
        return Objects.equals(this.studentCourseId, entity.studentCourseId) &&
            Objects.equals(this.assignmentId, entity.assignmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentCourseId, assignmentId);
    }

}