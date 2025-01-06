package com.example.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // Auditing 기능 활성화
public class ProfileApplication {

    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH시");
        String formattedDate = zonedDateTime.format(formatter);

        String formattedDate2 = LocalDateTime.now().format(formatter);
        System.out.println(formattedDate2 + " 로컬시간");
        System.out.println(formattedDate + " 존시간");

        SpringApplication.run(ProfileApplication.class, args);
    }
}