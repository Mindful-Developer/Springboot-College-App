package com.busse.chrisbusse_comp303_assignement2.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;


@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static final List<String> PUBLIC_PATHS = Arrays.asList("/", "/login", "/register", "/css/", "/js/", "/images/");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getRequestURI();

        if (PUBLIC_PATHS.stream().anyMatch(requestPath::startsWith)) {
            return true;
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("student") == null) {
            response.sendRedirect("/?error=Please login first");
            return false;
        }

        return true;
    }
}