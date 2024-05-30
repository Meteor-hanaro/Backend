package com.hana.app.service;

import com.hana.app.repository.UsersRepository;
import com.hana.app.security.jwt.JwtTokenProvider;
import com.hana.dto.response.UsersDto;
import com.hana.exception.NotFoundException;
import com.hana.response.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate redisTemplate;

    public UsersDto.TokenInfo login(String email, String password) {

        if (usersRepository.findByEmail(email) == null) {
            throw new NotFoundException(ErrorType.NOT_FOUND);
        }

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication;
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (AuthenticationException ex) {
            throw new NotFoundException(ErrorType.NOT_FOUND);
        }

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        UsersDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 4. RefreshToken Redis 저장 (expirationTime 설정을 통해 자동 삭제 처리)
        log.info("RT:" + authentication.getName()+" : "+tokenInfo.getRefreshToken()+" : "+tokenInfo.getRefreshTokenExpirationTime()+" : "+ TimeUnit.MILLISECONDS);
        redisTemplate.opsForValue().set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        // 5.
        log.info(tokenInfo + " : 로그인에 성공했습니다.");
        return tokenInfo;
    }
}
