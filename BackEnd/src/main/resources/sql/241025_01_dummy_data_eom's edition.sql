INSERT INTO avatar_image (avatar_image_url)
VALUES ('https://www.moti.co.kr/data/item/1701766013/thumb-64iE64Sk652g64Sk_7I2464Sk7J28_01_600x600.png'),
       ('https://kubakery.co.kr/wp-content/uploads/2015/02/bbang-0053.jpg'),
       ('https://upload.wikimedia.org/wikipedia/commons/b/b2/누네띠네_사진.jpg'),
       ('https://jjmall.shop/web/product/big/202303/00587e7d7430563103bbb6194dfcc3ed.jpg'),
       ('https://image.auction.co.kr/itemimage/30/16/27/3016278b36.jpg'),
       ('https://www.moti.co.kr/data/item/1701766013/thumb-64iE64Sk652g64Sk_7I2464Sk7J28_01_600x600.png'),
       ('https://kubakery.co.kr/wp-content/uploads/2015/02/bbang-0053.jpg'),
       ('https://upload.wikimedia.org/wikipedia/commons/b/b2/누네띠네_사진.jpg'),
       ('https://jjmall.shop/web/product/big/202303/00587e7d7430563103bbb6194dfcc3ed.jpg'),
       ('https://image.auction.co.kr/itemimage/30/16/27/3016278b36.jpg');

INSERT INTO member_type (type)
VALUES ('ROLE_STUDENT'),
       ('ROLE_TEACHER'),
       ('ROLE_ADMIN');

INSERT INTO member (member_id, password, name, email, phone, member_type_id, avatar_image_id,
                    git_url,
                    bio)
VALUES ('student01', 'password1', '가슬기', 'john@example.com', '010-1234-5678', 1, 1,
        'https://github.com/johndoe', 'Hello, I am John Doe, a student.'),
       ('student02', 'password2', '나슬기', 'jane@example.com', '010-2345-6789', 2, 2,
        'https://github.com/janesmith', 'Hi, I am Jane Smith, passionate about learning.'),
       ('teacher01', 'password3', '다슬기', 'alice@example.com', '010-3456-7890', 1, 3,
        'https://github.com/alicebrown', 'I am Alice Brown, a Mathematics teacher.'),
       ('teacher02', 'password4', '라슬기', 'bob@example.com', '010-4567-8901', 1, 4,
        'https://github.com/bobwhite', 'I am Bob White, a Physics teacher.'),
       ('admin01', 'password5', '임상우', 'charlie@example.com', '010-5678-9012', 2, 5,
        NULL, 'I am Charlie Black, an administrator of the system.'),
       ('seulki', '$2a$10$5h01H087V8At4/K3Ued..uRYEqoqkTZDD2JyZ2bQXXcC5mjBM8RRm', '강슬기',
        'daniel@example.com', '010-6789-0123', 3, 6, 'https://github.com/danielgreen',
        'I am Daniel Green, a student interested in programming.'),
       ('sangwoo', '$2a$10$LAgc5zc3O3jwSsBGIe5zMOH6PDOaeF0wZFaS3etewbbhR8DTpriV6', '임하우',
        'emily@example.com', '010-7890-1234', 3, 7, 'https://github.com/emilyblue',
        'I am Emily Blue, studying data science.'),
       ('seu1lk1i', '$2a$10$/fqpt6eskS8QoTB0X6G5TuJCWdyvsve9Z.ww4JvOdNUd5pSxPTW/u', '임하좌',
        'frank@example.com', '010-8901-2345', 2, 8, 'https://github.com/frankred',
        'I am Frank Red, a Chemistry teacher.'),
       ('ftfg777', '$2a$10$njMi5JMPg05vkuHuy9.AAOOE/3EPHB5NApFlnGeR.2Rdbj/minRke', '정찬우',
        'grace@example.com', '010-9012-3456', 1, 9, 'https://github.com/graceyellow',
        'I am Grace Yellow, enthusiastic about science.'),
       ('quitendexit', '$2a$10$H1iOXIWK9UK7.4Jfz1jRYOJxY1ZBCYejLkFVdlaRGaQcxcHVxz3Rm', '최동인',
        'hannah@example.com', '010-0123-4567', 3, 10, NULL,
        'I am Hannah Violet, an administrator managing the platform.');


