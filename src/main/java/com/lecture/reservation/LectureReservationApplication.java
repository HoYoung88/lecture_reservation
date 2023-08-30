package com.lecture.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by HoYoung on 2023/08/25.
 */

@SpringBootApplication
@EnableJpaAuditing
public class LectureReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(LectureReservationApplication.class, args);
    }
}
