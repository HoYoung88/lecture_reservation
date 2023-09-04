package com.lecture.reservation.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by HoYoung on 2023/08/31.
 */
@Builder
@Getter
@ToString
@Schema(description = "강연장 등록 Request")
public class VenueRequest {
    @Schema(description = "강연장 이름")
    private final String venueName;

    @Schema(description = "최대 수용 가능한 인원 수")
    private final int maxCapacity;
}
