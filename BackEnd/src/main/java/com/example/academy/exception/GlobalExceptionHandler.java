package com.example.academy.exception;

import com.example.academy.exception.common.FileStorageException;
import com.example.academy.exception.common.NotFoundException;
import com.example.academy.exception.common.UnauthorizedException;
import com.example.academy.exception.post.PostBadRequestException;
import com.example.academy.exception.post.PostEmptyException;
import com.example.academy.exception.post.PostEmptyTitleException;
import com.example.academy.exception.post.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // =============== 공용 ===============
    // 권한 없음
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handlePostNotFoundException(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> NotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body("접근이 거부되었습니다.");
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<String> handleFileStorageException(FileStorageException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // =============== Post ===============
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handlePostNotFoundException(PostNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PostEmptyTitleException.class)
    public ResponseEntity<String> handlePostEmptyTitleException(PostEmptyTitleException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(PostBadRequestException.class)
    public ResponseEntity<String> handlePostBadRequestException(PostBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(PostEmptyException.class)
    public ResponseEntity<String> handlePostEmptyException(PostEmptyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}
