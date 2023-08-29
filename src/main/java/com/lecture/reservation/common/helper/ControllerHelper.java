package com.lecture.reservation.common.helper;

import com.lecture.reservation.common.dto.ApiResultResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

/**
 * Created by HoYoung on 2023/03/07.
 */
@Getter
@Slf4j
public class ControllerHelper {

    protected <T> ResponseEntity<ApiResultResponse<T>> responseBody(T data) {
        return ResponseEntity.ok(ApiResultResponse.ofResponse(data));
    }

    protected <T> ResponseEntity<ApiResultResponse<T>> responseBody(String code, String message, T data) {
        return ResponseEntity.ok(ApiResultResponse.ofResponse(code, message, data));
    }

}
