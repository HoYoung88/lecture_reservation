package com.lecture.reservation.api.controller;

import com.lecture.reservation.api.dto.VenueDetailResponse;
import com.lecture.reservation.api.dto.VenueRequest;
import com.lecture.reservation.api.entity.Venue;
import com.lecture.reservation.api.mapper.VenueMapper;
import com.lecture.reservation.api.service.VenueService;
import com.lecture.reservation.common.dto.ApiResultResponse;
import com.lecture.reservation.common.helper.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by HoYoung on 2023/08/31.
 */
@RestController
@RequestMapping(path = "/venues", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VenueController extends ControllerHelper {

    private final VenueService venueService;
    private final VenueMapper venueMapper;

    @GetMapping(path = "")
    @Operation(description = "강연 장소 정보 조회",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(array = @ArraySchema(schema = @Schema(implementation = VenueDetailResponse.class)))
                    })
            })
    public ResponseEntity<ApiResultResponse<List<VenueDetailResponse>>> getVenues() {
        List<Venue> venues = venueService.findVenues();
        return super.responseBody(venueMapper.toResponses(venues));
    }

    @PostMapping(path = "")
    @Operation(description = "강연 장소 등록",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = VenueDetailResponse.class))
                    })
            })
    public ResponseEntity<?> postVenue(@RequestBody VenueRequest venueRequest) {
        Venue venue = venueMapper.toEntity(venueRequest);
        return super.responseBody(venueMapper.toResponse(venueService.saveVenue(venue)));
    }
}
