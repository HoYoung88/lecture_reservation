package com.lecture.reservation.common.exception.handler;

import com.lecture.reservation.common.dto.ApiResultResponse;
import com.lecture.reservation.common.error.ErrorCode;
import com.lecture.reservation.common.error.GlobalErrorCode;
import com.lecture.reservation.common.exception.LectureReservationServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by HoYoung on 2023/03/07.
 */
@RestControllerAdvice
public class LectureReservationServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = LectureReservationServiceException.class)
    protected ResponseEntity<Object> handleServiceException(LectureReservationServiceException ex,
                                                            WebRequest request) {

        ApiResultResponse<Void> apiResultResponse = this.createApiResultResponse(ex.getErrorCode());
        return this.handleExceptionInternal(ex, apiResultResponse, HttpHeaders.EMPTY,
                ex.getStatusCode(), request);
    }

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex,
                                                            WebRequest request) {

        return this.handleExceptionInternal(ex, null, HttpHeaders.EMPTY,
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatusCode status,
                                                                   WebRequest request) {

        ApiResultResponse<Void> apiResultResponse = this.createApiResultResponse(
                GlobalErrorCode.PAGE_NOT_FOUND, ex.getMessage());
        return this.handleExceptionInternal(ex, apiResultResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatusCode status,
                                                                         WebRequest request) {

        ApiResultResponse<Void> apiResultResponse = this.createApiResultResponse(
                GlobalErrorCode.METHOD_NOT_SUPPORTED, ex.getMessage());
        return this.handleExceptionInternal(ex, apiResultResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
                                                               HttpHeaders headers,
                                                               HttpStatusCode status,
                                                               WebRequest request) {

        ApiResultResponse<Void> apiResultResponse = this.createApiResultResponse(
                GlobalErrorCode.MISSING_PATH_VARIABLE, ex.getMessage());
        return this.handleExceptionInternal(ex, apiResultResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatusCode status,
                                                                          WebRequest request) {

        ApiResultResponse<Void> apiResultResponse = this.createApiResultResponse(
                GlobalErrorCode.MISSING_SERVLET_REQUEST_PARAMETER, ex.getMessage());
        return this.handleExceptionInternal(ex, apiResultResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        return this.handleExceptionInternal(ex, null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatusCode statusCode,
                                                             WebRequest request) {

        if (body instanceof ApiResultResponse<?>) {
            return super.createResponseEntity(body, headers, statusCode, request);
        } else {

            ApiResultResponse<Void> apiResultResponse = this.createApiResultResponse(
                    GlobalErrorCode.INTERNAL_SERVER_ERROR);
            return super.createResponseEntity(apiResultResponse, headers, statusCode, request);
        }
    }

    private ApiResultResponse<Void> createApiResultResponse(ErrorCode errorCode) {
        return ApiResultResponse.ofResponse(errorCode,
                Objects.requireNonNull(super.getMessageSource())
                        .getMessage(errorCode.getMessageKey(), null,
                                Locale.getDefault()));
    }

    private ApiResultResponse<Void> createApiResultResponse(ErrorCode errorCode, String message) {
        return ApiResultResponse.ofResponse(errorCode, message);
    }

}
