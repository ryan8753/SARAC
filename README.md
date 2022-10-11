# 사락 (詞樂)

---

> 詞(글 사) 
> 
> 樂(즐거울 락) 
> 
> 책을 넘기는 소리, 글을 읽고 쓰고 나누는 즐거움

## 📅프로젝트 진행 기간

---

2022.08.22 - 2022.10.07

## 💡기획 배경

---

대표적인 소셜미디어 인스타그램은 월간 활성 사용자 수(MAU)는 한국 1800만명, 전 세계 20억명으로 전체인구의 1/3이 사용하는 서비스 입니다. 그 중 북스타그램 관련 게시물은 8000만건 이상으로 매우 많이 사용하고 있습니다. 

 하지만 인스타그램에서는 책의 상세정보를 제공하지 않기 때문에  네이버,알라딘과 같은 책 전용 검색 플랫폼을 따로 사용해야한다는 불편함이 존재합니다.

## 📍서비스 소개

---

 '사락' 은 독후감을 통해 사람과 소통하여 자연스럽게 책 내용에 관해 이야기 나눌 수 있고, 독후감을 통해 흥미를 느낀 사람도 책의 정보를 한번에 찾을 수 있는 서비스입니다.

 추가로 개인별, 책 별 통계량과 WordCloud를 제공함으로서 더 재미있게 서비스를 즐길 수 있습니다.

## 💻사용 기술

---

### Backend

- 버전
  
  - IDE : IntelliJ
  - 언어 : Java(OpenJDK 11)
  - DataBase : MySQL 8
  - Framework
    - Spring Boot
    - Gradle
    - Application.yml
    - JPA
    - Spring Security

- 설정파일
  
  <details>
  <summary>build.gradle</summary>
  
  ![Untitled](./README/build.png)
  
  </details>
  
  <details>
  <summary>application.yml</summary>
  
  ![Untitled](./README/application.png)
  
  </details>

### Frontend

- 버전
  
  - IDE : Visual Studio Code
  - Framework
    - Vue 2 LTS
      - router
      - vuetify
      - bootstrap vue
      - babel
      - webpack
    - vuetifym bootstrap vue
  - Nodejs(16.17.0 LTS)

- 설정파일
  
  <details>
  <summary>.env</summary>
  
  ![Untitled](./README/env.png)
  
  </details>
  
  <details>
  <summary>.env.development</summary>
  
  ![Untitled](./README/env_development.png)
  
  </details>
  
  <details>
  <summary>package.json</summary>
  
  ![Untitled](./README/package.png)
  
  </details>

### CI/CD

- AWS EC2

- Docker

- Docker-compose

- Jenkins

- Nginx

- S3

### DATA

- Hadoop

### 협업 환경

- GitLab
- Jira
- MatterMost
- Discord
- Webex
- Notion

## 팀원 소개

---

- 신민아 - TeamLeader, FE, BE, Hadoop, 데이터 수집 + 가공
- 김창현 - FE, 디자인, 데이터 가공
- 남궁준 - Server(CI/CD), FE, BE, 데이터 가공
- 양소정 - FE, BE, 데이터 가공
- 이경준 - FE, BE, UCC, 데이터 가공
- 정다은 - FE, BE, 디자인, 데이터 가공

## 📖프로젝트 산출물

---

<details>
  <summary>ERD</summary>

  ![Untitled](./README/erd.png)

</details>

[기능명세서.pdf](./README/기능_명세.pdf)

## 📖프로젝트 산출물

---

[포팅_메뉴얼.pdf](./README/포팅메뉴얼.pdf)

[API명세서.gif](./README/API.gif)

[Server_&_CI/CD_Architecture.png](./README/SystemArchitecture.png)

[Wireframe](./README/Wireframe.png)

[최종_발표.pdf]()

## 💎사락 서비스 화면

---

### 회원가입

- 카카오톡을 이용하여 회원가입 및 로그인을 진행할 수 있습니다.
  
  ![login](README-asset/login.png)

### 책 검색

- 책 검색 – 책 이름, 저자, ISBN, 키워드

- 바코드 검색

- 독서 트렌드 단어 WordCloud
  
  ![BookSearch](./README/BookSearch.gif)
  ![BarcodeSearch](./README/BarcodeSearch.gif)

### 리뷰/댓글 작성

- 책 검색을 통해 책 내용 가져옴

- 사진 업로드와 해시태그 기능

- 사용자 소통 - 댓글, 좋아요
  
  ![ReviewRegist](./README/review_regist.png)
  ![ReviewRegist](./README/comment_regist.png)

### 개인 통계 데이터 제공

#### 1. 개인통계

- 책에 대한 통계 표시

- 많이 읽은 장르 Top3 표시

- 읽은 책에 대한 통계 표시

- 리뷰에 많이 쓴 해시태그 WordCloud
  ![Statistics](./README/statistics.png)

#### 2. 개인 서재

- 읽고있는책

- 완독한 책

- 읽고 싶은 책
  ![Library](./README/library.png)

### 리뷰 모아 보기/ 인기 도서

- 다른 유저의 최신 리뷰 모아보기

- 최신 인기 도서 
  ![UserReviews](./README/UserReviews.gif)
  ![PopularBooks](./README/PopularBooks.png)

### 주변 탐색

- 책을 읽을 장소 탐색

- 평균 측정 소음 

- 유저 평가
  ![Nearby](./README/)
