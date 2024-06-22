<template>
  <div class="container text-center">
    <nav
      style="padding: 50px 0 0 0; --bs-breadcrumb-divider: '>'"
      aria-label="breadcrumb"
    >
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="/" style="color: blue; text-decoration: none">Home</a>
        </li>
        <li class="breadcrumb-item active" aria-current="page">통합검색</li>
      </ol>
    </nav>

    <h3
      style="
        text-align: left;
        margin-top: -20px;
        margin-bottom: 30px;
        color: #333;
      "
    >
      통합검색
    </h3>

    <!-- 검색 박스 -->
    <div
      class="container text-center"
      style="
        background-color: #f2f2f2;
        border-top: 2px solid #162b59;
        border-bottom: 2px solid #162b59;
      "
    >
      <div
        class="container text-center"
        style="gap: 5px; width: 650px; padding-top: 10px; height: 60px"
        id="search_box"
      >
        <div class="row">
          <div class="col">
            <button
              class="btn btn-primary dropdown-toggle"
              type="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              id="search_ck"

              style="background-color: #162b59; border: none; color: #fff"

            >
              -- 통합검색 선택 ---
            </button>
            <ul class="dropdown-menu" style="text-align: center">
              <li><a class="dropdown-item" href="#">공지사항</a></li>
              <li><a class="dropdown-item" href="#">자유게시판</a></li>
              <li><a class="dropdown-item" href="#">동아리게시판</a></li>
              <li><a class="dropdown-item" href="#">건의/문의사항</a></li>
            </ul>
          </div>
          <div class="col">
            <!-- 검색어를 입력하세요 -->
            <div class="input-group mb-3">
              <input
                type="text"
                class="form-control"
                aria-label="Sizing example input"
                aria-describedby="inputGroup-sizing-default"
                placeholder="검색어를 입력하세요."
                v-model="searchTitle"
              />
            </div>
          </div>
          <!-- 검색 버튼 -->
          <div class="col-auto">
            <!-- col-auto로 변경하여 너비를 자동 조정 -->
            <button
              class="btn btn-outline-secondary"
              type="button"
              id="button-search"
              @click="SearchAll"
              style="background-color: #162b59; color: #fff; border: none"
            >
              검색
            </button>
          </div>
          <!--  초기화 버튼 -->
          <div class="col-auto">
            <!-- col-auto로 변경하여 너비를 자동 조정 -->
            <button
              class="btn"
              type="button"
              id="button-reset"
              style="background-color: #fff"
              @click="resetSearch"
            >
              초기화
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- 검색박스 끝 -->

    <h4 class="mt-5 mb-5" style="color: #333; letter-spacing: -1.5px">
      검색어 " {{searchTitle}}  " 에 관해 전체 '  {{noticesTotalItems+freeBoardsTotalItems+clubBoardsTotalItems+complaintBoardsTotalItems}} '건이 검색되었습니다.

    </h4>

    <!-- 공지사항 테이블 -->
    <h5
      style="
        text-align: left;
        margin: 50px 0 0 0;
        font-weight: bold;
        color: #333;
        letter-spacing: -1.5px;
      "
    >
      공지사항 ( {{noticesTotalItems}} )
    </h5>
    <table class="table mt-5">
      <thead class="text-center">
        <tr style="font-size: 12px">
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">등록일</th>
          <th scope="col">조회수</th>
        </tr>
      </thead>
      <tbody>
        <!-- 반복문 시작할 행 -->
        <tr v-for="(data, index) in notices" :key="index">
          <td class="text-center">
            {{ index + 1 }}
          </td>
          <td @click="viewsUp" class="col-5">
            <router-link
              :to="`/notice/notice-check/` + data.noticeId + '/' + data.eventYN"
              style="
                color: #444444;
                font-weight: bold;
                text-decoration: none;
                text-align: left;
                padding-left: 5vw;
              "
            >
              [{{ data.noticeType }}] {{ data.title }}
            </router-link>
          </td>
          <td class="text-center">관리자</td>
          <td class="text-center">{{ data.insertTime }}</td>
          <td class="text-center">{{ data.views }}</td>
        </tr>
      </tbody>
    </table>

    <br />
    <!-- <router-link to="#">
      <h6 style="text-align: right; font-size: 14px; letter-spacing: -1.3px">
        결과 더 보기 >
      </h6>
    </router-link> -->

    <!-- 공지사항 페이징  -->
    <div class="row justify-content-center mt-4">
      <div class="col-auto">
        <b-pagination
          class="col-12 mb-3 custom-pagination"
          v-model="page"
          :total-rows="noticesTotalItems"
          :per-page="pageSize"
        ></b-pagination>
      </div>
    </div>

    <br />
    <br />
    <br />

    <!-- 자유게시판 테이블 -->
    <h5
      style="
        text-align: left;
        margin: 50px 0 0 0;
        font-weight: bold;
        color: #333;
        letter-spacing: -1.5px;
      "
    >
      자유게시판 ( {{freeBoardsTotalItems}} )
    </h5>
    <table class="table mt-5">
      <thead class="text-center">
        <tr style="font-size: 12px">
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">등록일</th>
          <th scope="col">조회수</th>
        </tr>
      </thead>
      <tbody>
        <!-- 반복문 시작할 행 -->
        <tr v-for="(data, index) in freeBoards" :key="index">
          <td class="text-center">
            {{ index + 1 }}
          </td>
          <td @click="viewsUp" class="col-5">
            <router-link
              :to="`/notice/notice-check/` + data.noticeId + '/' + data.eventYN"
              style="
                color: #444444;
                font-weight: bold;
                text-decoration: none;
                text-align: left;
                padding-left: 5vw;
              "
            >
               {{ data.title }}
            </router-link>
          </td>
          <td class="text-center">관리자</td>
          <td class="text-center">{{ data.insertTime }}</td>
          <td class="text-center">{{ data.views }}</td>
        </tr>
      </tbody>
    </table>
    <br />
    <!-- <router-link to="#">
      <h6 style="text-align: right; font-size: 14px; letter-spacing: -1.3px">
        결과 더 보기 >
      </h6>
    </router-link> -->

    <!-- 자유게시판 페이징 -->
    <div class="row justify-content-center mt-4">
      <div class="col-auto">
        <b-pagination
          class="col-12 mb-3 custom-pagination"
          v-model="page"
          :total-rows="freeBoardsTotalItems"
          :per-page="pageSize"
        ></b-pagination>
      </div>
    </div>

    <br />
    <br />
    <br />
    <!-- 동아리게시판 테이블 -->
    <h5
      style="
        text-align: left;
        margin: 50px 0 0 0;
        font-weight: bold;
        color: #333;
        letter-spacing: -1.5px;
      "
    >
      동아리게시판 ( {{clubBoardsTotalItems}} )
    </h5>
    <table class="table mt-5">
      <thead class="text-center">
        <tr style="font-size: 12px">
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">등록일</th>
          <th scope="col">조회수</th>
        </tr>
      </thead>
      <tbody>
        <!-- 반복문 시작할 행 -->
        <tr v-for="(data, index) in clubBoards" :key="index">
          <td class="text-center">
            {{ index + 1 }}
          </td>
          <td @click="viewsUp" class="col-5">
            <router-link
              :to="`/notice/notice-check/` + data.noticeId + '/' + data.eventYN"
              style="
                color: #444444;
                font-weight: bold;
                text-decoration: none;
                text-align: left;
                padding-left: 5vw;
              "
            >
              {{ data.title }}
            </router-link>
          </td>
          <td class="text-center">관리자</td>
          <td class="text-center">{{ data.insertTime }}</td>
          <td class="text-center">{{ data.views }}</td>
        </tr>
      </tbody>
    </table>
    <br />
    <!-- <router-link to="#">
      <h6 style="text-align: right; font-size: 14px; letter-spacing: -1.3px">
        결과 더 보기 >
      </h6>
    </router-link> -->

    <!-- 동아리게시판 페이징 -->
    <div class="row justify-content-center mt-4">
      <div class="col-auto">
        <b-pagination
          class="col-12 mb-3 custom-pagination"
          v-model="page"
          :total-rows="clubBoardsTotalItems"
          :per-page="pageSize"
        ></b-pagination>
      </div>
    </div>

    <br />
    <br />
    <br />
    <!-- 건의게시판 테이블 -->
    <h5
      style="
        text-align: left;
        margin: 50px 0 0 0;
        font-weight: bold;
        color: #333;
        letter-spacing: -1.5px;
      "
    >
      건의게시판 ( {{complaintBoardsTotalItems}} )
    </h5>
    <table class="table mt-5">
      <thead class="text-center">
        <tr style="font-size: 12px">
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">등록일</th>
          <th scope="col">조회수</th>
        </tr>
      </thead>
      <tbody>
        <!-- 반복문 시작할 행 -->
        <tr v-for="(data, index) in complaintBoards" :key="index">
          <td class="text-center">
            {{ index + 1 }}
          </td>
          <td @click="viewsUp" class="col-5">
            <router-link
              :to="`/notice/notice-check/` + data.noticeId + '/' + data.eventYN"
              style="
                color: #444444;
                font-weight: bold;
                text-decoration: none;
                text-align: left;
                padding-left: 5vw;
              "
            >
            {{ data.title }}
            </router-link>
          </td>
          <td class="text-center">관리자</td>
          <td class="text-center">{{ data.insertTime }}</td>
          <td class="text-center">{{ data.views }}</td>
        </tr>
      </tbody>
    </table>
    <br />
    <!-- <router-link to="#">
      <h6 style="text-align: right; font-size: 14px; letter-spacing: -1.3px">
        결과 더 보기 >
      </h6>
    </router-link> -->

    <!-- 건의게시판 페이징 -->
    <div class="row justify-content-center mt-4">
      <div class="col-auto">
        <b-pagination
          class="col-12 mb-3 custom-pagination"
          v-model="page"
          :total-rows="complaintBoardsTotalItems"
          :per-page="pageSize"
        ></b-pagination>
      </div>
    </div>
  </div>
  <br />
  <br />
  <br />
  <br />
  <br />
  <br />
  <br />
  <br />
  <br />
