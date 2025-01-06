package com.example.academy.repository.mysql;

import com.example.academy.domain.BoardType;
import com.example.academy.domain.Course;
import com.example.academy.domain.Post;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"author", "boardType", "course"})
    List<Post> findAll();

    List<Post> findByCourse(Course course);


    Post findSinglePostByCourse(Course course);

    // PostRepository에 메서드 추가
    @Query("SELECT p FROM Post p ORDER BY CASE WHEN p.boardType.type = '공지사항' THEN 0 ELSE 1 END, p.createdAt DESC")
    List<Post> findAllPostsOrderByBoardType();

    List<Post> findByCourseIsNullAndBoardType(BoardType announcementType);

    @Query("SELECT p FROM Post p " +
        "LEFT JOIN p.course c " +
        "LEFT JOIN p.boardType bt " +
        "ORDER BY " +
        "CASE WHEN c IS NULL THEN 0 ELSE 1 END, " +             // course가 없는 글을 우선 표시
        "CASE WHEN bt.type = '공지사항' THEN 0 ELSE 1 END, " +    // '공지사항' 게시글을 다음에 표시
        "p.id DESC")
        // id로 내림차순 정렬
    List<Post> findAllPostsOrderByPriority();
}
