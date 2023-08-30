package com.lecture.reservation.api.service;

import com.lecture.reservation.api.entity.Lecture;
import com.lecture.reservation.api.entity.LectureApplicant;
import com.lecture.reservation.api.exception.LectureErrorCode;
import com.lecture.reservation.api.repository.LectureRepository;
import com.lecture.reservation.common.exception.LectureReservationServiceException;
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

    /**
     * 등록한 강연 전체 리스트
     */
    @Transactional(readOnly = true)
    public List<Lecture> findAllLectures() {
        return this.lectureRepository.findAll();
    }

    /**
     * 신청가능한 강연 리스트
     */
    @Transactional(readOnly = true)
    public List<Lecture> findAllActiveLecture() {
        return this.lectureRepository.findAllActiveLecture();
    }

    /**
     * 강연 상세 정보 조회
     *
     * @param lectureId 상세 정보를 조회할 강연 아이디
     */
    @Transactional(readOnly = true)
    public Lecture findByLectureId(Long lectureId) {
        return this.lectureRepository.findAllByLectureAndApplicants(lectureId)
                .orElseThrow(() -> new LectureReservationServiceException(LectureErrorCode.LECTURE_NOT_FOUND));
    }

    /**
     * 강연 정보를 저장
     *
     * @param lecture 등록할 강연 정보
     */
    @Transactional
    public Lecture saveLecture(Lecture lecture) {
        return this.lectureRepository.save(lecture);
    }

    /**
     * 강연 신청자 정보를 저장
     *
     * @param lectureId        등록할 강연 아이디
     * @param lectureApplicant 저장할 신청자 entity
     */
    @Transactional
    public LectureApplicant saveLectureApplicant(Long lectureId, LectureApplicant lectureApplicant) {

        Lecture lecture = this.findByLectureId(lectureId);

        validateDuplicateApplicant(lecture, lectureApplicant);

        lecture.addApplicant(lectureApplicant);
        this.lectureRepository.save(lecture);

        validateLectureCapacity(lecture);

        return lectureApplicant;

    }

    /**
     * 인기 강연 목록 조회
     */
    @Transactional(readOnly = true)
    public List<Lecture> findPopularLecturesForLast3Days() {
        return this.lectureRepository.findPopularLecturesForLast3Days();
    }

    /**
     * 강연 등록 중복 체크 벨리데이션
     *
     * @param lecture          강연 entity
     * @param lectureApplicant 강연 신청 entity
     */
    private void validateDuplicateApplicant(Lecture lecture, LectureApplicant lectureApplicant) {
        boolean isDuplicate = lecture.getApplicants().stream()
                .anyMatch(applicant -> applicant.getEmployeeNumber().equals(lectureApplicant.getEmployeeNumber()));

        if (isDuplicate) {
            throw new LectureReservationServiceException(LectureErrorCode.LECTURE_DUPLICATE_RESERVATION);
        }
    }

    /**
     * 강연 최대 수용 가능한 인원 수 벨리데이션
     *
     * @param lecture 강연 entity
     */
    private void validateLectureCapacity(Lecture lecture) {
        int lectureMaxCapacity = lecture.getMaxCapacity();

        if (lecture.getApplicants().size() >= lectureMaxCapacity) {
            throw new LectureReservationServiceException(LectureErrorCode.LECTURE_APPLICANT_MAX_CAPACITY);
        }
    }

}
