package com.lecture.reservation.api.dto;

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
public class LectureApplicantRequest {
    private String employeeNumber;
}
