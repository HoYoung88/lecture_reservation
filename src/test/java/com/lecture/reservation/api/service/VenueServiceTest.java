package com.lecture.reservation.api.service;

import com.lecture.reservation.api.entity.Lecture;
import com.lecture.reservation.api.entity.Venue;
import com.lecture.reservation.event.NotifyEventPublisherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by HoYoung on 2023/08/31.
 */
@SpringBootTest
class VenueServiceTest {

    @Autowired
    private VenueService venueService;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private NotifyEventPublisherService notifyEventPublisherService;

    @Test
    @DisplayName("강연장 정보 등록")
    void saveVenues() {
        List<Venue> venues = List.of(
                Venue.builder().venueName("강남 101호").maxCapacity(10).build(),
                Venue.builder().venueName("강남 102호").maxCapacity(20).build(),
                Venue.builder().venueName("강남 103호").maxCapacity(5).build()
        );

        List<Venue> saveVenues = this.venueService.saveVenues(venues);

        Assertions.assertEquals(saveVenues.size(), 3, "등록된 강연장은 3개");
    }

    @Test
    @DisplayName("강연장 정보 확인")
    void findVenue() {
        Long venueId = 2L;

        Venue venue = this.venueService.findVenue(venueId);

        Assertions.assertEquals(venueId, venue.getId(), "강연장 아이디 1확인");

    }

    @Test
    @DisplayName("강연장 정보 삭제")
    void deleteVenue() {
        Long venueId = 1L;

        this.venueService.deleteVenue(venueId);
    }

    @Test
    void lectureSave() {
        List<Venue> venues = List.of(
                Venue.builder().venueName("김포 101호").maxCapacity(10).build()
        );

        List<Venue> saveVenues = this.venueService.saveVenues(venues);

        Venue venue = saveVenues.get(0);

        Lecture saveLecture = Lecture.builder()
                .speakerName("홍길동")
                .content("컨탠츠")
                .lectureStartTime(LocalDateTime.now())
                .build();

        this.lectureService.saveLecture(saveLecture, venue.getId());
    }
}