INSERT INTO classroom (name, is_occupied)
VALUES ('101 호', FALSE),
       ('102 호', TRUE),
       ('103 호', FALSE),
       ('104 호', TRUE),
       ('105 호', FALSE),
       ('106 호', TRUE),
       ('107 호', FALSE),
       ('108 호', TRUE),
       ('109 호', FALSE),
       ('110 호', TRUE);

INSERT INTO course (title, description, start_date, end_date, layout_image_url, instructor_id,
                    classroom_id)
VALUES ('자바 프로그래밍 기초', '자바 언어의 기초 문법과 객체 지향 프로그래밍을 배우는 강의입니다.', '2024-01-01', '2024-06-01', null, 2,
        1),
       ('파이썬 프로그래밍 입문', '초보자를 위한 파이썬 프로그래밍 기본 강의입니다.', '2024-01-01', '2024-06-01', null, null, 2),
       ('웹 개발 실전', 'HTML, CSS, 자바스크립트를 활용한 웹사이트 구축 실습 강의입니다.', '2024-01-01', '2024-06-01', null,
        null,
        1),
       ('데이터베이스 기초', 'SQL 문법과 MySQL을 활용한 데이터베이스 설계 및 관리 방법을 배웁니다.', '2024-01-01', '2024-06-01',
        null, null,
        2),
       ('알고리즘과 자료구조', '알고리즘 문제 해결 능력을 향상시키기 위한 자료구조 강의입니다.', '2024-01-01', '2024-06-01', null, 8,
        1),
       ('프론트엔드 개발', 'React와 JavaScript를 활용한 모던 프론트엔드 개발 기초 강의입니다.', '2024-01-01', '2024-06-01',
        null, null,
        2),
       ('백엔드 개발 실습', 'Spring Boot를 활용한 백엔드 애플리케이션 개발 기초 강의입니다.', '2024-01-01', '2024-06-01', null,
        null, 1),
       ('머신러닝 입문', 'Python과 TensorFlow를 이용한 인공지능과 머신러닝 기초 강의입니다.', '2024-01-01', '2024-06-01', null,
        null,
        2),
       ('데이터 분석 기초', 'Python을 활용한 데이터 분석 및 시각화 실습 강의입니다.', '2024-01-01', '2024-06-01', null, null,
        1),
       ('소프트웨어 공학', '효율적인 소프트웨어 개발 방법론과 도구들을 배우는 강의입니다.', '2024-01-01', '2024-06-01', null, null,
        2);


INSERT INTO assignment (course_id, title, description, due_date)
VALUES (1, '과제 1', '자바 기초 문법과 객체지향 개념을 설명하는 과제입니다.', '2024-05-01'),
       (1, '과제 2', '클래스와 인터페이스의 차이점을 설명하는 과제입니다.', '2024-05-15'),
       (2, '과제 1', '파이썬 기초 문법을 활용한 간단한 프로그램 작성 과제입니다.', '2024-05-01'),
       (2, '과제 2', '파이썬을 사용하여 데이터를 분석하는 실습 과제입니다.', '2024-05-15'),
       (1, '과제 3', '자바에서 상속과 다형성을 활용한 프로그램을 작성하는 과제입니다.', '2024-06-01'),
       (2, '과제 3', '파이썬의 데이터 구조를 활용한 문제 해결 과제입니다.', '2024-06-01'),
       (1, '과제 4', '예외 처리를 포함한 자바 애플리케이션 작성 과제입니다.', '2024-06-15'),
       (2, '과제 4', '파이썬에서 파일 입출력을 사용하는 과제입니다.', '2024-06-15'),
       (1, '과제 5', '멀티스레드를 이용한 자바 프로그램 작성 과제입니다.', '2024-07-01'),
       (2, '과제 5', '파이썬을 활용한 데이터 시각화 과제입니다.', '2024-07-01');

