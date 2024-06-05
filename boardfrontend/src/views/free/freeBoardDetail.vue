<template>
  <!-- 전체 박스 -->
  <div class="fbd_all" style="height: auto">
    <!-- 해당 게시판 이름 부분 -->
    <div class="container text-center mb-5">
      <h3 style="text-align: left" id="fbd_h3">자유 게시판 글 상세보기</h3>
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
        {{ freeBoard.title }}
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
            등록일 | {{ freeBoard.insertTime }}
          </div>
          <div class="col" style="color: #999999">
            등록자 | {{ freeBoard.userId }}
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
        v-html="freeBoard.content"
      ></div>
      <!-- TODO: 좋아요버튼 -->
      <div class="mt-5 text-center">
        <button
          type="button"
          class="btn btn-light"
          @click="likeUp"
          style="border: none; text-align:center; height: 8vh; width: 18vw; padding: 1vw; "
        >
          <img src="@/assets/img/like_icon.png" width="40" height="40" />
          공감해요
          {{ this.freeBoard.likes }}
        </button>
        <button type="button" class="btn btn-light" style="margin-left: 3vh; height: 8vh; width: 10vw; padding: 1vw;">신고</button>
      </div>
      <!-- 파일첨부 -->
      <!-- <div class="mt-5" style="width: 500px">
        <input
          class="form-control form-control-sm"
          id="formFileSm"
          type="file"
          style="margin-left: 20px"
        />
      </div> -->
    </div>
    <!--  첫번째 게시판 큰 박스 끝-->

    <div class="container text-center mt-5">
      <div
        class="row"
        style="margin-top: 100px"
        v-if="freeBoard.userId === this.$store.state.user?.userId"
      >
        <!-- 삭제 -->
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
            @click="deleteFreeBoard"
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
                  margin-right: 20px;
                  margin-top: 10px;
                  color: #ffffff;
                  text-align: center;
                "
              >
                삭제
              </div>
            </div>
          </button>
        </div>

        <!-- 수정 -->
        <div class="col mb-5">
          <router-link
            :to="'/free/free-board/Update/' + this.$route.params.freeBoardId"
            class="fbd_d container text-center"
            style="
              width: 300px;
              text-decoration: none;
              background-color: #162b59;
              font-size: 20px;
              text-align: center;
              height: 50px;
              border-radius: 20px;
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
                style="margin-right: 20px; margin-top: 10px; color: #ffffff"
              >
                수정
              </div>
            </div>
          </router-link>
        </div>
      </div>
    </div>

    <!-- --------------------------------------------------- -->

    <!-- 댓글 작성 및 조회/대댓글-->
    <div
      class="container text-center mt-5"
      id="comments"
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
          font-size: 20px;
          font-weight: 600;
          color: #595959;
        "
      >
        댓글
      </div>

      <!-- 댓글 입력하기 -->
      <div
        class="lotto_new row row-cols-lg-4 gap-5 justify-content-left mb-3 mt-5"
      >
        <div class="col" style="color: #595959; font-weight: bold">
          <span style="color: #999999; font-weight: 200">등록자 |</span>
          {{ newComment.userId }}
        </div>

        <div class="col" style="color: #999999">
          날짜 | {{ newComment.insertTime }}
        </div>
      </div>

      <!-- 댓글 글쓰기 칸 -->
      <div class="mb-5 comment-box" style="border: #595959 1.5px solid">
        <div class="comment-input">
          <textarea
            class="form-control"
            id="exampleFormControlTextarea1"
            rows="3"
            v-model="newComment.content"
            @input="updateCharacterCount"
            maxlength="1000"
            placeholder="댓글을 입력하세요."
            style="
              height: 150px;
              border-bottom: #cccccc 1px solid;
              word-wrap: break-word;
              word-break: break-all;
            "
          ></textarea>
          <div
            class="char-count"
            style="text-align: left; padding: 10px 0 20px 10px"
          >
            글자 수: {{ charCount }}/300
          </div>
          <!-- (댓글작성) 등록 버튼-->
          <button
            @click="submitComment()"
            class="fbd_d container text-center mt-3"
            style="
              width: 80px;
              text-decoration: none;
              background-color: #162b59;
              font-size: 18px;
              text-align: center;
              height: 40px;
              font-weight: 100;
              position: absolute;
              bottom: 10px;
              right: 10px;
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
          </button>
        </div>
      </div>

      <!-- 댓글들 -->
      <div
        class="container text-left"
        v-for="(data, index) in freeBoardComments"
        :key="index"
      >
        <div
          class="lotto_new col row-cols-lg-4 gap-5 justify-content-left mb-3"
        >
          <!-- 아이디 -->
          <div
            class="row mt-5"
            style="color: #333333; text-align: left; font-weight: bold"
          >
            <div
              style="
                background: #162b59;
                height: 30px;
                width: 30px;
                border-radius: 50%;
                margin-right: 5px;
              "
            ></div>
            {{ data.userId }}
          </div>

          <!-- 시간 -->
          <div
            class="row"
            style="color: #999999; text-align: left; margin-left: 22px"
          >
            {{ data.insertTime }}
          </div>
        </div>
        <!-- 답변 -->
        <div
          style="
            padding-bottom: 30px;
            border-bottom: 1px solid #cccccc;
            text-align: left;
            word-wrap: break-word;
            word-break: break-all;
            margin-left: 22px;
          "
        >
          {{ data.content }}
          <br />
          <button
            style="border: none; margin-top: 15px"
            @click="toggleReplyForm(data.commentId)"
          >
            {{
              replyVisible && replyToCommentId === data.commentId
                ? "답글접기"
                : "답글"
            }}
          </button>

          <!-- 답글 버튼 클릭 시 답글 입력 폼이 열리도록 수정 -->
          <!-- <button
            style="border: none; margin-top: 15px"
            @click="toggleReplyForm(data.commentId)"
          >
            {{
              replyVisible && replyToCommentId === data.commentId
                ? "접기"
                : "답글"
            }}
          </button> -->

          <!-- 답변(대댓글) 폼 -->
          <div v-if="replyVisible && replyToCommentId === data.commentId">
            <div
              class="lotto_new row row-cols-lg-4 gap-5 justify-content-left mb-3 mt-5"
            >
              <div class="col" style="color: #595959; font-weight: bold">
                <span style="color: #999999; font-weight: 200">등록자 |</span>
                {{ newReply.userId }}
              </div>
              <div class="col" style="color: #999999">
                날짜 | {{ newReply.insertTime }}
              </div>
            </div>

            <!-- 답변(대댓글) 입력 칸 -->
            <div class="mb-5 comment-box" style="border: #595959 1.5px solid">
              <div class="comment-input">
                <textarea
                  class="form-control"
                  id="replyTextarea"
                  rows="3"
                  v-model="newReply.content"
                  @input="updateReplyCharacterCount"
                  maxlength="1000"
                  placeholder="댓글을 입력하세요."
                  style="
                    height: 150px;
                    border-bottom: #cccccc 1px solid;
                    word-wrap: break-word;
                    word-break: break-all;
                  "
                ></textarea>
                <div
                  class="char-count"
                  style="text-align: left; padding: 10px 0 20px 10px"
                >
                  글자 수: {{ charCountReply }}/300
                </div>
                <!-- (답변(대댓글)) 등록 버튼-->
                <button
                  @click="submitReply()"
                  class="fbd_d container text-center mt-3"
                  style="
                    width: 60px;
                    text-decoration: none;
                    background-color: #ccc;
                    border: none;
                    font-size: 15px;
                    text-align: center;
                    height: 40px;
                    font-weight: 100;
                    position: absolute;
                    bottom: 10px;
                    right: 10px;
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
                </button>
              </div>
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
import FreeBoardService from "@/services/board/free/FreeBoardService";
// import { ref } from "vue";

