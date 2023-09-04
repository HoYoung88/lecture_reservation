package com.lecture.reservation.event;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by HoYoung on 2023/09/01.
 */
@Getter
@Builder
public class NotifyEvent {
    private final String message;
}
