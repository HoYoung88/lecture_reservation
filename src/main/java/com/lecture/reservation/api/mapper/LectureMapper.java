package com.lecture.reservation.api.mapper;

import com.lecture.reservation.api.dto.LectureDetailResponse;
import com.lecture.reservation.api.dto.LectureRequest;
import com.lecture.reservation.api.dto.LectureResponse;
import com.lecture.reservation.api.entity.Lecture;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by HoYoung on 2023/08/25.
 */
@Mapper(componentModel = "spring")
public interface LectureMapper {
    Lecture toEntity(LectureRequest lectureRequest);

    LectureResponse toDto(Lecture lecture);
    LectureDetailResponse toDetailDto(Lecture lecture);

    List<LectureResponse> toDto(List<Lecture> lectures);
    List<LectureDetailResponse> toDetailDtos(List<Lecture> lectures);
}
