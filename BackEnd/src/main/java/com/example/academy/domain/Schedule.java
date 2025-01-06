package com.example.academy.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "schedule")
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;


  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @Size(max = 100)
  @NotNull
  @Column(name = "event_title", nullable = false, length = 100)
  private String eventTitle;

  @NotNull
  @Column(name = "start_date", nullable = false)
  private LocalDateTime startDate;

  @NotNull
  @Column(name = "end_date", nullable = false)
  private LocalDateTime endDate;

  @Lob
  @Column(name = "description")
  private String description;

}