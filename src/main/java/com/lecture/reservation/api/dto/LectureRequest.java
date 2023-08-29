package com.lecture.reservation.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class LectureRequest {
    @Schema(description = "강연자")
    private final String speakerName;

    @Schema(description = "강연장")
    private final String venue;

    @Schema(description = "강연 내용")
    private final String content;

    @Schema(description = "강연 시작 시간")
    private final LocalDateTime lectureStartTime;

    @Schema(description = "최대 수용 가능한 인원 수")
    private final int maxCapacity;
}
