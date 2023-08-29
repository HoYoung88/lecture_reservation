package com.lecture.reservation.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Created by HoYoung on 2023/08/25.
 */

@Getter
@Builder
@ToString
public class LectureResponse {
    private final Long id;
    private final String speakerName;
    private final String venue;
    private final String content;
    private final long lectureStartTime;
    private final int maxCapacity;

    private final List<LectureApplicantResponse> applicants;

}
