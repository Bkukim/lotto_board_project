<template>
  <div class="main-all">
    <!-- 메인배너 1 -->
    <div class="main-banner">
      <!-- 메인배너 1 -->
      <!-- 이미지가 아니라 배경 이미지로 설정 -->
      <div class="banner-content">
        <!-- 이 부분에 배너 내용 추가 -->
      </div>
    </div>

    <!-- HOT & 공지사항 보이는 부분 -->
    <div class="container text-center mt-5">
      <div class="row" style="margin-top: 100px">
        <!-- 이달의 HOT -->
        <div class="col">
          <router-link
            to="/hot/hotBoard"
            class="hot_cm container text-center"
            style="width: 500px; text-decoration: none"
          >
            <div
              style="
                display: flex;
                align-items: center;
                justify-content: center;
              "
            >
              <div class="router-text" style="margin-right: 50px">
                이달의 HOT!
              </div>
              <img
                src="@/assets/img/hot_icon.png"
                alt="Loo"
                width="70"
                height="70"
                class="d-inline-block align-text-top"
                style="margin-left: 15px"
              />
            </div>
          </router-link>

          <!-- 이달의 hot 테이블 -->
          <div
            class="row mt-3 container text-center"
            style="width: 500px; margin-left: 50px"
          >
            <!-- 테이블 시작-->
            <table class="table mt-5">
              <thead>
                <tr>
                  <th scope="col" style="font-size: 14px">번호</th>
                  <th scope="col" style="font-size: 14px; text-align: left">
                    제목
                  </th>
                </tr>
              </thead>
              <tbody>
                <!-- 반복문 시작할 행 -->
                <tr v-for="(data, index) in hotList" :key="index">
                  <td style="font-size: 15px">
                    {{ index + 1 }}
                  </td>

                  <td class="col-8" style="text-align: left">
                    <router-link
                      style="color: #444444; text-decoration: none"
                      :to="'/free/free-boardDetail/' + data.freeBoardId"
                      class="router-link-exact-active alltext"
                      >{{ data.title }}</router-link
                    >
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- 공지사항 -->
        <div class="col">
          <router-link
            to="/notice/notice-board"
            class="hot_cm container text-center"
            style="width: 500px; text-decoration: none"
          >
            <div
              style="
                display: flex;
                align-items: center;
                justify-content: center;
              "
            >
              <div class="router-text" style="margin-right: 50px">공지사항</div>
              <img
                src="@/assets/img/Community_icon.png"
                alt="Loo"
                width="70"
                height="70"
                class="d-inline-block align-text-top"
                style="margin-left: 15px"
              />
            </div>
          </router-link>

          <!-- 공지사항 테이블 -->
          <div
            class="row mt-3 container text-center"
            style="width: 500px; margin-left: 50px"
          >
            <!-- TODO : 공지사항 테이블 시작-->
            <table class="table mt-5">
              <thead>
                <tr>
                  <th scope="col" style="font-size: 14px">번호</th>
                  <th scope="col" style="font-size: 14px; text-align: left">
                    제목
                  </th>
                </tr>
              </thead>
              <tbody>
                <!-- 반복문 시작할 행 -->
                <tr v-for="(data, index) in notice" :key="index">
                  <td style="font-size: 15px">
                    {{ index + 1 }}
                  </td>

                  <td class="col-8" style="text-align: left">
                    <router-link
                      :to="
                        `/notice/notice-check/` +
                        data.noticeId +
                        '/' +
                        data.eventYN
                      "
                      style="
                        color: #444444;
                        text-decoration: none;
                        text-align: left !important;
                      "
                    >
                      {{ data.title }}</router-link
                    >
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <div class="row">
        <!--자유게시판 -->
        <div class="col mt-5">
          <router-link
            to="/free/free-board"
            class="hot_cm container text-center"
            style="width: 500px; text-decoration: none"
          >
            <div
              style="
                display: flex;
                align-items: center;
                justify-content: center;
              "
            >
              <div class="router-text" style="margin-right: 50px">
                자유게시판
              </div>
              <img
                src="@/assets/img/main_free.png"
                alt="Loo"
                width="70"
                height="70"
                class="d-inline-block align-text-top"
                style="margin-left: 15px"
              />
            </div>
          </router-link>

          <!-- 자유게시판 테이블 -->
          <div
            class="row mt-3 container text-center"
            style="width: 500px; margin-left: 50px"
          >
            <!-- 테이블 시작-->
            <table class="table mt-5">
              <thead>
                <tr>
                  <th scope="col" style="font-size: 14px">번호</th>
                  <th scope="col" style="font-size: 14px; text-align: left">
                    제목
                  </th>
                </tr>
              </thead>
              <tbody>
                <!-- 반복문 시작할 행 -->
                <tr v-for="(data, index) in LatestFreeBoard" :key="index">
                  <td style="font-size: 15px">
                    {{ index + 1 }}
                  </td>

                  <td class="col-8" style="text-align: left">
                    <router-link
                      style="color: #444444; text-decoration: none"
                      :to="'/free/free-boardDetail/' + data.freeBoardId"
                      class="router-link-exact-active alltext"
                      >{{ data.title }}</router-link
                    >
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- 건의게시판 -->
        <div class="col mt-5">
          <router-link
            to="/complaint/complaint-board"
            class="hot_cm container text-center"
            style="width: 500px; text-decoration: none"
          >
            <div
              style="
                display: flex;
                align-items: center;
                justify-content: center;
              "
            >
              <div class="router-text" style="margin-right: 50px">
                건의게시판
              </div>
              <img
                src="@/assets/img/main_cp.png"
                alt="Loo"
                width="70"
                height="70"
                class="d-inline-block align-text-top"
                style="margin-left: 15px"
              />
            </div>
          </router-link>

          <!-- 건의게시판 테이블 -->
          <div
            class="row mt-3 container text-center"
            style="width: 500px; margin-left: 50px"
          >
            <!-- 테이블 시작-->
            <table class="table mt-5">
              <thead>
                <tr>
                  <th scope="col" style="font-size: 14px">번호</th>
                  <th scope="col" style="font-size: 14px; text-align: left">
                    제목
                  </th>
                </tr>
              </thead>
              <tbody>
                <!-- 반복문 시작할 행 -->
                <tr v-for="(data, index) in LatestComplaintBoard" :key="index">
                  <td style="font-size: 15px">
                    {{ index + 1 }}
                  </td>

                  <td class="col-8" style="text-align: left">
                    <router-link
                      style="color: #444444; text-decoration: none"
                      :to="
                        '/complaint/complaint-boardDetail/' +
                        data.complaintBoardId
                      "
                      class="router-link-exact-active alltext"
                      >{{ data.title }}</router-link
                    >
                    <!-- {{data.complaintBoardId}} -->
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- 중앙 파랑 띠? -->
    <div class="main_bn_box">Growth through Technology</div>

    <div class="widgets-container container text-center mt-5">
      <CalendarWidget />
      <!-- <MemoWidget /> -->
    </div>

    <!-- 로또의 새로운 소식 (중앙정렬) -->
    <!-- <div class="container text-center">
      <div class="container text-center">
        <h2 class="main_tt1 mb-5">LOTOO의 새로운 소식</h2>

        <div class="lotto_new row row-cols-lg-4 gap-4 justify-content-center">
          <div
            class="col shadow"
            style="background-color: width; height: 300px"
          >
            news 1
          </div>
          <div
            class="col shadow"
            style="background-color: width; height: 300px"
          >
            news 2
          </div>
          <div
            class="col shadow"
            style="background-color: width; height: 300px"
          >
            news 3
          </div>
        </div> -->
    <!-- 루또의 새로운 소식 끝 -->
    <!-- </div> -->
    <!-- 루또의 새로운 소식 (중앙정렬) 끝 -->
    <!-- </div> -->

    <div class="main_img container text-center"></div>
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
  <br />
  <br />
