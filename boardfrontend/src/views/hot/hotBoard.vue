<template>
  <div class="container text-center" id="fb_all">
    <h3 class="mb-5 mt-5">이달의 HOT</h3>
    <p class="mb-5">
      HOT 게시판은 자유로운 의견을 남기는 공간으로 건의관련 답변은 드리지
      않습니다. <br />
      건의관련 및 문의사항은 건의게시판을 이용해주시길 바랍니다.
    </p>

    <nav style="--bs-breadcrumb-divider: '>'" aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="/" style="color: blue; text-decoration: none">Home</a>
        </li>
        <li
          class="breadcrumb-item active"
          aria-current="page"
          style="text-decoration: underline"
        >
          HOT 게시판
        </li>
      </ol>
    </nav>
    <!-- 검색박스 -->
    <div class="container text-center" style="gap: 5px" id="search_box">
      <div class="row">
        <div class="col"></div>
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
            @click="searchFreeBoard"
          >
            검색
          </button>
        </div>
        <!--  초기화 버튼 -->
        <div class="col-auto">
          <!-- col-auto로 변경하여 너비를 자동 조정 -->
          <button
            class="btn btn-outline-secondary"
            type="button"
            id="button-reset"
            @click="resetSearch"
          >
            초기화
          </button>
        </div>
      </div>
    </div>

    <!-- 테이블 -->
    <table class="table mt-5">
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">등록일</th>
          <th scope="col">좋아요</th>
          <!-- <th scope="col">조회수</th> -->
        </tr>
      </thead>
      <tbody>
        <!-- 반복문 시작할 행 -->
        <tr v-for="(data, index) in hotList" :key="index">
          <td>{{ (page - 1) * pageSize + index + 1 }}</td>
          <td>
            <router-link
              :to="'/free/free-boardDetail/' + data.freeBoardId"
              style="
                color: #444444;
                text-decoration: none;
                text-align: left !important;
                padding-left: 5vw;
              "
            >
              {{ data.title }}
            </router-link>
          </td>
          <td>{{ data.userId }}</td>
          <td>{{ data.insertTime }}</td>
          <td>{{ data.likes }}</td>
        </tr>
      </tbody>
    </table>

    <!-- 글쓰기 버튼-->
    <div class="mt-5">
      <router-link to="">
        <button
          class="btn btn-outline-secondary"
          type="button"
          id="button-Writing"
          style="margin-left: 1220px"
        >
          글쓰기
        </button>
      </router-link>
    </div>

   
  </div>
  <!-- 자유게시판 중앙정렬 전체박스 끝 -->
</template>

<script>
import FreeBoardService from "@/services/board/free/FreeBoardService";

export default {
  data() {
    return {
      hotList: [],
      searchTitle: "",
      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 10, // 1페이지당개수(select태그)
    };
  },
  methods: {
    // 전체조회 함수
    async retrieveHotBoard() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await FreeBoardService.getHotBoard();
        // TODO: 복습 : 2) 객체분할 할당
        const { hotList, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.hotList = hotList; // 부서배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log("데[이터확인]",response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 검색 함수
    async searchFreeBoard() {
      console.log("검색 함수 호출");
      await this.retrieveHotBoard();
    },
    // 초기화 함수
    resetSearch() {
      this.searchTitle = "";
      this.retrieveHotBoard();
    },
  },
  mounted() {
    this.retrieveHotBoard();
    window.scrollTo(0, 0);
  },
};
</script>

<style>
/* 페이지 전체 높이 */
#fb_all {
  height: 100vw;
}
h3,
p {
  letter-spacing: -1px;
}

/* 페이징 번호 디자인 */
.custom-pagination .page-item.active .page-link {
  background-color: #162b59;
  border-color: #ffffff;
  color: white;
}

.custom-pagination .page-link {
  color: #162b59;
}

.custom-pagination .page-link:hover {
  background-color: #ffffff;
  border-color: 1px solid#8f8f8f;
  color: #162b59;
  /* border: none; */
}

.custom-pagination .page-link:focus {
  outline: none;
  box-shadow: 0 0 0 0.2rem #162b59;
  border-color: #162b59;
}

/* 검색버튼 */
.btn {
  margin: 0 2px; /* 버튼 간 간격을 줄이기 위해 여백을 조정 */
}

/* 검색 전체 배경 */
#search_box {
  background-color: #e2e2e28c;
  height: 75px;
  padding: 20px;
}
#search_ck {
  background-color: #ffffff;
  color: #212121;
  border: none;
}
#button-search,
#button-Writing {
  background-color: #162b59;
  color: #ffffff;
  border: none;
}

#button-reset {
  background-color: #162b59;
  color: #ffffff;
  border: none;
}
</style>
