package com.example.academy.mapper.comment;

import com.example.academy.domain.Comment;
import com.example.academy.domain.Member;
import com.example.academy.domain.Post;
import com.example.academy.dto.auth.AuthResponseDTO;
import com.example.academy.dto.comment.CommentResponseDTO;
import com.example.academy.dto.post.PostResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CommentResponseMapper {

    public static CommentResponseDTO toDTO(Comment comment) {
        return CommentResponseDTO.builder()
            .id(comment.getId())
            .postTitle(comment.getPost().getTitle())
            .authorId(comment.getAuthor().getId())
            .author(comment.getAuthor().getName())
            .profileImageUrl(comment.getAuthor().getAvatarImage().getAvatarImageUrl())
            .content(comment.getContent())
            .createdAt(comment.getCreatedAt())
            .build();
    }

    public static List<CommentResponseDTO> toDtoList(List<Comment> comments) {
        if (comments.isEmpty()) {
            return null;
        }
        return comments.stream()
            .map(comment -> toDTO(comment))
            .collect(Collectors.toList());
    }

}
