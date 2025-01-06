package com.example.academy.exception.common;

public class FileStorageException extends RuntimeException {

    // 기본 값
    public FileStorageException() {
        super("파일 저장이 실패했습니다.");
    }


    // 에러 메시지 커스텀
    public FileStorageException(String s) {
        super(s);
    }

}
