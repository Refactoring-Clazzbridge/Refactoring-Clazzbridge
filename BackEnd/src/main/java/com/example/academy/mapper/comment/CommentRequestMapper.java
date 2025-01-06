package com.example.academy.mapper.comment;

import com.example.academy.domain.BoardType;
import com.example.academy.domain.Comment;
import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import com.example.academy.domain.Post;
import com.example.academy.dto.comment.CommentRequestDTO;
import com.example.academy.dto.post.PostCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentRequestMapper {

    public static Comment toEntity(String content, Member author, Post post) {
        return Comment.builder()
            .author(author)
            .content(content)
            .post(post)
            .build();
    }
}