INSERT INTO board_type (type)
VALUES ('일반'),
       ('질문과 답변'),
       ('공지사항'),
       ('자료실'),
       ('토론'),
       ('피드백'),
       ('지원'),
       ('기술 지원'),
       ('아이디어'),
       ('기타');


INSERT INTO post (course_id, board_type_id, author_id, title, content, created_at)
VALUES (1, 1, 1, '자바 기초 수업 후기', '자바 기초 수업에서 배운 내용을 정리합니다.', '2024-01-01 10:00:00'),
       (2, 1, 2, '파이썬 과제 질문', '파이썬 과제 중에서 변수 관련 질문이 있습니다.', '2024-01-02 11:00:00'),
       (3, 1, 3, 'Spring Boot 설정 방법', 'Spring Boot 설정 과정에서 발생한 문제를 공유합니다.', '2024-01-03 12:00:00'),
       (4, 2, 4, '알고리즘 문제 풀이', '알고리즘 수업에서 다룬 문제의 풀이를 공유합니다.', '2024-01-04 13:00:00'),
       (5, 2, 5, '프론트엔드 개발 팁', 'React를 사용한 프론트엔드 개발 팁을 공유합니다.', '2024-01-05 14:00:00'),
       (6, 2, 1, '백엔드 최적화 질문', 'Spring Boot로 백엔드를 최적화하는 방법에 대해 질문이 있습니다.', '2024-01-06 15:00:00'),
       (7, 2, 2, '머신러닝 프로젝트 발표', '머신러닝 프로젝트 발표 준비사항을 공유합니다.', '2024-01-07 16:00:00'),
       (8, 3, 3, '데이터베이스 성능 개선', 'MySQL 성능 개선 방법에 대해 논의합니다.', '2024-01-08 17:00:00'),
       (9, 3, 4, '프로젝트 협업 툴 추천', '팀 프로젝트에서 사용하기 좋은 협업 툴을 추천합니다.', '2024-01-09 18:00:00'),
       (10, 3, 5, '최종 프로젝트 계획서', '최종 프로젝트 계획서를 공유합니다.', '2024-01-10 19:00:00');


INSERT INTO comment (post_id, author_id, content, created_at)
VALUES (1, 1, '수업 내용이 정말 유익했어요. 감사합니다.', '2024-01-01 11:00:00'),
       (2, 2, '변수 관련하여 좀 더 자세히 설명해주실 수 있나요?', '2024-01-02 12:00:00'),
       (3, 3, 'Spring Boot 설정에 어려움을 겪고 있었는데 도움이 되었습니다.', '2024-01-03 13:00:00'),
       (4, 4, '알고리즘 풀이가 너무 복잡했는데 쉽게 풀어주셔서 감사해요.', '2024-01-04 14:00:00'),
       (5, 5, '프론트엔드 개발 팁 정말 유용합니다. 감사합니다.', '2024-01-05 15:00:00'),
       (6, 1, '저도 백엔드 최적화에 대해 고민 중인데 좋은 정보 감사합니다.', '2024-01-06 16:00:00'),
       (7, 2, '발표 자료 잘 준비된 것 같아요! 응원합니다.', '2024-01-07 17:00:00'),
       (8, 3, '데이터베이스 성능 개선에 대해 좀 더 알고 싶습니다.', '2024-01-08 18:00:00'),
       (9, 4, '저희 팀에서도 협업 툴을 찾아보고 있었는데 좋은 추천 감사합니다.', '2024-01-09 19:00:00'),
       (10, 5, '최종 프로젝트 계획서 잘 읽어봤습니다. 기대되네요!', '2024-01-10 20:00:00');

