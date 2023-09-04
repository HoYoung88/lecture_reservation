package com.lecture.reservation.api.controller;

import com.lecture.reservation.api.dto.LectureApplicantRequest;
import com.lecture.reservation.api.dto.LectureDetailResponse;
import com.lecture.reservation.api.dto.LectureRequest;
import com.lecture.reservation.api.entity.Lecture;
import com.lecture.reservation.api.mapper.LectureApplicantMapper;
import com.lecture.reservation.api.mapper.LectureMapper;
import com.lecture.reservation.api.service.LectureApplicantService;
import com.lecture.reservation.api.service.LectureService;
import com.lecture.reservation.common.dto.ApiResultResponse;
import com.lecture.reservation.common.helper.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by HoYoung on 2023/08/25.
 */
@RestController
@RequestMapping(path = "/lectures")
@RequiredArgsConstructor
//@Tag(name = "강연")
public class LectureController extends ControllerHelper {

    private final LectureMapper lectureMapper;
    private final LectureService lectureService;
    private final LectureApplicantService lectureApplicantService;
    private final LectureApplicantMapper lectureApplicantMapper;

    @GetMapping(path = "")
    @Operation(summary = "강연 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = LectureDetailResponse.class)))
            })
    })
    public ResponseEntity<ApiResultResponse<List<LectureDetailResponse>>> getLectures() {
        return super.responseBody(this.lectureMapper.toDetailDtos(this.lectureService.findLectures()));
    }

    @GetMapping(path = "/{lectureId}")
    @Operation(summary = "강연 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = LectureDetailResponse.class))
            })
    })
    public ResponseEntity<?> getLecture(@PathVariable Long lectureId) {
        Lecture lecture = this.lectureService.findLecture(lectureId);
        return super.responseBody(this.lectureMapper.toDetailDto(lecture));
    }

    @PostMapping(path = "")
    @Operation(summary = "강연 등록", responses = {
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = LectureDetailResponse.class))
            })
    })
    public ResponseEntity<ApiResultResponse<LectureDetailResponse>> postLecture(@RequestBody LectureRequest lectureRequest) {
        Lecture request = this.lectureMapper.toEntity(lectureRequest);
        return super.responseBody(this.lectureMapper.toDetailDto(this.lectureService.saveLecture(request, lectureRequest.getVenueId())));
    }

    @Operation(summary = "강연 참여자 등록", responses = {
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = LectureDetailResponse.class))
            })
    })
    @PostMapping(path = "/{lectureId}/applicants")
    public ResponseEntity<?> postLectureApplicants(@PathVariable Long lectureId,
                                                   @RequestBody LectureApplicantRequest lectureApplicantRequest) {
        return super.responseBody("");
    }



//
//    @GetMapping(path = "/active")
//    @Operation(summary = "신청 가능한 강연 목록 조회")
//    public ResponseEntity<?> getActiveLectureApplications() {
//        return super.responseBody(this.lectureMapper.toDetailDtos(this.lectureService.findAllActiveLecture()));
//    }
//
//    @GetMapping(path = "/{lectureId}")
//    @Operation(summary = "강연 상세 조회")
//    public ResponseEntity<?> getLecture(@PathVariable Long lectureId) {
//        return super.responseBody(this.lectureMapper.toDetailDto(this.lectureService.findByLectureId(lectureId)));
//    }
//
//    @GetMapping(path = "/populars")
//    @Operation(summary = "인기 강연 목록 조회")
//    public ResponseEntity<?> getLecturePopulars() {
//        List<LectureDetailResponse> responses = this.lectureMapper.toDetailDtos(this.lectureService.findPopularLecturesForLast3Days());
//        return super.responseBody(responses);
//    }
//
//    @PostMapping(path = "")
//    @Operation(summary = "강연 등록")
//    public ResponseEntity<?> postLectures(@RequestBody LectureRequest lectureRequest) {
//        Lecture request = this.lectureMapper.toEntity(lectureRequest);
//        this.lectureService.saveLecture(request);
//        return super.responseBody("");
//    }
//
//    @PostMapping(path = "/{lectureId}/applicants")
//    @Operation(summary = "강연 신청")
//    public ResponseEntity<?> postLecturesApplicant(@PathVariable Long lectureId,
//                                                   @RequestBody LectureApplicantRequest lectureApplicantRequest) {
//        LectureApplicant request = this.lectureApplicantMapper.toEntity(lectureApplicantRequest);
//        this.lectureService.saveLectureApplicant(lectureId, request);
//        return super.responseBody("");
//    }
//
//    @GetMapping(path = "/{lectureId}/applicants")
//    @Operation(summary = "강연 신청한 수강자 목록")
//    public ResponseEntity<?> getLecturesApplicants(@PathVariable Long lectureId) {
//        return super.responseBody(this.lectureService.findByLectureId(lectureId));
//    }
//
//    @DeleteMapping(path = "/{lectureId}/applicants/{employeeNumber}")
//    @Operation(summary = "신청한 강연 취소")
//    public ResponseEntity<?> deleteLectureApplicant(@PathVariable Long lectureId, @PathVariable String employeeNumber) {
//        this.lectureApplicantService.removeLectureApplicant(lectureId, employeeNumber);
//        return super.responseBody("");
//    }
//
//    @GetMapping(path = "/applicants/{employeeNumber}")
//    @Operation(summary = "강연 신청내역 조회")
//    public ResponseEntity<?> getLectureApplicant(@PathVariable String employeeNumber) {
//        List<LectureDetailResponse> responses = this.lectureMapper.toDetailDtos(this.lectureApplicantService.findByEmployeeNumber(employeeNumber));
//        return super.responseBody(responses);
//    }


}
