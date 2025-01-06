package com.example.academy.enums;

public enum BoardTypes {
    일반(1L),
    질문과답변(2L),
    공지사항(3L),
    자료실(4L),
    토론(5L),
    피드백(6L),
    지원(7L),
    기술지원(8L),
    아이디어(9L),
    기타(10L);

    private final Long id;

    BoardTypes(Long id) {
        this.id = id;
    }

    public Long getDescription() {
        return id;
    }

}
