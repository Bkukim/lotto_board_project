<!-- 관리자 :: 각 게시판별 공지사항 조회 :MasterBoardNoticesAdmin.vue -->
<template>
  <div class="container">
    <h2 class="mt-3">공지사항 통합관리</h2>
    <!-- 테이블 옆 버튼 3개 -->
    <div class="col-12 d-flex align-items-start justify-content-end">
      <div class="d-flex flex-column">
        <button type="button" class="btn btn-primary mb-2" @click="goNoticeAdd">
          공지사항 등록
        </button>
        <button type="button" class="btn btn-success">공지사항 전체삭제</button>
      </div>
    </div>
    <!-- 게시판별 공지사항  div 총 3개 -->
    <!--자유게시판 공지사항  -->
    <div class="row mt-5 border justify-content-center">
      <h3>자유게시판</h3>
      <!-- 테이블시작, 작게 왼쪽 -->
      <div class="col-11">
        <table class="table mt-5 text-center">
          <thead>
            <tr>
              <th scope="col">번호</th>
              <th scope="col">유형</th>
              <th scope="col">제목</th>
              <th scope="col">등록일</th>
              <th scope="col">삭제</th>
            </tr>
          </thead>
          <tbody>
            <!-- 반복문 시작할 행 -->
            <tr v-for="(data, index) in noticeFree" :key="index">
              <td>
                {{ index + 1 }}
              </td>
              <td>{{ data.noticeType }}</td>
              <td>{{ data.title }}</td>
              <td>{{ data.insertTime }}</td>
              <td>
                <button
                  type="button"
                  class="btn btn-success"
                  @click="deleteNotice(data.noticeId)"
                >
                  삭제
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 부서게시판 공지사항 -->
    <div class="row mt-3 border justify-content-center">
      <h3>부서게시판</h3>
      <!-- 테이블시작, 작게 왼쪽 -->
      <div class="col-11">
        <table class="table mt-5 text-center">
          <thead>
            <tr>
              <th scope="col">번호</th>
              <th scope="col">유형</th>
              <th scope="col">제목</th>
              <th scope="col">등록일</th>
              <th scope="col">삭제</th>
            </tr>
          </thead>
          <tbody>
            <!-- 반복문 시작할 행 -->
            <tr v-for="(data, index) in noticeDept" :key="index">
              <td>
                {{ index + 1 }}
              </td>
              <!-- dto에 없어서 noticeType이 안나왔던것 안나오면 dto한번더 확인 -->
              <td>{{ data.noticeType }}</td>
              <td>{{ data.title }}</td>
              <td>{{ data.insertTime }}</td>
              <td>
                <button
                  type="button"
                  class="btn btn-success"
                  @click="deleteNotice(data.noticeId)"
                >
                  삭제
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 건의게시판 공지사항 -->
    <div class="row mt-3 border justify-content-center">
      <h3>건의게시판</h3>
      <!-- 테이블시작, 작게 왼쪽 -->
      <div class="col-11">
        <table class="table mt-5 text-center">
          <thead>
            <tr>
              <th scope="col">번호</th>
              <th scope="col">유형</th>
              <th scope="col">제목</th>
              <th scope="col">등록일</th>
              <th scope="col">삭제</th>
            </tr>
          </thead>
          <tbody>
            <!-- 반복문 시작할 행 -->
            <tr v-for="(data, index) in noticeComplaint" :key="index">
              <td>
                {{ index + 1 }}
              </td>
              <!-- dto에 없어서 noticeType이 안나왔던것 안나오면 dto한번더 확인 -->
              <td>{{ data.noticeType }}</td>
              <td>{{ data.title }}</td>
              <td>{{ data.insertTime }}</td>
              <td>
                <button
                  type="button"
                  class="btn btn-success"
                  @click="deleteNotice(data.noticeId)"
                >
                  삭제
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 동호회 게시판 공지사항 -->
    <div class="row mt-3 border justify-content-center">
      <h3>동호회 게시판</h3>
      <!-- 테이블시작, 작게 왼쪽 -->
      <div class="col-11">
        <table class="table mt-5 text-center">
          <thead>
            <tr>
              <th scope="col">번호</th>
              <th scope="col">유형</th>
              <th scope="col">제목</th>
              <th scope="col">등록일</th>
              <th scope="col">삭제</th>
            </tr>
          </thead>
          <tbody>
            <!-- 반복문 시작할 행 -->
            <tr v-for="(data, index) in noticeGroup" :key="index">
              <td>
                {{ index + 1 }}
              </td>
              <!-- dto에 없어서 noticeType이 안나왔던것 안나오면 dto한번더 확인 -->
              <td>{{ data.noticeType }}</td>
              <td>{{ data.title }}</td>
              <td>{{ data.insertTime }}</td>
              <td>
                <button
                  type="button"
                  class="btn btn-success"
                  @click="deleteNotice(data.noticeId)"
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
</template>
<script>
import NoticeService from "@/services/notice/NoticeService";

