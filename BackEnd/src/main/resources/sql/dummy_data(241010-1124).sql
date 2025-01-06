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

INSERT INTO member (member_id, password, name, email, phone, member_type, profile_image_id, git_url,
                    bio)
VALUES ('student01', 'password1', '가슬기', 'john@example.com', '010-1234-5678', 'ROLE_STUDENT', 1,
        'https://github.com/johndoe', 'Hello, I am John Doe, a student.'),
       ('student02', 'password2', '나슬기', 'jane@example.com', '010-2345-6789', 'ROLE_STUDENT', 2,
        'https://github.com/janesmith', 'Hi, I am Jane Smith, passionate about learning.'),
       ('teacher01', 'password3', '다슬기', 'alice@example.com', '010-3456-7890', 'ROLE_TEACHER', 3,
        'https://github.com/alicebrown', 'I am Alice Brown, a Mathematics teacher.'),
       ('teacher02', 'password4', '라슬기', 'bob@example.com', '010-4567-8901', 'ROLE_TEACHER', 4,
        'https://github.com/bobwhite', 'I am Bob White, a Physics teacher.'),
       ('admin01', 'password5', '임상우', 'charlie@example.com', '010-5678-9012', 'ROLE_ADMIN', 5,
        NULL, 'I am Charlie Black, an administrator of the system.'),
       ('seulki', '$2a$10$5h01H087V8At4/K3Ued..uRYEqoqkTZDD2JyZ2bQXXcC5mjBM8RRm', '강슬기',
        'daniel@example.com', '010-6789-0123', 'ROLE_ADMIN', 6, 'https://github.com/danielgreen',
        'I am Daniel Green, a student interested in programming.'),
       ('sangwoo', '$2a$10$LAgc5zc3O3jwSsBGIe5zMOH6PDOaeF0wZFaS3etewbbhR8DTpriV6', '임하우',
        'emily@example.com', '010-7890-1234', 'ROLE_STUDENT', 7, 'https://github.com/emilyblue',
        'I am Emily Blue, studying data science.'),
       ('seu1lk1i', '$2a$10$/fqpt6eskS8QoTB0X6G5TuJCWdyvsve9Z.ww4JvOdNUd5pSxPTW/u', '임하좌',
        'frank@example.com', '010-8901-2345', 'ROLE_TEACHER', 8, 'https://github.com/frankred',
        'I am Frank Red, a Chemistry teacher.'),
       ('ftfg777', '$2a$10$njMi5JMPg05vkuHuy9.AAOOE/3EPHB5NApFlnGeR.2Rdbj/minRke', '정찬우',
        'grace@example.com', '010-9012-3456', 'ROLE_TEACHER', 9, 'https://github.com/graceyellow',
        'I am Grace Yellow, enthusiastic about science.'),
       ('quitendexit', '$2a$10$H1iOXIWK9UK7.4Jfz1jRYOJxY1ZBCYejLkFVdlaRGaQcxcHVxz3Rm', '최동인',
        'hannah@example.com', '010-0123-4567', 'ROLE_ADMIN', 10, NULL,
        'I am Hannah Violet, an administrator managing the platform.');

INSERT INTO classroom (name, capacity, is_occupied)
VALUES ('101 호', 30, FALSE),
       ('102 호', 40, TRUE),
       ('103 호', 50, FALSE),
       ('104 호', 35, TRUE),
       ('105 호', 45, FALSE),
       ('106 호', 25, TRUE),
       ('107 호', 60, FALSE),
       ('108 호', 55, TRUE),
       ('109 호', 70, FALSE),
       ('110 호', 80, TRUE);

