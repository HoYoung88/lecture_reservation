package com.lecture.reservation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Created by HoYoung on 2023/08/25.
 */
@Getter
@Builder
@ToString
public class LectureRequest {
    private final String speakerName;
    private final String venue;
    private final String content;
    private final LocalDateTime lectureTime;
}