</template>

<script>
import CalendarWidget from "./CalendarWidget.vue";
import FreeBoardService from "@/services/board/free/FreeBoardService";
import NoticeService from "@/services/notice/NoticeService";
import ComplaintBoardService from "@/services/board/complaint/ComplaintBoardService";

// import MemoWidget from './MemoWidget.vue';
export default {
  components: {
    CalendarWidget,
    // MemoWidget
  },
  data() {
    return {
      hotList: [],
      LatestComplaintBoard: [],
      LatestFreeBoard: [],
      count: 0, // 전체데이터개수
      notice: [], //공지사항 가져오기
    };
  },
  methods: {
    // todo: 좋아요순 자유게시판 메인 조회
    async retrieveHotList() {
      console.log("아이디가 안들어오니?", this.hotList.freeBoardId);
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await FreeBoardService.getFreeboardLike();
        // TODO: 복습 : 2) 객체분할 할당
        const { hotList, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.hotList = hotList; // 부서배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // todo: 공지사항 메인 조회
    async retrieveNotice() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await NoticeService.getAllMain();
        // TODO: 복습 : 2) 객체분할 할당
        const { notice } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.notice = notice; // 부서배열(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        // console.log("notice 조회", this.notice);
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // todo: 자유게시판 메인 조회
    async retrieveRecentFreeBoard() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await FreeBoardService.getRecentFreeboard();
        // TODO: 복습 : 2) 객체분할 할당
        const { LatestFreeBoard } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.LatestFreeBoard = LatestFreeBoard; // 부서배열(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log("자유게시판 5개", this.LatestFreeBoard);
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // todo: 건의게시판 메인 조회
    async retrieveRecentComplaintBoard() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await ComplaintBoardService.getRecentComplaintboard();
        // TODO: 복습 : 2) 객체분할 할당
        const { LatestComplaintBoard } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.LatestComplaintBoard = LatestComplaintBoard; // 부서배열(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log("건의게시판 5개", this.LatestComplaintBoard);
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    goToLogin() {
      if (this.$store.state.user == null) {
        this.$router.push("/member/login");
      } else {
        return;
      }
    },
  },
  mounted() {
    console.log(this.$store.state.notifyCount);
    this.goToLogin();
    this.retrieveHotList();
    this.retrieveNotice();
    this.retrieveRecentFreeBoard();
    this.retrieveRecentComplaintBoard();
  },
};
</script>

<style>
.main-all {
  height: auto;
}

/* 메인배너 */
.main-banner {
  height: 250px;
  width: 100%; /* 고정된 너비 설정 */
  background-image: url("@/assets/img/MAIN_BN4.jpg"); /* 배경 이미지 설정 */
  background-repeat: no-repeat;
  background-size: cover; /* 요소에 맞게 이미지 확대 */
  background-position: center;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto; /* 가로로 가운데 정렬 */
}

/* 로또의 새로운 소식 */
.main_tt1 {
  color: #5353535c;
  text-align: center;
  font-family: "Inter-Bold", sans-serif;
  font-size: 40px;
  font-weight: 700;
  margin-top: 150px;
  letter-spacing: -2.5px;
}

.main_bn_box {
  height: 150px;
  width: auto;
  background-color: #4480fe;
  margin: 150px 0 0 0;
  color: #ffffff;
  text-align: center;
  font-family: "Inter-Bold", sans-serif;
  font-size: 55px;
  font-weight: 700;
  padding-top: 30px;
}

.main_img {
  background-image: url("@/assets/img/MAIN_IMG.jpg"); /* 배경 이미지 설정 */
  background-size: cover;
  background-position: center;
}

/* HOT */
.hot_cm {
  height: 100px;
  background-color: #f0f0f0b9;
  text-align: center;
  font-size: 20px;
  padding: 20px;
  border-radius: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
}
.hot_cm::before {
  /* border-top: 5px solid  #162b59; */
  content: "";
  position: absolute;
  left: 150px;
  top: -2px;
  width: 200px;
  height: 5px; /* 선의 높이 조절 */
  background-color: #4480fe; /* 선의 색상 설정 */
  opacity: 0; /* 호버 시 선이 나타나도록 설정 */
  transition: opacity 0.2s ease; /* 부드러운 애니메이션 효과를 위한 트랜지션 */
}
.hot_cm:hover::before {
  opacity: 1; /* 호버 시 선이 나타나도록 설정 */
}
.router-text {
  color: #4b4b4b;
  font-weight: bold;
  text-decoration: none;
}

.router-link {
  text-decoration: none;
}

.router-link:hover {
  text-decoration: none;
}
.widgets-container {
  display: flex;
  justify-content: space-between;
  margin-top: 50px;
}
</style>
