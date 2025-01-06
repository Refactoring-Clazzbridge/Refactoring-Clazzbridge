package com.example.academy.exception.common;

public class UnauthorizedException extends RuntimeException {

    // 기본 값
    public UnauthorizedException() {
        super("권한이 없습니다.");
    }


    // 에러 메시지 커스텀
    public UnauthorizedException(String s) {
        super(s);
    }

}
