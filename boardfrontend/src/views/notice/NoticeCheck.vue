<!-- NoticeCheck.vue ::공지사항 상세조회  :: 디자인 안했어요 -->
<template>
  <!-- 전체 박스스 -->
  <div class="fbd_all" style="height: 2000px; background-color: #f2f2f2">
    <!-- 해당 게세판 이름 부분 -->
    <div class="container text-center">
      <h3 style="text-align: left" id="fbd_h3">공지사항 상세보기</h3>
    </div>
    <!-- 해당 게세판 이름 부분  끝-->

    <!--  첫번째 게시판 큰 박스-->
    <div
      class="container text-center mt-5"
      style="
        height: 70vh;
        border: none;
        border-radius: 50px;
        background-color: #ffffff;
      "
    >
      <div
        style="
          text-align: left;
          border-bottom: #cccccc solid 1px;
          padding: 20px 0 20px 30px;
          font-size: 20px;
          font-weight: 600;
          color: #595959;
        "
      >
        {{ notice.title }}
      </div>

      <div
        style="
          text-align: left;
          border-bottom: #cccccc solid 1px;
          padding: 20px 0 20px 30px;
          font-size: 15px;
          font-weight: 600;
        "
      >
        <div class="lotto_new row row-cols-lg-4 gap-5 justify-content-left">
          <div class="col" style="color: #999999">
            등록일 | {{ notice.insertTime }}
          </div>
          <div class="col" style="color: #999999">등록자 | Admin</div>
        </div>
      </div>

      <div
        style="
          text-align: left;
          border-bottom: #cccccc solid 1px;
          padding: 20px 0 20px 30px;
          font-size: 15px;
          font-weight: 600;
          height: 45vh;
        "
        class="notice-content"
        v-html="notice.content"
      ></div>
    </div>
    <!--  첫번째 게시판 큰 박스 끝-->
    <!-- v-if="freeBoardList.userId=== this.$store.state.user?.userId" -->
    <div class="container text-center mt-5"  v-if="this.$store.state.user?.role=='ROLE_ADMIN'">
      <div class="row" style="margin-top: 100px">
        <!-- 삭제 -->
        <div class="col">
          <div
            style="
              display: flex;
              align-items: center;
              justify-content: center;
              width: 300px;
              text-decoration: none;
              background-color: #ffffff;
              font-size: 20px;
              text-align: center;
              height: 50px;
              border-radius: 20px;
              line-height: 50px;
            "
          >
            <div
              class="router-text"
              style="margin-right: 20px; margin-top: 10px"
              @click="deleteNotice"
            >
              삭제
            </div>
          </div>
        </div>

        <!-- 수정 -->
        <div class="col mb-5">
          <div
            style="display: flex; align-items: center; justify-content: center"
          >
            <div
              class="router-text"
              style="
                margin-right: 20px;
                margin-top: 10px;
                color: #ffffff;
                width: 300px;
                text-decoration: none;
                background-color: #162b59;
                font-size: 20px;
                text-align: center;
                height: 50px;
                border-radius: 20px;
                line-height: 50px;
              "
              @click="goUpdate"
            >
              수정
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 댓글 작성-->
    <div
      class="container text-center mt-5"
      id="comments"
      style="
        height: 330px;
        border: none;
        border-radius: 50px;
        background-color: #ffffff;
      "
    >
      <div
        style="
          text-align: left;
          border-bottom: #cccccc solid 1px;
          padding: 20px 0 20px 30px;
          font-size: 20px;
          font-weight: 600;
          color: #595959;
        "
      >
        댓글
      </div>

      <div
        style="
          text-align: left;
          padding: 20px 0 20px 30px;
          font-size: 15px;
          font-weight: 600;
        "
      >
        <div
          class="lotto_new row row-cols-lg-4 gap-5 justify-content-left mb-3"
        >
          <div class="col" style="color: #999999">등록자 | {값}</div>

          <div class="col" style="color: #999999">날짜 |</div>
        </div>

        <!-- 글쓰기 칸 -->
        <div class="mb-5">
          <textarea
            class="form-control"
            id="exampleFormControlTextarea1"
            rows="3"
            v-model="text"
            @input="updateCharacterCount"
            maxlength="1000"
          ></textarea>
          <div class="char-count">글자 수: {{ charCount }}/1000</div>
        </div>

        <!-- 등록 버튼-->
        <div class="col">
          <router-link
            to="/"
            class="fbd_d container text-center"
            style="
              width: 80px;
              text-decoration: none;
              background-color: #162b59;
              font-size: 18px;
              text-align: center;
              height: 40px;
              font-weight: 100;
              margin-left: 1180px;
              margin-top: -35px;
            "
          >
            <div
              style="
                display: flex;
                align-items: center;
                justify-content: center;
              "
            >
              <div
                class="router-text"
                style="
                  margin-right: 5px;
                  margin-top: 5px;
                  color: #ffffff;
                  font-weight: 100;
                  text-align: center;
                "
              >
                등록
              </div>
            </div>
          </router-link>
        </div>
      </div>
    </div>
  </div>
  <!-- 전체 박스 끝 -->
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
        alert("정말로 삭제하시겠습니까?");
        let response = await NoticeService.delete(this.notice.noticeId);

        // 로깅
        console.log(response.data);
        alert("정상적으로 삭제되었습니다.");
        this.$router.push("/notice/notice-board");
      } catch (e) {
        console.log(e);
      }
    },
    goUpdate() {
      this.$router.push(`/admin/notice-update/${this.notice.noticeId}`);
    },
  },
  mounted() {
    // 상세조회 실행
    this.get(this.$route.params.noticeId);
    window.scrollTo(0, 0);
  },
};
</script>

<style>
@import "@/assets/css/NoticeStyle.css";
</style>
