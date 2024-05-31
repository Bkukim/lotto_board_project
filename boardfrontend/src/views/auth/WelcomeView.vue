<template>
  <div class="container text-center">
    <br />
    <br />
    <br />
    <br />
    <br />

    <img src="@/assets/img/join_ok.png" style="width: 70px; height: 70px" />
    <br />
    <br />
    <h2 class="text-center" style="letter-spacing: -3px; color: #00479d">
      회원가입 완료!
    </h2>
   
    <br />
    <br />
    <br />
    <div class="container">
      <div class="row justify-content-md-center">
        <div class="col-8">로또에 회원가입이 성공적으로 완료되었습니다.</div>
      </div>
    </div>
    <div class="col"></div>
  </div>
  <br />
  <br />
  <div class="container text-center">
    <div class="row justify-content-md-center">
      <div class="col-md-auto">
        <button
          class="text-light go-login btn-sm mt-4"
          id=""
          type="submit"
          @click="goLogin"
        >
          로그인 하러 가기
        </button>
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
</template>
<script>
export default {
  methods: {
    goLogin() {
      this.$router.push("/member/login");
    },
  },
  mounted() {
    window.scrollTo(0, 0);
  },
};
</script>
<style>
#box {
  border: 1px solid #cccccc;
  padding: 60px 100px 60px 80px;
}
.go-login {
  background-color: #162b59;
  color: white;
  font-size: 20px;
  width: 200px;
  height: 50px;
}
</style>
