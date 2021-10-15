package com.hello.project.domain.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //id pw 정상적으로 로그인이 완료 되면 토큰을 만들어주고 그걸 응답을 해준다.
        //클라이언트는 요청할 대 마다 header Authorization에 value 값으로 토큰을 가지고온다.
        //그때 토큰이 넘어오면 내가 만든 토큰이 맞는지 검증만 하면됨.
        res.setCharacterEncoding("euc-kr");
        if (req.getMethod().equals("POST")) {
            System.out.println("post요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);

            if (headerAuth.equals("cos")) {
                chain.doFilter(req, res);
            } else {
                PrintWriter writer = res.getWriter();
                writer.println("인증안됨");
            }
        } else {
//            PrintWriter writer = res.getWriter();
//            writer.println("포스트요청하세요~");
//            System.out.println("=======My Filter1===========");
//            chain.doFilter(req, res);
            System.out.println("=====이자식떄문이네=====");
        }


    }
}
