<template>
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-KyZXEAg3QhqLMpG8r+Knujsl5+ENtDY6Q7r7W+hXGCv3vzvK79BmwJ4tcGw8g6Bq"
    crossorigin="anonymous"
  />

  <!-- 전체 박스 -->
  <div class="fbd_all" style="height: auto">
    <br />
    <br />
    <!-- 해당 게시판 이름 부분 -->
    <!-- <div class="container text-center mb-5"> -->
    <!-- <h3 style="text-align: left" id="fbd_h3">건의 게시판 글 상세보기</h3> -->
    <!-- </div> -->
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
        {{ complaintBoard.title }}
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
            등록일 | {{ complaintBoard.insertTime }}
          </div>
          <div class="col" style="color: #999999">등록자 | 익명</div>
        </div>
      </div>
      <div
         id="editor-container"
        v-html="complaintBoard.content"
      ></div>
      <div class="mt-5 text-center">
        <button
          type="button"
          class="btn btn-light"
          @click="upLike"
          style="
            border: none;
            text-align: center;
            height: 8vh;
            width: 15vw;
            padding: 1vw;
          "
        >
          <img src="@/assets/img/like_icon.png" width="40" height="40" />
          좋아요
          {{ this.complaintBoard.likes }}
        </button>

        <!-- <button
          type="button"
          class="btn btn-light"
          style="margin-left: 3vh; height: 8vh; width: 10vw; padding: 1vw"
          data-bs-toggle="modal"
          data-bs-target="#reportModal"
        >
          <img src="@/assets/img/report_icon.png" width="40" height="40" />
          신고
        </button> -->

        <!-- 모달 -->
        <!-- <div
          class="modal fade"
          id="reportModal"
          tabindex="-1"
          aria-labelledby="reportModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5
                  class="modal-title"
                  id="reportModalLabel"
                  style="font-weight: bold"
                >
                  <img
                    src="@/assets/img/report_icon.png"
                    width="20"
                    height="20"
                  />
                  신고하기
                </h5>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">
                <form>
                  <div class="mb-3">
                    <label for="reportReason" class="form-label"
                      >신고 이유를 작성해주세요.</label
                    >
                    <textarea
                      class="form-control"
                      id="reportReason"
                      rows="3"
                    ></textarea>
                  </div>
                  <button type="submit" class="btn btn-primary">제출</button>
                </form>
              </div>
            </div>
          </div>
        </div> -->

      </div>
    

      <!-- 목록으로 버튼 -->
      <div class="col mb-5">
        <router-link
          :to="'/complaint/complaint-board'"
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

    <!-- TODO: 좋아요버튼 -->
    <!-- <div class="d-flex justify-content-center mt-3">
      <button type="button" class="btn btn-primary" @click="likeUp">
        공감해요 {{ this.freeBoard.likes }}
      </button>
    </div> -->

    <!-- 삭제 -->
    <div class="container text-center mt-5">
      <div
        class="row"
        style="margin-top: 100px"
        v-if="this.complaintBoard.userId === this.$store.state.user?.userId"
      >
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
            @click="deleteComplaintBoard"
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
          <router-link
            :to="
              '/complaint/complaint-board/Update/' +
              this.$route.params.complaintBoardId
            "
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
        관리자 댓글
      </div>

      <!-- 댓글 입력하기 -->
      <div
        class="lotto_new row row-cols-lg-4 gap-5 justify-content-left mb-3 mt-5"
      v-if="isAdmin"
      >
        <div class="col" style="color: #595959; font-weight: bold">
          <span style="color: #999999; font-weight: bold">등록자 |</span>
          {{ newComment.userId }}
        </div>

        <div class="col" style="color: #999999">
          날짜 | {{ newComment.insertTime }}
        </div>
      </div>

      <!-- 댓글 글쓰기 칸 -->

      <div class="mb-5 comment-box" style="border: #595959 1.5px solid"       v-if="isAdmin">
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

      <br />
      <br />
      <br />

      <!-- 댓글들 -->
      <div
        class="container text-left"
        v-for="(data, index) in complaintBoardComments"
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
            관리자
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
            position: relative;
          "
        >
          {{ data.content }}
          <br />
          <button
            v-if="isAdmin"
            @click="deleteComment(data.complaintBoardCommentId)"
            class="container text-center mt-3"
            style="
              width: 150px;
              text-decoration: none;
              background-color: #162b59;
              font-size: 18px;
              text-align: center;
              height: 40px;
              font-weight: 100;
              position: absolute;
              bottom: 10px;
              right: 10px;
              display: flex;
              justify-content: center;
              align-items: center;
              border-radius: 5px;
            "
          >
            <div style="color: #ffffff; font-weight: 100">댓글 삭제하기</div>
          </button>
        </div>
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
            @click="
              retrieveComplaintBoardComment(this.$route.params.complaintBoardId)
            "
          ></b-pagination>
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
import ComplaintBoardService from "@/services/board/complaint/ComplaintBoardService";
import ComplaintBoardLikeService from "@/services/board/complaint/ComplaintBoardLikeService";
// import { ref } from "vue";

