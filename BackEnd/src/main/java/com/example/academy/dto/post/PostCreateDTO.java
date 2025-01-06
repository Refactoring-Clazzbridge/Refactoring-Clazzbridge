package com.example.academy.dto.post;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostCreateDTO {

    @Schema(description = "게시글 제목", example = "예시 제목입니다.")
    private String title;

    @Schema(description = "게시글 본문", example = "예시 본문입니다.")
    private String content;

    @Schema(description = "게시글 타입 ID 번호", example = "2")
    private Long boardTypeId;

    @Schema(description = "강의 ID 번호", example = "2")
    private Long courseId;
}
