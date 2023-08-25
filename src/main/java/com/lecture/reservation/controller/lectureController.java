package com.lecture.reservation.controller;

import com.lecture.reservation.common.helper.ControllerHelper;
import com.lecture.reservation.dto.LectureApplicantRequest;
import com.lecture.reservation.dto.LectureRequest;
import com.lecture.reservation.service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HoYoung on 2023/08/25.
 */
@RestController
@RequestMapping(path = "/lectures")
@RequiredArgsConstructor
@Tag(name = "강연")
public class lectureController extends ControllerHelper {

    private final LectureService lectureService;

    @GetMapping(path = "")
    @Operation(summary = "강연 목록")
    public ResponseEntity<?> getLectures() {

        return super.responseBody(this.lectureService.findLectures());
    }

    @GetMapping(path = "/{lectureId}")
    @Operation(summary = "강연 상세")
    public ResponseEntity<?> getLecture(@PathVariable Long lectureId) {
        return super.responseBody(this.lectureService.findByLectureId(lectureId));
    }

    @PostMapping(path = "")
    @Operation(summary = "강연 등록")
    public ResponseEntity<?> postLectures(@RequestBody LectureRequest lectureRequest) {
        this.lectureService.saveLecture(lectureRequest);
        return super.responseBody("");
    }

    @GetMapping(path = "/{lectureId}/applicants")
    @Operation(summary = "강연 신청한 수강자 목록")
    public ResponseEntity<?> getLecturesApplicants(@PathVariable Long lectureId) {

        return super.responseBody("");
    }

    @PostMapping(path = "/{lectureId}/applicants")
    public ResponseEntity<?> postLecturesApplicant(@PathVariable Long lectureId,
                                                   @RequestBody LectureApplicantRequest lectureApplicantRequest) {

        this.lectureService.saveLectureApplicant(lectureId, lectureApplicantRequest);
        return super.responseBody("");
    }

}
