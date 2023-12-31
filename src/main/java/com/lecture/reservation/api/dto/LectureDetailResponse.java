package com.lecture.reservation.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by HoYoung on 2023/08/25.
 */

@Getter
@Builder
@ToString
@Schema(description = "강연 상세")
public class LectureDetailResponse {
    @Schema(accessMode = AccessMode.READ_ONLY)
    private final Long id;

    @Schema(description = "강연자")
    private final String speakerName;

    @Schema(description = "강연 내용")
    private final String content;

    @Schema(description = "강연 시작 시간")
    private final long lectureStartTime;

    @Schema(description = "강연장 이름")
    private final String venueName;

    @Schema(description = "최대 수용 가능한 인원 수")
    private final int maxCapacity;

}
