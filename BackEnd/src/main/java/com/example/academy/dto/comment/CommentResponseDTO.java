package com.example.academy.dto.comment;

import com.example.academy.domain.Member;
import com.example.academy.domain.Post;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponseDTO {

    private Long id;

    private String postTitle;

    private Long authorId;
    
    private String author;

    private String profileImageUrl;

    private String content;

    private Date createdAt;

}
