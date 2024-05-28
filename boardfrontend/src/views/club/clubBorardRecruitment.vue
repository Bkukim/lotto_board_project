<template>
  <div style="background-color: #f2f2f2; height: 5000px">
    <div class="container text-center">
      <div style="width: 100%; height: 500px; background-color: #4480fe">
        <img
          src="@/assets/img/i_a6003444220f.jpg"
          alt="clud_poto"
          width="100%"
          height="500"
          class="d-inline-block align-text-top"
        />
      </div>
    </div>

    <div class="container text-center mt-1">
      <div class="container text-center">
        <div class="lotto_new row row-cols-lg-4 gap-5 justify-content-center">
          <!-- 매치 포인트 박스 -->
          <div
            class="col"
            style="
              height: 400px;
              width: 900px;
              background-color: #ffffff;
              margin-right: 24px;
            "
          >
            <p
              class="ontainer text-left"
              style="
                text-align: left;
                margin: 20px 0 0 10px;
                font-size: 20px;
                letter-spacing: -1px;
                font-weight: 600;
              "
            >
              매치 포인트
            </p>
          </div>

          <!-- 오른쪽 박스 -->
          <div class="col" style="background-color: #ffffff; height: 400px">
            news 2
          </div>
        </div>

        <!-- 매치데이터 박스 -->
        <div class="row mt-3" style="background-color: #ffffff; height: 700px">
          <p
            class="ontainer text-left"
            style="
              text-align: left;
              margin: 20px 0 0 10px;
              font-size: 20px;
              letter-spacing: -1px;
              font-weight: 600;
            "
          >
            매치 데이터
          </p>
        </div>

        <div class="row mt-3" style="background-color: #ffffff; height: 700px;text-align: left;
       ">
            <p
            class="ontainer text-left"
            style="
              text-align: left;
              margin: 20px 0 0 10px;
              font-size: 20px;
              letter-spacing: -1px;
              font-weight: 600;
            "
          >
            매치 진행방식
          </p>
     <ul style=" padding: 0 0 0 30px; font-size: 20px; padding-left: 50px;">
        <b>매치 규칙</b>
        <li>모든 파울은 사이드라인에서 킥인</li>
        <li>골키퍼에게 백패스 가능 손으로는 잡으면 안 돼요</li>
        <li>사람을 향한 태클 금지</li>
     </ul>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {};
</script>
<style>
#clud_left {
  width: 750px;
  background-color: #ffffff;
}
</style> 