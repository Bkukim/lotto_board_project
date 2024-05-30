<!-- NoticeCheck.vue ::공지사항 상세조회  :: 디자인 안했어요 -->
<template>
  <div class="mt-5 mb-5 col-13">
    <!-- 카테고리 -->
    <div class="container">
      <h4 class="main_text">공지사항</h4>
      <table scope="row" class="table">
        <tbody>
          <tr>
            <th scope="row" class="col-1">제목</th>
            <td colspan="5">{{ notice.title }}</td>
          </tr>
          <tr>
            <th scope="row" class="col-1">작성자</th>
            <td scope="row" class="col-1">ADMIN</td>
            <td scope="row" class="col-1">유형</td>
            <td scope="row" class="col-1">{{ notice.noticeType }}</td>
            <td scope="row" class="col-1">등록일: </td>
            <td scope="row" class="col-1">{{ notice.insertTime }}</td>
          </tr>

          <tr>
            <!--TODO: html가져오기 v-html -->
            <th scope="row">내용</th>
            <td colspan="5" v-html="notice.content"></td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 문의사항 등록 버튼  :: 공지사항거 들고오기:: 
         v-if="this.$store.state.user?.role == 'ROLE_ADMIN'"
    -->
    <div class="row justify-content-end">
      <!-- 등록시 고객센터 글 목록으로 재이동 -->
      <router-link :to="'/notice/notice-update/' + notice.noticeId">
        <button class="btn btn-primary col-1" type="button">수정</button>
      </router-link>

      <button class="btn btn-primary col-1" type="button" @click="deleteNotice">
        삭제
      </button>
    </div>
  </div>
</template>

<script>
import NoticeService from "@/services/notice/NoticeService";
export default {
  data() {
    return {
      notice: {
        noticeId: this.$route.params.noticeId, // this.$router.params.기본키
        noticeType: "",
        title: "",
        content: "",
      },
    };
  },
  methods: {
    // 상세조회
    async get(noticeId) {
      // todo: 공통 상세조회 함수: get()
      try {
        let response = await NoticeService.getNotice(noticeId);
        this.notice = response.data;
        // 로깅
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async deleteNotice() {
      try {
        // todo: 공통 장바구니 삭제 서비스 함수 실행
        let response = await NoticeService.delete(this.notice.noticeId);
        this.$router.push("/notice/notice-board");
        // 로깅
        console.log(response.data);
        alert("정상적으로 삭제되었습니다.");
      } catch (e) {
        console.log(e);
      }
    },
    
  },
  mounted() {
    this.get(this.$route.params.noticeId);
    window.scrollTo(0, 0);
  },
};
</script>

<style></style>
