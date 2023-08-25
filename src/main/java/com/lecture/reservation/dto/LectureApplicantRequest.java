package com.lecture.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by HoYoung on 2023/08/25.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureApplicantRequest {
    private String employeeNumber;
}
