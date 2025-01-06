package com.example.academy.exception.post;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(Long id) {
        super("해당 게시글이 없습니다. ID: " + id);
    }


    public PostNotFoundException(String s) {
        super(s);
    }

}


