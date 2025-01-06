-- 과제
ALTER TABLE assignment
    DROP FOREIGN KEY FK_course_TO_assignment;
-- 강의 -> 과제

-- 댓글
ALTER TABLE comment
    DROP FOREIGN KEY FK_member_TO_comment; -- 멤버 -> 댓글
ALTER TABLE comment
    DROP FOREIGN KEY FK_post_TO_comment;
-- 게시글 -> 댓글

-- 강의
ALTER TABLE course
    DROP FOREIGN KEY FK_member_TO_course; -- 멤버 -> 강의
ALTER TABLE course
    DROP FOREIGN KEY FK_classroom_TO_course;
-- 강의실 -> 강의

-- 멤버
ALTER TABLE member
    DROP FOREIGN KEY FK_avatar_image_TO_member; -- 아바타사진 -> 멤버
ALTER TABLE member
    DROP FOREIGN KEY FK_member_type_TO_member;
-- 멤버유형 -> 멤버

-- 게시글
ALTER TABLE post
    DROP FOREIGN KEY FK_member_TO_post; -- 멤버 -> 게시글
ALTER TABLE post
    DROP FOREIGN KEY FK_course_TO_post; -- 강의 -> 게시글
ALTER TABLE post
    DROP FOREIGN KEY FK_board_type_TO_post;
-- 게시판_타입 -> 게시글

-- 질문
ALTER TABLE question
    DROP FOREIGN KEY FK_student_course_TO_question;
-- 학생_강의 -> 질문

-- 일정
ALTER TABLE schedule
    DROP FOREIGN KEY FK_course_TO_schedule;
-- 강의 -> 일정

-- 좌석
ALTER TABLE seat
    DROP FOREIGN KEY FK_member_TO_seat; -- 멤버 -> 좌석
ALTER TABLE seat
    DROP FOREIGN KEY FK_course_TO_seat;

-- 강의 -> 좌석

-- 학생_강의
ALTER TABLE student_course
    DROP FOREIGN KEY FK_member_TO_student_course; -- 멤버 -> 학생_강의
ALTER TABLE student_course
    DROP FOREIGN KEY FK_course_TO_student_course;
-- 강의 -> 학생_강의

-- 과제물
ALTER TABLE submission
    DROP FOREIGN KEY FK_student_course_TO_submission; -- 학생_강의 -> 과제물
ALTER TABLE submission
    DROP FOREIGN KEY FK_assignment_TO_submission;
-- 과제 -> 과제물

-- 투표
ALTER TABLE vote
    DROP FOREIGN KEY FK_course_TO_vote;
-- 강의 -> 투표

-- 투표응답
ALTER TABLE vote_response
    DROP FOREIGN KEY FK_student_course_TO_vote_response; -- 학생_강의 -> 투표응답
ALTER TABLE vote_response
    DROP FOREIGN KEY FK_vote_option_TO_vote_response; -- 투표항목 -> 투표응답
ALTER TABLE vote_response
    DROP FOREIGN KEY FK_vote_TO_vote_response;
-- 투표 -> 투표응답

-- 투표항목
ALTER TABLE vote_option
    DROP FOREIGN KEY FK_vote_TO_vote_option;
-- 투표 -> 투표항목

-- 과제
DROP TABLE IF EXISTS assignment RESTRICT;

-- 게시판_타입
DROP TABLE IF EXISTS board_type RESTRICT;

-- 강의실
DROP TABLE IF EXISTS classroom RESTRICT;

-- 댓글
DROP TABLE IF EXISTS comment RESTRICT;

-- 강의
DROP TABLE IF EXISTS course RESTRICT;

-- 멤버
DROP TABLE IF EXISTS member RESTRICT;

-- 게시글
DROP TABLE IF EXISTS post RESTRICT;

-- 아바타사진
DROP TABLE IF EXISTS avatar_image RESTRICT;

-- 질문
DROP TABLE IF EXISTS question RESTRICT;

