<div align="center">



![LOTTO_LOGO](https://github.com/Bkukim/lotto_board_project/assets/153472858/1963ea69-a5b8-46ca-a747-0deb1cace729)
### 📋 직원들이 소통하고 정보를 얻을 수 있는 사내 게시판 서비스
</div>

<br>

# 사내 게시판 프로젝트
Spring boot와 Vue.js를 활용한 사내 커뮤니티 게시판 프로젝트입니다.

<br>


## 💻 프로젝트 소개
#### 1. LOTTO BOARD는 사원들이 소통하고 정보를 공유할 수 있는 사내 게시판 서비스 입니다.
#### 2. 관리자 페이지로 별도의 코딩없이 게시판을 관리 할 수 있습니다.
#### 3. 동아리 모집 게시판으로 사원들이 동아리 활동을 할때 동아리원을 쉽게 모집할 수 있습니다.
#### 4. 이벤트가 발생할 경우 실시간 알림으로 사용자에게 쉽게 이벤트를 알릴수 있습니다.

<br>

## 📌주요 기능
#### 1. 게시판별 글 작성/수정/삭제 기능 및 댓글기능
- 게시판별 CRUD 기능이 있으며 댓글을 추가 하고 조회할 수 있습니다.
#### 2. 전체 게시판 검색 기능
- 한번의 검색어 입력으로 모든 게시판의 게시글을 검색할 수 있습니다.
#### 3. 좋아요 및 신고 기능
- 게시글에 좋아요를 표시할 수 있습니다.
- 부적절한 게시글을 관리자에게 신고가 가능하며 관리자는 이를 관리자 페이지에서 쉽게 처리할 수 있습니다.
#### 4. HOT 게시판 기능
 - 좋아요수가 높은 글은 HOT 게시판 글로 자동으로 이동하게 됩니다.
#### 5. 동아리원 모집기능
- 날씨 api와 지도 api, 주소api, 날짜api 를 이용하여 사원들의 동아리 활동 장소와 날짜등을 쉽게 확인할 수 있습니다.
- 신청 및 승인 기능으로 쉽게 동아리원을 모집할 수 있습니다.
#### 6. 일반 로그인 및 소셜 로그인
- Spring Security 와 JWT를 이용하여 로그인 및 권한관리를 가능하게 하였습니다.
- OAuth2.0을 이용하여 간편한 소셜로그인을 할 수 있습니다. 
#### 7. 알림 기능
 - Redis와 SSE를 이용하여 다중서버에서도 실시간 알림을 이용할 수 있습니다.
#### 8. 관리자 기능
 - 관리자 페이지를 통한 게시판 관리 및 회원 관리, 신고 관리를 할 수 있습니다.

<br>

## 📌개발 기간
2024.05.20(월) ~ 2024.06.24(월)

<br>

## 📌개발 환경
- Version : Java 17
- IDE : IntelliJ
- Framework : SpringBoot 3.1.11
- ORM : JPA

<br>

## 📌 역할 분담
🍊고지연
UI
페이지 : 홈, 검색, 게시글 작성, 게시글 수정, 게시글 상세, 채팅방
공통 컴포넌트 : 게시글 템플릿, 버튼
기능
유저 검색, 게시글 등록 및 수정, 게시글 상세 확인, 댓글 등록, 팔로워 게시글 불러오기, 좋아요 기능

👻김민제
UI
페이지 : 프로필 설정, 프로필 수정, 팔로잉&팔로워 리스트, 상품 등록, 상품 수정, 채팅 목록, 404 페이지
공통 컴포넌트 : 탭메뉴, InputBox, Alert 모달, 댓글
기능
프로필 설정 및 수정 페이지 유저 아이디 유효성 및 중복 검사, 상품 등록 및 수정

😎양희지
UI
페이지 : splash 페이지, sns 로그인 페이지, 로그인, 회원가입
공통 컴포넌트 : 상품 카드, 사용자 배너
기능
splash 페이지, sns로그인 페이지, 로그인 유효성 및 중복 검사, 회원가입 유효성 및 중복 검사, 이메일 검증, 프로필 설정, 접근제한 설정

🐬지창언
UI
페이지 : 사용자 프로필 페이지
공통 컴포넌트 : 탑배너, 하단 모달창
기능
팔로우 & 언팔로우, 로그아웃, 하단 모달창, 댓글 삭제, 게시글 삭제, 상품 삭제, 사용자 게시글 앨범형 이미지, 탑 배너 뒤로가기 버튼, Alert 모달

<br>

## 📌기술 스택
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"/> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"/> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"/> <img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white"/> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/> 
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"/> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"/> <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"> <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"> <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"> <img src="https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white">

