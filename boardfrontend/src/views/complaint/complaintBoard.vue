<template>
  <div class="container text-center" id="fb_all">
    <h3 class="mb-5 mt-5">건의 / 문의사항 게시판</h3>
        <p class="mb-5">
      건의 게시판은 전달하고 싶은 다양한 의견이나 아이디어를 익명으로 전달하는 게시판입니다. 
    </p>

    <!-- 검색 박스 -->
    <div class="container text-center" style="gap: 5px" id="search_box">
      <div class="row">
        <div class="col">
          <button
            class="btn btn-primary dropdown-toggle"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
            id="search_ck"
          >
            -- 검색 선택 ---
          </button>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
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
            @click="searchComplaintBoard"
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
            @click="resetComplaintSearch"
          >
            초기화
          </button>
        </div>
      </div>
    </div>
    <!-- 검색박스 끝 -->

    <!-- 테이블 -->
    <table class="table mt-5">
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">등록일</th>
          <th scope="col">좋아요</th>
          <th scope="col">상태</th>
        </tr>
      </thead>
      <tbody>
        <!-- 반복문 시작할 행 -->
        <tr v-for="(data, index) in complaintBoardList" :key="index">
          <td>{{ (page - 1) * pageSize + index + 1 }}</td>
          <td id="router_hv" style="text-align: left; padding-left: 100px;">
            <router-link style="color: #444444; font-weight: bold; text-decoration:none ;"
              :to="'/complaint/complaint-boardDetail/' + data.complaintBoardId"
              class="router-link-exact-active alltext"
            >
              {{ data.title }}
            </router-link>
          </td>
          <td>익명</td>
          <td>{{ data.insertTime }}</td>
          <td>{{ data.likes }}</td>
          <td>{{ data.status }}</td>
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
          @click="writeComplaintBoard"
        >
          글쓰기
        </button>
      </router-link>
    </div>

    <!-- 페이징 -->
    <!-- {/* paging 시작 */} -->
    <div class="row justify-content-center mt-5">
      <div class="col-auto" style="margin-top: 50px">
        <b-pagination
          class="custom-pagination col-12 mb-3"
          v-model="page"
          :total-rows="count"
          :per-page="pageSize"
          @click="retrieveComplaintBoard"
        ></b-pagination>
      </div>
    </div>
  </div>
  <!-- 자유게시판 중앙정렬 전체박스 끝 -->
</template>

<script>
import ComplaintBoardService from "@/services/board/complaint/ComplaintBoardService";

export default {
  data() {
    return {
      complaintBoardList: [],
      searchTitle: "",
      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 10, // 1페이지당개수(select태그)
    };
  },
  methods: {
    // 전체조회 함수
    async retrieveComplaintBoard() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await ComplaintBoardService.getAllComplaintBoard(
          this.searchTitle, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        // TODO: 복습 : 2) 객체분할 할당
        const { complaintBoardList, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.complaintBoardList = complaintBoardList; // 부서배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
        // 검색 함수
    async searchComplaintBoard() {
      console.log("검색 함수 호출");
      await this.retrieveComplaintBoard();
    },
        // 초기화 함수
    resetComplaintSearch() {
      this.searchTitle = "";
      this.retrieveFreeBoard();
    },
    // 글 쓰러가기 함수
    writeComplaintBoard() {
      this.$router.push("/complaint/complaint-boardAdd");
    },
  },
  mounted() {
    this.retrieveComplaintBoard();
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
  color: #342a26;
}

.custom-pagination .page-link:hover {
  background-color: #ffffff;
  border-color: 1px solid#8f8f8f;
  color: #342a26;
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
  height: 75PX;
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
