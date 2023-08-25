package com.lecture.reservation.common.filters;

import com.lecture.reservation.common.context.DemoRequestHeaderContext;
import com.lecture.reservation.common.context.DemoRequestHeaderContextHolder;
import com.lecture.reservation.common.context.DemoRequestHeaderKey;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by HoYoung on 2023/03/07.
 */
@Component
public class RequestHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String platform = Optional.ofNullable(
                request.getHeader(DemoRequestHeaderKey.PLATFORM.getHeaderKey())).orElse("");
        DemoRequestHeaderContextHolder.setDemoRequestHeaderContext(
                new DemoRequestHeaderContext(platform));

        filterChain.doFilter(request, response);
    }
}
