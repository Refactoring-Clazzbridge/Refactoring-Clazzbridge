package com.example.academy.dto.comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentRequestDTO {

    private Long postId;
    
    private String content;

}
