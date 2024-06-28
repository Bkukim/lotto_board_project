<template>
  <div class="container" id="fb_all">
    <div
      class="notice_bn mb-5 text-start"
      style="
        height: 200px;
        background-color: #162b59;
        color: #fff;
        padding-left: 30px;
      "
    >
      <h3 class="mb-5 mt-5" style="font-weight: bold">공지사항</h3>
      <p class="mb-5">로또 사내게시판에서 알리는 새소식을 안내해 드립니다.</p>
    </div>

    <nav style="--bs-breadcrumb-divider: '>'" aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="/" style="color: blue; text-decoration: none">Home</a>
        </li>
        <li class="breadcrumb-item active" aria-current="page">공지사항</li>
      </ol>
    </nav>
    <!-- 검색 박스 -->
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
              v-model="title"
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
            @click="searchNotice"
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
    <!-- 검색박스 끝 -->

    <!-- 테이블 -->
    <table class="table mt-5">
      <thead class="text-center">
        <tr>
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
            {{ count - ((page - 1) * pageSize + index) }}
            <!-- {{ index + 1 }} -->
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

    <!-- 페이징 -->
    <!-- {/* paging 시작 */} -->
    <div class="row justify-content-center mt-4">
      <div class="col-auto">
        <b-pagination
          class="custom-pagination col-12 mb-3"
          v-model="page"
          :total-rows="count"
          :per-page="pageSize"
          @click="retrieveNotice"
        ></b-pagination>
      </div>
    </div>
  </div>
  <!-- 자유게시판 중앙정렬 전체박스 끝 -->
</template>

<script>
import NoticeService from "@/services/notice/NoticeService";

export default {
  data() {
    return {
      notices: [],
      title: "",

      // 공통속성
      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 10, // 1페이지당개수(select태그)
    };
  },
  methods: {
    // 전체조회 함수
    async retrieveNotice() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await NoticeService.getAll(
          this.title, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        console.log("프론트입니다");
        const { notices, totalItems } = response.data; // 부서배열(벡엔드 전송)
        this.notices = notices; // 부서배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 상세조회 이동+조회수

    goNoticeAdd() {
      this.$router.push("/admin/notice-add");
    },
    // 검색함수
    async searchNotice() {
      console.log("검색 함수 호출");
      await this.retrieveNotice();
    },
    resetSearch() {
      this.title = "";
      this.retrieveNotice();
    },
  },
  mounted() {
    this.retrieveNotice();
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
  box-shadow: 0 0 0 0.2rem #342a26bf;
  border-color: #342a26bf;
}

.nt_h3 {
  color: #535353;
  text-align: left;
  font-family: "Inter-Light", sans-serif;
  font-size: 30px;
  font-weight: bold;
}

/* 검색버튼 */
.btn {
  margin: 0 2px; /* 버튼 간 간격을 줄이기 위해 여백을 조정 */
}

/* 검색 전체 배경 */
#search_box {
  /* background-color: #e2e2e28c; */
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
}

#button-reset {
  background-color: #162b59;
  color: #ffffff;
  border: none;
}
.notice_bn {
  background-image: url("@/assets/img/notice_icon.png");
  background-repeat: no-repeat;
  background-position: calc(100% - 50px) center; /* 오른쪽에서 20px 왼쪽으로 이동 */
  background-size: 170px 170px;
}
</style>
