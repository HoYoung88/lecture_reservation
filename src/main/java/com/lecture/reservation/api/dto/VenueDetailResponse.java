package com.lecture.reservation.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by HoYoung on 2023/08/31.
 */
//@Builder
@Getter
@ToString
@Builder
@Schema(description = "강연장 상세 Response")
public class VenueDetailResponse {
    @Schema(accessMode = AccessMode.READ_ONLY)
    private final long id;

    @Schema(description = "강연장 이름")
    private final String venueName;

    @Schema(description = "최대 수용 가능한 인원 수")
    private final int maxCapacity;

}
