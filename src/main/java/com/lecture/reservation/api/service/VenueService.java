package com.lecture.reservation.api.service;

import com.lecture.reservation.api.entity.Venue;
import com.lecture.reservation.api.repository.VenueRepository;
import com.lecture.reservation.common.exception.LectureReservationServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lecture.reservation.api.exception.VenueErrorCode.VENUE_NOT_FOUND;

/**
 * Created by HoYoung on 2023/08/31.
 */
@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    @Transactional
    public List<Venue> saveVenues(List<Venue> venues) {
        return this.venueRepository.saveAll(venues);
    }

    @Transactional
    public Venue saveVenue(Venue venues) {
        return this.venueRepository.save(venues);
    }

    @Transactional(readOnly = true)
    public List<Venue> findVenues() {
        return this.venueRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Venue findVenue(Long venueId) {
        return this.venueRepository.findByIdOrThrow(venueId);
    }

    @Transactional
    public void deleteVenue(Long venueId) {
        if(!this.venueRepository.existsById(venueId)) {
            throw new LectureReservationServiceException(VENUE_NOT_FOUND);
        }

        this.venueRepository.deleteById(venueId);
    }
}
