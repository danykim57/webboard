package com.webboard.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "";
        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            errorMessage = "아이디나 비밀번호가 맞지 않습니다.";
        }
        else if (exception instanceof DisabledException) {
            errorMessage = "계정이 비활성화되었습니다.";
        }
        else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "비밀번호 유효기간이 만료되었습니다.";
        }
        else {
            errorMessage = "알 수 없는 이유로 로그인이 불가능합니다.";
            log.error("로그인 인증 알 수 없는 에러 발생");
        }
        request.setAttribute("errorMessage", "error");
        response.sendRedirect("login-fail");
    }
}
