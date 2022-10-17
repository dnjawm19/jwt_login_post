package com.example.jwt_login_post.account.controller;

public class TestCon {
    // a 게시글이 있습니다.
    // b,c,d 라는 유저가 있습니다.

    // a를 좋아요 누른 사람 : b
    // a를 좋아요 안 누른 사람 : c, d

    // 유저가 볼 때 a게시글에 있는 좋아요 버튼이 한 개 라고 생각이 되죠?
    // b, c, d 좋아요를 누른 여부를 알 수가 없습니다.

    // b, c, d 가 좋아요를 누르면
    // a의 게시글 b의 좋아요
    // a의 게시글 c의 좋아요
    // a의 게시글 d의 좋아요

    // b, c, d 가 좋아요를 누르면
    // e의 게시글 b의 좋아요
    // e의 게시글 c의 좋아요
    // e의 게시글 d의 좋아요


    // b, c, 가 좋아요를 누르면
    // a의 게시글 b의 좋아요
    // a의 게시글 d의 좋아요

    // 조회할 때
    // b의 좋아요를 조회하는 겁니다.
    // 왼쪽 부분이 b가 누른 좋아요의 내역인거죠.
    // List<Like> like = likeRepository.findByUser(b);
}
