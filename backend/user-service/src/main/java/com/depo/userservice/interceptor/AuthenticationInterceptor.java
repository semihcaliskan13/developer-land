package com.depo.userservice.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Value("${header.key}")
    private String HEADER_KEY;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {

        String authorizationHeader = request.getHeader("Authorization");
        String headerKey = authorizationHeader.substring(7);
        if (headerKey.equals(HEADER_KEY)) {
            return true;
        }
        response.setStatus(401);
        return false;
    }
}
