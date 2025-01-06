package com.example.academy.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostResponseDTO {

    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private String authorName;
    private String boardType;
    private String courseTitle;
    private Long courseId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    public PostResponseDTO() {
    }

    @Builder
    public PostResponseDTO(Long id, String title, String content, String authorName,
        String boardType,
        String courseTitle, Date createdAt, Long authorId, Long courseId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.authorName = authorName;
        this.boardType = boardType;
        this.courseTitle = courseTitle;
        this.createdAt = createdAt;
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return this.courseId != null ? this.courseId : null;  // courseId가 null이면 null을 반환
    }

}
