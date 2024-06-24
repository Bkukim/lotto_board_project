// 관리자 신고 게시글
<template>
  <HeaderCom :hideHeader="true" />
  <!-- 관리자 페이지에서는 헤더를 숨김 -->
  <div class="main-container d-flex" style="height: 900px;">
    <AdminHeaderCom class="sidebar" :hideHeader="true" />
    <div class="content-wrapper" style="flex: 1; padding: 20px; height: auto;">
      <h2 class="text-center my-5 font-weight-bold" style="letter-spacing: -1.5px;"> 관리자 신고 게시글</h2>
      <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="section-title mt-5 mb-5" style="letter-spacing: -3px; font-size: 25px;">신고 목록</h3>


      </div>

      <!-- 게시판 탭 시작 -->
      <div class="tab-container">
        <ul class="nav nav-tabs">
          <!-- <li class="nav-item" @click="retrieveFreeBoardReport"> -->
          <li class="nav-item" @click="setActiveTab('free')">
            <a class="nav-link" :class="{ active: activeTab === 'free' }"
              >자유게시판</a
            >
          </li>
          <!-- <li class="nav-item" @click="retrieveDeptBoardReport"> -->
          <li class="nav-item" @click="setActiveTab('dept')">
            <a class="nav-link" :class="{ active: activeTab === 'dept' }"
              >부서게시판</a
            >
          </li>
          <li class="nav-item" @click="setActiveTab('freeprocessed')">
            <a
              class="nav-link"
              :class="{ active: activeTab === 'freeprocessed' }"
              >자유게시판 처리 완료</a
            >
          </li>
          <li class="nav-item" @click="setActiveTab('deptprocessed')">
            <a
              class="nav-link"
              :class="{ active: activeTab === 'deptprocessed' }"
              >부서게시판 처리 완료</a
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
                    <th scope="col">취소</th>
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
                    <td>{{ (freeBoardPage - 1) * freeBoardPageSize + index + 1 }}</td>
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
                        @click="updateFreeBoardReport(data.reportId, data)"
                        style="
                          background-color: #fff;
                          border: 1px solid #162b59;
                          color: #162b59;
                          text-align: center;
                        "

                      >
                        신고 사유
                      </th>
                      <th scope="col">작성자</th>
                      <th scope="col">등록일</th>
                      <!-- <th scope="col">좋아요</th> -->
                      <th scope="col">취소</th>
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
                          @click="updateFreeBoardReport(data.freeBoardId)"
                          style="background-color: #fff; border: 1px solid #162B59; color: #162B59; text-align: center;"
                        >
                          취소
                        </button>
                      </td>
                      <td>
                        <button
                          class="btn btn-outline-secondary"
                          type="button"
                          id="button-search"
                          @click="deleteFreeBoardReport(data.freeBoardId)"
                          style="background-color: red;"
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

            <!-- 부서 신고테이블 -->
            <div v-show="activeTab === 'dept'">
              <div class="table-responsive shadow">
                <table class="table mt-5">
                  <thead>
                    <tr>
                      <th scope="col">번호</th>
                      <th scope="col">부서</th>
                      <th
                        scope="col"
                        style="text-align: left; padding-left: 100px"
                      >
                        신고 사유
                      </th>
                      <th scope="col">작성자</th>
                      <th scope="col">등록일</th>
                      <!-- <th scope="col">좋아요</th> -->
                      <th scope="col">취소</th>
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
                      <td>{{ data.deptId }}</td>
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
                          :to="'/dept/board/detail/' + data.deptBoardId"
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
                          @click="updateDeptBoardReport(data.deptBoardId)"
                          style="background-color: #fff; border: 1px solid #162B59; color: #162B59; text-align: center;"

                        >
                          취소
                        </button>
                      </td>
                      <td>
                        <button
                          class="btn btn-outline-secondary"
                          type="button"
                          id="button-search"
                          @click="deleteDeptBoardReport(data.deptBoardId)"
                          style="background-color: red;"

                        >
                          삭제
                        </button>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>

            <!-- 자유 신고 처리 완료 -->
            <div v-show="activeTab === 'freeprocessed'">
              <div class="table-responsive shadow">
                <table class="table mt-5">
                  <thead>
                    <tr>
                      <th scope="col">번호</th>
                      <!-- <th scope="col">부서</th> -->
                      <th
                        scope="col"
                        style="text-align: left; padding-left: 100px"
                      >
                        신고 사유
                      </th>
                      <th scope="col">작성자</th>
                      <th scope="col">등록일</th>
                      <!-- <th scope="col">좋아요</th> -->
                      <th scope="col">상태</th>
                      <!-- <th scope="col">삭제</th> -->
                      <!-- <th scope="col">조회수</th> -->
                    </tr>
                  </thead>
                  <tbody>
                    <!-- 반복문 시작할 행 -->
                    <tr
                      v-for="(data, index) in freeBoardReportsListProcessed"
                      :key="index"
                    >
                      <td>{{ (page - 1) * pageSize + index + 1 }}</td>
                      <!-- <td>{{data.deptId}}</td> -->
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
                      <td>처리 완료</td>
                      <!-- <td>
                        <button
                          class="btn btn-outline-secondary"
                          type="button"
                          id="button-search"
                          @click="deleteDeptBoardReport(data.deptBoardId)"
                        >
                          삭제
                        </button>
                      </td> -->
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>

            <!-- 부서 신고 처리 완료 -->
            <div v-show="activeTab === 'deptprocessed'">
              <div class="table-responsive shadow">
                <table class="table mt-5">
                  <thead>
                    <tr>
                      <th scope="col">번호</th>
                      <!-- <th scope="col">부서</th> -->
                      <th
                        scope="col"
                        style="text-align: left; padding-left: 100px"
                      >
                        신고 사유
                      </th>
                      <th scope="col">작성자</th>
                      <th scope="col">등록일</th>
                      <!-- <th scope="col">좋아요</th> -->
                      <th scope="col">상태</th>
                      <!-- <th scope="col">조회수</th> -->
                    </tr>
                  </thead>
                  <tbody>
                    <!-- 반복문 시작할 행 -->
                    <tr
                      v-for="(data, index) in deptBoardReportsListProcessed"
                      :key="index"
                    >
                      <td>{{ (page - 1) * pageSize + index + 1 }}</td>
                      <!-- <td>{{data.deptId}}</td> -->
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
                          :to="'/dept/board/detail/' + data.deptBoardId"
                          class="router-link-exact-active alltext"
                        >
                          {{ data.content }}
                        </router-link>
                      </td>
                      <td>{{ data.userId }}</td>
                      <td>{{ data.insertTime }}</td>
                      <!-- <td>{{ data.likes }}</td> -->
                      <td>처리 완료</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>

      </div>

  </div>
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
      deptBoardReportsListProcessed: [],
      freeBoardReportsListProcessed: [],

      searchUserId: "",

      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 10, // 1페이지당개수(select태그)
      activeTab: "free", // 추가된 부분: 활성화된 탭 상태

      
    };
  },
  methods: {
    // 자유 :  전체조회
    async retrieveFreeBoardReport() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await FreeBoardService.getAllFreeBoardReport(
          this.searchUserId, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        // TODO: 복습 : 2) 객체분할 할당
        const { freeBoardReportsList, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.freeBoardReportsList = freeBoardReportsList; // 부서배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log("자유게시판 신고목록", this.freeBoardReportsList);
      } catch (e) {
        console.log(e);
      }
    },

    // 자유 :  처리완료 전체조회
    async retrieveFreeBoardReportProcessed() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await FreeBoardService.getAllFreeBoardReportProcessed(
          this.searchUserId, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        // TODO: 복습 : 2) 객체분할 할당
        const { freeBoardReportsListProcessed, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.freeBoardReportsListProcessed = freeBoardReportsListProcessed; // 부서배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log(
          "자유게시판 신고처리 목록",
          this.freeBoardReportsListProcessed
        );
      } catch (e) {
        console.log(e);
      }
    },

    // 부서 :  전체조회 
    async retrieveDeptBoardReport() {
      try {
        // alert("부서게시판 신고");
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await DeptBoardService.getAllDeptBoardReport(
          this.searchUserId, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        // TODO: 복습 : 2) 객체분할 할당
        const { deptBoardReportsList, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.deptBoardReportsList = deptBoardReportsList; // 부서배열(벡엔드 전송)

        this.deptBoardCount = totalItems; // 전체페이지수(벡엔드 전송)
        this.activeTab = "dept";

        // TODO: 4) 프론트 로깅 : console.log
        // alert("부서게시판 신고목록" + deptBoardReportsList);
        console.log("부서게시판 신고목록=", this.deptBoardReportsList);
        console.log("부서게시판 목록=", this.deptBoardCount);

      } catch (e) {
        console.log(e);
      }
    },

    // 부서 :  처리완료 전체조회 
    async retrieveDeptBoardReportProcessed() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await DeptBoardService.getAllDeptBoardReportProcessed(
          this.searchUserId, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        // TODO: 복습 : 2) 객체분할 할당
        const { deptBoardReportsListProcessed, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.deptBoardReportsListProcessed = deptBoardReportsListProcessed; // 부서배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log("부서게시판 신고처리 목록", this.deptBoardReportsListProcessed);
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
      this.searchUserId = "";
      this.retrieveFreeBoard();
    },

    // 자유 취소 함수
    async updateFreeBoardReport(freeBoardId) {
      try {
        if (confirm("취소하시겠습니까?")) {
          // let response = await FreeBoardService.deleteFreeBoardReport(freeBoardId);
          let response = await FreeBoardService.updateFreeBoardReport(
            freeBoardId
          );
          console.log(response);
          alert("취소완료");
          console.log(response.data);
          this.retrieveFreeBoardReport(); // 목록 갱신
        } else {
          return;
        }
      } catch (e) {
        console.log(e);
      }
    },

    // 부서 취소 함수
    async updateDeptBoardReport(deptBoardId) {
      try {
        if (confirm("취소하시겠습니까?")) {
          // let response = await FreeBoardService.deleteFreeBoardReport(freeBoardId);
          let response = await DeptBoardService.updateDeptBoardReport(
            deptBoardId
          );
          console.log(response);
          alert("취소완료");
          console.log(response.data);
          this.retrieveDeptBoardReport(); // 목록 갱신
        } else {
          return;
        }
      } catch (e) {
        console.log(e);
      }
    },

    // 자유 삭제 함수
    async deleteFreeBoardReport(freeBoardId) {
      try {
        if (confirm("정말로 삭제하시겠습니까?")) {
          let response = await FreeBoardService.deleteFreeBoardReport(
            freeBoardId
          );
          // 로깅
          // FreeBoardService.updateFreeBoardReport(freeBoardId);
          console.log(response.data);
          alert("게시글이 삭제되었습니다.");
          this.retrieveFreeBoardReport(); // 목록 갱신
        } else {
          return;
        }
      } catch (e) {
        console.log(e);
      }
    },

    // 부서 삭제 함수
    async deleteDeptBoardReport(deptBoardId) {
      try {
        if (confirm("정말로 삭제하시겠습니까?")) {
          let response = await DeptBoardService.deleteDeptBoardReport(
            deptBoardId
          );
          // 로깅
          console.log(response.data);
          alert("게시글이 삭제되었습니다.");
          this.retrieveDeptBoardReport(); // 목록 갱신
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
    // this.retrieveFreeBoardReportProcessed();
    // this.retrieveDeptBoardReport();
    // this.retrieveDeptBoardReportProcessed();
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
}

.custom-pagination .page-link:focus {
  outline: none;
  box-shadow: 0 0 0 0.2rem #162b59;
  border-color: #162b59;
} 

/* 검색버튼 */
.btn {
  margin: 0 2px; 
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
