package com.example.jwt_login_post.account.service;

import com.example.jwt_login_post.account.dto.CommentDto;
import com.example.jwt_login_post.account.entity.Comment;
import com.example.jwt_login_post.account.entity.Post;
import com.example.jwt_login_post.account.repository.CommentRepository;
import com.example.jwt_login_post.account.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, CommentDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        comment.update(requestDto);
        return comment.getId();
    }

    public Comment createComment(CommentDto requestDto, String email) {
        // requestDto에 있는 postId를 이용해서 post를 들고옵니다. (postRepository를 사용)
        // a게시글을 들고옴
        Post post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("d"));

        // b댓글을 저장할 때 a 게시글을 같이 저장합니다.
        // requestdto에서 값을 들고와서 comment에 넣어주는 방법과 비슷하게 해봅시다.
        Comment comment = new Comment(email, requestDto, post);
        commentRepository.save(comment);
        return comment;
    }
}