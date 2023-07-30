# 게시판 구현 프로젝트
해당 깃허브 프로젝트 마일스톤, 이슈에서 작업 플로우를 확인하실 수 있으십니다.

**프로젝트 개발 기간** : 2023.07-25 ~ 2023.07.30

## 1. 기술 스택
### Back-end
Spring Data JPA, JDK 17, QueryDSL, MySQL, Gradle

### Front-end
Vue2, Bootstrap Vue, javascript

### 개발 환경
Window 11, intelliJ IDEA 2022.2.1, Visutal Studio

## 2. 사용법
1. git clone "https://github.com/hyeong-jun-kim/nts-KimHyeongJun.git" 를 터미널에 입력해 프로젝트 폴더를 복사한다
2. intelliJ IDEA를 사용해 board_backend 프로젝트 폴더를 열고 Run 'BoardBackendApplication'을 한다
3.MySQL에서 스키마를 생성하고, application.yml에 연결 정보를 업데이트한다
4. Visual Studio를 사용해 board_front 프로젝트 폴더를 열고 `npm run serve` 명령어를 입력한다
5. 백엔드 포트는 `8080`, 프론트 포트는 `3000`으로 되어있다.
6. 프론트관련 라이브러리는 board_front 폴더에 있는 README.md를 참고한다

## 3. Git 규약
### Git-flow
work -> develop, develop -> main, hotfix -> main 작업 반영시에 PR을 올려 반영한다.

- `main`: 최종 배포용 브랜치
- `hotfix`: 배포 후 버그 수정 브랜치
- `develop`: 작업 병합 브랜치
- `work`: 이슈 티켓번호로 이루어진 작업 브랜치

### Git Commit Convetion
- `feat` : 새로운 기능 추가
- `fix` : 버그 수정
- `docs` : 문서 작성 및 수정
- `style` : 코드 포맷팅, 세미콜론 누락 등 수정
- `refactor` : 코드 리팩토링
- `test` : 테스트 코드 작성
- `chore` : 빌드 업무 수정
- `setting` : 초기 환경 세팅

## 4. ERD
![image](https://github.com/hyeong-jun-kim/RNS-Spring/assets/53989167/0bbba5b0-2248-4bf7-91b7-3d88f6fb8012)


## 5. 구현 화면
### 1. 게시글 목록
![image](https://github.com/hyeong-jun-kim/RNS-Spring/assets/53989167/4b6a69e2-c251-4752-944f-f6af75c720bd)

### 2. 게시글 작성
![image](https://github.com/hyeong-jun-kim/RNS-Spring/assets/53989167/f799c772-0c2c-4afb-a5e2-da85199d1efd)

### 3. 게시글 수정
![image](https://github.com/hyeong-jun-kim/RNS-Spring/assets/53989167/99fc9af3-1e69-4e81-ad86-28a114fd43a6)


### 4. 게시글 상세보기
![image](https://github.com/hyeong-jun-kim/RNS-Spring/assets/53989167/8eadfe36-0a79-48bd-82b5-2a2cc1b9a5d1)

### 5. 댓글
![image](https://github.com/hyeong-jun-kim/RNS-Spring/assets/53989167/15792e99-aa91-415c-a5e7-7ad82fe65aed)

### 6. 게시글 검색
![image](https://github.com/hyeong-jun-kim/RNS-Spring/assets/53989167/efa2bc91-da7c-43ae-aabe-e4a23b784c7d)
