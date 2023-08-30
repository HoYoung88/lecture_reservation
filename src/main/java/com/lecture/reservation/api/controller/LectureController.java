package com.lecture.reservation.api.controller;

import com.lecture.reservation.api.dto.LectureApplicantRequest;
import com.lecture.reservation.api.dto.LectureDetailResponse;
import com.lecture.reservation.api.dto.LectureRequest;
import com.lecture.reservation.api.entity.Lecture;
import com.lecture.reservation.api.entity.LectureApplicant;
import com.lecture.reservation.api.mapper.LectureApplicantMapper;
import com.lecture.reservation.api.mapper.LectureMapper;
import com.lecture.reservation.api.service.LectureApplicantService;
import com.lecture.reservation.api.service.LectureService;
import com.lecture.reservation.common.helper.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@Tag(name = "강연")
public class LectureController extends ControllerHelper {

    private final LectureMapper lectureMapper;
    private final LectureApplicantMapper lectureApplicantMapper;
    private final LectureService lectureService;
    private final LectureApplicantService lectureApplicantService;

    @GetMapping(path = "")
    @Operation(summary = "등록한 강연 목록 조회")
    public ResponseEntity<?> getLectures() {
        return super.responseBody(this.lectureMapper.toDetailDtos(this.lectureService.findAllLectures()));
    }

    @GetMapping(path = "/active")
    @Operation(summary = "신청 가능한 강연 목록 조회")
    public ResponseEntity<?> getActiveLectureApplications() {
        return super.responseBody(this.lectureMapper.toDetailDtos(this.lectureService.findAllActiveLecture()));
    }

    @GetMapping(path = "/{lectureId}")
    @Operation(summary = "강연 상세 조회")
    public ResponseEntity<?> getLecture(@PathVariable Long lectureId) {
        return super.responseBody(this.lectureMapper.toDetailDto(this.lectureService.findByLectureId(lectureId)));
    }

    @GetMapping(path = "/populars")
    @Operation(summary = "인기 강연 목록 조회")
    public ResponseEntity<?> getLecturePopulars() {
        List<LectureDetailResponse> responses = this.lectureMapper.toDetailDtos(this.lectureService.findPopularLecturesForLast3Days());
        return super.responseBody(responses);
    }

    @PostMapping(path = "")
    @Operation(summary = "강연 등록")
    public ResponseEntity<?> postLectures(@RequestBody LectureRequest lectureRequest) {
        Lecture request = this.lectureMapper.toEntity(lectureRequest);
        this.lectureService.saveLecture(request);
        return super.responseBody("");
    }

    @PostMapping(path = "/{lectureId}/applicants")
    @Operation(summary = "강연 신청")
    public ResponseEntity<?> postLecturesApplicant(@PathVariable Long lectureId,
                                                   @RequestBody LectureApplicantRequest lectureApplicantRequest) {
        LectureApplicant request = this.lectureApplicantMapper.toEntity(lectureApplicantRequest);
        this.lectureService.saveLectureApplicant(lectureId, request);
        return super.responseBody("");
    }

    @GetMapping(path = "/{lectureId}/applicants")
    @Operation(summary = "강연 신청한 수강자 목록")
    public ResponseEntity<?> getLecturesApplicants(@PathVariable Long lectureId) {
        return super.responseBody(this.lectureService.findByLectureId(lectureId));
    }

    @DeleteMapping(path = "/{lectureId}/applicants/{employeeNumber}")
    @Operation(summary = "신청한 강연 취소")
    public ResponseEntity<?> deleteLectureApplicant(@PathVariable Long lectureId, @PathVariable String employeeNumber) {
        this.lectureApplicantService.removeLectureApplicant(lectureId, employeeNumber);
        return super.responseBody("");
    }

    @GetMapping(path = "/applicants/{employeeNumber}")
    @Operation(summary = "강연 신청내역 조회")
    public ResponseEntity<?> getLectureApplicant(@PathVariable String employeeNumber) {
        List<LectureDetailResponse> responses = this.lectureMapper.toDetailDtos(this.lectureApplicantService.findByEmployeeNumber(employeeNumber));
        return super.responseBody(responses);
    }


}
