package com.example.academy.dto.member;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
public class StudentDTO {

    private String name;
    private String avatarImage;
    private String content;
    private LocalDate submissionDate;
    private boolean submitted;

    @Builder
    public StudentDTO(String name, String avatarImage) {
        this.name = name;
        this.avatarImage = avatarImage;
    }
}
