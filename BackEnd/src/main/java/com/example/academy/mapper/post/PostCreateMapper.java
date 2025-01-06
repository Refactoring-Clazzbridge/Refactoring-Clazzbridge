package com.example.academy.mapper.post;

import com.example.academy.domain.BoardType;
import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import com.example.academy.domain.Post;
import com.example.academy.dto.post.PostCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class PostCreateMapper {

    public Post toEntity(PostCreateDTO postDTO, Member member, BoardType boardType, Course course) {
        return Post.builder()
            .boardType(boardType)
            .author(member)
            .course(course)
            .title(postDTO.getTitle())
            .content(postDTO.getContent())
            .build();
    }

    public Post toEntity(PostCreateDTO postDTO, Member member, BoardType boardType) {
        return Post.builder()
            .boardType(boardType)
            .author(member)
            .title(postDTO.getTitle())
            .content(postDTO.getContent())
            .build();
    }

//    public List<Post> toEntityList(List<PostCreateDTO> postDTOList) {
//        if (postDTOList == null) {
//            return null;
//        }
//        return postDTOList.stream()
//            .map(dto -> toEntity(dto))
//            .collect(Collectors.toList());
//    }

}
