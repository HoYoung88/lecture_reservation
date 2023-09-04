package com.lecture.reservation.common.helper;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by HoYoung on 2023/09/04.
 */
@Component
@Getter
public class MessageSourceHelper implements MessageSourceAware {

    private MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, new Object[]{}, Locale.getDefault());
    }

    public String getMessage(String code, String defaultMessage) {
        return messageSource.getMessage(code, new Object[]{}, defaultMessage, Locale.getDefault());
    }

    public String getMessage(String code, String...args) {
        return messageSource.getMessage(code, args, Locale.getDefault());
    }
}
