package com.example.academy.exception.post;

public class PostEmptyException extends RuntimeException {

    public PostEmptyException() {
        super("데이터가 없습니다. ID ");
    }

    public PostEmptyException(String s) {
        super(s);
    }
}
