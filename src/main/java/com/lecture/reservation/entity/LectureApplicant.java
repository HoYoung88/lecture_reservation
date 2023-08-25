package com.lecture.reservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by HoYoung on 2023/08/25.
 */
@Table(name = "lecture_applicants")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureApplicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Builder
    public LectureApplicant(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
}