INSERT INTO student_course (student_id, course_id)
VALUES (1, 1),
       (3, 1),
       (4, 2),
       (9, 1);

INSERT INTO question (id, student_course_id, content, created_at, ai_answer, is_recommended)
VALUES (1, 1, '자바에서 오버로딩은 무엇을 의미하는지 설명해주세요.', '2024-01-10 10:00:00', 'ai 답변', 0),
       (2, 2, '객체 지향 프로그래밍의 장점은 무엇이 있나요?', '2024-01-11 11:00:00', 'ai 답변', 1),
       (3, 3, '파이썬에서 리스트와 튜플의 차이점은 무엇인가요?', '2024-01-12 12:00:00', 'ai 답변', 0),
       (4, 4, '함수형 프로그래밍의 특징은 무엇이 있나요?', '2024-01-13 13:00:00', 'ai 답변', 1),
       (5, 2, '객체 지향 프로그래밍에서 상속은 무엇을 의미하는지 설명해주세요.', '2024-01-14 14:00:00', 'ai 답변', 0),
       (6, 1, '자바스크립트에서 콜백 함수는 무엇을 의미하는지 설명해주세요.', '2024-01-15 15:00:00', 'ai 답변', 1),
       (7, 2, '함수 오버로딩과 함수 오버라이딩의 차이점은 무엇인가요?', '2024-01-16 16:00:00', 'ai 답변', 1),
       (8, 3, '파이썬에서 데코레이터는 무엇을 의미하는지 설명해주세요.', '2024-01-17 17:00:00', 'ai 답변', 0),
       (9, 4, '객체 지향 프로그래밍에서 캡슐화는 무엇을 의미하는지 설명해주세요.', '2024-01-18 18:00:00', 'ai 답변', 1),
       (10, 3, '자바에서 인터페이스는 무엇을 의미하는지 설명해주세요.', '2024-01-19 19:00:00', 'ai 답변', 0);

INSERT INTO answer (question_id, teacher_id, content, created_at)
VALUES (1, 1, '강사 답변 1: 자바 오버로딩 설명', '2024-01-12 12:00:00'),
       (2, 2, '강사 답변 2: 객체 지향 프로그래밍의 장점 설명', '2024-01-12 12:00:00'),
       (3, 3, '강사 답변 3: 파이썬 리스트와 튜플 차이점 설명', '2024-01-12 12:00:00'),
       (4, 4, '강사 답변 4: 함수형 프로그래밍 특징 설명', '2024-01-12 12:00:00'),
       (5, 2, '강사 답변 5: 상속에 대한 설명', '2024-01-12 12:00:00'),
       (6, 1, '강사 답변 6: 콜백 함수 설명', '2024-01-12 12:00:00'),
       (7, 2, '강사 답변 7: 함수 오버로딩과 오버라이딩 차이점 설명', '2024-01-12 12:00:00'),
       (8, 3, '강사 답변 8: 데코레이터 설명', '2024-01-12 12:00:00'),
       (9, 4, '강사 답변 9: 캡슐화 설명', '2024-01-12 12:00:00'),
       (10, 3, '강사 답변 10: 자바 인터페이스 설명', '2024-01-12 12:00:00');

