package com.lecture.reservation.api.service;

import com.lecture.reservation.api.dto.LectureApplicantRequest;
import com.lecture.reservation.api.dto.LectureApplicantResponse;
import com.lecture.reservation.api.dto.LectureDetailResponse;
import com.lecture.reservation.api.dto.LectureRequest;
import com.lecture.reservation.api.dto.LectureResponse;
import com.lecture.reservation.api.entity.Lecture;
import com.lecture.reservation.api.entity.LectureApplicant;
import com.lecture.reservation.api.exception.LectureErrorCode;
import com.lecture.reservation.api.mapper.LectureApplicantMapper;
import com.lecture.reservation.api.mapper.LectureMapper;
import com.lecture.reservation.api.repository.LectureRepository;
import com.lecture.reservation.common.exception.LectureReservationServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by HoYoung on 2023/08/25.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;
    private final LectureApplicantMapper lectureApplicantMapper;

    @Transactional(readOnly = true)
    public List<LectureDetailResponse> findAllLectures() {
        List<Lecture> lectures = this.lectureRepository.findAll();
        return this.lectureMapper.toDetailDtos(lectures);
    }

    public List<LectureDetailResponse> findAllActiveLecture() {
        List<Lecture> lectures = this.lectureRepository.findAllActiveLecture();
        return this.lectureMapper.toDetailDtos(lectures);
    }


    @Transactional(readOnly = true)
    public LectureDetailResponse findByLectureId(Long lectureId) {
        Lecture lecture = this.lectureRepository.findAllByLectureAndApplicants(lectureId)
                .orElseThrow(() -> new LectureReservationServiceException(LectureErrorCode.LECTURE_NOT_FOUND));
        return this.lectureMapper.toDetailDto(lecture);
    }

    @Transactional
    public LectureResponse saveLecture(LectureRequest lectureRequest) {
        Lecture lecture = this.lectureMapper.toEntity(lectureRequest);
        Lecture saveLecture = this.lectureRepository.save(lecture);
        return this.lectureMapper.toDto(saveLecture);
    }

    @Transactional
    public LectureApplicantResponse saveLectureApplicant(Long lectureId, LectureApplicantRequest lectureApplicantRequest) {
        Lecture lecture = this.lectureRepository.findById(lectureId)
                .orElseThrow(() -> new LectureReservationServiceException(LectureErrorCode.LECTURE_NOT_FOUND));

        validateDuplicateApplicant(lecture, lectureApplicantRequest);

        LectureApplicant lectureApplicant = this.lectureApplicantMapper.toEntity(lectureApplicantRequest);
        lecture.addApplicant(lectureApplicant);
        this.lectureRepository.save(lecture);

        validateLectureCapacity(lecture);

        return this.lectureApplicantMapper.toDto(lectureApplicant);

    }

    @Transactional(readOnly = true)
    public List<LectureDetailResponse> findPopularLecturesForLast3Days() {
        List<Lecture> popularLectures = this.lectureRepository.findPopularLecturesForLast3Days();
        return this.lectureMapper.toDetailDtos(popularLectures);
    }

    private void validateDuplicateApplicant(Lecture lecture, LectureApplicantRequest lectureApplicantRequest) {
        boolean isDuplicate = lecture.getApplicants().stream()
                .anyMatch(applicant -> applicant.getEmployeeNumber().equals(lectureApplicantRequest.getEmployeeNumber()));

        if (isDuplicate) {
            throw new LectureReservationServiceException(LectureErrorCode.LECTURE_DUPLICATE_RESERVATION);
        }
    }

    private void validateLectureCapacity(Lecture lecture) {
        int lectureMaxCapacity = lecture.getMaxCapacity();

        if (lecture.getApplicants().size() >= lectureMaxCapacity) {
            throw new LectureReservationServiceException(LectureErrorCode.LECTURE_APPLICANT_MAX_CAPACITY);
        }
    }

}
