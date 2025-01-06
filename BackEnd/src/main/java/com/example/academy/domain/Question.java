package com.example.academy.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "student_course_id", nullable = false)
  private StudentCourse studentCourse;

  @NotNull
  @Lob
  @Column(name = "content", nullable = false)
  private String content;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private Date createdAt;

  @Lob
  @Column(name = "ai_answer")
  private String aiAnswer;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Answer> answers; // 여러 개의 답변을 포함하는 리스트


  @Column(name = "is_recommended")
  private boolean isRecommended;

  public void updateContent(String content) {
    this.content = content;
  }

  public void toggleRecommended(boolean isRecommended) {
    this.isRecommended = isRecommended;
  }

  public void addAnswer(Answer answer) {
    this.answers.add(answer);
    answer.setQuestion(this); // 양방향 연관관계 설정
  }

  public void removeAnswer(Answer answer) {
    this.answers.remove(answer);
    answer.setQuestion(null);
  }

}