export default {
  data() {
    return {
      // 받아올 각 부서의 공지사항 목록들
      noticeDept: [],
      noticeFree: [],
      noticeComplaint: [],
      noticeGroup: [],
      // complaint: [],
      // free: [],
      // participants: [],

      // 공통속성
      pageSize: 25, // 1페이지당개수(select태그)
    };
  },
  // 함수
  methods: {
    // TODO:  부서
    async retrieveNoticeDept() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await NoticeService.getDept(
          this.pageSize // 1페이지당개수(size) - 5개 이상 등록못하게 할것
        );
        const { noticeDept } = response.data; // 부서배열(벡엔드 전송)
        this.noticeDept = noticeDept; // 부서배열(벡엔드 전송)
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // TODO:Free
    async retrieveNoticeFree() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await NoticeService.getFree(
          this.pageSize // 1페이지당개수(size) - 5개 이상 등록못하게 할것
        );
        const { noticeFree } = response.data; // 부서배열(벡엔드 전송)
        this.noticeFree = noticeFree; // 부서배열(벡엔드 전송)
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // TODO:Complaint
    async retrieveNoticeComplaint() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await NoticeService.getComplaint(
          this.pageSize // 1페이지당개수(size) - 5개 이상 등록못하게 할것
        );
        const { noticeComplaint } = response.data; // 부서배열(벡엔드 전송)
        this.noticeComplaint = noticeComplaint; // 부서배열(벡엔드 전송)
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // TODO:Complaint
    async retrieveNoticeGroup() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await NoticeService.getGroup(
          this.pageSize // 1페이지당개수(size) - 5개 이상 등록못하게 할것
        );
        const { noticeGroup } = response.data; // 부서배열(벡엔드 전송)
        this.noticeGroup = noticeGroup; // 부서배열(벡엔드 전송)
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // dept지만 전체 공지사항 다 삭제할 수있는것임 나중에 수정 요망
    async deleteNotice(noticeId) {
      console.log("아이디" + noticeId);
      try {
        // todo: 공통 장바구니 삭제 서비스 함수 실행
        let response = await NoticeService.delete(noticeId);
        // 로깅
        console.log(response.data);
        // alert 대화상자
        alert("정상적으로 삭제되었습니다.");
        this.retrieveNoticeDept();
      } catch (e) {
        console.log(e);
      }
    },

    // gonotice
    goNoticeAdd() {
      this.$router.push("/admin/notice-add");
      },
    //   if (this.pageSize == 25) {
    //     alert("공지사항이 25개 이상입니다");
    //   }
    //   else{
        
    // },
  },
  // 마운티드
  mounted() {
    this.retrieveNoticeDept();
    this.retrieveNoticeFree();
    this.retrieveNoticeComplaint();
    this.retrieveNoticeGroup();
  },
};
</script>
<style></style>
