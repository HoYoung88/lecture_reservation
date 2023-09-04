package com.lecture.reservation.api.repository;

import com.lecture.reservation.api.entity.Venue;
import com.lecture.reservation.common.exception.LectureReservationServiceException;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.lecture.reservation.api.exception.VenueErrorCode.VENUE_NOT_FOUND;

/**
 * Created by HoYoung on 2023/08/31.
 */
public interface VenueRepository extends JpaRepository<Venue, Long> {

    default Venue findByIdOrThrow(Long venueId) {
        return findById(venueId)
                .orElseThrow(() -> new LectureReservationServiceException(VENUE_NOT_FOUND));
    }
}
