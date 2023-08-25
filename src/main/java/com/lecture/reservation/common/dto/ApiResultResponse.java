package com.lecture.reservation.common.dto;

import com.lecture.reservation.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by HoYoung on 2023/03/07.
 */
@Getter
@ToString
@AllArgsConstructor(staticName = "ofResponse")
public class ApiResultResponse<T> {

    private final String code;
    private final String message;
    private final T data;

    public static <T> ApiResultResponse<T> ofResponse(T data) {
        return new ApiResultResponse<>("SUCCESS", "", data);
    }

    public static ApiResultResponse<Void> ofResponse(String code, String message) {
        return new ApiResultResponse<>(code, message, null);
    }

    public static ApiResultResponse<Void> ofResponse(ErrorCode errorCode, String message) {
        return ofResponse(errorCode.name(), message);
    }

}
