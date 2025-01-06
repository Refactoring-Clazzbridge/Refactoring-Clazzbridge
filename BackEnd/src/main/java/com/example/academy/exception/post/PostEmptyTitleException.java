package com.example.academy.exception.post;

public class PostEmptyTitleException extends RuntimeException {

    public PostEmptyTitleException(Long id) {
        super("해당 게시글에 제목이 없습니다. ID: " + id);
    }
    
    public PostEmptyTitleException(String s) {
        super(s);
    }
}
