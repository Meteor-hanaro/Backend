package com.hana.config;

import com.hana.security.jwt.JwtAuthenticationEntryPoint;
import com.hana.security.jwt.JwtAuthenticationFilter;
import com.hana.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig  {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate redisTemplate;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private static final String[] AUTH_BLACK_LIST = {
            "/api/pb/**", "/api/vip/**"
    };


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //CSRF, CORS
        http.csrf((csrf) -> csrf.disable());
        http.cors(Customizer.withDefaults());

        //세션 관리 상태 없음으로 구성, Spring Security가 세션 생성 or 사용 X
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));

        //FormLogin, BasicHttp 비활성화
        http.formLogin((form) -> form.disable());
        http.httpBasic(AbstractHttpConfigurer::disable);

        //JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
        http
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, redisTemplate), UsernamePasswordAuthenticationFilter.class);

        //error 처리
        http.exceptionHandling((exceptionHandling) -> exceptionHandling
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        );

        //권한 규칙 작성
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/pb/login", "/api/vip/login").permitAll() // 로그인 경로에 대한 예외 설정
                .requestMatchers(AUTH_BLACK_LIST).authenticated() // AUTH_BLACK_LIST에 정의된 경로들에 대해 인증이 필요함
                .anyRequest().permitAll()); // 그 외의 모든 요청은 인증 없이 허용함

        return http.build();
    }
}