INSERT INTO schedule (course_id, event_title, start_date, end_date, description)
VALUES (1, '자바 기초 실습', '2024-10-01 09:00:00', '2024-10-01 10:00:00', '자바 기초 실습을 위한 시간입니다.'),
       (1, '파이썬 중간 평가', '2024-10-02 10:00:00', '2024-10-02 11:00:00', '파이썬 중간 평가 시험을 진행합니다.'),
       (2, '알고리즘 워크숍', '2024-10-03 11:00:00', '2024-10-03 12:00:00', '알고리즘 문제 풀이 워크숍입니다.'),
       (2, '프론트엔드 디자인 실습', '2024-10-04 12:00:00', '2024-10-04 13:00:00', '프론트엔드 디자인 실습을 진행합니다.'),
       (1, '자바 프로젝트 발표', '2024-10-05 13:00:00', '2024-10-05 14:00:00', '자바 프로젝트 최종 발표 시간입니다.'),
       (1, '백엔드 최적화 세미나', '2024-10-06 14:00:00', '2024-10-06 15:00:00', '백엔드 최적화 방법에 대한 세미나입니다.'),
       (2, '머신러닝 프로젝트 발표', '2024-10-07 15:00:00', '2024-10-07 16:00:00', '머신러닝 프로젝트 발표 시간이 있습니다.'),
       (2, '데이터베이스 최적화 워크숍', '2024-10-08 16:00:00', '2024-10-08 17:00:00',
        '데이터베이스 최적화에 대한 워크숍입니다.'),
       (1, '프로젝트 협업 툴 교육', '2024-10-09 17:00:00', '2024-10-09 18:00:00', '프로젝트 협업 툴 사용법을 교육합니다.'),
       (1, '최종 프로젝트 피드백 세션', '2024-10-10 18:00:00', '2024-10-10 19:00:00',
        '최종 프로젝트에 대한 피드백을 받는 시간입니다.');

INSERT INTO seat (course_id, seat_number, is_exist, is_online, member_id)
VALUES (1, 'A1', TRUE, TRUE, 3),
       (1, 'A2', TRUE, TRUE, 1),
       (1, 'A3', TRUE, FALSE, NULL),
       (1, 'A4', TRUE, FALSE, NULL),
       (2, 'B1', TRUE, TRUE, 2),
       (2, 'B2', TRUE, FALSE, NULL),
       (2, 'B3', TRUE, FALSE, 7),
       (2, 'B4', TRUE, FALSE, NULL),
       (2, 'B5', TRUE, FALSE, NULL),
       (2, 'B6', TRUE, TRUE, 10),
       (2, 'B7', TRUE, TRUE, NULL),
       (2, 'B8', TRUE, TRUE, NULL),
       (2, 'B9', TRUE, TRUE, NULL);



INSERT INTO submission (assignment_id, student_course_id, content, submission_url, submission_date)
VALUES (1, 1, '제출물 1에 대한 내용입니다.',
        'https://www.moti.co.kr/data/item/1701766013/thumb-64iE64Sk652g64Sk_7I2464Sk7J28_01_600x600.png',
        '2024-04-30'),
       (2, 1, '제출물 2에 대한 내용입니다.',
        'https://kubakery.co.kr/wp-content/uploads/2015/02/bbang-0053.jpg', '2024-05-15'),
       (3, 2, '제출물 3에 대한 내용입니다.',
        'https://upload.wikimedia.org/wikipedia/commons/b/b2/누네띠네_사진.jpg', '2024-06-01'),
       (4, 2, '제출물 4에 대한 내용입니다.',
        'https://jjmall.shop/web/product/big/202303/00587e7d7430563103bbb6194dfcc3ed.jpg',
        '2024-06-15'),
       (5, 3, '제출물 5에 대한 내용입니다.',
        'https://image.auction.co.kr/itemimage/30/16/27/3016278b36.jpg', '2024-07-01'),
       (6, 3, '제출물 6에 대한 내용입니다.',
        'https://www.moti.co.kr/data/item/1701766013/thumb-64iE64Sk652g64Sk_7I2464Sk7J28_01_600x600.png',
        '2024-07-15'),
       (7, 4, '제출물 7에 대한 내용입니다.',
        'https://kubakery.co.kr/wp-content/uploads/2015/02/bbang-0053.jpg', '2024-08-01'),
       (8, 4, '제출물 8에 대한 내용입니다.',
        'https://upload.wikimedia.org/wikipedia/commons/b/b2/누네띠네_사진.jpg', '2024-08-15'),
       (9, 3, '제출물 9에 대한 내용입니다.',
        'https://jjmall.shop/web/product/big/202303/00587e7d7430563103bbb6194dfcc3ed.jpg',
        '2024-09-01'),
       (10, 4, '제출물 10에 대한 내용입니다.',
        'https://image.auction.co.kr/itemimage/30/16/27/3016278b36.jpg', '2024-09-15');

