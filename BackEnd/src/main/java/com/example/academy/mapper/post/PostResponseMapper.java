package com.example.academy.mapper.post;

import com.example.academy.domain.Post;
import com.example.academy.dto.post.PostResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PostResponseMapper {

    public PostResponseDTO toDto(Post post) {
        if (post == null) {
            return null;
        }
        return PostResponseDTO.builder()
            .id(post.getId())
            .authorId(post.getAuthor().getId())
            .authorName(post.getAuthor().getName())
            .title(post.getTitle())
            .content(post.getContent())
            .boardType(post.getBoardType().getType())
            .courseTitle(post.getCourse() != null ? post.getCourse().getTitle() : null)
            .courseId(post.getCourse() != null ? post.getCourse().getId() : null)
            .createdAt(post.getCreatedAt())
            .build();
    }

    public List<PostResponseDTO> toDtoList(List<Post> posts) {

        return posts.stream()
            .map(todo -> toDto(todo))
            .collect(Collectors.toList());
    }

//    public Post toEntity(PostResponseDTO postDTO) {
//        if (postDTO == null) {
//            return null;
//        }
//        return new Post(
//            postDTO.getId(),
//            postDTO.getTitle(),
//            postDTO.getContent(),
//            postDTO.getAuthorName(),
//            postDTO.getBoardType(),
//            postDTO.getClassroomName()
//        );
//    }

//    public List<Post> toEntityList(List<PostResponseDTO> postDTOList) {
//        if (postDTOList == null) {
//            return null;
//        }
//        return postDTOList.stream()
//            .map(dto -> toEntity(dto))
//            .collect(Collectors.toList());
//    }

}
