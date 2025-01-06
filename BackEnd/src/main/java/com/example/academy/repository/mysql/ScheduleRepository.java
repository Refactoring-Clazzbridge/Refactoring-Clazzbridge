package com.example.academy.repository.mysql;

import com.example.academy.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

  @Query("SELECT MAX(s.id) FROM Schedule s")
  Long findLastId();
}
