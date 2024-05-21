<template >
  <div class="main-all">
    <!-- 메인배너 1 -->
    <div class="main-banner">
      <!-- 메인배너 1 -->
      <!-- 이미지가 아니라 배경 이미지로 설정 -->
      <div class="banner-content">
        <!-- 이 부분에 배너 내용 추가 -->
      </div>
    </div>

    <!-- 루또의 새로운 소식 (중앙정렬) -->
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

    <div class="main_bn_box">Growth through Technology</div>

    <div class="main_img container text-center">

    </div>
  </div>
</template>
<script>
export default {};
</script>
<style>
.main-all{
  height: 100vw;
}
.main-banner {
  height: 70vh;
  width: auto;
  background-image: url("@/assets/img/MAIN_BN3.jpg"); /* 배경 이미지 설정 */
  background-size: cover; /* 요소에 맞게 이미지 확대 */
  background-position: center; /* 이미지 중앙 정렬 */
  display: flex;
  justify-content: center;
  align-items: center;
}

.banner-content {
  /* 배너 내용 스타일링 */
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

.main_img{
  background-image: url("@/assets/img/MAIN_IMG.jpg"); /* 배경 이미지 설정 */
  background-size: cover; 
  background-position: center; 
}
</style>