INSERT INTO course (title, description, start_date, end_date, instructor_id, classroom_id)
VALUES ('자바 프로그래밍 기초', '자바 언어의 기초 문법과 객체 지향 프로그래밍을 배우는 강의입니다.', '2024-01-01', '2024-06-01', 2, 1),
       ('파이썬 프로그래밍 입문', '초보자를 위한 파이썬 프로그래밍 기본 강의입니다.', '2024-01-01', '2024-06-01', 3, 2),
       ('웹 개발 실전', 'HTML, CSS, 자바스크립트를 활용한 웹사이트 구축 실습 강의입니다.', '2024-01-01', '2024-06-01', 2, 1),
       ('데이터베이스 기초', 'SQL 문법과 MySQL을 활용한 데이터베이스 설계 및 관리 방법을 배웁니다.', '2024-01-01', '2024-06-01', 4,
        2),
       ('알고리즘과 자료구조', '알고리즘 문제 해결 능력을 향상시키기 위한 자료구조 강의입니다.', '2024-01-01', '2024-06-01', 5, 1),
       ('프론트엔드 개발', 'React와 JavaScript를 활용한 모던 프론트엔드 개발 기초 강의입니다.', '2024-01-01', '2024-06-01', 6,
        2),
       ('백엔드 개발 실습', 'Spring Boot를 활용한 백엔드 애플리케이션 개발 기초 강의입니다.', '2024-01-01', '2024-06-01', 7, 1),
       ('머신러닝 입문', 'Python과 TensorFlow를 이용한 인공지능과 머신러닝 기초 강의입니다.', '2024-01-01', '2024-06-01', 8,
        2),
       ('데이터 분석 기초', 'Python을 활용한 데이터 분석 및 시각화 실습 강의입니다.', '2024-01-01', '2024-06-01', 2, 1),
       ('소프트웨어 공학', '효율적인 소프트웨어 개발 방법론과 도구들을 배우는 강의입니다.', '2024-01-01', '2024-06-01', 9, 2);

INSERT INTO seat (course_id, seat_number, is_exist, is_online, member_id)
VALUES (1, 'A1', TRUE, TRUE, NULL),
       (1, 'A2', TRUE, TRUE, NULL),
       (1, 'A3', TRUE, FALSE, NULL),
       (1, 'A4', TRUE, FALSE, NULL);
       (2, 'B1', TRUE, TRUE, 2),
       (2, 'B2', TRUE, FALSE, NULL),
       (2, 'B3', TRUE, FALSE, 7),
       (2, 'B4', TRUE, FALSE, NULL),
       (2, 'B5', TRUE, FALSE, NULL),
       (2, 'B6', TRUE, TRUE, 11),
       (2, 'B7', TRUE, TRUE, NULL),
       (2, 'B8', TRUE, TRUE, NULL),
       (2, 'B9', TRUE, TRUE, NULL);


