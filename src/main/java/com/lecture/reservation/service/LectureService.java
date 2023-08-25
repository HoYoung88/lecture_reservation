package com.lecture.reservation.service;

import com.lecture.reservation.common.exception.LectureReservationServiceException;
import com.lecture.reservation.dto.LectureApplicantRequest;
import com.lecture.reservation.dto.LectureRequest;
import com.lecture.reservation.dto.LectureResponse;
import com.lecture.reservation.entity.Lecture;
import com.lecture.reservation.entity.LectureApplicant;
import com.lecture.reservation.exception.LectureErrorCode;
import com.lecture.reservation.mapper.LectureApplicantMapper;
import com.lecture.reservation.mapper.LectureMapper;
import com.lecture.reservation.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by HoYoung on 2023/08/25.
 */
@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;
    private final LectureApplicantMapper lectureApplicantMapper;

    public List<LectureResponse> findLectures() {
        List<Lecture> lectures = this.lectureRepository.findAll();
        return this.lectureMapper.toDto(lectures);
    }

    public LectureResponse findByLectureId(Long lectureId) {
        Lecture lecture = this.lectureRepository.findById(lectureId)
                .orElseThrow(() -> new LectureReservationServiceException(LectureErrorCode.LECTURE_NOT_FOUND));
        return this.lectureMapper.toDto(lecture);
    }

    public void saveLecture(LectureRequest lectureRequest) {
        Lecture lectureEntity = this.lectureMapper.toEntity(lectureRequest);
        this.lectureRepository.save(lectureEntity);
    }

    @Transactional
    public void saveLectureApplicant(Long lectureId, LectureApplicantRequest lectureApplicantRequest) {
        Lecture lecture = this.lectureRepository.findById(lectureId)
                .orElseThrow(() -> new LectureReservationServiceException(LectureErrorCode.LECTURE_NOT_FOUND));

        LectureApplicant lectureApplicant = this.lectureApplicantMapper.toEntity(lectureApplicantRequest);

        lecture.getApplicants().stream()
                .filter(applicant -> applicant.getEmployeeNumber().equals(lectureApplicant.getEmployeeNumber()))
                .findFirst()
                .orElseThrow(() -> new LectureReservationServiceException(LectureErrorCode.LECTURE_DUPLICATE_RESERVATION));

        lecture.addApplicant(lectureApplicant);
        this.lectureRepository.save(lecture);

    }
}