// 댓글 글자 작성 수 올라가는 것 확인
export default {
  data() {
    return {
      replyVisible: false, // 답글 입력 폼의 표시 여부를 관리하는 변수
      replyToCommentId: null, // 어떤 댓글에 대한 답글인지 식별하기 위한 변수
      newReply: {
        // 새로 작성할 답글
        userId: this.$store.state.user?.userId,
        content: "",
      },
      charCountReply: 0, // 답글 글자 수
      freeBoard: {
        freeBoardId: this.$route.params.freeBoardId,
        userId: "",
        content: "",
        title: "",
        likes: 0,
      },
      freeBoardComments: [], // 기존 댓글 목록
      newComment: {
        // 새로 작성할 댓글
        userId: this.$store.state.user?.userId, // 로그인된 사용자 ID
        content: "",
      },
      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 5, // 1페이지당개수(select태그)

      charCount: 0,
    };
  },
  watch: {
    "newComment.content"(newVal) {
      this.charCount = newVal.length;
    },
  },
  methods: {
    toggleReplyForm(commentId) {
      // 클릭된 답글 버튼이 이미 열려있는 상태이면 폼을 닫고, 그렇지 않으면 엽니다.
      this.replyVisible =
        this.replyVisible && this.replyToCommentId === commentId ? false : true;
      this.replyToCommentId = commentId; // 현재 선택된 댓글 ID 업데이트
      this.newReply.content = ""; // 입력 폼 내용 초기화
      this.charCountReply = 0; // 글자 수 초기화
    },

    // 댓글 작성 시 글자 수 세기
    updateCharacterCount() {
      if (this.newComment.content.length > 300) {
        this.newComment.content = this.newComment.content.slice(0, 300);
      }
      this.charCount = this.newComment.content.length;
    },
    // freeBoardId로 상세조회 : 화면뜰때 실행
    async retrieveGetFreeBoard(freeBoardId) {
      try {
        let response = await FreeBoardService.getFreeBoardId(freeBoardId);
        this.freeBoard = response.data;
        console.log(response.data);
      } catch (e) {
        alert("에러");
        console.log(e);
      }
    },
    // freeBoardId로 댓글조회 : 화면뜰때 실행
    async retrieveFreeBoardComment(freeBoardId) {
      try {
        let response = await FreeBoardService.getFreeBoardComment(
          freeBoardId,
          this.page - 1,
          this.pageSize
        );
        // TODO: 복습 : 2) 객체분할 할당
        const { freeBoardComments, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.freeBoardComments = freeBoardComments; // 부서배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        // console.log("response.data",response.data);
        // console.log("this.comments" ,this.freeBoardComments);
      } catch (e) {
        alert("페이징 에러");
        console.log(e);
      }
    },
    // 댓글 등록 함수
    async submitComment() {
      try {
        let data = {
          userId: this.newComment.userId,

          freeBoardId: this.freeBoard.freeBoardId,
          content: this.newComment.content,
          secretCommentYn: "N",
        };
        await FreeBoardService.createFreeBoardComment(data);
      } catch (e) {
        // alert("댓글 등록 중 에러가 발생했습니다.");
        console.log(e);
      }
      this.newComment.content = "";
      this.charCount = 0;
      alert("댓글이 등록되었습니다.");
      this.retrieveFreeBoardComment(this.$route.params.freeBoardId);
    },
    // 삭제 함수
    async deleteFreeBoard() {
      try {
        if (confirm("정말로 삭제하시겠습니까?")) {
          let response = await FreeBoardService.deleteFreeBoard(
            this.freeBoard.freeBoardId
          );
          // 로깅
          console.log(response.data);
          alert("게시글이 삭제되었습니다.");
          this.$router.push("/free/free-board");
        } else {
          return;
        }
      } catch (e) {
        console.log(e);
      }
    },
    // 수정 함수
    async likeUp() {
      this.freeBoard.likes = +1;

      try {
        let response = await FreeBoardService.updateFreeBoard(
          this.freeBoard.likes
        );
        // 로깅
        console.log(response.data);
        this.$router.push("/free/free-board/:freeBoardId");
      } catch (e) {
        console.log(e);
      }
    },
    // 답글 버튼 클릭 시 호출되는 메소드
    showReplyForm(commentId) {
      this.replyVisible = true;
      this.replyToCommentId = commentId;
    },

    // 답글 글자 수 세기
    updateReplyCharacterCount() {
      if (this.newReply.content.length > 300) {
        this.newReply.content = this.newReply.content.slice(0, 300);
      }
      this.charCountReply = this.newReply.content.length;
    },

    // 답글 제출
    async submitReply() {
      try {
        let data = {
          userId: this.newReply.userId,
          freeBoardId: this.freeBoard.freeBoardId,
          parentCommentId: this.replyToCommentId, // 부모 댓글 ID
          content: this.newReply.content,
          secretCommentYn: "N",
        };
        await FreeBoardService.createFreeBoardComment(data);
      } catch (e) {
        console.log(e);
      }
      this.newReply.content = "";
      this.charCountReply = 0;
      this.replyVisible = false; // 답글 입력 폼 숨기기
      alert("답글이 등록되었습니다.");
      this.retrieveFreeBoardComment(this.$route.params.freeBoardId);
    },
  },
  mounted() {
    this.retrieveGetFreeBoard(this.$route.params.freeBoardId);
    this.retrieveFreeBoardComment(this.$route.params.freeBoardId);
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
