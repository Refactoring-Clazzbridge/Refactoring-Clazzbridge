package com.example.academy.domain;


import com.example.academy.enums.MemberRole;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String memberId; //로그인용 아이디

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "member_type_id", nullable = false)
    private MemberType memberType;

    @ManyToOne
    //(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_image_id", nullable = false)
    private AvatarImage avatarImage;

    private String gitUrl;

    private String bio;

    public boolean isAdmin() {
        return MemberRole.ROLE_ADMIN.toString().equalsIgnoreCase(this.memberType.getType());
    }

}