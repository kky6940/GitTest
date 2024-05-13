package com.example.company.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;//데이터베이스의 자료를 가져오는 기능, Spring Security에서 유저의 정보를 가져오는 인터페이스이다

    @Bean //객체를 생성
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {//패스워드를 암호화 시키는 클래스
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /* @formatter:off */
        http
                .authorizeRequests()
                .requestMatchers("/", "/empInsert", "/goodsInsert", "/bookInsert").permitAll() // 설정한 리소스의 접근을 인증절차 없이 허용(여기에 적힌 url은 인증없이 사용가능한 페이지)
                .anyRequest().authenticated() // 그 외 모든 리소스를 의미하며 인증 필요 -> 즉, 로그인을 하라는 뜻
                .and()
                .formLogin()
                .permitAll()
                .loginPage("/login") // 기본 로그인 페이지가 아닌 새로운 로그인 페이지 호출 설정(사용자 정의 로그인 페이지)
                .loginProcessingUrl("/loginProcess") //실제 로그인을 진행, 로그인 페이지의 폼에서 action을 loginProcess로 설정해주면 됨
                .usernameParameter("id")//기본값: username이므로 username의 파라미터는 id다 라고 설정
                .passwordParameter("pw")//기본값: password
                .defaultSuccessUrl("/")//로그인 성공시 보여줄 페이지
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)throws IOException, ServletException {
                        System.out.println("성공");
                        String userId = authentication.getName(); // 인증된 사용자의 ID를 가져옴
                        request.getSession().setAttribute("id", userId); // 세션에 사용자의 ID를 추가
                        response.sendRedirect("/");
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {//인증에 실패했을 때 어떤 처리를 할 것인지를 정해줌
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        System.out.println("exception : " + exception.getMessage());
                        System.out.println("아이디 : " + request.getParameter("username"));
                        System.out.println("패스워드 : " + request.getParameter("password"));
                        response.sendRedirect("/login");
                    }
                })
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/logout") // 로그아웃 URL (기본 값 : /logout)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 주소창에 요청해도 포스트로 인식하여 로그아웃
                .deleteCookies("JSESSIONID") // 로그아웃 시 JSESSIONID 제거
                .invalidateHttpSession(true) // 로그아웃 시 세션 종료
                .clearAuthentication(true); // 로그아웃 시 권한 제거

        return http.build();
        /* @formatter:on */
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {//인증 허가, AuthenticationManagerBuilder는 스프링 시큐리티의 인증에 대한 지원을 설정하는 몇 가지 메소드를 가지고 있다.
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}