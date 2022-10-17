package com.example.jwt_login_post.account.controller;

import com.example.jwt_login_post.account.dto.CommentDto;
import com.example.jwt_login_post.account.entity.Comment;
import com.example.jwt_login_post.account.repository.CommentRepository;
import com.example.jwt_login_post.account.service.CommentService;
import com.example.jwt_login_post.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    private final JwtUtil jwtUtil;

    @PostMapping("")           //댓글 생성
    public Comment createComment(@RequestBody CommentDto requestDto, @RequestHeader("ACCESS_TOKEN") String token) {
        String email = jwtUtil.getEmailFromToken(token);
        return commentService.createComment(requestDto,email);
    }

    @GetMapping("/get")         //댓글 전체 조회
    public List<Comment> getComment() {
        return commentRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/get/{id}")       //댓글 상세 조회
    public Optional<Comment> getCommentId(@PathVariable Long id) {
        return commentRepository.findById(id);
    }

    @PutMapping("/{id}")       //댓글 수정
    public Long updateComment(@PathVariable Long id, @RequestBody CommentDto requestDto) {
        commentService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/{id}")     //댓글 삭제
    public Long deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}
