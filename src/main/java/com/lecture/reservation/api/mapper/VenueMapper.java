package com.lecture.reservation.api.mapper;

import com.lecture.reservation.api.dto.VenueRequest;
import com.lecture.reservation.api.dto.VenueDetailResponse;
import com.lecture.reservation.api.entity.Venue;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by HoYoung on 2023/08/31.
 */
@Mapper(componentModel = "spring")
public interface VenueMapper {
    Venue toEntity(VenueRequest venue);
    List<Venue> toEntitys(List<VenueRequest> venueRequest);
    VenueDetailResponse toResponse(Venue venue);
    List<VenueDetailResponse> toResponses(List<Venue> venue);
}
