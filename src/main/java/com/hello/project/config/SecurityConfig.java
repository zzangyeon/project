package com.hello.project.config;

import com.hello.project.config.jwt.JwtAccessDeniedHandler;
import com.hello.project.config.jwt.JwtAuthenticationEntryPoint;
import com.hello.project.config.jwt.JwtSecurityConfig;
import com.hello.project.config.jwt.TokenProvider;
import com.hello.project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@EnableWebSecurity // 기본적인 web보안 활성화하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAUthorize 를 메소드단위로 사용하기 위해 추가
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder encoded() {//암호화 할 떄 사용함.
		return new BCryptPasswordEncoder();
	}

	private final CorsFilter corsFilter;
	private final UserRepository userRepository;
	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				//.httpBasic().disable()
				.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.accessDeniedHandler(jwtAccessDeniedHandler)

				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

				.and()
				//.addFilter(corsFilter) //@CrossOrigin(인증없을떄 controller 메소드 위에 사용) , 이건 시큐리티 필터에 등록 인증O.
				//.formLogin().disable()
				.authorizeRequests()
				//.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/article/**").authenticated()
				.anyRequest().permitAll()

				.and()
				.apply(new JwtSecurityConfig(tokenProvider));
				//.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				//.addFilterBefore(new JwtAuthorizationFilter(authenticationManager(),userRepository),BasicAuthenticationFilter.class);//Authentication manager 던져줘야댐.

//	super.configure(http); 삭제 -> 기존 시큐리티가 가지고 있는 기능이 다 비활성화 됨.
	//403코드 - 미승인 사용자.
//	http.csrf().disable();//csrf 토큰검사 비활성화
//	http.authorizeRequests()
//		.antMatchers("/admin").authenticated()//인증이 필요한 주소
//		.anyRequest().permitAll()//그 외의 것들은 모두 허용
//		.and()
//		.formLogin()//인증이 필요한 저 url(위로세번째 줄)로 접근하면 폼로그인페이지로 옮길건데
//		.loginPage("/auth/signin")//GET 그 페이지가 이거임
//		.loginProcessingUrl("/login")//POST로 요청오면 ->spring security가 로그인프로세스진행. 회원가입 컨트롤러는 만들었지만 로그인 컨트롤러는 시큐리티가 해줌!
//		//spring security가 로그인 프로세스 진행시 userDetailsService를 실행.(principalDetailsService가 상속받아 @Service등록하여 pds가 로그인 진행)
//		.defaultSuccessUrl("/");//로그인성공하면 여기로 가게 할거임

	}	

}
