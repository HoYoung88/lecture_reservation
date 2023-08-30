package com.lecture.reservation.common.filters;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Created by HoYoung on 2023/03/07.
 */
@Configuration
@Slf4j(topic = "REQUEST-LOG")
public class CustomCommonsRequestLoggingFilter extends CommonsRequestLoggingFilter {

    @Value("${springdoc.context-root}")
    private String springdocContextRoot;
    private StopWatch sw;

    @PostConstruct
    public void init() {
        setIncludeClientInfo(true);
        setIncludeQueryString(true);
        setIncludePayload(true);
        setIncludeHeaders(true);
        setMaxPayloadLength(1024 * 1024);
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        this.sw = new StopWatch();
        return !(new AntPathMatcher().match("/" + springdocContextRoot + "/**",
                request.getRequestURI()));
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        log.info("beforeRequest :: {}", this.createMessage(request));
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        log.info("afterRequest :: {}", this.createMessage(request));
    }

    private String createMessage(HttpServletRequest request) {
        if (this.sw.isRunning()) {
            this.sw.stop();
        } else {
            this.sw.start();
        }

        String msg = "request [";
        msg += request.getMethod() + ' ' + request.getRequestURI();
        msg += "\theaders=[]";

        val payload = getMessagePayload(request);
        if (payload != null) {
            msg += ", payload=" + payload;
        }

        msg += "]";
        msg += "\telapsedTime: " + this.sw.getTotalTimeMillis() + " ms";

        return msg;
    }
}