-- 답변
DROP TABLE IF EXISTS answer RESTRICT;

-- 일정
DROP TABLE IF EXISTS schedule RESTRICT;

-- 좌석
DROP TABLE IF EXISTS seat RESTRICT;

-- 학생_강의
DROP TABLE IF EXISTS student_course RESTRICT;

-- 과제물
DROP TABLE IF EXISTS submission RESTRICT;

-- 투표
DROP TABLE IF EXISTS vote RESTRICT;

-- 멤버유형
DROP TABLE IF EXISTS member_type RESTRICT;

-- 투표응답
DROP TABLE IF EXISTS vote_response RESTRICT;

-- 투표항목
DROP TABLE IF EXISTS vote_option RESTRICT;

-- 과제
CREATE TABLE assignment
(
    id          INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '과제 ID', -- 과제 ID
    course_id   INT                COMMENT '강의 ID',             -- 강의 ID
    title       VARCHAR(100)       NOT NULL COMMENT '제목',                -- 제목
    description TEXT               NOT NULL COMMENT '설명',                -- 설명
    due_date    DATE               NOT NULL COMMENT '기한'                 -- 기한
)
    COMMENT '과제';

-- 게시판_타입
CREATE TABLE board_type
(
    id   INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '게시판 타입 ID', -- 게시판 타입 ID
    type VARCHAR(100)       NOT NULL COMMENT '게시판타입명'                 -- 게시판타입명
)
    COMMENT '게시판_타입';

-- 강의실
CREATE TABLE classroom
(
    id          INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '강의실 ID', -- 강의실 ID
    name        VARCHAR(100)       NOT NULL COMMENT '이름',                 -- 이름
    is_occupied BOOLEAN            NOT NULL COMMENT '사용중'                 -- 사용중
)
    COMMENT '강의실';

-- 댓글
CREATE TABLE comment
(
    id         INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '댓글 ID', -- 댓글 ID
    post_id    INT                NOT NULL COMMENT '게시글 ID',            -- 게시글 ID
    author_id  INT                NOT NULL COMMENT '작성자 ID',            -- 작성자 ID
    content    TEXT               NOT NULL COMMENT '내용',                -- 내용
    created_at TIMESTAMP          NOT NULL COMMENT '작성 날짜'              -- 작성 날짜
)
    COMMENT '댓글';

-- 강의
CREATE TABLE course
(
    id               INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '강의 ID', -- 강의 ID
    instructor_id    INT                NULL COMMENT '강사 ID',                 -- 강사 ID
    classroom_id     INT                NULL COMMENT '강의실 ID',                -- 강의실 ID
    title            VARCHAR(100)       NOT NULL COMMENT '제목',                -- 제목
    description      TEXT               NOT NULL COMMENT '설명',                -- 설명
    start_date       DATE               NOT NULL COMMENT '시작 날짜',             -- 시작 날짜
    end_date         DATE               NOT NULL COMMENT '종료 날짜',             -- 종료 날짜
    layout_image_url VARCHAR(255)       NULL COMMENT '배치도'                    -- 배치도
)
    COMMENT '강의';

-- 멤버
CREATE TABLE member
(
    id              INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '멤버 ID', -- 멤버 ID
    avatar_image_id INT                NULL COMMENT '아바타사진 ID',              -- 아바타사진 ID
    member_type_id  INT                NOT NULL COMMENT '멤버유형번호',            -- 멤버유형번호
    member_id       VARCHAR(20)        NOT NULL COMMENT '아이디',               -- 아이디
    password        VARCHAR(100)       NOT NULL COMMENT '비밀번호',              -- 비밀번호
    name            VARCHAR(10)        NOT NULL COMMENT '이름',                -- 이름
    email           VARCHAR(30)        NOT NULL COMMENT '이메일',               -- 이메일
    phone           VARCHAR(20)        NOT NULL COMMENT '핸드폰번호',             -- 핸드폰번호
    git_url         VARCHAR(100)       NULL COMMENT '깃 주소',                  -- 깃 주소
    bio             TEXT               NULL COMMENT '자기소개'                   -- 자기소개
)
    COMMENT '멤버';

