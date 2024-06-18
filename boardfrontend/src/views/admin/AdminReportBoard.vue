// 관리자 신고 게시판
<template>
  <div class="main-container d-flex" style="height: auto">
    <AdminHeaderCom />
    <div class="main-content">
      <div class="container text-center" id="fb_all">
        <h3 class="mb-5 mt-5">관리자 신고 게시판 관리</h3>
        <!-- <p class="mb-5">
      자유게시판은 자유로운 의견을 남기는 공간으로 건의관련 답변은 드리지
      않습니다. <br />
      건의관련 및 문의사항은 건의게시판을 이용해주시길 바랍니다.
    </p> -->

        <!-- 검색 박스 -->
        <div class="container text-center" style="gap: 5px" id="search_box">
          <div class="row">
            <div class="col">
              <!-- <button
            class="btn btn-primary dropdown-toggle"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
            id="search_ck"
          >
            
          </button> -->
              <!-- <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul> -->
            </div>
            <div class="col">
              <!-- 검색어를 입력하세요 -->
              <div class="input-group mb-3">
                <input
                  type="text"
                  class="form-control"
                  aria-label="Sizing example input"
                  aria-describedby="inputGroup-sizing-default"
                  placeholder="아이디를 입력하세요."
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
                @click="searchFreeBoardReport"
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

        <!-- 게시판 탭 시작 -->
        <div class="tab-container">
          <ul class="nav nav-tabs">
            <li class="nav-item" @click="activeTab = 'free'">
              <a class="nav-link" :class="{ active: activeTab === 'free' }"
                >자유게시판</a
              >
            </li>
            <li class="nav-item" @click="activeTab = 'dept'">
              <a class="nav-link" :class="{ active: activeTab === 'dept' }"
                >부서게시판</a
              >
            </li>
          </ul>
          <div class="tab-content">
            <!-- 자유 신고테이블 -->
            <div v-show="activeTab === 'free'">
              <div class="table-responsive shadow">
                <table class="table mt-5">
                  <thead>
                    <tr>
                      <th scope="col">번호</th>
                      <th
                        scope="col"
                        style="text-align: left; padding-left: 100px"
                      >
                        신고 사유
                      </th>
                      <th scope="col">작성자</th>
                      <th scope="col">등록일</th>
                      <!-- <th scope="col">좋아요</th> -->
                      <th scope="col">반려</th>
                      <th scope="col">삭제</th>
                      <!-- <th scope="col">조회수</th> -->
                    </tr>
                  </thead>
                  <tbody>
                    <!-- 반복문 시작할 행 -->
                    <tr
                      v-for="(data, index) in freeBoardReportsList"
                      :key="index"
                    >
                      <td>{{ (page - 1) * pageSize + index + 1 }}</td>
                      <td
                        id="router_hv"
                        style="text-align: left; padding-left: 100px"
                      >
                        <router-link
                          style="
                            color: #444444;
                            font-weight: bold;
                            text-decoration: none;
                          "
                          :to="'/free/free-boardDetail/' + data.freeBoardId"
                          class="router-link-exact-active alltext"
                        >
                          {{ data.content }}
                        </router-link>
                      </td>
                      <td>{{ data.userId }}</td>
                      <td>{{ data.insertTime }}</td>
                      <!-- <td>{{ data.likes }}</td> -->
                      <td>
                        <button
                          class="btn btn-outline-secondary"
                          type="button"
                          id="button-search"
                          data-bs-toggle="modal"
                          data-bs-target="#exampleModal"
                          data-bs-whatever="@mdo"
                          @click="getUser(data.userId)"
                        >
                          반려
                        </button>
                      </td>
                      <td>
                        <button
                          class="btn btn-outline-secondary"
                          type="button"
                          id="button-search"
                          data-bs-toggle="modal"
                          data-bs-target="#exampleModal"
                          data-bs-whatever="@mdo"
                          @click="deleteFreeBoardReport"
                        >
                          삭제
                        </button>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>

            <!-- 부서 신고테이블 -->
            <div v-show="activeTab === 'dept'">
              <div class="table-responsive shadow">
                <table class="table mt-5">
                  <thead>
                    <tr>
                      <th scope="col">번호</th>
                      <th
                        scope="col"
                        style="text-align: left; padding-left: 100px"
                      >
                        신고 사유
                      </th>
                      <th scope="col">작성자</th>
                      <th scope="col">등록일</th>
                      <!-- <th scope="col">좋아요</th> -->
                      <th scope="col">반려</th>
                      <th scope="col">삭제</th>
                      <!-- <th scope="col">조회수</th> -->
                    </tr>
                  </thead>
                  <tbody>
                    <!-- 반복문 시작할 행 -->
                    <tr
                      v-for="(data, index) in deptBoardReportsList"
                      :key="index"
                    >
                      <td>{{ (page - 1) * pageSize + index + 1 }}</td>
                      <td
                        id="router_hv"
                        style="text-align: left; padding-left: 100px"
                      >
                        <router-link
                          style="
                            color: #444444;
                            font-weight: bold;
                            text-decoration: none;
                          "
                          :to="'/free/free-boardDetail/' + data.freeBoardId"
                          class="router-link-exact-active alltext"
                        >
                          {{ data.content }}
                        </router-link>
                      </td>
                      <td>{{ data.userId }}</td>
                      <td>{{ data.insertTime }}</td>
                      <!-- <td>{{ data.likes }}</td> -->
                      <td>
                        <button
                          class="btn btn-outline-secondary"
                          type="button"
                          id="button-search"
                          data-bs-toggle="modal"
                          data-bs-target="#exampleModal"
                          data-bs-whatever="@mdo"
                          @click="getUser(data.userId)"
                        >
                          반려
                        </button>
                      </td>
                      <td>
                        <button
                          class="btn btn-outline-secondary"
                          type="button"
                          id="button-search"
                          data-bs-toggle="modal"
                          data-bs-target="#exampleModal"
                          data-bs-whatever="@mdo"
                          @click="getUser(data.userId)"
                        >
                          삭제
                        </button>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>

        <!-- 글쓰기 버튼-->
        <!-- <div class="mt-5">
          <router-link to="">
            <button
              class="btn btn-outline-secondary"
              type="button"
              id="button-Writing"
              style="margin-left: 1220px"
              @click="writeFreeBoard"
            >
              글쓰기
            </button>
          </router-link>
        </div> -->

        <!-- 페이징 -->
        <!-- {/* paging 시작 */} -->
        <div class="row justify-content-center mt-5">
          <div class="col-auto" style="margin-top: 50px">
            <b-pagination
              class="custom-pagination col-12 mb-3"
              v-model="page"
              :total-rows="count"
              :per-page="pageSize"
              @click="retrieveFreeBoardReport"
            ></b-pagination>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 자유게시판 중앙정렬 전체박스 끝 -->
