package com.example.academy.dto.comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentUpdateDTO {

    private Long id;
    private Long postId;
    private String comment;
    
}
