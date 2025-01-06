package com.example.academy.repository.mysql;

import com.example.academy.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  // 사용자 이름으로만 조회
  Optional<Member> findByName(String userName);

  List<Member> findAll();

  Optional<Member> findByMemberId(String memberId);


  boolean existsByMemberId(String memberId);

  boolean existsByEmail(String email);

  @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Member m WHERE m.memberId = :memberId AND m.id <> :id")
  boolean existsByMemberIdAndIdNot(@Param("memberId") String memberId, @Param("id") Long id);

  @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Member m WHERE m.email = :email AND m.id <> :id")
  boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);

}
