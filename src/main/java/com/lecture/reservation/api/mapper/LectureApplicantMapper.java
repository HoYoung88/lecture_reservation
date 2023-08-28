package com.lecture.reservation.api.mapper;

import com.lecture.reservation.api.dto.LectureApplicantRequest;
import com.lecture.reservation.api.dto.LectureApplicantResponse;
import com.lecture.reservation.api.entity.LectureApplicant;
import org.mapstruct.Mapper;

/**
 * Created by HoYoung on 2023/08/25.
 */
@Mapper(componentModel = "spring")
public interface LectureApplicantMapper {
    LectureApplicant toEntity(LectureApplicantRequest lectureApplicantRequest);
    LectureApplicantResponse toDto(LectureApplicant lectureApplicant);
}
