package com.lecture.reservation.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by HoYoung on 2023/08/28.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LectureApplicantResponse {
    private Long id;
    private String employeeNumber;
}
