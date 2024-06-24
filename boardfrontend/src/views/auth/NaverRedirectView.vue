<template>
  <div>네이버 로그인 중...</div>
</template>

<script>
import AuthService from "@/services/auth/AuthService";

export default {
  methods: {
    async naverLogin(code) {
      try {
        let response = await AuthService.naverLogin(code);
        if (response.data.accessToken == null) {
          this.$router.push("/member/login/additional-info/" + response.data.userId);
        } else {
          let user = response.data;
          localStorage.setItem("user", JSON.stringify(user));
          this.$store.commit("loginSuccess", user);
          this.$router.push("/");
        }
      } catch (e) {
        console.log(e);
      }
    },
  },
  mounted() {
    let code = this.$route.query.code;
    this.naverLogin(code);
  },
};
</script>
