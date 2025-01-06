package com.example.academy.exception.common;

public class NotFoundException extends RuntimeException {

    // 기본 값
    public NotFoundException() {
        super("데이터가 없습니다.");
    }


    // 에러 메시지 커스텀
    public NotFoundException(String s) {
        super(s);
    }

}
