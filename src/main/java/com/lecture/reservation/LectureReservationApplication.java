package com.lecture.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by HoYoung on 2023/08/25.
 */

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class LectureReservationApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LectureReservationApplication.class, args);
        //기본 데이터 입력
//        LectureService lectureService = run.getBean(LectureService.class);
//
//        LectureRequest lectureRequest = LectureRequest.builder()
//                .speakerName("홍길동")
//                .venue("강남")
//                .content("강연 상세 내용")
//                .maxCapacity(10)
//                .lectureTime(LocalDateTime.now())
//                .build();
//        lectureService.saveLecture(lectureRequest);
//
//        lectureRequest = LectureRequest.builder()
//                .speakerName("김철수")
//                .venue("강서")
//                .content("강연 상세 내용")
//                .maxCapacity(5)
//                .lectureTime(LocalDateTime.now().minusDays(3))
//                .build();
//        lectureService.saveLecture(lectureRequest);
//
//        lectureRequest = LectureRequest.builder()
//                .speakerName("안철수")
//                .venue("강북")
//                .content("강연 상세 내용")
//                .maxCapacity(15)
//                .lectureTime(LocalDateTime.now().minusDays(10))
//                .build();
//        lectureService.saveLecture(lectureRequest);
//
//        lectureRequest = LectureRequest.builder()
//                .speakerName("이철수")
//                .venue("강동")
//                .content("강연 상세 내용")
//                .maxCapacity(20)
//                .lectureTime(LocalDateTime.now().minusDays(5))
//                .build();
//        lectureService.saveLecture(lectureRequest);

    }
}
