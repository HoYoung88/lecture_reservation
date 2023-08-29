package com.lecture.reservation.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by HoYoung on 2023/08/25.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema
public class LectureApplicantRequest {
    @Schema(description = "사원 번호")
    private String employeeNumber;
}
