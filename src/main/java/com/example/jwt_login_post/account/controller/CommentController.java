package com.example.jwt_login_post.account.controller;

import com.example.jwt_login_post.account.dto.CommentDto;
import com.example.jwt_login_post.account.entity.Comment;
import com.example.jwt_login_post.account.repository.CommentRepository;
import com.example.jwt_login_post.account.service.CommentService;
import com.example.jwt_login_post.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;


    @PostMapping("")           //댓글 생성
    public Comment createComment(@RequestBody CommentDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(requestDto,userDetails);
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
    public String updateComment(@PathVariable Long id, @RequestBody CommentDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.update(id, requestDto, userDetails);
    }

    @DeleteMapping("/{id}")     //댓글 삭제
    public String deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(id, userDetails);
    }
}
