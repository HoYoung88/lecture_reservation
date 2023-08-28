package com.lecture.reservation.api.exception;

import com.lecture.reservation.common.error.ErrorCode;

/**
 * Created by HoYoung on 2023/08/25.
 */
public enum LectureErrorCode implements ErrorCode {
    LECTURE_NOT_FOUND,
    LECTURE_DUPLICATE_RESERVATION,
    LECTURE_APPLICANT_MAX_CAPACITY;
}
