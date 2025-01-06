package com.example.academy.repository.mysql;

import com.example.academy.domain.AvatarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageRepository extends JpaRepository<AvatarImage, Long> {


}
