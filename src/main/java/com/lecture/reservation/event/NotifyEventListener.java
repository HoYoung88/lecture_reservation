package com.lecture.reservation.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by HoYoung on 2023/09/01.
 */
@Component
public class NotifyEventListener {

    @EventListener
    public void onApplicationEvent(NotifyEvent event) {
        System.out.println("Received Notify event: " + event.getMessage());
    }
}
