<template>
    <br>
    <br>
  <div class="container text-center mt-5 pt-5">
    <img src="@/assets/img/join_ok.png" class="mb-4 join-ok-icon" />
    <h2 class="text-primary fw-bold mb-4">회원가입 완료!</h2>
    <p class="lead">회원가입이 성공적으로 완료되었습니다.</p>
    <button class="btn btn-primary mt-4 px-5 py-2" @click="goLogin">
      로그인 하러 가기
    </button>
  </div>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
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

<style scoped>
.container {
  max-width: 600px;
  margin: auto;
  padding: 20px;
  border-radius: 10px;
  background-color: #f8f9fa;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.join-ok-icon {
  width: 100px;
  height: 100px;
}

h2 {
  letter-spacing: -1px;
  color: #00479d;
}

.lead {
  font-size: 1.25rem;
  color: #555;
}

.btn-primary {
  background-color: #00479d;
  border: none;
  font-size: 1rem;
}

.btn-primary:hover {
  background-color: #003366;
}
</style>