-- 게시글
CREATE TABLE post
(
    id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '게시글 ID', -- 게시글 ID
    course_id     INT                NULL COMMENT '강의 ID',                  -- 강의 ID
    board_type_id INT                NOT NULL COMMENT '게시판 타입 ID',          -- 게시판 타입 ID
    author_id     INT                NOT NULL COMMENT '작성자 ID',             -- 작성자 ID
    title         VARCHAR(100)       NOT NULL COMMENT '제목',                 -- 제목
    content       TEXT               NOT NULL COMMENT '내용',                 -- 내용
    created_at    TIMESTAMP          NOT NULL COMMENT '생성 날짜'               -- 생성 날짜
)
    COMMENT '게시글';

-- 아바타사진
CREATE TABLE avatar_image
(
    id               INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '아바타사진 ID', -- 아바타사진 ID
    avatar_image_url VARCHAR(255)       NOT NULL COMMENT '아바타사진 경로'              -- 아바타사진 경로
)
    COMMENT '아바타사진';

-- 질문
-- 질문 테이블 (기존 테이블 수정)
CREATE TABLE question
(
    id                INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '질문 ID', -- 질문 ID
    student_course_id INT                NOT NULL COMMENT '수강번호',             -- 수강번호 (외래키)
    content           TEXT               NOT NULL COMMENT '질문',               -- 질문 내용
    created_at        DATETIME           NOT NULL COMMENT '질문일자',             -- 질문일자
    ai_answer         TEXT               NULL COMMENT 'AI자동답변',               -- AI 자동 답변
    is_recommended    BOOLEAN            NULL COMMENT '추천여부'                 -- 추천 여부
);

    COMMENT '질문';

    -- 답변
CREATE TABLE answer
(
        id              INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '답변 ID', -- 답변 ID
        question_id     INT                NOT NULL COMMENT '질문 ID',             -- 질문 ID (외래키)
        teacher_id      INT                NOT NULL COMMENT '교사 ID',             -- 교사 ID (외래키)
        content         TEXT               NOT NULL COMMENT '답변 내용',           -- 답변 내용
        created_at      DATETIME           NOT NULL COMMENT '답변일자'            -- 답변일자
);

    COMMENT '답변';



-- 일정
CREATE TABLE schedule
(
    id          INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '일정 ID', -- 일정 ID
    course_id   INT                NOT NULL COMMENT '강의 ID',             -- 강의 ID
    event_title VARCHAR(100)       NOT NULL COMMENT '제목',                -- 제목
    start_date  TIMESTAMP          NOT NULL COMMENT '시작 날짜',             -- 시작 날짜
    end_date    TIMESTAMP          NOT NULL COMMENT '종료 날짜',             -- 종료 날짜
    description TEXT               NULL COMMENT '설명'                     -- 설명
)
    COMMENT '일정';

-- 좌석
CREATE TABLE seat
(
    id           INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '좌석 ID', -- 좌석 ID
    course_id INT                NOT NULL COMMENT '강의 ID',             -- 강의 ID
    seat_number  VARCHAR(10)        NOT NULL COMMENT '좌석 번호',             -- 좌석 번호
    member_id    INT                NULL COMMENT '멤버 ID',                 -- 멤버 ID
    is_exist     BOOLEAN            NOT NULL COMMENT '존재 여부',             -- 존재 여부
    is_online    BOOLEAN            NOT NULL COMMENT '온라인 여부'             -- 온라인 여부
    
)
    COMMENT '좌석';

-- 좌석 유니크 인덱스
CREATE UNIQUE INDEX UIX_seat
    ON seat ( -- 좌석
             classroom_id ASC, -- 강의 ID
             seat_number ASC -- 좌석 번호
        );

