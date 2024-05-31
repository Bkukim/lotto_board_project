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
        {{ complaintBoardList.title }}
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
            등록일 | {{ complaintBoardList.insertTime }}
          </div>
          <div class="col" style="color: #999999">
            등록자 | 익명
          </div>
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
        v-html="complaintBoardList.content"
      >
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
      <div class="row" style="margin-top: 100px" v-if="complaintBoardList.userId=== this.$store.state.user?.userId">
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
            :to="'/complaint/complaint-board/Update/'+ this.$route.params.complaintBoardId"
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

import { ref } from "vue";
import ComplaintBoardService from '@/services/board/complaint/ComplaintBoardService';

// 댓글 글자 작성 수 올라가는 것 확인
export default {
    setup() {
    const text = ref("");
    const charCount = ref(0);
    const maxChars = 1000;

    function updateCharacterCount() {
      if (text.value.length > maxChars) {
        text.value = text.value.slice(0, maxChars);
      }
      charCount.value = text.value.length;
    }

    return {
      text,
      charCount,
      updateCharacterCount,
    };
  },
  data() {
    return {
      complaintBoardList: {
        complaintBoardId: this.$route.params.complaintBoardId,
        userId: "",
        content: "",
        title: "",
      },
    };
  },
  methods: {
    // freeBoardId로 상세조회 : 화면뜰때 실행
    async retrieveGetComplaintBoard(complaintBoardId) {
      try {
        let response = await ComplaintBoardService.getComplaintBoardId(complaintBoardId);
        this.complaintBoardList = response.data;
        console.log(response.data);
      } catch (e) {
        alert("에러");
        console.log(e);
      }
    },
    async deleteComplaintBoard(){
        try {
        let result = confirm("정말로 삭제하시겠습니까?")
        if (result) {
            let response = await ComplaintBoardService.deleteComplaintBoard(this.complaintBoardList.complaintBoardId);
        // 로깅
        console.log(response.data);
        alert("게시글이 삭제되었습니다.");
        this.$router.push("/complaint/complaint-board");
        } else{
          return;
        }
      } catch (e) {
        console.log(e);
      }
    }
  },
  mounted(){
    this.retrieveGetComplaintBoard(this.$route.params.complaintBoardId);
  }
}
</script>

<style>
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

