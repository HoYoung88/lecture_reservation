package com.lecture.reservation.common.helper;

import com.lecture.reservation.common.context.DemoRequestHeaderContextHolder;
import com.lecture.reservation.common.dto.ApiResultResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by HoYoung on 2023/03/07.
 */
@Getter
@Slf4j
public class ControllerHelper {

    private String platform = "";

    @ModelAttribute
    public void context() {
        this.platform = DemoRequestHeaderContextHolder.getDemoRequestHeaderContext().platform();
    }

    protected <T> ResponseEntity<ApiResultResponse<T>> responseBody(T data) {
        return ResponseEntity.ok(ApiResultResponse.ofResponse(data));
    }

    protected <T> ResponseEntity<ApiResultResponse<T>> responseBody(String code, String message, T data) {
        return ResponseEntity.ok(ApiResultResponse.ofResponse(code, message, data));
    }

}
