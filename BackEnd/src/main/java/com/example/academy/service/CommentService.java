package com.example.academy.service;

import com.example.academy.domain.Comment;
import com.example.academy.domain.Member;
import com.example.academy.domain.Post;
import com.example.academy.dto.comment.CommentRequestDTO;
import com.example.academy.dto.comment.CommentResponseDTO;
import com.example.academy.dto.comment.CommentUpdateDTO;
import com.example.academy.dto.member.CustomUserDetails;
import com.example.academy.enums.MemberRole;
import com.example.academy.exception.common.NotFoundException;
import com.example.academy.exception.common.UnauthorizedException;
import com.example.academy.mapper.comment.CommentRequestMapper;
import com.example.academy.mapper.comment.CommentResponseMapper;
import com.example.academy.repository.mysql.CommentRepository;
import com.example.academy.repository.mysql.MemberRepository;
import com.example.academy.repository.mysql.PostRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    private final AuthService authService;


    public CommentService(CommentRepository commentRepository, PostRepository postRepository,
        AuthService authService,
        MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.authService = authService;
        this.memberRepository = memberRepository;

    }


    public List<CommentResponseDTO> getCommentsByPost(Long postId) {

        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new NotFoundException("해당 게시글이 존재하지 않습니다."));

        List<Comment> comments = commentRepository.findByPostId(post.getId()).stream()
            .sorted(Comparator.comparing(Comment::getId).reversed())
            .collect(Collectors.toList());

        return CommentResponseMapper.toDtoList(comments);
    }


    @Transactional
    public CommentResponseDTO save(CommentRequestDTO commentRequestDTO) {
        CustomUserDetails user = authService.getAuthenticatedUser();

        if (commentRequestDTO.getContent() == null || commentRequestDTO.getContent().isEmpty()) {
            throw new NotFoundException("댓글이 존재하지 않습니다.");
        }

        Member author = memberRepository.findById(user.getUserId())
            .orElseThrow(() -> new NotFoundException("작성자가 존재하지 않습니다."));

        Post post = postRepository.findById(commentRequestDTO.getPostId())
            .orElseThrow(() -> new NotFoundException("해당 게시글이 존재하지 않습니다."));

        Comment comment = CommentRequestMapper.toEntity(commentRequestDTO.getContent(), author,
            post);
        commentRepository.save(comment);

        return CommentResponseMapper.toDTO(comment);
    }


    @Transactional
    public void delete(Long id) {
        CustomUserDetails user = authService.getAuthenticatedUser();

        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("댓글이 존재하지 않습니다."));

        // 관리자가 아니고, 작성자가 본인이 아닌 경우 권한 에러 발생
        if (!Objects.equals(comment.getAuthor().getId(), user.getUserId()) && !user.getUserType()
            .equals(MemberRole.ROLE_ADMIN)) {
            throw new UnauthorizedException("삭제 권한이 없습니다.");
        }

        commentRepository.delete(comment);

    }

    @Transactional
    public CommentResponseDTO update(CommentUpdateDTO commentUpdateDTO) {
        CustomUserDetails user = authService.getAuthenticatedUser();

        Comment comment = commentRepository.findById(commentUpdateDTO.getId())
            .orElseThrow(() -> new NotFoundException("해당 댓글이 존재하지 않습니다."));

        if (!Objects.equals(comment.getAuthor().getId(), user.getUserId())) {
            throw new NotFoundException("업데이트 권한이 없습니다.");
        }

        comment.updateComment(commentUpdateDTO.getComment());
        commentRepository.save(comment);

        return CommentResponseMapper.toDTO(comment);

    }
}
