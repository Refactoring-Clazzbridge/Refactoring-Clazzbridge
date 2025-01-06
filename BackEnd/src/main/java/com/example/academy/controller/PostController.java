package com.example.academy.controller;


import com.example.academy.dto.post.PostCreateDTO;
import com.example.academy.dto.post.PostResponseDTO;
import com.example.academy.dto.post.PostUpdateDTO;
import com.example.academy.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "전체 게시글 리스트 반환", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("")
    public ResponseEntity<List<PostResponseDTO>> findAllPosts() {
        List<PostResponseDTO> postDTOs = postService.findAllPosts();
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "전체 자유게시판 게시글 리스트 반환", security = {
        @SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/freeBoard")
    public ResponseEntity<List<PostResponseDTO>> findAllFreePosts() {
        List<PostResponseDTO> postDTOs = postService.findAllFreePosts();
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "전체 공지사항 게시글 리스트 반환", security = {
        @SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/notification")
    public ResponseEntity<List<PostResponseDTO>> findAllNotificationPosts() {
        List<PostResponseDTO> postDTOs = postService.findAllNotificationPosts();
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "로그인된 유저의 강의 자유게시글 리스트 반환", security = {
        @SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/users/me/courses/freePosts")
    public ResponseEntity<List<PostResponseDTO>> getUserCourseFreePosts() {
        List<PostResponseDTO> postDTOs = postService.getUserCourseFreePosts();
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "로그인된 유저의 강의 공지사항 리스트 반환", security = {
        @SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/users/me/courses/notification")
    public ResponseEntity<List<PostResponseDTO>> getUserCourseNotifications() {
        List<PostResponseDTO> postDTOs = postService.getUserCourseNotifications();
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "강의별 전체 게시글 리스트 반환", security = {
        @SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/{courseId}/all")
    public ResponseEntity<List<PostResponseDTO>> getCourseAllPosts(
        @PathVariable(value = "courseId") Long courseId) {
        List<PostResponseDTO> postDTOs = postService.getCourseAllPost(courseId);
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "강의별 공지사항 게시글 리스트 반환", security = {
        @SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/{courseId}/notification")
    public ResponseEntity<List<PostResponseDTO>> getCourseNotices(
        @PathVariable(value = "courseId") Long courseId) {
        List<PostResponseDTO> postDTOs = postService.getCourseNotices(courseId);
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "강의별 자유게시판 게시글 리스트 반환", security = {
        @SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/{courseId}/free")
    public ResponseEntity<List<PostResponseDTO>> getFreePostsByCourseId(
        @RequestParam(required = false) String type,
        @PathVariable(value = "courseId") Long courseId) {
        List<PostResponseDTO> postDTOs = postService.getCourseFreePost(courseId);
        return ResponseEntity.ok().body(postDTOs);
    }

    @Operation(summary = "전체 게시글 중 하나 반환", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable(value = "id") Long id) {
        PostResponseDTO postDTO = postService.findById(id);
        return ResponseEntity.ok().body(postDTO);
    }

    @Operation(summary = "강의별 게시글 중 하나 반환", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/{courseId}/{id}")
    public ResponseEntity<PostResponseDTO> getCourseOnePost(
        @PathVariable(value = "courseId") Long courseId,
        @PathVariable(value = "id") Long id) {
        PostResponseDTO postDTO = postService.getCourseOnePost(courseId, id);
        return ResponseEntity.ok().body(postDTO);
    }

    @Operation(summary = "게시글 저장", security = {@SecurityRequirement(name = "bearerAuth")})
    @PostMapping("")
    public ResponseEntity<PostResponseDTO> save(@RequestBody PostCreateDTO postDTO) {
        // 사용자 id 처리
        PostResponseDTO savedPost = postService.save(postDTO);
        return ResponseEntity.ok().body(savedPost);
    }

    @Operation(summary = "게시글 수정", security = {@SecurityRequirement(name = "bearerAuth")})
    @PutMapping("")
    public ResponseEntity<PostResponseDTO> update(@RequestBody PostUpdateDTO postDTO) {
        PostResponseDTO updatedPost = postService.update(postDTO);

        return ResponseEntity.ok().body(updatedPost);
    }

    @Operation(summary = "게시글 삭제", security = {@SecurityRequirement(name = "bearerAuth")})
    @DeleteMapping("")
    public HttpStatus delete(@RequestBody List<Long> ids) {
        postService.delete(ids);
        return HttpStatus.OK;
    }


}
