package com.lecture.reservation;

import com.lecture.reservation.api.dto.LectureApplicantRequest;
import com.lecture.reservation.api.dto.LectureRequest;
import com.lecture.reservation.api.dto.LectureResponse;
import com.lecture.reservation.api.entity.Lecture;
import com.lecture.reservation.api.entity.LectureApplicant;
import com.lecture.reservation.api.exception.LectureErrorCode;
import com.lecture.reservation.api.mapper.LectureApplicantMapper;
import com.lecture.reservation.api.mapper.LectureMapper;
import com.lecture.reservation.api.service.LectureApplicantService;
import com.lecture.reservation.api.service.LectureService;
import com.lecture.reservation.common.exception.LectureReservationServiceException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssertAlternative;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by HoYoung on 2023/08/28.
 */
@SpringBootTest
@Slf4j
public class LectureTest {

    @Autowired
    private LectureService lectureService;
    @Autowired
    private LectureApplicantService lectureApplicantService;
    @Autowired
    private LectureMapper lectureMapper;
    @Autowired
    private LectureApplicantMapper lectureApplicantMapper;

    @Test
    @DisplayName("강연 등록")
    public void testSaveLecture() {
        LectureRequest lectureRequest = LectureRequest.builder()
                .speakerName("홍길동")     //강연자
                .venue("강남")             //강연자
                .content("테스트 강연")    //강연내용
                .lectureStartTime(LocalDateTime.now()) //강연시간
                .maxCapacity(10) //신청 가능 인원
                .build();

        Lecture request = this.lectureMapper.toEntity(lectureRequest);
        LectureResponse lectureResponse = this.lectureMapper.toDto(this.lectureService.saveLecture(request));

        Assertions.assertThat(lectureResponse.getSpeakerName()).isEqualTo(lectureRequest.getSpeakerName());
        Assertions.assertThat(lectureResponse.getMaxCapacity()).isEqualTo(lectureRequest.getMaxCapacity());

    }

    @Test
    @DisplayName("강연 수강 신청")
    public void testSaveLectureApplication() {
        Long lectureId = 1L;
        LectureApplicantRequest lectureApplicantRequest = LectureApplicantRequest.builder()
                .employeeNumber("230829")
                .build();
        LectureApplicant request = this.lectureApplicantMapper.toEntity(lectureApplicantRequest);

        LectureApplicant saveLectureApplicant  = this.lectureService.saveLectureApplicant(lectureId, request);

        Assertions.assertThat(saveLectureApplicant.getEmployeeNumber()).isEqualTo(lectureApplicantRequest.getEmployeeNumber());

    }

    @Test
    @DisplayName("강연 중복 신청 불가")
    public void testSaveDuplicateLectureApplication() {
        Long lectureId = 1L;
        LectureApplicantRequest lectureApplicantRequest = LectureApplicantRequest.builder()
                .employeeNumber("230829")
                .build();
        LectureApplicant request = this.lectureApplicantMapper.toEntity(lectureApplicantRequest);

        ThrowableAssertAlternative<LectureReservationServiceException> thrownBy = Assertions.assertThatExceptionOfType(LectureReservationServiceException.class)
                .isThrownBy(() -> {
                    this.lectureService.saveLectureApplicant(lectureId, request);
                });

        thrownBy.extracting(LectureReservationServiceException::getErrorCode)
                .isEqualTo(LectureErrorCode.LECTURE_DUPLICATE_RESERVATION);
    }

    @Test
    @DisplayName("신청내역 조회(사번 입력)")
    public void testGetFindLectureByEmployeeNumber() {
        String employeeNumber = "230829";

        List<Lecture> lectures = this.lectureApplicantService.findByEmployeeNumber(employeeNumber);

        Assertions.assertThat(lectures.size()).isGreaterThan(-1);
    }

    @Test
    @DisplayName("신청한 강연 취소")
    public void testDeleteLectureApplicant() {
        Long lectureId = 1L;
        String employeeNumber = "230829";

        this.lectureApplicantService.removeLectureApplicant(lectureId, employeeNumber);
        List<Lecture> lectures = this.lectureApplicantService.findByEmployeeNumber(employeeNumber);
        long count = lectures.stream().filter(lecture -> lecture.getId().equals(lectureId)).count();

        Assertions.assertThat(count).isEqualTo(0);
    }

    @Test
    @DisplayName("인기 강연 목록 조회")
    public void testGetLecturePopulars() {
        Lecture saveLecture = this.lectureService.saveLecture(Lecture.builder()
                .speakerName("홍길동")
                .venue("강서")
                .content("테스트 강연")
                .lectureStartTime(LocalDateTime.now())
                .maxCapacity(5)
                .build());

        this.lectureService.saveLectureApplicant(saveLecture.getId(), this.lectureApplicantMapper.toEntity(LectureApplicantRequest.builder()
                .employeeNumber("230829")
                .build()));
        this.lectureService.saveLectureApplicant(saveLecture.getId(), this.lectureApplicantMapper.toEntity(LectureApplicantRequest.builder()
                .employeeNumber("230830")
                .build()));

        saveLecture = this.lectureService.saveLecture(Lecture.builder()
                .speakerName("김철수")
                .venue("강서")
                .content("테스트 강연")
                .lectureStartTime(LocalDateTime.now())
                .maxCapacity(5)
                .build());

        this.lectureService.saveLectureApplicant(saveLecture.getId(), this.lectureApplicantMapper.toEntity(LectureApplicantRequest.builder()
                .employeeNumber("230829")
                .build()));
        this.lectureService.saveLectureApplicant(saveLecture.getId(), this.lectureApplicantMapper.toEntity(LectureApplicantRequest.builder()
                .employeeNumber("230830")
                .build()));
        this.lectureService.saveLectureApplicant(saveLecture.getId(), this.lectureApplicantMapper.toEntity(LectureApplicantRequest.builder()
                .employeeNumber("230831")
                .build()));

        List<Lecture> lectures = this.lectureService.findPopularLecturesForLast3Days();
        Assertions.assertThat(lectures.get(0).getSpeakerName()).isEqualTo("김철수");

    }



}
