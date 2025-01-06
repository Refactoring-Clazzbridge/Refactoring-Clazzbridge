package com.example.academy.repository.mysql;

import com.example.academy.domain.BoardType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTypeRepository extends JpaRepository<BoardType, Long> {

    Optional<BoardType> findByType(String type);
}