-- 학생_강의
CREATE TABLE student_course
(
    id         INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '수강번호', -- 수강번호
    student_id INT                NOT NULL COMMENT '학생 ID',            -- 학생 ID
    course_id  INT                NOT NULL COMMENT '강의 ID'             -- 강의 ID
)
    COMMENT '학생_강의';

-- 학생_강의 유니크 인덱스
CREATE UNIQUE INDEX UIX_student_course
    ON student_course ( -- 학생_강의
                       student_id ASC, -- 학생 ID
                       course_id ASC -- 강의 ID
        );

-- 과제물
CREATE TABLE submission
(
    assignment_id     INT          NOT NULL COMMENT '과제 ID', -- 과제 ID
    student_course_id INT          NOT NULL COMMENT '수강번호',  -- 수강번호
    content           TEXT         NOT NULL COMMENT '내용',    -- 내용
    submission_url    VARCHAR(255) NULL COMMENT '제출물 URL',   -- 제출물 URL
    submission_date   DATE         NOT NULL COMMENT '제출 날짜'  -- 제출 날짜
)
    COMMENT '과제물';

-- 과제물
ALTER TABLE submission
    ADD CONSTRAINT PK_submission -- 과제물 기본키
        PRIMARY KEY (
                     assignment_id, -- 과제 ID
                     student_course_id -- 수강번호
            );

-- 투표
CREATE TABLE vote
(
    id          INT AUTO_INCREMENT    NOT NULL PRIMARY KEY COMMENT '투표 ID', -- 투표 ID
    course_id   INT                   NOT NULL COMMENT '강의 ID',             -- 강의 ID
    title       VARCHAR(100)          NOT NULL COMMENT '제목',                -- 제목
    description TEXT                  NULL COMMENT '설명',                    -- 설명
    start_date  TIMESTAMP             NOT NULL COMMENT '시작 날짜',             -- 시작 날짜
    end_date    TIMESTAMP             NOT NULL COMMENT '종료 날짜',             -- 종료 날짜
    is_expired  BOOLEAN DEFAULT FALSE NULL COMMENT '종료 여부'                  -- 종료 여부
)
    COMMENT '투표';

-- 멤버유형
CREATE TABLE member_type
(
    id   INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '멤버유형번호', -- 멤버유형번호
    type VARCHAR(100)       NOT NULL COMMENT '멤버유형명'               -- 멤버유형명
)
    COMMENT '멤버유형';

-- 투표응답
CREATE TABLE vote_response
(
    id                INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '투표 응답 ID',
    vote_id           INT                NOT NULL COMMENT '투표 ID', -- 투표 ID
    student_course_id INT                NOT NULL COMMENT '수강번호',  -- 수강번호
    vote_option_id    INT                NOT NULL COMMENT '투표항목번호' -- 투표항목번호
)
    COMMENT '투표응답';

-- 학생_강의 유니크 인덱스
CREATE UNIQUE INDEX UIX_vote_response
    ON vote_response ( -- 학생_강의
                      student_course_id ASC, -- 학생 ID
                      vote_id ASC -- 강의 ID
        );

-- 투표항목
CREATE TABLE vote_option
(
    id          INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '투표항목번호', -- 투표항목번호
    vote_id     INT                NOT NULL COMMENT '투표 ID',              -- 투표 ID
    option_text VARCHAR(40)        NOT NULL COMMENT '투표항목'                -- 투표항목
)
    COMMENT '투표항목';

-- 과제
ALTER TABLE assignment
    ADD CONSTRAINT FK_course_TO_assignment -- 강의 -> 과제
        FOREIGN KEY (
                     course_id -- 강의 ID
            )
            REFERENCES course ( -- 강의
                               id -- 강의 ID
                )ON DELETE CASCADE;

