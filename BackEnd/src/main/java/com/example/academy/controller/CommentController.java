package com.example.academy.controller;

import com.example.academy.domain.Comment;
import com.example.academy.dto.comment.CommentRequestDTO;
import com.example.academy.dto.comment.CommentResponseDTO;
import com.example.academy.dto.comment.CommentUpdateDTO;
import com.example.academy.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "게시판 댓글 리스트 반환", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByPost(
        @PathVariable(value = "postId") Long postId) {
        List<CommentResponseDTO> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok().body(comments);
    }

    @Operation(summary = "게시판 댓글 작성", security = {@SecurityRequirement(name = "bearerAuth")})
    @PostMapping("")
    public ResponseEntity<CommentResponseDTO> save(
        @RequestBody CommentRequestDTO commentRequestDTO) {

        CommentResponseDTO comments = commentService.save(commentRequestDTO);

        return ResponseEntity.ok().body(comments);
    }

    @Operation(summary = "게시판 댓글 삭제", security = {@SecurityRequirement(name = "bearerAuth")})
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable(value = "id") Long id) {

        commentService.delete(id);

        return HttpStatus.OK;
    }

    @Operation(summary = "게시판 댓글 수정", security = {@SecurityRequirement(name = "bearerAuth")})
    @PutMapping("")
    public ResponseEntity<CommentResponseDTO> update(
        @RequestBody CommentUpdateDTO commentUpdateDTO) {

        CommentResponseDTO commentResponseDTO = commentService.update(commentUpdateDTO);

        return ResponseEntity.ok().body(commentResponseDTO);
    }


}
