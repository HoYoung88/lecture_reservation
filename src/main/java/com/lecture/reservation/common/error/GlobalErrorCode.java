package com.lecture.reservation.common.error;

/**
 * Created by HoYoung on 2023/03/07.
 */
public enum GlobalErrorCode implements ErrorCode {
    PAGE_NOT_FOUND,
    METHOD_NOT_SUPPORTED,
    MISSING_PATH_VARIABLE,
    MISSING_SERVLET_REQUEST_PARAMETER,
    INTERNAL_SERVER_ERROR
}
