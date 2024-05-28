<template>
  <!-- 전체 박스스 -->
  <div class="fbd_all" style="height: 2000px; background-color: #f2f2f2">
    <!-- 해당 게세판 이름 부분 -->
    <div class="container text-center">
      <h3 style="text-align: left" id="fbd_h3">자유 게시판 글 상세보기</h3>
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
        {{ freeBoardList.title }}
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
            등록일 | {{ freeBoardList.insertTime }}
          </div>
          <div class="col" style="color: #999999">
            등록자 | {{ freeBoardList.userId }}
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
      >
      {{freeBoardList.content}}</div>

      <!-- 파일첨부 -->
      <div class="mt-5" style="width: 500px">
        <input
          class="form-control form-control-sm"
          id="formFileSm"
          type="file"
          style="margin-left: 20px"
        />
      </div>
    </div>
    <!--  첫번째 게시판 큰 박스 끝-->

    <div class="container text-center mt-5">
      <div class="row" style="margin-top: 100px">
        <!-- 삭제 -->
        <div class="col">
          <router-link
            to="/"
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
          </router-link>
        </div>

        <!-- 수정 -->
        <div class="col mb-5">
          <router-link
            to="/"
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

    <!-- 댓글 -->
    <div
      class="container text-center mt-5"
      id="comments"
      style="
        height: 250px;
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
          <div class="col" style="color: #999999">등록자 |</div>
          <div class="col" style="color: #999999">날짜 |</div>
        </div>

        <div class="mb-5">
          <textarea
            class="form-control"
            id="exampleFormControlTextarea1"
            rows="3"
          ></textarea>
        </div>
      </div>
    </div>
  </div>
  <!-- 전체 박스 끝 -->
</template>
<script>
import FreeBoardService from '@/services/board/free/FreeBoardService';
export default {
  data() {
    return {
      freeBoardList: {
        freeBoardId: this.$route.params.freeBoardId,
        userId: "",
        content: "",
        title: "",
      },
    };
  },
  methods: {
    // freeBoardId로 상세조회 : 화면뜰때 실행
    async retrieveGetFreeBoard(freeBoardId) {
      try {
        let response = await FreeBoardService.getFreeBoardId(freeBoardId);
        this.freeBoardList = response.data;
        console.log(response.data);
      } catch (e) {
        alert("에러");
        console.log(e);
      }
    },
  },
  mounted(){
    this.retrieveGetFreeBoard(this.$route.params.freeBoardId);
  }
};
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
</style>
