package com.lecture.reservation.api.mapper;

import com.lecture.reservation.api.dto.LectureDetailResponse;
import com.lecture.reservation.api.dto.LectureRequest;
import com.lecture.reservation.api.entity.Lecture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HoYoung on 2023/08/25.
 */
@Mapper(componentModel = "spring")
public interface LectureMapper {
    Lecture toEntity(LectureRequest lectureRequest);
//
//    LectureResponse toDto(Lecture lecture);
//
//    LectureDetailResponse toDetailDto(Lecture lecture);
//
//    List<LectureResponse> toDto(List<Lecture> lectures);
//

//    @Named("E2R")
    @Mapping(source = "lecture.venue.venueName", target = "venueName")
    @Mapping(source = "lecture.venue.maxCapacity", target = "maxCapacity")
    LectureDetailResponse toDetailDto(Lecture lecture);

    //    @IterableMapping(qualifiedByName = "E2R")
    default List<LectureDetailResponse> toDetailDtos(List<Lecture> lectures) {
        return lectures.stream().map(lecture -> LectureDetailResponse.builder()
                        .id(lecture.getId())
                        .speakerName(lecture.getSpeakerName())
                        .content(lecture.getContent())
                        .lectureStartTime(lecture.getLectureStartTime())
                        .venueName(lecture.getVenue().getVenueName())
                        .maxCapacity(lecture.getVenue().getMaxCapacity())
                        .build())
                .collect(Collectors.toList());
    }
}
