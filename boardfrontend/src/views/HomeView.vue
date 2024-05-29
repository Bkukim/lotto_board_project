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

    <!-- HOT & 커뮤니티 보이는 부분 -->
    <div class="container text-center mt-5" >
      <div class="row" style="margin-top: 100px;">
        <!-- 이달의 HOT -->
        <div class="col">
          <router-link
            to="/"
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
                이달의 HOT
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
                  <th scope="col" style="font-size: 14px">작성자</th>
                  <th scope="col" style="font-size: 14px">제목</th>
                </tr>
              </thead>
              <tbody>
                <!-- 반복문 시작할 행 -->
                <tr v-for="(data, index) in hotList" :key="index">
                  <td style="font-size: 15px">
                    {{ index + 1 }}
                  </td>
                  <td class="col-8"></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- 커뮤니티 -->
        <div class="col">
          <router-link
            to="/"
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
              <div class="router-text" style="margin-right: 50px">커뮤니티</div>
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

          <!-- 커뮤니티 테이블 -->
          <div
            class="row mt-3 container text-center"
            style="width: 500px; margin-left: 50px"
          >
            <!-- 테이블 시작-->
            <table class="table mt-5">
              <thead>
                <tr>
                  <th scope="col" style="font-size: 14px">작성자</th>
                  <th scope="col" style="font-size: 14px">제목</th>
                </tr>
              </thead>
              <tbody>
                <!-- 반복문 시작할 행 -->
                <tr v-for="(data, index) in communityList" :key="index">
                  <td style="font-size: 15px">
                    {{ index + 1 }}
                  </td>
                  <td class="col-8"></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <div class="row">
        <!-- 이달의 HOT -->
        <div class="col mt-5">
          <router-link
            to="/"
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
                이달의 HOT2
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

          <!-- 이달의 hot2 테이블 -->
          <div
            class="row mt-3 container text-center"
            style="width: 500px; margin-left: 50px"
          >
            <!-- 테이블 시작-->
            <table class="table mt-5">
              <thead>
                <tr>
                  <th scope="col" style="font-size: 14px">작성자</th>
                  <th scope="col" style="font-size: 14px">제목</th>
                </tr>
              </thead>
              <tbody>
                <!-- 반복문 시작할 행 -->
                <tr v-for="(data, index) in hotList" :key="index">
                  <td style="font-size: 15px">
                    {{ index + 1 }}
                  </td>
                  <td class="col-8"></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- 커뮤니티2 -->
        <div class="col mt-5">
          <router-link
            to="/"
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
              <div class="router-text" style="margin-right: 50px">커뮤니티2</div>
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

          <!-- 커뮤니티 테이블 -->
          <div
            class="row mt-3 container text-center"
            style="width: 500px; margin-left: 50px"
          >
            <!-- 테이블 시작-->
            <table class="table mt-5">
              <thead>
                <tr>
                  <th scope="col" style="font-size: 14px">작성자</th>
                  <th scope="col" style="font-size: 14px">제목</th>
                </tr>
              </thead>
              <tbody>
                <!-- 반복문 시작할 행 -->
                <tr v-for="(data, index) in communityList" :key="index">
                  <td style="font-size: 15px">
                    {{ index + 1 }}
                  </td>
                  <td class="col-8"></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- 중앙 파랑 띠? -->
    <div class="main_bn_box">Growth through Technology</div>


    <!-- 로또의 새로운 소식 (중앙정렬) -->
    <div class="container text-center">
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
        </div>
        <!-- 루또의 새로운 소식 끝 -->
      </div>
      <!-- 루또의 새로운 소식 (중앙정렬) 끝 -->
    </div>


    <div class="main_img container text-center"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      hotList: [1, 2, 3, 4, 5],
      communityList: [1, 2, 3, 4, 5],
    };
  },
};
</script>

<style>
.main-all {
  height: 3500px;
}

/* 메인배너 */
.main-banner {
  height: 20vh;
  width: auto;
  background-image: url("@/assets/img/MAIN_BN3.jpg"); /* 배경 이미지 설정 */
  background-size: 100%; /* 요소에 맞게 이미지 확대 */
  background-position: center; /* 이미지 중앙 정렬 */
  display: flex;
  justify-content: center;
  align-items: center;
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
  height: 15vh;
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
.hot_cm::before{
  /* border-top: 5px solid  #162b59; */
  content: '';
    position: absolute;
    left: 150px;
    top: -2px; 
    width: 200px; 
    height: 5px; /* 선의 높이 조절 */
    background-color: #4480fe; /* 선의 색상 설정 */
    opacity: 0; /* 호버 시 선이 나타나도록 설정 */
    transition: opacity 0.2s ease; /* 부드러운 애니메이션 효과를 위한 트랜지션 */
}
.hot_cm:hover::before{
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
</style>
