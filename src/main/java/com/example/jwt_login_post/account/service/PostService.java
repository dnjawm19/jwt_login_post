package com.example.jwt_login_post.account.service;

import com.example.jwt_login_post.account.dto.PostDto;
import com.example.jwt_login_post.account.dto.PostResponseDto;
import com.example.jwt_login_post.account.entity.Likes;
import com.example.jwt_login_post.account.entity.Post;
import com.example.jwt_login_post.account.repository.PostRepository;
import com.example.jwt_login_post.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post create(PostDto postDto, UserDetailsImpl userDetails){
        Post post = new Post(postDto,userDetails.getAccount());
        return postRepository.save(post);
    }

    @Transactional
    public List<Post> getAll() {
        for (Post posts : postRepository.findAll()){
            List<Likes> likes = posts.getLikes();
            int countLike = likes.size();
            posts.updateLikeCount(countLike);
        }
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public String update(Long id, PostDto requestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(post.getUserEmail().equals(userDetails.getAccount().getEmail())){
            post.update(requestDto);
            return "수정 완료";
        } else {
            return "작성자가 아닙니다.";
        }
    }

    @Transactional
    public PostResponseDto detail(Long postid){
        Post post = postRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("")
        );

        List<Likes> likes = post.getLikes();
        int countLike = likes.size();
        post.updateLikeCount(countLike);

        PostResponseDto postDto = new PostResponseDto(post);
        return postDto;
    }

    @Transactional
    public String deletePost(Long id, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if(post.getUserEmail().equals(userDetails.getAccount().getEmail())) {
            postRepository.deleteById(id);
            return "삭제 성공";
        }else{
            return "작성자가 아닙니다.";
        }
    }
}
