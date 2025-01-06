package com.example.academy.exception.assignment;

public class AssignmentDeadlineException extends RuntimeException {

    // 기본 값
    public AssignmentDeadlineException() {
        super("마감된 과제입니다.");
    }


    // 에러 메시지 커스텀
    public AssignmentDeadlineException(String s) {
        super(s);
    }

}