-- 댓글
ALTER TABLE comment
    ADD CONSTRAINT FK_member_TO_comment -- 멤버 -> 댓글
        FOREIGN KEY (
                     author_id -- 작성자 ID
            )
            REFERENCES member ( -- 멤버
                               id -- 멤버 ID
                )ON DELETE CASCADE;

-- 댓글
ALTER TABLE comment
    ADD CONSTRAINT FK_post_TO_comment -- 게시글 -> 댓글
        FOREIGN KEY (
                     post_id -- 게시글 ID
            )
            REFERENCES post ( -- 게시글
                             id -- 게시글 ID
                )ON DELETE CASCADE;

-- 강의
ALTER TABLE course
    ADD CONSTRAINT FK_member_TO_course -- 멤버 -> 강의
        FOREIGN KEY (
                     instructor_id -- 강사 ID
            )
            REFERENCES member ( -- 멤버
                               id -- 멤버 ID
                )ON DELETE CASCADE;

-- 강의
ALTER TABLE course
    ADD CONSTRAINT FK_classroom_TO_course -- 강의실 -> 강의
        FOREIGN KEY (
                     classroom_id -- 강의실 ID
            )
            REFERENCES classroom ( -- 강의실
                                  id -- 강의실 ID
                )ON DELETE CASCADE;

-- 멤버
ALTER TABLE member
    ADD CONSTRAINT FK_avatar_image_TO_member -- 아바타사진 -> 멤버
        FOREIGN KEY (
                     avatar_image_id -- 아바타사진 ID
            )
            REFERENCES avatar_image ( -- 아바타사진
                                     id -- 아바타사진 ID
                )ON DELETE CASCADE;

-- 멤버
ALTER TABLE member
    ADD CONSTRAINT FK_member_type_TO_member -- 멤버유형 -> 멤버
        FOREIGN KEY (
                     member_type_id -- 멤버유형번호
            )
            REFERENCES member_type ( -- 멤버유형
                                    id -- 멤버유형번호
                )ON DELETE CASCADE;

-- 게시글
ALTER TABLE post
    ADD CONSTRAINT FK_member_TO_post -- 멤버 -> 게시글
        FOREIGN KEY (
                     author_id -- 작성자 ID
            )
            REFERENCES member ( -- 멤버
                               id -- 멤버 ID
                )ON DELETE CASCADE;

-- 게시글
ALTER TABLE post
    ADD CONSTRAINT FK_course_TO_post -- 강의 -> 게시글
        FOREIGN KEY (
                     course_id -- 강의 ID
            )
            REFERENCES course ( -- 강의
                               id -- 강의 ID
                )ON DELETE CASCADE;

-- 게시글
ALTER TABLE post
    ADD CONSTRAINT FK_board_type_TO_post -- 게시판_타입 -> 게시글
        FOREIGN KEY (
                     board_type_id -- 게시판 타입 ID
            )
            REFERENCES board_type ( -- 게시판_타입
                                   id -- 게시판 타입 ID
                )ON DELETE CASCADE;

-- 질문
ALTER TABLE question
    ADD CONSTRAINT FK_student_course_TO_question -- 학생_강의 -> 질문
        FOREIGN KEY (
                     student_course_id -- 수강번호
            )
            REFERENCES student_course ( -- 학생_강의
                                       id -- 수강번호
                )ON DELETE CASCADE;

-- 답변
ALTER TABLE answer
    ADD CONSTRAINT FK_question_TO_answer -- 질문 -> 답변
        FOREIGN KEY (question_id)        -- 질문 ID
        REFERENCES question(id)          -- 질문 테이블의 질문 ID 참조
        ON DELETE CASCADE;

-- 답변
ALTER TABLE answer
    ADD CONSTRAINT FK_teacher_TO_answer -- 교사 -> 답변
        FOREIGN KEY (teacher_id)        -- 교사 ID
        REFERENCES member(id)           -- 교사(member) 테이블의 교사 ID 참조
        ON DELETE CASCADE;

