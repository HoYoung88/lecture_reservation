package com.lecture.reservation.mapper;

import com.lecture.reservation.dto.LectureApplicantRequest;
import com.lecture.reservation.entity.LectureApplicant;
import org.mapstruct.Mapper;

/**
 * Created by HoYoung on 2023/08/25.
 */
@Mapper(componentModel = "spring")
public interface LectureApplicantMapper {
    LectureApplicant toEntity(LectureApplicantRequest lectureApplicantRequest);
}
