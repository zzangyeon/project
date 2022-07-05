package com.hello.project.domain.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.user.User;
import com.hello.project.domain.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//스프링 시큐리티에서 username어쩌구 필터가 있는데 원래 /login요청해서 username,password전송하면
// 이 필터가 동작을 함. 하지만 formLogin  disable했기 때문에 다시 등록해야댐.
@RequiredArgsConstructor
  public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    //  /login요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("========Jwt 로그인 시도중===========");

        String username = "";
        String password = "";

        // 1. username, password 받아서
        try {
            BufferedReader br = request.getReader();
            String input = null;
            while((input = br.readLine()) != null) {
                String[] params = input.split("&");
                username = params[0].split("=")[1];
                password = params[1].split("=")[1];
            }

//            ObjectMapper objectMapper = new ObjectMapper();
//            User user = objectMapper.readValue(request.getInputStream(), User.class);
//            System.out.println(user);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username,password);

            //2. authenticationManager로 로그인 시도를하면 PrincipaldetailsService 의 loadbyUsername()함수 실행됨
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            //3. return하면! authentication 객체가 session영역에 저장됨.
            //return 이유는 권한관리를 security가 대신해주기 때문! ( 권한 관리를 위해 세션에 저장 )
            request.setAttribute("username",username);
            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // attemptAuthentication 실행후 인증이 정상되면 successfulAuthentication함수가 실행됨.
    // 4. JWT토큰을 만들어서 request요청한 사용자에게 토큰 주면됨.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("============인증이 완료됨!==========");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        //hs256방식 / 토큰만들기
        String jwtToken = Jwts.builder()
                .setSubject("zzangyeontoken")
                .claim("id",principalDetails.getUser().getId())
                .claim("username",principalDetails.getUser().getUsername())
                .signWith(Keys.hmacShaKeyFor("zz123456789012345678901234567890".getBytes()), SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis()+(60000*10)))
                .compact();

        response.addHeader("Authorization","Bearer " + jwtToken);

        super.successfulAuthentication(request,response,chain,authResult);
    }
}
