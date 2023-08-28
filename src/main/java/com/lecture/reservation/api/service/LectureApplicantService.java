package com.lecture.reservation.api.service;

import com.lecture.reservation.api.dto.LectureDetailResponse;
import com.lecture.reservation.api.entity.Lecture;
import com.lecture.reservation.api.entity.LectureApplicant;
import com.lecture.reservation.api.mapper.LectureMapper;
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
@Slf4j
public class LectureApplicantService {
    private final LectureApplicantRepository lectureApplicantRepository;
    private final LectureMapper lectureMapper;

    @Transactional
    public List<LectureDetailResponse> findByEmployeeNumber(String employeeNumber) {
        List<LectureApplicant> lectureApplicants = this.lectureApplicantRepository.findByEmployeeNumber(employeeNumber);
        List<Lecture> lectures = lectureApplicants.stream().map(LectureApplicant::getLecture).collect(Collectors.toList());
        return this.lectureMapper.toDetailDtos(lectures);
    }

    @Transactional
    public void removeLectureApplicant(Long lectureId, String employeeNumber) {
        this.lectureApplicantRepository.removeByEmployeeNumberAndLectureId(lectureId, employeeNumber);
    }

}
