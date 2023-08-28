package com.lecture.reservation.api.dto;

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
public class LectureDetailResponse {
    private final Long id;
    private final String speakerName;
    private final String venue;
    private final String content;
    private final LocalDateTime lectureStartTime;
    private final int maxCapacity;

}
