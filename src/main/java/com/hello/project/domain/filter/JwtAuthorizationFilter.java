package com.hello.project.domain.filter;


import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.user.User;
import com.hello.project.domain.user.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

//시큐리티의 BasicAuthentication filter는 권한 및 인증이 필요한 특정 주소를 요청했을 때 이 필터 탄다.
//만약 권한 인증이 필요한 주소가 아니면 이 필터를 거치지 않는다.

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwtHeader = request.getHeader("Authorization");
        System.out.println("===== 토큰이 와써염 : "+jwtHeader);

        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        //JWT토큰 검증해서 정상 사용자인지 확인
        String jwtToken = request.getHeader("Authorization").replace("Bearer ","");
        System.out.println("===== 정상 JWT 토큰인지 확인중~~ =======");
        Claims claims = Jwts.parserBuilder().setSigningKey("zz123456789012345678901234567890".getBytes()).build().parseClaimsJws(jwtToken).getBody();
        String username = (String) claims.get("username");

        if (username != null) {
            User userEntity = userRepository.findByUsername(username);
            PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
            //jwt 토큰 서명이 정상이면 Authentication 객체를 만들어준다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null,principalDetails.getAuthorities());

            //강제로 시큐리티의 세션에 접근해 authentication 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request,response);
        }

    }
}
