## 개발언어
  - Java 17
## 사용한 프레임워크
  - Spring Boot 3.0
  - mapstruct
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
- venue: 강연이 진행되는 장소 (Foreign Key referencing Venue)
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

## 그 밖에 고민하셨던 또는 설명하고 싶으신 부분
데이터베이스 모델을 설계하는 과정에서 향후 추가되거나 변경될 수 있는 요소들을 고려하여 Entity를 구성했습니다.
이렇게 함으로써 필드 추가에 따른 변화를 최소화하며 유연한 데이터베이스 구조를 확보하여 새로운 요구사항에 빠르게 대응할 수 있는 기반을 마련하였습니다.
또한 API 요청 중 발생할 수 있는 예외 상황에 대한 처리를 강화하였습니다. 예를 들어 서비스 로직 처리 중 발생하는 오류를 LectureReservationServiceException과 같은
예외 클래스를 통해 처리하고, 이를 LectureReservationServiceExceptionHandler 클래스를 통해 클라이언트에게 적절한 오류 메시지를 반환하도록 구성했습니다.
이렇게 함으로써 클라이언트가 발생한 문제에 대해 빠르게 이해하고 대응할 수 있도록 지원하며, 오류 처리 과정을 효과적으로 관리할 수 있습니다.
뿐만 아니라 강연에 대한 동시 신청을 고려하여 트랜잭션을 활용하여 동시성 이슈를 고려하였습니다. 이렇게 함으로써 여러 사용자가
동시에 강연을 신청하는 상황에서 데이터 일관성과 정확성을 유지하면서 문제없이 처리할 수 있도록 보장하였습니다.