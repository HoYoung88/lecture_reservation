package com.lecture.reservation.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by HoYoung on 2023/08/25.
 */
@Table(name = "lecture")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("강연자")
    private String speakerName;

    @Comment("강연장")
    private String venue;

    @Comment("강연 내용")
    private String content;

    @Comment("강연 시간")
    private LocalDateTime lectureTime;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private final Set<LectureApplicant> applicants = new HashSet<>();

    @Builder
    public Lecture(String speakerName, String venue, String content, LocalDateTime lectureTime) {
        this.speakerName = speakerName;
        this.venue = venue;
        this.content = content;
        this.lectureTime = lectureTime;
    }

    public void addApplicant(LectureApplicant lectureApplicant) {
        applicants.add(lectureApplicant);
        lectureApplicant.setLecture(this);
    }
}
