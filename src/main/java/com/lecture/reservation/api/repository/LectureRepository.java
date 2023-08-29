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
            " WHERE DATE_SUB(NOW(), interval 1 WEEK) <= lecture_start_time" +
            " AND lecture_start_time <= DATE_ADD(NOW(), interval 1 day) ", nativeQuery = true)
    List<Lecture> findAllActiveLecture();

    @Query(value = "select l " +
            " from Lecture l " +
            " left join fetch l.applicants la " +
            " where l.id = :lectureId")
    Optional<Lecture> findAllByLectureAndApplicants(Long lectureId);

    @Query(value = "SELECT l.*," +
            " COUNT(a.id) AS application_count" +
            " FROM lecture l JOIN lecture_applicants a ON l.id = a.lecture_id" +
            " WHERE a.application_date_time >= DATE_SUB(NOW(), INTERVAL 3 DAY)" +
            " GROUP BY l.id" +
            " ORDER BY application_count DESC", nativeQuery = true)
    List<Lecture> findPopularLecturesForLast3Days();
}
