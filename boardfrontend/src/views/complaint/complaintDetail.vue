<template>
  <!-- 전체 박스스 -->
  <div class="fbd_all" style="height: 2000px; background-color: #f2f2f2">
    <!-- 해당 게세판 이름 부분 -->
    <div class="container text-center">
      <h3 style="text-align: left" id="fbd_h3">건의 게시판 글 상세보기</h3>
    </div>
    <!-- 해당 게세판 이름 부분  끝-->

    <!--  첫번째 게시판 큰 박스-->
    <div
      class="container text-center mt-5"
      style="
        height: 700px;
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
        style="
          text-align: left;
          border-bottom: #cccccc solid 1px;
          padding: 20px 0 20px 30px;
          font-size: 15px;
          font-weight: 600;
          height: 450px;
        "
        v-html="complaintBoard.content"
      ></div>

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
        v-if="complaintBoard.userId === this.$store.state.user?.userId"
      >
        <!-- 삭제 -->
        <div class="col">
          <button
            class="fbd_d container text-center"
            style="
              width: 300px;
              text-decoration: none;
              background-color: #ffffff;
              font-size: 20px;
              text-align: center;
              height: 50px;
              border-radius: 20px;
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
                style="margin-right: 20px; margin-top: 10px"
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

    <!-- 댓글 조회-->
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

      <div
        style="
          text-align: left;
          padding: 20px 0 20px 30px;
          font-size: 15px;
          font-weight: 600;
        "
      >
        <div v-for="(data, index) in complaintBoardComments" :key="index">
          <div
            class="lotto_new row row-cols-lg-4 gap-5 justify-content-left mb-3"
          >
            <div class="col" style="color: #999999">
              등록자 | {{ data.userId }}
            </div>

            <div class="col" style="color: #999999">
              날짜 | {{ data.insertTime }}
            </div>
          </div>

          <!-- 글쓰기 칸 -->
          <div class="mb-5">
            <!-- <textarea
              class="form-control"
              id="exampleFormControlTextarea1"
              rows="3"
            ></textarea> -->
            <div
              style="
                padding-left: 30px;
                padding-bottom: 30px;
                border: 1px solid #ccc;
                border-radius: 5px;
              "
            >
              {{ data.content }}
            </div>
          </div>
        </div>

        <!-- 댓글 입력 -->
        <div>
          <div
            class="lotto_new row row-cols-lg-4 gap-5 justify-content-left mb-3"
          >
            <div class="col" style="color: #999999">등록자 | 익명</div>

            <div class="col" style="color: #999999">
              날짜 | {{ newComment.insertTime }}
            </div>
        </div>

        <!-- 글쓰기 칸 -->
        <div class="mb-5">
            <textarea
              class="form-control"
              id="exampleFormControlTextarea1"
              rows="3"
              v-model="newComment.content"
              @input="updateCharacterCount"
              maxlength="1000"
            ></textarea>
            <div class="char-count">글자 수: {{ charCount }}/1000</div>
          </div>
        </div>

        <!-- 등록 버튼-->
        <div class="col">
          <button
            @click="submitComplaintComment()"
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
          </button>
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
                retrieveComplaintBoardComment(
                  this.$route.params.complaintBoardId
                )
              "
            ></b-pagination>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 전체 박스 끝 -->
</template>
<script>
import ComplaintBoardService from "@/services/board/complaint/ComplaintBoardService";

// 댓글 글자 작성 수 올라가는 것 확인
export default {
  data() {
    return {
      complaintBoard: {
        complaintBoardId: this.$route.params.complaintBoardId,
        userId: "",
        content: "",
        title: "",
      },
      complaintBoardComments: [], // 기존 댓글 목록
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
    // 댓글 작성 시 글자 수 세기
    updateCharacterCount() {
      if (this.newComment.content.length > 1000) {
        this.newComment.content = this.newComment.content.slice(0, 1000);
      }
      this.charCount = this.newComment.content.length;
    },
    // complaintBoardId로 상세조회 : 화면뜰때 실행
    async retrieveGetComplaintBoard(complaintBoardId) {
      try {
        let response = await ComplaintBoardService.getComplaintBoardId(
          complaintBoardId
        );
        this.complaintBoard = response.data;
        console.log(response.data);
      } catch (e) {
        alert("에러");
        console.log(e);
      }
    },
    // complaintBoardId로 댓글조회 : 화면뜰때 실행
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
        // TODO: 4) 프론트 로깅 : console.log
        // console.log("response.data",response.data);
        // console.log("this.comments" ,this.freeBoardComments);
      } catch (e) {
        alert("페이징 에러");
        console.log(e);
      }
    },
    // 댓글 등록 함수
    async submitComplaintComment() {
      try {
        let data = {
          userId: this.newComment.userId,
          complaintBoardId: this.complaintBoard.complaintBoardId,
          content: this.newComment.content,
        };
        await ComplaintBoardService.createComplaintBoardComment(data);
      } catch (e) {
        // alert("댓글 등록 중 에러가 발생했습니다.");
        console.log(e);
      }
      this.newComment.content = "";
      this.charCount = 0;
      alert("댓글이 등록되었습니다.");
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
  },
  mounted() {
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
</style>
