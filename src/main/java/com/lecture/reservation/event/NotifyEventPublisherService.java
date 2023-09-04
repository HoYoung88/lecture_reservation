package com.lecture.reservation.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Created by HoYoung on 2023/09/01.
 */
@Service
@RequiredArgsConstructor
public class NotifyEventPublisherService {
    private final ApplicationEventPublisher eventPublisher;

    public void publishCustomEvent(String message) {
        NotifyEvent notifyEvent = NotifyEvent.builder().message(message).build();
        eventPublisher.publishEvent(notifyEvent);
    }
}