// 댓글 글자 작성 수 올라가는 것 확인
export default {
  data() {
    return {
      // replyVisible: false, // 답글 입력 폼의 표시 여부를 관리하는 변수
      replyToCommentId: null, // 어떤 댓글에 대한 답글인지 식별하기 위한 변수

      // 새로 작성할 답글
      newReply: {
        userId: this.$store.state.user?.userId,
        content: "",
      },

      // 답글 글자 수
      charCountReply: 0,

      // 해당 게시글
      complaintBoard: {
        complaintBoardId: this.$route.params.complaintBoardId,
        userId: "",
        content: "",
        title: "",
        likes: 0,
      },

      // 기존 댓글 목록
      complaintBoardComments: [],

      // 기존 대댓글 목록
      // complaintBoardReComments: [],

      // 새로 작성할 댓글
      newComment: {
        userId: this.$store.state.user?.userId, // 로그인된 사용자 ID
        content: "",
      },

      // 페이징
      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 5, // 1페이지당개수(select태그)

      // 댓글 글자수

      charCount: 0,
    };
  },
  computed: {
    // 로그인한 계정이 관리자인지 확인하기
    isAdmin() {
      const user = this.$store.state.user;
      return user && user.role && user.role.includes("ROLE_ADMIN");
    },
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
      this.replyToCommentId =
        this.replyToCommentId === commentId ? null : commentId;

      // 현재 선택된 댓글 ID 업데이트
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
    async retrieveGetComplaintBoard(complaintBoardId) {
      try {
        let response = await ComplaintBoardService.getComplaintBoardId(
          complaintBoardId
        );
        this.complaintBoard = response.data;
        await this.checkLike();

        console.log(response.data);
      } catch (e) {
        alert("에러");
        console.log(e);
      }
    },

    // complaintBoardId 댓글조회 : 화면뜰때 실행
    async retrieveComplaintBoardComment(complaintBoardId) {
      try {
        let response = await ComplaintBoardService.getComplaintBoardComment(
          complaintBoardId,
          this.page - 1,
          this.pageSize
        );
        // TODO: 복습 : 2) 객체분할 할당
        const { complaintBoardComments, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.complaintBoardComments = complaintBoardComments; // 부서배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        this.retrieveComplaintBoardRecomment(complaintBoardId);
        // TODO: 4) 프론트 로깅 : console.log
        // console.log("response.data",response.data);
        // console.log("this.comments" ,this.freeBoardComments);
      } catch (e) {
        // alert("페이징 댓글 에러");
        console.log(e);
      }
    },

    // 댓글 등록 함수
    async submitComment() {
      if (!this.newComment.content.trim()) {
        alert("댓글을 입력해주세요.");
        return;
      }
      try {
        let data = {
          userId: this.newComment.userId,

          complaintBoardId: this.complaintBoard.complaintBoardId,
          content: this.newComment.content,
          secretCommentYn: "N",
        };
        await ComplaintBoardService.createComplaintBoardComment(data);
      } catch (e) {
        console.log(e);
      }
      this.newComment.content = "";
      this.charCount = 0;

      this.retrieveComplaintBoardComment(this.$route.params.complaintBoardId);
    },

    // 삭제 함수
    async deleteComplaintBoard() {
      try {
        if (confirm("정말로 삭제하시겠습니까?")) {
          let response = await ComplaintBoardService.deleteComplaintBoard(
            this.complaintBoard.complaintBoardId
          );
          // 로깅
          console.log(response.data);
          alert("게시글이 삭제되었습니다.");
          this.$router.push("/complaint/complaint-board");
        } else {
          return;
        }
      } catch (e) {
        console.log(e);
      }
    },

    // 댓글 삭제 함수
    async deleteComment(commentId) {
      console.log("Deleting comment with ID:", commentId); // Debugging line
      if (!commentId) {
        console.log("No commentId provided");
        return;
      }
      try {
        if (confirm("정말로 댓글을 삭제하시겠습니까?")) {
          await ComplaintBoardService.deleteComplaintBoardComment(commentId);
          this.retrieveComplaintBoardComment(
            this.$route.params.complaintBoardId
          );
        }
      } catch (e) {
        console.log(e);
      }
    },

//TODO: 좋아요 버튼
//  좋아요 함수
async upLike() {
      const complaintBoardId = this.complaintBoard.complaintBoardId;
      const userId = this.$store.state.user.userId; // 로그인한 유저 ID 가져오기

      if (this.isLiked) {
        if (!this.likeId) {
          console.error("Like ID is not defined");
          return;
        }
        try {
          await ComplaintBoardLikeService.deleteComplaintBoardLike(this.likeId);
          this.isLiked = false;
          this.likeId = null;
          this.complaintBoard.likes -= 1;
        } catch (error) {
          console.error("Error deleting like:", error);
        }
      } else {
        try {
          const response = await ComplaintBoardLikeService.createComplaintBoardLike({
            userId,
            complaintBoardId,
          });
          this.isLiked = true;
          this.likeId = response.data.likeId;
          this.complaintBoard.likes += 1;
        } catch (error) {
          console.error("Error creating like:", error);
          if (
            error.response &&
            error.response.data === "Already liked by this user"
          ) {
            // 이미 좋아요가 존재하는 경우 상태를 유지
            this.isLiked = true;
          }
        }
      }
    },
    // like조회
    async checkLike() {
      const complaintBoardId = this.$route.params.complaintBoardId;
      const userId = this.$store.state.user.userId; // Vuex store에서 로그인한 유저 ID 가져오기
      try {
        const response = await ComplaintBoardLikeService.getLikeId(
          userId,
          complaintBoardId
        );
        if (response.status === 204) {
          // NO_CONTENT 상태 코드 처리
          this.isLiked = false;
          this.likeId = null;
        } else {
          this.isLiked = true;
          this.likeId = response.data;
        }
      } catch (error) {
        console.error("Error checking like:", error);
        this.isLiked = false;
        this.likeId = null; // 에러 발생 시 likeId 초기화
      }
    },

    // 답글 버튼 클릭 시 호출되는 메소드
    showReplyForm(complaintBoardCommentId) {
      this.replyVisible = true;
      this.replyToCommentId = complaintBoardCommentId;
    },

    // 답글 글자 수 세기
    updateReplyCharacterCount() {
      if (this.newReply.content.length > 300) {
        this.newReply.content = this.newReply.content.slice(0, 300);
      }
      this.charCountReply = this.newReply.content.length;
    },
  },
  async mounted() {
    this.retrieveGetComplaintBoard(this.$route.params.complaintBoardId);
    this.retrieveComplaintBoardComment(this.$route.params.complaintBoardId);
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
#editor-container {
  text-align: left;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}
#editor-container img {
  max-width: 100%;
  max-height: 1200px;
  height: auto;
}
</style>