-- 일정
ALTER TABLE schedule
    ADD CONSTRAINT FK_course_TO_schedule -- 강의 -> 일정
        FOREIGN KEY (
                     course_id -- 강의 ID
            )
            REFERENCES course ( -- 강의
                               id -- 강의 ID
                )ON DELETE CASCADE;

-- 좌석
ALTER TABLE seat
    ADD CONSTRAINT FK_member_TO_seat -- 멤버 -> 좌석
        FOREIGN KEY (
                     member_id -- 멤버 ID
            )
            REFERENCES member ( -- 멤버
                               id -- 멤버 ID
                )ON DELETE CASCADE;

-- 좌석
ALTER TABLE seat
    ADD CONSTRAINT FK_course_TO_seat -- 강의 -> 좌석
        FOREIGN KEY (
                     course_id -- 강의 ID
            )
            REFERENCES course ( -- 강의
                               id -- 강의 ID
                )ON DELETE CASCADE;

-- 학생_강의
ALTER TABLE student_course
    ADD CONSTRAINT FK_member_TO_student_course -- 멤버 -> 학생_강의
        FOREIGN KEY (
                     student_id -- 학생 ID
            )
            REFERENCES member ( -- 멤버
                               id -- 멤버 ID
                ) ON DELETE CASCADE ;

-- 학생_강의
ALTER TABLE student_course
    ADD CONSTRAINT FK_course_TO_student_course -- 강의 -> 학생_강의
        FOREIGN KEY (
                     course_id -- 강의 ID
            )
            REFERENCES course ( -- 강의
                               id -- 강의 ID
                )ON DELETE CASCADE;

-- 과제물
ALTER TABLE submission
    ADD CONSTRAINT FK_student_course_TO_submission -- 학생_강의 -> 과제물
        FOREIGN KEY (
                     student_course_id -- 수강번호
            )
            REFERENCES student_course ( -- 학생_강의
                                       id -- 수강번호
                )ON DELETE CASCADE;

-- 과제물
ALTER TABLE submission
    ADD CONSTRAINT FK_assignment_TO_submission -- 과제 -> 과제물
        FOREIGN KEY (
                     assignment_id -- 과제 ID
            )
            REFERENCES assignment ( -- 과제
                                   id -- 과제 ID
                )ON DELETE CASCADE;

-- 투표
ALTER TABLE vote
    ADD CONSTRAINT FK_course_TO_vote -- 강의 -> 투표
        FOREIGN KEY (
                     course_id -- 강의 ID
            )
            REFERENCES course ( -- 강의
                               id -- 강의 ID
                )ON DELETE CASCADE;

-- 투표응답
ALTER TABLE vote_response
    ADD CONSTRAINT FK_student_course_TO_vote_response -- 학생_강의 -> 투표응답
        FOREIGN KEY (
                     student_course_id -- 수강번호
            )
            REFERENCES student_course ( -- 학생_강의
                                       id -- 수강번호
                )ON DELETE CASCADE;

-- 투표응답
ALTER TABLE vote_response
    ADD CONSTRAINT FK_vote_option_TO_vote_response -- 투표항목 -> 투표응답
        FOREIGN KEY (
                     vote_option_id -- 투표항목번호
            )
            REFERENCES vote_option ( -- 투표항목
                                    id -- 투표항목번호
                )ON DELETE CASCADE;

-- 투표응답
ALTER TABLE vote_response
    ADD CONSTRAINT FK_vote_TO_vote_response -- 투표 -> 투표응답
        FOREIGN KEY (
                     vote_id -- 투표 ID
            )
            REFERENCES vote ( -- 투표
                             id -- 투표 ID
                )ON DELETE CASCADE;

-- 투표항목
ALTER TABLE vote_option
    ADD CONSTRAINT FK_vote_TO_vote_option -- 투표 -> 투표항목
        FOREIGN KEY (
                     vote_id -- 투표 ID
            )
            REFERENCES vote ( -- 투표
                             id -- 투표 ID
                )ON DELETE CASCADE;
