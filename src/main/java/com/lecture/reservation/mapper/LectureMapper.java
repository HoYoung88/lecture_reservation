package com.lecture.reservation.mapper;

import com.lecture.reservation.dto.LectureRequest;
import com.lecture.reservation.dto.LectureResponse;
import com.lecture.reservation.entity.Lecture;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by HoYoung on 2023/08/25.
 */
@Mapper(componentModel = "spring")
public interface LectureMapper {
    Lecture toEntity(LectureRequest lectureRequest);

    LectureResponse toDto(Lecture lecture);
    List<LectureResponse> toDto(List<Lecture> lectures);
}
