package com.example.jwt_login_post.account.service;

import com.example.jwt_login_post.account.dto.AccountReqDto;
import com.example.jwt_login_post.account.dto.AccountResponseDto;
import com.example.jwt_login_post.account.dto.LoginReqDto;
import com.example.jwt_login_post.account.entity.Account;
import com.example.jwt_login_post.account.entity.Post;
import com.example.jwt_login_post.account.entity.RefreshToken;
import com.example.jwt_login_post.account.repository.AccountRepository;
import com.example.jwt_login_post.account.repository.RefreshTokenRepository;
import com.example.jwt_login_post.global.dto.GlobalResDto;
import com.example.jwt_login_post.jwt.dto.TokenDto;
import com.example.jwt_login_post.jwt.util.JwtUtil;
import com.example.jwt_login_post.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public GlobalResDto signup(AccountReqDto accountReqDto) {
        // email 중복 검사
        if(accountRepository.findByEmail(accountReqDto.getEmail()).isPresent()){
            throw new RuntimeException("Overlap Check");
        }

        accountReqDto.setEncodePwd(passwordEncoder.encode(accountReqDto.getPassword()));
        Account account = new Account(accountReqDto);

        accountRepository.save(account);
        return new GlobalResDto("Success signup", HttpStatus.OK.value());
    }

    @Transactional
    public GlobalResDto login(LoginReqDto loginReqDto, HttpServletResponse response) {

        Account account = accountRepository.findByEmail(loginReqDto.getEmail()).orElseThrow(
                () -> new RuntimeException("Not found Account")
        );

        if(!passwordEncoder.matches(loginReqDto.getPassword(), account.getPassword())) {
            throw new RuntimeException("Not matches Password");
        }

        TokenDto tokenDto = jwtUtil.createAllToken(loginReqDto.getEmail());

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccountEmail(loginReqDto.getEmail());

        if(refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        }else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginReqDto.getEmail());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);

        return new GlobalResDto("Success Login", HttpStatus.OK.value());

    }

    @Transactional
    public AccountResponseDto getAccount(UserDetailsImpl userDetails) {
        Account account = accountRepository.findById(userDetails.getAccount().getId()).orElseThrow(
                () -> new IllegalArgumentException("")
        );
        account.getLikes();
        return new AccountResponseDto(account);
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }
}