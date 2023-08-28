package com.lecture.reservation.api.repository;

import com.lecture.reservation.api.entity.LectureApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by HoYoung on 2023/08/28.
 */
public interface LectureApplicantRepository extends JpaRepository<LectureApplicant, Long> {

    @Query(value = "select la " +
            " from LectureApplicant la" +
            " inner join fetch la.lecture" +
            " where la.employeeNumber = :employeeNumber")
    List<LectureApplicant> findByEmployeeNumber(String employeeNumber);

    @Modifying
    @Query(value = "delete from lecture_applicants " +
            " where lecture_id = :lectureId " +
            " AND employee_number = :employeeNumber ", nativeQuery = true)
    void removeByEmployeeNumberAndLectureId(Long lectureId, String employeeNumber);

}
