package com.example.academy.exception.post;

public class PostBadRequestException extends RuntimeException {

    public PostBadRequestException() {
        super("올바른 데이터를 입력해 주세요.");
    }

    public PostBadRequestException(String s) {
        super(s);
    }


}
