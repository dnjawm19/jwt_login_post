package com.example.jwt_login_post.account.controller;

import com.example.jwt_login_post.account.dto.AccountResponseDto;
import com.example.jwt_login_post.account.entity.Account;
import com.example.jwt_login_post.account.entity.Likes;
import com.example.jwt_login_post.account.repository.LikesRepository;
import com.example.jwt_login_post.account.service.AccountService;
import com.example.jwt_login_post.account.service.LikesService;
import com.example.jwt_login_post.jwt.util.JwtUtil;
import com.example.jwt_login_post.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/like")
public class LikesController {
    private final LikesService likesService;
    private final AccountService accountService;

    @PostMapping("/{postId}")
    public String createLike(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.createLike(postId, userDetails);
    }

    @GetMapping("/get")
    public AccountResponseDto getAccount(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return accountService.getAccount(userDetails);
    }

    // 좋아요 저장로직
    // 좋아요를 누른 경우 (좋아요 해제, 좋아요 추가)
    // front에서 받아와야 하는 정보 (postid, memberId)

    // 유저가 해당 게시글에 좋아요를 누른 여부 확인
    // Boolean bool = likeRepository.existsBy{User}And{Post}(userId, postId)
//    likesRepository.findByUserAndPost(user, post)

    // bool이 true면 좋아요를 누른 상태 -> 삭제 해주면 됩니다.
    // bool이 false면 좋아요를 안 누른 상태 -> 저장 해주면 됩니다.
}