</template>
<script>

import ClubBoardService from "@/services/board/club/ClubBoardService";
import ComplaintBoardService from "@/services/board/complaint/ComplaintBoardService";
import FreeBoardService from "@/services/board/free/FreeBoardService";
import NoticeService from "@/services/notice/NoticeService";


export default {
  data() {
    return {

      // // 자유게시판 페이지네이션 상태
      // freeBoardPage: 1,
      // freeBoardCount: 0,
      // freeBoardPageSize: 3,
      // // 부서게시판 페이지네이션 상태

      // deptBoardPage: 1,
      // deptBoardCount: 0,
      // deptBoardPageSize: 3,

      // // 건의게시판 페이지네이션 상태
      // complaintBoardPage: 1,
      // complaintBoardCount: 0,
      // complaintBoardPageSize: 3,

      // // 동호회게시판 페이지네이션 상태
      // clubBoardPage: 1,
      // clubBoardCount: 0,
      // clubBoardPageSize: 3,

      notices: [],
      noticesTotalItems:0,
      freeBoards: [],
      freeBoardsTotalItems:0,
      clubBoards: [],
      clubBoardsTotalItems:0,
      complaintBoards: [],
      complaintBoardsTotalItems:0,
      searchTitle: "",
      page: 1, // 현재페이지번
      pageSize: 3, // 1페이지당개수(select태그)

    };
  },
  methods: {
    // 검색 함수
    async AllsearchBoard() {
      console.log("검색 함수 호출");
      await this.retrieveSearchBoard();
    },
    // 공지사항 전체조회 함수
    async retrieveNotice() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await NoticeService.getAll(
          this.searchTitle, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        console.log("프론트입니다");
        const { notices, totalItems } = response.data; // 부서배열(벡엔드 전송)
        this.notices = notices; // 부서배열(벡엔드 전송)
        // this.count = totalItems; // 전체페이지수(벡엔드 전송)
        this.noticesTotalItems = totalItems; // 전체페이지수(벡엔드 전송)
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
     // 자유게시판 전체조회 함수
     async retrieveFreeBoard() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await FreeBoardService.getAllBoard(
          this.searchTitle, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        const { freeBoardList, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.freeBoards = freeBoardList; // 부서배열(벡엔드 전송)
        // this.count = totalItems; // 전체페이지수(벡엔드 전송)
        this.freeBoardsTotalItems = totalItems; // 전체페이지수(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

     // 동호회게시판 젆체조회
     async retrieveClubBoard() {``
      try {
        let response = await ClubBoardService.getAllClubBoardByLocation(
          this.searchTitle, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        const { clubBoardList, totalItems } = response.data;
        this.clubBoards = clubBoardList;
        // this.clubBoardCount = totalItems;
        this.clubBoardsTotalItems = totalItems;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
     // 건의 게시판 전체조회 함수
     async retrieveComplaintBoard() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await ComplaintBoardService.getAllComplaintBoard(
          this.searchTitle, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        const { complaintBoardList, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.complaintBoards = complaintBoardList; // 부서배열(벡엔드 전송)
        // this.count = totalItems; // 전체페이지수(벡엔드 전송)
        this.complaintBoardsTotalItems = totalItems; // 전체페이지수(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 초기화 함수
    resetSearch() {
      this.searchTitle = "";
      this.retrieveSearchBoard();
    },
    SearchAll(){
      console.log("asdfa")
      this.retrieveNotice();
      this.retrieveFreeBoard();
      this.retrieveClubBoard();
      this.retrieveComplaintBoard();
    }
  
  },
};
</script>
<style>
/* 검색버튼 */
.btn {
  margin: 0 2px; /* 버튼 간 간격을 줄이기 위해 여백을 조정 */
}

/* 검색 전체 배경 */
/* #search_box {
  height: 75px;
  padding: 20px;
}
#search_ck {
  background-color: #ffffff;
  color: #212121;
  border: none;
}
#button-search {
  background-color: #162b59;
  color: #ffffff;
  border: none;
} */
</style>