</template>

<script>
import FreeBoardService from "@/services/board/free/FreeBoardService";
import AdminHeaderCom from "@/components/common/AdminHeaderCom.vue";
import DeptBoardService from "@/services/board/dept/DeptBoardService";

export default {
  components: {
    AdminHeaderCom,
  },
  data() {
    return {
      freeBoardReportsList: [],
      deptBoardReportsList: [],
      freeBoardList:{},

      searchTitle: "",

      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 10, // 1페이지당개수(select태그)
      activeTab: "free", // 추가된 부분: 활성화된 탭 상태
    };
  },
  methods: {
    // 자유 신고 전체조회 함수
    async retrieveFreeBoardReport() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await FreeBoardService.getAllFreeBoardReport(
          this.searchTitle, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        // TODO: 복습 : 2) 객체분할 할당
        const { freeBoardReportsList, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.freeBoardReportsList = freeBoardReportsList; // 부서배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // 부서 신고 전체조회 함수
    async retrieveDeptBoardReport() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await DeptBoardService.getAllDeptBoardReport(
          this.searchTitle, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        // TODO: 복습 : 2) 객체분할 할당
        const { deptBoardReportsList, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.deptBoardReportsList = deptBoardReportsList; // 부서배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 검색 함수
    async searchFreeBoardReport() {
      console.log("검색 함수 호출");
      await this.retrieveFreeBoard();
    },
    // 초기화 함수
    resetSearch() {
      this.searchTitle = "";
      this.retrieveFreeBoard();
    },
    // // 글 쓰러가기 함수
    // writeFreeBoard() {
    //   this.$router.push("/free/free-boardAdd");
    // },

        // 삭제 함수
    async deleteFreeBoardReport() {
      try {
        if (confirm("정말로 삭제하시겠습니까?")) {
          let response = await FreeBoardService.deleteFreeBoard(
            this.freeBoardList.freeBoardId
          );
          // 로깅
          console.log(response.data);
          alert("게시글이 삭제되었습니다.");
          this.$router.push("/admin/report");
        } else {
          return;
        }
      } catch (e) {
        console.log(e);
      }
    },
  },
  mounted() {
    this.retrieveFreeBoardReport();
    this.retrieveDeptBoardReport();
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
#router_hv:hover {
  text-decoration: underline 1px solid;
}
</style>
