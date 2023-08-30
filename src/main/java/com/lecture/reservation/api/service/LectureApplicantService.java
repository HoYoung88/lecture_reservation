package com.lecture.reservation.api.service;

import com.lecture.reservation.api.entity.Lecture;
import com.lecture.reservation.api.entity.LectureApplicant;
import com.lecture.reservation.api.repository.LectureApplicantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HoYoung on 2023/08/28.
 */
@Service
@RequiredArgsConstructor
public class LectureApplicantService {
    private final LectureApplicantRepository lectureApplicantRepository;

    /**
     * 사원 번호로 강의 정보를 조회
     *
     * @param employeeNumber 사원 번호
     */
    @Transactional(readOnly = true)
    public List<Lecture> findByEmployeeNumber(String employeeNumber) {
        List<LectureApplicant> lectureApplicants = this.lectureApplicantRepository.findByEmployeeNumber(employeeNumber);
        return lectureApplicants.stream().map(LectureApplicant::getLecture).collect(Collectors.toList());
    }

    /**
     * 등록한 강의 신청 취소
     *
     * @param lectureId      강의 아이디
     * @param employeeNumber 사원 번호
     */
    @Transactional
    public void removeLectureApplicant(Long lectureId, String employeeNumber) {
        this.lectureApplicantRepository.removeByEmployeeNumberAndLectureId(lectureId, employeeNumber);
    }

}
