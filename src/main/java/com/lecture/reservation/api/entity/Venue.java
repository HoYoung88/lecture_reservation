package com.lecture.reservation.api.entity;

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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by HoYoung on 2023/08/31.
 */
@Table(name = "Venue")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("강연장 이름")
    private String venueName;

    @Comment("최대 수용 가능한 인원 수")
    private int maxCapacity;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private final Set<Lecture> lectures = new HashSet<>();

    @Builder
    public Venue(String venueName, int maxCapacity) {
        this.venueName = venueName;
        this.maxCapacity = maxCapacity;
    }

    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
        lecture.setVenue(this);
    }

    public Set<Lecture> getLectures() {
        return Collections.unmodifiableSet(lectures);
    }
}
