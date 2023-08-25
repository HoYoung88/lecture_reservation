package com.lecture.reservation.common.context;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by HoYoung on 2023/03/07.
 */
@Getter
@AllArgsConstructor
public enum DemoRequestHeaderKey {
    PLATFORM("X-Demo-Platform");

    private final String headerKey;

}
