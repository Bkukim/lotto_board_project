<template>
  <!-- 전체 박스 -->
  <div class="fbd_all" style="height: auto">
    <!-- 해당 게시판 이름 부분 -->
    <div class="container text-center mb-5">
      <h3 style="text-align: left" id="fbd_h3">공지사항 글 상세보기</h3>
    </div>
    <!-- 해당 게시판 이름 부분  끝-->

    <!--  첫번째 게시판 큰 박스-->
    <div
      class="container text-center mt-5"
      style="
        height: auto;
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
          font-size: 25px;
          font-weight: bolder;
          font-weight: 600;
          color: #595959;
          background-color: #f2f2f2;
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
          <div class="col" style="color: #999999">
            등록자 | {{ notice.userId }}
          </div>
        </div>
      </div>

      <div
        style="
          text-align: left;
          padding: 20px 0 20px 30px;
          font-size: 15px;
          font-weight: 600;
          border-bottom: 1px solid #cccccc;
          word-wrap: break-word;
          word-break: break-all;
        "
        v-html="notice.content"
      ></div>

      <!-- 목록으로 버튼 -->
      <div class="col mb-5 justify-content-right">
        <router-link
          :to="'/notice/notice-board'"
          class="fbd_d container text-center"
          style="
            width: 150px;
            text-decoration: none;
            background-color: #3363cc;
            font-size: 15px;
            text-align: center;
            height: 40px;
            border-radius: 50px;
            margin-top: 50px;
          "
        >
          <div
            style="display: flex; align-items: center; justify-content: center"
          >
            <div
              class="router-text"
              style="
                margin-top: 10px;
                color: #fff;
                text-align: center;
                font-weight: 300;
              "
            >
              목록으로
            </div>
          </div>
        </router-link>
      </div>
    </div>
    <!--  첫번째 게시판 큰 박스 끝-->

    <!-- 삭제 -->
    <div
      class="container text-center mt-5"
      v-if="this.$store.state.user?.role == 'ROLE_ADMIN'"
    >
      <div class="row" style="margin-top: 100px">
        <div class="col">
          <button
            class="fbd_d container text-center"
            style="
              width: 300px;
              text-decoration: none;
              background-color: #cccccc;
              font-size: 20px;
              height: 50px;
              border-radius: 20px;
              border: none;
            "
            @click="deleteNotice"
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
                style="color: #ffffff; text-align: center"
              >
                삭제
              </div>
            </div>
          </button>
        </div>

        <!-- 수정 -->
        <div class="col mb-5">
          <div
            style="display: flex; align-items: center; justify-content: center"
          >
            <div
              class="router-text"
              style="
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
  <br />
  <br />
  <!-- 전체 박스 끝 -->
</template>
<script>
import NoticeService from "@/services/notice/NoticeService";

// 댓글 글자 작성 수 올라가는 것 확인
export default {
  data() {
    return {
      notice: {
        noticeId: this.$route.params.noticeId, // this.$router.params.기본키
        noticeType: "",
        title: "",
        content: "",
        views: 0,
        eventYN: "N",
      },


      // 페이징
      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 5, // 1페이지당개수(select태그)

      // 댓글 글자수

      charCount: 0,
    };
  },

 
  methods: {
    async get(noticeId,eventYN) {

      // todo: 공통 상세조회 함수: get()
      try {
        let response = await NoticeService.getNotice(noticeId, eventYN);
        this.notice = response.data;
        // 로깅
        console.log(response.data.views);

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
    async viewsUp() {
      try {
        // +하고 이동
        let views = (this.notice.views = this.notice.views + 1);
        // todo: 오타였음, views
        let data = {
          views: views,
          noticeId: this.notice.noticeId,
          title: this.notice.title,
          noticeType: this.notice.noticeType,
          content: this.notice.content,
        };
        console.log(data);
        let response = await NoticeService.updateVeiws(
          this.notice.noticeId,
          data
        );
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    },

  async mounted() {
    await this.get(this.$route.params.noticeId,this.$route.params.eventYN);
    this.viewsUp();
    window.scrollTo(0, 0);
  },
};
</script>

<style>
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

#fbd_h3 {
  color: #424242e8;
  text-align: center;
  font-family: "Inter-Bold", sans-serif;
  font-size: 30px;
  font-weight: 700;
  letter-spacing: -3px;
  padding-top: 68px;
}

#comments {
  margin-top: 500px;
}

.char-count {
  text-align: right;
  color: #999999;
  font-size: 14px;
  margin-top: 5px;
}

.comment-box {
  border: #595959 solid 2px;
}

.comment-box textarea {
  border: none;
  width: 100%;
  word-wrap: break-word;
  word-break: break-all;
}

.comment-box textarea:focus {
  outline: none;
  box-shadow: none;
}

.comment-box div {
  word-wrap: break-word;
  word-break: break-all;
}

.comment-box textarea {
  border: none;
  width: 100%;
  word-wrap: break-word;
  word-break: break-all;
  padding-right: 90px; /* Ensure space for button */
}

.comment-box textarea:focus {
  outline: none;
  box-shadow: none;
}

.comment-box div {
  word-wrap: break-word;
  word-break: break-all;
}

.comment-input {
  display: flex;
  flex-direction: column;
  position: relative;
}

.comment-input button {
  position: absolute;
  bottom: 20px;
  right: 10px;
}
</style>
