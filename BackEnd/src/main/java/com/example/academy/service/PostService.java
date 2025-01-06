package com.example.academy.service;

import com.example.academy.domain.BoardType;
import com.example.academy.domain.Course;
import com.example.academy.domain.Member;
import com.example.academy.domain.Post;
import com.example.academy.domain.StudentCourse;
import com.example.academy.dto.member.CustomUserDetails;
import com.example.academy.dto.post.PostCreateDTO;
import com.example.academy.dto.post.PostResponseDTO;
import com.example.academy.dto.post.PostUpdateDTO;
import com.example.academy.enums.BoardTypes;
import com.example.academy.enums.MemberRole;
import com.example.academy.exception.common.NotFoundException;
import com.example.academy.exception.common.UnauthorizedException;
import com.example.academy.exception.post.PostBadRequestException;
import com.example.academy.exception.post.PostEmptyTitleException;
import com.example.academy.exception.post.PostNotFoundException;
import com.example.academy.mapper.post.PostCreateMapper;
import com.example.academy.mapper.post.PostResponseMapper;
import com.example.academy.repository.mysql.BoardTypeRepository;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.MemberRepository;
import com.example.academy.repository.mysql.PostRepository;
import com.example.academy.repository.mysql.StudentCourseRepository;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;
    private final BoardTypeRepository boardTypeRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final PostResponseMapper postResponseMapper;
    private final PostCreateMapper postCreateMapper;

    // 로그인된 유저 정보에 접근할 수 있는 서비스
    private final AuthService authService;

    public PostService(PostRepository postRepository, MemberRepository memberRepository,
        CourseRepository courseRepository, PostResponseMapper postResponseMapper,
        StudentCourseRepository studentCourseRepository,
        BoardTypeRepository boardTypeRepository,
        PostCreateMapper postCreateMapper,
        AuthService authService) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.courseRepository = courseRepository;
        this.postResponseMapper = postResponseMapper;
        this.postCreateMapper = postCreateMapper;
        this.boardTypeRepository = boardTypeRepository;
        this.studentCourseRepository = studentCourseRepository;
        this.authService = authService;
    }

    public PostResponseDTO findById(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("해당 게시글이 없습니다 ID: " + id));
        return postResponseMapper.toDto(post);
    }

    public List<PostResponseDTO> findAllPosts() {
//        List<Post> posts = postRepository.findAll();

        List<Post> posts = postRepository.findAllPostsOrderByPriority();

//        return postResponseMapper.toDtoList(posts).stream()
//            .sorted(Comparator.comparing(PostResponseDTO::getId).reversed()).toList();
        return postResponseMapper.toDtoList(posts);
    }

    public List<PostResponseDTO> getCourseNotices(Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new NotFoundException("해당 강의가 없습니다."));

        List<Post> posts = postRepository.findByCourse(course);

        if (!posts.isEmpty()) {
            return postResponseMapper.toDtoList(posts).stream()
                .filter(post -> BoardTypes.공지사항.name().equals(post.getBoardType()))
                .sorted(Comparator.comparing(PostResponseDTO::getId).reversed())
                .toList();
        } else {
            return Collections.emptyList(); // 빈 리스트 반환
        }

    }

    public List<PostResponseDTO> getCourseFreePost(Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new NotFoundException("해당 강의가 없습니다."));

        List<Post> posts = postRepository.findByCourse(course);

        if (!posts.isEmpty()) {
            return postResponseMapper.toDtoList(posts).stream()
                .filter(post -> BoardTypes.일반.name().equals(post.getBoardType()))
                .sorted(Comparator.comparing(PostResponseDTO::getId).reversed())
                .toList();
        } else {
            return Collections.emptyList(); // 빈 리스트 반환
        }
    }

    public List<PostResponseDTO> findAllFreePosts() {
        List<Post> posts = postRepository.findAll()
            .stream()
            .filter(post -> post.getBoardType().getType().equals(BoardTypes.일반.name()))
            .toList();

        return postResponseMapper.toDtoList(posts);
    }

    public List<PostResponseDTO> findAllNotificationPosts() {
        List<Post> posts = postRepository.findAll()
            .stream()
            .filter(post -> post.getBoardType().getType().equals(BoardTypes.공지사항.name()))
            .toList();
        return postResponseMapper.toDtoList(posts);
    }

    @Transactional
    public PostResponseDTO save(PostCreateDTO postDTO) {
        CustomUserDetails user = authService.getAuthenticatedUser();

        Member member = memberRepository.findById(user.getUserId())
            .orElseThrow(PostBadRequestException::new);

        BoardType boardType = boardTypeRepository.findById(postDTO.getBoardTypeId())
            .orElseThrow(PostBadRequestException::new);

        Course course = null;
        Post savedPost;

        // 코스 ID가 없을 경우 전체 게시글로 저장
        if (postDTO.getCourseId() != null && postDTO.getCourseId() != 0) {
            // 학생 강의 조회, 관리자의 경우 강의가 없을 수 있음
            if (!member.isAdmin()) {
                if (member.getMemberType().getType().equals(MemberRole.ROLE_TEACHER.name())) {
                    course = courseRepository.findByInstructor_Id(member.getId())
                        .orElseThrow(() -> new NotFoundException("배정 받은 강의가 없습니다."));
                }
                StudentCourse studentCourse = studentCourseRepository.findByStudentId(
                    member.getId());
                if (studentCourse != null) {
                    course = studentCourse.getCourse();
                }
            } else {
                course = courseRepository.findById(postDTO.getCourseId())
                    .orElseThrow(() -> new NotFoundException("해당 과정이 존재하지 않습니다."));
            }

            if (boardType.getType().equals(BoardTypes.공지사항.name()) && !member.getMemberType()
                .getType()
                .equals(MemberRole.ROLE_ADMIN.name())) {
                throw new UnauthorizedException();
            }
            savedPost = postCreateMapper.toEntity(postDTO, member, boardType, course);
        } else {
            // courseId가 0인 경우 전체 게시글로 저장
            savedPost = postCreateMapper.toEntity(postDTO, member, boardType);
        }

        postRepository.save(savedPost);

        return postResponseMapper.toDto(savedPost);
    }

    @Transactional
    public PostResponseDTO update(PostUpdateDTO postDTO) {
        CustomUserDetails user = authService.getAuthenticatedUser();

        // 회원 존재 여부 확인
        Member member = memberRepository.findById(user.getUserId())
            .orElseThrow(() -> new NotFoundException("등록된 사용자가 아닙니다."));

        // 게시글 존재 여부 확인
        Post updatedPost = postRepository.findById(postDTO.getId())
            .orElseThrow(() -> new PostNotFoundException(postDTO.getId()));

        // 게시글 작성자 또는 관리자 권한 확인
        if (!updatedPost.getAuthor().getId().equals(member.getId()) && !member.isAdmin()) {
            throw new UnauthorizedException();
        }

        // 제목이 유효한지 확인
        if (postDTO.getTitle() == null || postDTO.getTitle().trim().isEmpty()) {
            throw new PostEmptyTitleException(postDTO.getId());
        }

        // 강의 정보가 있을 경우 강의 유효성 체크
        Course course = null;

        if (!member.isAdmin()) {
            // 학생 강의 조회
            StudentCourse studentCourse = studentCourseRepository.findByStudentId(member.getId());
            if (studentCourse != null) {
                course = studentCourse.getCourse();
            }
        }

        // 관리자가 아닌 사용자의 경우, 게시글 작성자가 수강 중인 강의인지 확인
        if (course != null) {
            studentCourseRepository.findByStudentIdAndCourseId(updatedPost.getAuthor().getId(),
                    course.getId())
                .orElseThrow(() -> new NotFoundException("회원이 수강 중인 강의번호와 일치하지 않습니다"));
        }

        // 게시글 제목과 내용 수정
        updatedPost.updateTitle(postDTO.getTitle());
        updatedPost.updateContent(postDTO.getContent());

        // 게시글 저장
        postRepository.save(updatedPost);

        return postResponseMapper.toDto(updatedPost);
    }


    @Transactional
    public void delete(List<Long> ids) {
        CustomUserDetails user = authService.getAuthenticatedUser();

        if (ids.isEmpty()) {
            throw new PostBadRequestException("삭제할 게시글이 없습니다");
        }

        for (Long id : ids) {
            Post deletedPost = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

            // 게시글 작성자 확인
            if (!deletedPost.getAuthor().getId().equals(user.getUserId()) &&
                !user.getUserType().equals(MemberRole.ROLE_ADMIN)) {
                throw new UnauthorizedException("삭제 권한이 없습니다.");
            }

            postRepository.delete(deletedPost);
        }
    }


    public PostResponseDTO getCourseOnePost(Long courseId, Long id) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new NotFoundException("해당 강의가 없습니다."));

        // 해당 강의에 속하는 게시물 목록을 가져옵니다.
        List<Post> posts = postRepository.findByCourse(course);

        // 게시물이 하나도 없을 경우 예외 처리
        if (posts.isEmpty()) {
            throw new PostNotFoundException("해당 강의에 게시물이 없습니다.");
        }

        // 게시물 조회
        Post post = posts.stream()
            .filter(p -> p.getId().equals(id)) // postId로 필터링
            .findFirst() // 첫 번째 요소 찾기
            .orElseThrow(() -> new PostNotFoundException("해당 게시글이 존재하지 않습니다."));

        // 게시물이 강의에 속하는지 확인
        if (!post.getCourse().equals(course)) {
            throw new UnauthorizedException("이 게시물은 해당 강의에 속하지 않습니다.");
        }

        return postResponseMapper.toDto(post);
    }

    public List<PostResponseDTO> getCourseAllPost(Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new NotFoundException("해당 강의가 없습니다."));

        // 공지사항 타입과 courseId가 null인 게시글 가져오기
        BoardType announcementType = boardTypeRepository.findByType(BoardTypes.공지사항.name())
            .orElseThrow(() -> new NotFoundException("공지사항 타입이 없습니다."));
        List<Post> generalAnnouncements = postRepository.findByCourseIsNullAndBoardType(
            announcementType);

        List<Post> posts = postRepository.findByCourse(course);

        if (!posts.isEmpty()) {
            return Stream.concat(generalAnnouncements.stream(), posts.stream())
                .map(postResponseMapper::toDto)
                .sorted(Comparator
                    .comparing((PostResponseDTO post) -> post.getCourseId() == null ? 0
                        : 1) // courseId가 null인 게시글을 상위로 배치
                    .thenComparing(
                        (PostResponseDTO post) -> BoardTypes.공지사항.name().equals(post.getBoardType())
                            ? 0
                            : 1) // boardType이 '공지사항'인 게시글을 그 다음에 배치
                    .thenComparing(PostResponseDTO::getId,
                        Comparator.reverseOrder())) // ID 내림차순으로 정렬
                .collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // 빈 리스트 반환
        }
    }

    public List<PostResponseDTO> getUserCourseFreePosts() {
        CustomUserDetails user = authService.getAuthenticatedUser();
        Course course;

        // 강사일 경우 강의 ID 가져오기
        if (user.getUserType().equals(MemberRole.ROLE_TEACHER)) {
            course = courseRepository.findByInstructor_Id(user.getUserId())
                .orElseThrow(() -> new NotFoundException("배정된 강의가 없습니다."));
        } else {
            // 학생일 경우 강의 ID 가져오기
            StudentCourse studentCourse = studentCourseRepository.findByStudentId(user.getUserId());
            if (studentCourse == null) {
                return Collections.emptyList();
            }
            course = studentCourse.getCourse();
        }

        // 강의에 맞는 게시글 목록을 가져와서 공지사항을 제외하고 반환
        List<Post> freePosts = postRepository.findByCourse(course);
        return freePosts.stream()
            .filter(post -> !post.getBoardType().getType().equals(BoardTypes.공지사항.name()))
            .map(postResponseMapper::toDto)
            .sorted(Comparator.comparing(PostResponseDTO::getId).reversed())
            .toList();
    }

    public List<PostResponseDTO> getUserCourseNotifications() {
        CustomUserDetails user = authService.getAuthenticatedUser();
        Course course;

        // 강사일 경우 강의 ID 가져오기
        if (user.getUserType().equals(MemberRole.ROLE_TEACHER)) {
            course = courseRepository.findByInstructor_Id(user.getUserId())
                .orElseThrow(() -> new NotFoundException("배정된 강의가 없습니다."));
        } else {
            // 학생일 경우 강의 ID 가져오기
            StudentCourse studentCourse = studentCourseRepository.findByStudentId(user.getUserId());
            if (studentCourse == null) {
                return Collections.emptyList();
            }
            course = studentCourse.getCourse();
        }

        // 공지사항 타입과 courseId가 null인 게시글 가져오기
        BoardType announcementType = boardTypeRepository.findByType(BoardTypes.공지사항.name())
            .orElseThrow(() -> new NotFoundException("공지사항 타입이 없습니다."));
        List<Post> generalAnnouncements = postRepository.findByCourseIsNullAndBoardType(
            announcementType);

        // 해당 강의의 공지사항 게시글 가져오기
        List<Post> courseNotifications = postRepository.findByCourse(course).stream()
            .filter(post -> post.getBoardType().getType().equals(BoardTypes.공지사항.name()))
            .toList();

        // 두 리스트를 합쳐서 공지사항 게시글을 상위에 배치
        return Stream.concat(generalAnnouncements.stream(), courseNotifications.stream())
            .map(postResponseMapper::toDto)
            .sorted(Comparator.comparing((PostResponseDTO post) ->
                    post.getCourseId() == null ? 0 : 1)  // courseId가 null인 게시글을 상위로 배치
                .thenComparing(PostResponseDTO::getId, Comparator.reverseOrder()))
            .collect(Collectors.toList());
    }
}