INSERT INTO student_course (student_id, course_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (4, 1),
       (4, 2),
       (5, 1),
       (5, 2);

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

INSERT INTO submission (assignment_id, student_id, title, content, submission_url, submission_date)
VALUES (1, 1, '제출물 1', '제출물 1에 대한 내용입니다.',
        'https://www.moti.co.kr/data/item/1701766013/thumb-64iE64Sk652g64Sk_7I2464Sk7J28_01_600x600.png',
        '2024-04-30'),
       (2, 1, '제출물 2', '제출물 2에 대한 내용입니다.',
        'https://kubakery.co.kr/wp-content/uploads/2015/02/bbang-0053.jpg', '2024-05-15'),
       (3, 2, '제출물 1', '제출물 3에 대한 내용입니다.',
        'https://upload.wikimedia.org/wikipedia/commons/b/b2/누네띠네_사진.jpg', '2024-06-01'),
       (4, 2, '제출물 2', '제출물 4에 대한 내용입니다.',
        'https://jjmall.shop/web/product/big/202303/00587e7d7430563103bbb6194dfcc3ed.jpg',
        '2024-06-15'),
       (5, 3, '제출물 1', '제출물 5에 대한 내용입니다.',
        'https://image.auction.co.kr/itemimage/30/16/27/3016278b36.jpg', '2024-07-01'),
       (6, 3, '제출물 2', '제출물 6에 대한 내용입니다.',
        'https://www.moti.co.kr/data/item/1701766013/thumb-64iE64Sk652g64Sk_7I2464Sk7J28_01_600x600.png',
        '2024-07-15'),
       (7, 4, '제출물 3', '제출물 7에 대한 내용입니다.',
        'https://kubakery.co.kr/wp-content/uploads/2015/02/bbang-0053.jpg', '2024-08-01'),
       (8, 4, '제출물 4', '제출물 8에 대한 내용입니다.',
        'https://upload.wikimedia.org/wikipedia/commons/b/b2/누네띠네_사진.jpg', '2024-08-15'),
       (9, 5, '제출물 5', '제출물 9에 대한 내용입니다.',
        'https://jjmall.shop/web/product/big/202303/00587e7d7430563103bbb6194dfcc3ed.jpg',
        '2024-09-01'),
       (10, 5, '제출물 6', '제출물 10에 대한 내용입니다.',
        'https://image.auction.co.kr/itemimage/30/16/27/3016278b36.jpg', '2024-09-15');
INSERT INTO board_type (description)
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

INSERT INTO board (board_type_id)
VALUES (1),
       (1),
       (1),
       (1),
       (2),
       (2),
       (2),
       (2),
       (3),
       (3);
INSERT INTO post (board_id, course_id, author_id, title, content, created_at)
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

INSERT INTO vote (title, course_id, description, start_date, end_date, is_expired, creator_id,
                  options, responses)
VALUES ('교장 선거', 1, '다음 교장 선거에 대한 투표입니다.', '2024-04-01 09:00:00', '2024-04-10 18:00:00', FALSE, 1,
        '{
          "Option A": 0,
          "Option B": 0
        }', '{
    "Option A": 30,
    "Option B": 20
  }'),
       ('베스트 프로젝트', 1, '베스트 프로젝트에 대한 투표입니다.', '2024-04-15 09:00:00', '2024-04-20 18:00:00', FALSE,
        2, '{
         "Project 1": 0,
         "Project 2": 0
       }', '{
         "Project 1": 25,
         "Project 2": 30
       }'),
       ('이벤트 날짜 결정', 1, '이벤트 날짜에 대한 투표입니다.', '2024-05-01 09:00:00', '2024-05-05 18:00:00', FALSE, 3,
        '{
          "Option 1": 0,
          "Option 2": 0
        }', '{
         "Option 1": 40,
         "Option 2": 10
       }'),
       ('과목 연장 투표', 1, '과목 연장에 대한 투표입니다.', '2024-06-01 09:00:00', '2024-06-10 18:00:00', TRUE, 4,
        '{
          "Yes": 0,
          "No": 0
        }', '{
         "Yes": 35,
         "No": 15
       }'),
       ('신규 클럽形成', 2, '신규 클럽 형성에 대한 투표입니다.', '2024-07-01 09:00:00', '2024-07-10 18:00:00', FALSE,
        5, '{
         "Approve": 0,
         "Reject": 0
       }', '{
         "Approve": 28,
         "Reject": 12
       }'),
       ('시험 일정 결정', 2, '시험 일정에 대한 투표입니다.', '2024-07-15 09:00:00', '2024-07-20 18:00:00', FALSE, 6,
        '{
          "Morning": 0,
          "Afternoon": 0
        }', '{
         "Morning": 22,
         "Afternoon": 18
       }'),
       ('필드 트립 목적지', 2, '필드 트립 목적지에 대한 투표입니다.', '2024-08-01 09:00:00', '2024-08-10 18:00:00', FALSE,
        7, '{
         "Beach": 0,
         "Mountains": 0
       }', '{
         "Beach": 15,
         "Mountains": 25
       }'),
       ('선생님 투표', 3, '선생님에 대한 투표입니다.', '2024-08-15 09:00:00', '2024-08-20 18:00:00', FALSE, 8,
        '{
          "Teacher A": 0,
          "Teacher B": 0
        }', '{
         "Teacher A": 18,
         "Teacher B": 12
       }'),
       ('신규 과목 추가', 3, '신규 과목 추가에 대한 투표입니다.', '2024-09-01 09:00:00', '2024-09-05 18:00:00', TRUE, 9,
        '{
          "Approve": 0,
          "Reject": 0
        }', '{
         "Approve": 20,
         "Reject": 15
       }'),
       ('휴일 이벤트 계획', 4, '휴일 이벤트 계획에 대한 투표입니다.', '2024-09-15 09:00:00', '2024-09-20 18:00:00', TRUE,
        10, '{
         "Activity 1": 0,
         "Activity 2": 0
       }', '{
         "Activity 1": 22,
         "Activity 2": 18
       }');

INSERT INTO question (member_id, content, created_at, updated_at, is_solved, is_recommended)
VALUES (1, '자바에서 오버로딩은 무엇을 의미하는지 설명해주세요.', '2024-01-10 10:00:00', '2024-01-10 10:00:00', FALSE,
        FALSE),
       (2, '객체 지향 프로그래밍의 장점은 무엇이 있나요?', '2024-01-11 11:00:00', '2024-01-11 11:00:00', TRUE, TRUE),
       (3, '파이썬에서 리스트와 튜플의 차이점은 무엇인가요?', '2024-01-12 12:00:00', '2024-01-12 12:00:00', FALSE,
        FALSE),
       (4, '함수형 프로그래밍의 특징은 무엇이 있나요?', '2024-01-13 13:00:00', '2024-01-13 13:00:00', TRUE, FALSE),
       (5, '객체 지향 프로그래밍에서 상속은 무엇을 의미하는지 설명해주세요.', '2024-01-14 14:00:00', '2024-01-14 14:00:00',
        FALSE, TRUE),
       (1, '자바스크립트에서 콜백 함수는 무엇을 의미하는지 설명해주세요.', '2024-01-15 15:00:00', '2024-01-15 15:00:00', TRUE,
        FALSE),
       (2, '함수 오버로딩과 함수 오버라이딩의 차이점은 무엇인가요?', '2024-01-16 16:00:00', '2024-01-16 16:00:00', TRUE,
        TRUE),
       (3, '파이썬에서 데코레이터는 무엇을 의미하는지 설명해주세요.', '2024-01-17 17:00:00', '2024-01-17 17:00:00', FALSE,
        FALSE),
       (4, '객체 지향 프로그래밍에서 캡슐화는 무엇을 의미하는지 설명해주세요.', '2024-01-18 18:00:00', '2024-01-18 18:00:00',
        TRUE, FALSE),
       (5, '자바에서 인터페이스는 무엇을 의미하는지 설명해주세요.', '2024-01-19 19:00:00', '2024-01-19 19:00:00', FALSE,
        TRUE);

INSERT INTO answer (question_id, teacher_id, content, created_at)
VALUES (1, 2, '자바에서 오버로딩은 동일한 이름의 메서드가 여러 개 존재할 수 있도록 허용하는 기능입니다.', '2024-01-10 11:00:00'),
       (2, 3, '객체 지향 프로그래밍의 장점은 재사용성, 유지보수성, 확장성이 높아진다는 것입니다.', '2024-01-11 12:00:00'),
       (3, 4, '파이썬에서 리스트는 변경 가능한 자료형이고, 튜플은 변경 불가능한 자료형입니다.', '2024-01-12 13:00:00'),
       (4, 5, '함수형 프로그래밍의 특징은 순수 함수, 불변성, 고차 함수 등이 있습니다.', '2024-01-13 14:00:00'),
       (5, 2, '객체 지향 프로그래밍에서 상속은 부모 클래스의 속성과 메서드를 자식 클래스가 물려받는 것을 의미합니다.', '2024-01-14 15:00:00'),
       (6, 3, '자바스크립트에서 콜백 함수는 함수를 인자로 전달하거나 반환하는 것을 의미합니다.', '2024-01-15 16:00:00'),
       (7, 4,
        '함수 오버로딩과 함수 오버라이딩의 차이점은 오버로딩은 동일한 이름의 함수가 여러 개 존재할 수 있도록 허용하는 반면, 오버라이딩은 상속받은 클래스에서 부모 클래스의 메서드를 재정의하는 것입니다.',
        '2024-01-16 17:00:00'),
       (8, 5, '파이썬에서 데코레이터는 함수를 꾸며주는 역할을 하는데, 함수를 호출하기 전에 또는 호출 후에 추가적인 작업을 수행할 수 있습니다.',
        '2024-01-17 18:00:00'),
       (9, 2, '객체 지향 프로그래밍에서 캡슐화는 데이터와 메서드를 하나의 단위로 묶어 외부에서 접근할 수 없도록 하는 것을 의미합니다.',
        '2024-01-18 19:00:00'),
       (10, 3, '자바에서 인터페이스는 클래스가 구현해야 하는 메서드의 선언을 포함하는 추상 클래스입니다.', '2024-01-19 20:00:00');


INSERT INTO student_status (is_understanding, is_hand_raised, member_id)
VALUES (TRUE, FALSE, 1),
       (FALSE, TRUE, 2),
       (TRUE, TRUE, 3),
       (FALSE, FALSE, 4),
       (TRUE, TRUE, 5),
       (FALSE, FALSE, 6),
       (TRUE, TRUE, 7),
       (FALSE, TRUE, 8),
       (TRUE, FALSE, 9),
       (FALSE, TRUE, 10);