package com.example.academy.repository.mysql;

import com.example.academy.domain.Classroom;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

  Optional<Classroom> findByName(String name); // 강의실 이름으로 조회

  boolean existsByName(String Name);

  List<Classroom> findAll();

}
