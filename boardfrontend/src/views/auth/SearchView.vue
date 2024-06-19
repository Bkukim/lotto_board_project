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
              style="background-color: #162b59; border: none"
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
              @click="AllsearchBoard"
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
    <router-link to="#">
      <h6 style="text-align: right; font-size: 14px; letter-spacing: -1.3px">
        결과 더 보기 >
      </h6>
    </router-link>

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
    <router-link to="#">
      <h6 style="text-align: right; font-size: 14px; letter-spacing: -1.3px">
        결과 더 보기 >
      </h6>
    </router-link>

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
    <router-link to="#">
      <h6 style="text-align: right; font-size: 14px; letter-spacing: -1.3px">
        결과 더 보기 >
      </h6>
    </router-link>

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
    <router-link to="#">
      <h6 style="text-align: right; font-size: 14px; letter-spacing: -1.3px">
        결과 더 보기 >
      </h6>
    </router-link>
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
import SearchService from "@/services/search/SearchService";

export default {
  data() {
    return {
      notices: [],
      noticesTotalItems: 0,
      freeBoards: [],
      freeBoardsTotalItems: 0,
      clubBoards: [],
      clubBoardsTotalItems: 0,
      complaintBoards: [],
      complaintBoardsTotalItems: 0,
      searchTitle: "",
      page: 1, // 현재페이지번호
      pageSize: 10, // 1페이지당개수(select태그)
    };
  },
  methods: {
    // 검색 함수
    async AllsearchBoard() {
      console.log("검색 함수 호출");
      await this.retrieveSearchBoard();
    },
    // 초기화 함수
    resetSearch() {
      this.searchTitle = "";
      this.retrieveSearchBoard();
    },
    async retrieveSearchBoard() {
      let response = await SearchService.searchAll(
        this.searchTitle,
        this.page-1,
        this.pageSize
      );
      const {
        notices,
        noticesTotalItems,
        freeBoards,
        freeBoardsTotalItems,
        clubBoards,
        clubBoardsTotalItems,
        complaintBoards,
        complaintBoardsTotalItems,
      } = response.data; 

      // TODO: 3) 바인딩변수(속성)에 저장
      this.notices = notices; 
      this.noticesTotalItems = noticesTotalItems; 
      this.freeBoards = freeBoards; 
      this.freeBoardsTotalItems = freeBoardsTotalItems; 
      this.clubBoards = clubBoards; 
      this.clubBoardsTotalItems = clubBoardsTotalItems; 
      this.complaintBoards = complaintBoards; 
      this.complaintBoardsTotalItems = complaintBoardsTotalItems; 
    },
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
