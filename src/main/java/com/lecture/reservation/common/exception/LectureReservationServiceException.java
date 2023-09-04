package com.lecture.reservation.common.exception;

import com.lecture.reservation.common.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Created by HoYoung on 2023/03/07.
 */
@Getter
public class LectureReservationServiceException extends ResponseStatusException {
    private final ErrorCode errorCode;

    public LectureReservationServiceException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode.name());
        this.errorCode = errorCode;
    }

    public LectureReservationServiceException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus);
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.resolve(super.getStatusCode().value());
    }

}
