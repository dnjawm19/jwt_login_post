package com.example.jwt_login_post.account.service;

import com.example.jwt_login_post.account.entity.Account;
import com.example.jwt_login_post.account.entity.Likes;
import com.example.jwt_login_post.account.entity.Post;
import com.example.jwt_login_post.account.repository.AccountRepository;
import com.example.jwt_login_post.account.repository.LikesRepository;
import com.example.jwt_login_post.account.repository.PostRepository;
import com.example.jwt_login_post.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final PostRepository postRepository;
    private final LikesRepository likesRepository;


    @Transactional
    public String createLike(Long postId, UserDetailsImpl userDetails) {
        Account account = userDetails.getAccount();
        // requestDto에 있는 postId를 이용해서 post를 들고옵니다. (postRepository를 사용)
        // a게시글을 들고옴
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("포스트 없음"));

//        Account account = accountRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("이메일 존재하지 않음"));
//        Boolean bool = likesRepository.existsByPostAndEmail(postId, email);

        if(!likesRepository.existsByPostAndAccount(post, account)){
            Likes likes = new Likes(post, account);
            likesRepository.save(likes);
            return "좋아요!";
        }else {
            likesRepository.deleteByPostAndAccount(post, account);
            return "좋아요 취소";
        }

        // 좋아요를 누른 상태인지, 누르지 않은 상태인지 확인할 필요가 있습니다.
//        Optional<Likes> foundLike = likesRepository.findByPostAndAccount(post, account);
//        if (foundLike.isPresent()) {
//            likesRepository.delete(foundLike.get());
//            List<Likes> likes1 = post.getLikes();
//            System.out.println(likes1.size());
//            return "좋아요 취소!";
//        } else {
//            Likes likes = new Likes(post, account);
//            likesRepository.save(likes);
//            List<Likes> likes1 = post.getLikes();
//            System.out.println(likes1.size());
//            return "좋아요";
//        }
    }
}