INSERT INTO vote (title, course_id, description, start_date, end_date, is_expired)
VALUES ('교장 선거', 1, '다음 교장 선거에 대한 투표입니다.', '2024-04-01 09:00:00', '2024-04-10 18:00:00', FALSE),
       ('베스트 프로젝트', 1, '베스트 프로젝트에 대한 투표입니다.', '2024-04-15 09:00:00', '2024-04-20 18:00:00', FALSE),
       ('이벤트 날짜 결정', 1, '이벤트 날짜에 대한 투표입니다.', '2024-05-01 09:00:00', '2024-05-05 18:00:00', FALSE),
       ('과목 연장 투표', 1, '과목 연장에 대한 투표입니다.', '2024-06-01 09:00:00', '2024-06-10 18:00:00', TRUE),
       ('신규 클럽形成', 2, '신규 클럽 형성에 대한 투표입니다.', '2024-07-01 09:00:00', '2024-07-10 18:00:00', FALSE),
       ('시험 일정 결정', 2, '시험 일정에 대한 투표입니다.', '2024-07-15 09:00:00', '2024-07-20 18:00:00', FALSE),
       ('필드 트립 목적지', 2, '필드 트립 목적지에 대한 투표입니다.', '2024-08-01 09:00:00', '2024-08-10 18:00:00',
        FALSE),
       ('선생님 투표', 3, '선생님에 대한 투표입니다.', '2024-08-15 09:00:00', '2024-08-20 18:00:00', FALSE),
       ('신규 과목 추가', 3, '신규 과목 추가에 대한 투표입니다.', '2024-09-01 09:00:00', '2024-09-05 18:00:00', TRUE),
       ('휴일 이벤트 계획', 4, '휴일 이벤트 계획에 대한 투표입니다.', '2024-09-15 09:00:00', '2024-09-20 18:00:00', TRUE);

INSERT INTO vote_option (vote_id, option_text)
VALUES (1, 'Option A'),  -- 교장 선거에서 Option A
       (1, 'Option B'),  -- 교장 선거에서 Option B
       (2, 'Project 1'), -- 베스트 프로젝트에서 Project 1
       (2, 'Project 2'), -- 베스트 프로젝트에서 Project 2
       (3, 'Option 1'),  -- 이벤트 날짜 결정에서 Option 1
       (3, 'Option 2'),  -- 이벤트 날짜 결정에서 Option 2
       (4, 'Yes'),       -- 과목 연장 투표에서 Yes
       (4, 'No'),        -- 과목 연장 투표에서 No
       (5, 'Approve'),   -- 신규 클럽 형성에서 Approve
       (5, 'Reject'); -- 신규 클럽 형성에서 Reject

INSERT INTO vote_response (vote_id, student_course_id, vote_option_id)
VALUES (1, 1, 1), -- 학생 1이 교장 선거에서 Option A를 선택
       (1, 2, 2), -- 학생 2가 교장 선거에서 Option B를 선택
       (2, 3, 3), -- 학생 3이 베스트 프로젝트에서 Project 1을 선택
       (2, 4, 4), -- 학생 4가 베스트 프로젝트에서 Project 2를 선택
       (3, 1, 5), -- 학생 5가 이벤트 날짜 결정에서 Option 1을 선택
       (3, 4, 6), -- 학생 6이 이벤트 날짜 결정에서 Option 2를 선택
       (4, 1, 7), -- 학생 1이 과목 연장 투표에서 Yes를 선택
       (4, 2, 8), -- 학생 2가 과목 연장 투표에서 No를 선택
       (5, 3, 9), -- 학생 3이 신규 클럽 형성 투표에서 Approve를 선택
       (5, 4, 10); -- 학생 4가 신규 클럽 형성 투표에서 Reject를 선택

