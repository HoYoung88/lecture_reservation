package com.lecture.reservation;

import com.lecture.reservation.api.dto.LectureApplicantRequest;
import com.lecture.reservation.api.dto.LectureApplicantResponse;
import com.lecture.reservation.api.dto.LectureRequest;
import com.lecture.reservation.api.dto.LectureResponse;
import com.lecture.reservation.api.exception.LectureErrorCode;
import com.lecture.reservation.api.service.LectureService;
import com.lecture.reservation.common.exception.LectureReservationServiceException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by HoYoung on 2023/08/28.
 */
@SpringBootTest
@Slf4j
public class LectureTest {

    @Autowired
    private LectureService lectureService;
    private LectureResponse lectureResponse;

    @Test
    @DisplayName("강연 등록 테스트")
    public void testLectureRegistration() {
        LectureRequest lectureRequest = LectureRequest.builder()
                .speakerName("홍길동")
                .venue("강남")
                .content("테스트 강연")
                .lectureStartTime(LocalDateTime.now())
                .maxCapacity(10)
                .build();
        lectureResponse = this.lectureService.saveLecture(lectureRequest);

        Assertions.assertThat(lectureResponse.getSpeakerName()).isEqualTo(lectureRequest.getSpeakerName());
        Assertions.assertThat(lectureResponse.getMaxCapacity()).isEqualTo(lectureRequest.getMaxCapacity());

    }

    @Test
    @DisplayName("강연 수강 신청 테스트")
    public void testLectureApplication() {
        this.testLectureRegistration();

        LectureApplicantRequest lectureApplicantRequest = LectureApplicantRequest.builder()
                .employeeNumber(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .build();

        LectureApplicantResponse lectureApplicantResponse = this.lectureService.saveLectureApplicant(lectureResponse.getId(), lectureApplicantRequest);

        Assertions.assertThat(lectureApplicantResponse.getEmployeeNumber()).isEqualTo(lectureApplicantRequest.getEmployeeNumber());

    }

    @Test
    @DisplayName("강연 중복 신청 불가 테스트")
    public void testLectureDuplicateReservation() {
        this.testLectureRegistration();

        Assertions.assertThatExceptionOfType(LectureReservationServiceException.class)
                .isThrownBy(() -> {
                    this.lectureService.saveLectureApplicant(lectureResponse.getId(), LectureApplicantRequest.builder().employeeNumber(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))).build());
                    this.lectureService.saveLectureApplicant(lectureResponse.getId(), LectureApplicantRequest.builder().employeeNumber(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))).build());
                })
                .extracting(LectureReservationServiceException::getErrorCode)
                .isEqualTo(LectureErrorCode.LECTURE_DUPLICATE_RESERVATION);

    }

}
