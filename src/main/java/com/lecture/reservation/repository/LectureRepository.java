package com.lecture.reservation.repository;

import com.lecture.reservation.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HoYoung on 2023/08/25.
 */
public interface LectureRepository extends JpaRepository<Lecture, Long> {

}
