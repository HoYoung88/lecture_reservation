package com.lecture.reservation.api.repository;

import com.lecture.reservation.api.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by HoYoung on 2023/08/25.
 */
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Query(value = "select * from lecture " +
            " WHERE DATE_SUB(now(), interval 1 WEEK) <= lecture_start_time" +
            " AND lecture_start_time <= DATE_ADD(now(), interval 1 day) ", nativeQuery = true)
    List<Lecture> findAllLectrues();

    @Query(value = "select l " +
            " from Lecture l " +
            " left join fetch l.applicants la " +
            " where l.id = :lectureId")
    Optional<Lecture> findAllByLectureAndApplicants(Long lectureId);
}
