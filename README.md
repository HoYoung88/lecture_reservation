## 개발언어
- Java 17

## 사용한 프레임워크
- Spring Boot 3.1.x
- MapStruct(Entity와 Dto간의 매핑을 지원)
- JPA

## 사용한 RDBMS
- Mysql

## 데이터 모델 설명
- 강연 (Lecture): 강연에 대한 정보를 저장합니다.
- 신청 (LectureApplicant): 강연 신청 정보 및 사원 번호를 저장합니다.

## 테이블 설계
### 강연 (Lecture)
- id: 강연 식별자 (Primary Key)
- speaker_name: 강연자 이름
- venue: 강연이 진행되는 장소
- lecture_start_time: 강연 시작 시간
- content: 강연 내용
- max_capacity: 최대 수용 가능한 인원 수

### 신청 (LectureApplicant)
- id: 강연 신청 식별자 (Primary Key)
- lecture_id: 강연(Foreign Key - Lecture 참조)
- employee_number: 신청자 사번
- application_date_time: 신청일시

## 데이터 관계
- 신청(LectureApplicant) 테이블은 신청 정보를 저장하고 있으며, lecture_id 필드를 외래 키로 사용하여 강연(Lecture) 테이블을 참조하고 있습니다.

## API Document
- SpringDoc를 사용하였습니다. (link: http://localhost:8080/docs/index.do)
