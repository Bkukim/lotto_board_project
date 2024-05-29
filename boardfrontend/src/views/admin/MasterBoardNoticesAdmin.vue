<!-- 관리자 :: 각 게시판별 공지사항 조회 :MasterBoardNoticesAdmin.vue -->
<template>
  <div class="container">
    <h2 class="mt-3">공지사항 MASTER</h2>

    <!-- 게시판별 공지사항  div 총 3개 -->
    <div class="row mt-5 border">
      <!--자유게시판 공지사항  -->
      <h3>자유게시판</h3>
      <!-- 테이블시작, 작게 왼쪽 -->
      <div class="col-7">
        <table class="table mt-5 text-center">
          <thead>
            <tr>
              <th scope="col">번호</th>
              <th scope="col">제목</th>
              <th scope="col">등록일</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <!-- 반복문 시작할 행 -->
            <tr v-for="(data, index) in notices" :key="index">
              <td>
                {{ index + 1 }}
              </td>
              <td class="col-8">{{ data.title }}</td>
              <td>관리자</td>
              <td>{{ data.insertTime }}</td>
              <button type="button" class="btn btn-success">
                공지사항 삭제
              </button>
              <td></td>
            </tr>
          </tbody>
        </table>
      </div>
      <!-- 테이블 옆 버튼 3개 -->
      <div class="col-5 d-flex align-items-start justify-content-center">
        <div class="d-flex flex-column">
          <button type="button" class="btn btn-primary mb-2">
            공지사항 등록
          </button>
          <button type="button" class="btn btn-success">공지사항 reset</button>
        </div>
      </div>
    </div>

    <!-- 부서게시판 공지사항 -->
    <div class="row mt-3 border">
      <!--자유게시판 공지사항  -->
      <h3>부서게시판</h3>
      <!-- 테이블시작, 작게 왼쪽 -->
      <div class="col-7">
        <table class="table mt-5 text-center">
          <thead>
            <tr>
              <th scope="col">번호</th>
              <th scope="col">제목</th>
              <th scope="col">등록일</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <!-- 반복문 시작할 행 -->
            <tr v-for="(data, index) in notices" :key="index">
              <td>
                {{ index + 1 }}
              </td>
              <td class="col-8">{{ data.title }}</td>
              <td>관리자</td>
              <td>{{ data.insertTime }}</td>
              <button type="button" class="btn btn-success">
                공지사항 삭제
              </button>
              <td></td>
            </tr>
          </tbody>
        </table>
      </div>
      <!-- 테이블 옆 버튼 3개 -->
      <div class="col-5 d-flex align-items-start justify-content-center">
        <div class="d-flex flex-column">
          <button type="button" class="btn btn-primary mb-2">
            공지사항 등록
          </button>
          <button type="button" class="btn btn-success">공지사항 reset</button>
        </div>
      </div>
    </div>

    <!-- 건의 게시판 공지사항 -->
    <div class="row mt-3 border">
      <h3>건의게시판</h3>
      <!-- 테이블시작, 작게 왼쪽 -->
      <div class="col-7">
        <table class="table mt-5 text-center">
          <thead>
            <tr>
              <th scope="col">번호</th>
              <th scope="col">제목</th>
              <th scope="col">등록일</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <!-- 반복문 시작할 행 -->
            <tr v-for="(data, index) in notices" :key="index">
              <td>
                {{ index + 1 }}
              </td>
              <td class="col-8">{{ data.title }}</td>
              <td>관리자</td>
              <td>{{ data.insertTime }}</td>
              <button type="button" class="btn btn-success">
                공지사항 삭제
              </button>
              <td></td>
            </tr>
          </tbody>
        </table>
      </div>
      <!-- 테이블 옆 버튼 3개 -->
      <div class="col-5 d-flex align-items-start justify-content-center">
        <div class="d-flex flex-column">
          <button type="button" class="btn btn-primary mb-2">
            공지사항 등록
          </button>
          <button type="button" class="btn btn-success">공지사항 reset</button>
        </div>
      </div>
    </div>

    <!-- 동호회 게시판 공지사항 -->
    <div class="row mt-3 border">
      <h3>동호회 게시판</h3>
      <!-- 테이블시작, 작게 왼쪽 -->
      <div class="col-7">
        <table class="table mt-5 text-center">
          <thead>
            <tr>
              <th scope="col">번호</th>
              <th scope="col">제목</th>
              <th scope="col">등록일</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <!-- 반복문 시작할 행 -->
            <tr v-for="(data, index) in notices" :key="index">
              <td>
                {{ index + 1 }}
              </td>
              <td class="col-8">{{ data.title }}</td>
              <td>관리자</td>
              <td>{{ data.insertTime }}</td>
              <button type="button" class="btn btn-success">
                공지사항 삭제
              </button>
              <td></td>
            </tr>
          </tbody>
        </table>
      </div>
      <!-- 테이블 옆 버튼 3개 -->
      <div class="col-5 d-flex align-items-start justify-content-center">
        <div class="d-flex flex-column">
          <button type="button" class="btn btn-primary mb-2">
            공지사항 등록
          </button>
          <button type="button" class="btn btn-success">공지사항 reset</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      // 받아올 각 부서의 공지사항 목록들
      notices: [],
      title: "",
      dept: [],
      complaint: [],
      free: [],
      participants: [],

      // 공통속성
      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 10, // 1페이지당개수(select태그)
    };
  },
  // 함수
  methods: {},
  // 마운티드
  mounted() {},
};
</script>
<style></style>
