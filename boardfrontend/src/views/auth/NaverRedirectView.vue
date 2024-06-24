<template>
  <div></div>
</template>

<script>
import AuthService from "@/services/auth/AuthService";

export default {
  data() {
    return {
      user: {},
    };
  },
  methods: {
    async naverLogin(code) {
      try {
        let response = await AuthService.socialLogin(code);

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
    console.log(code);
    this.naverLogin(code);
  },
};
</script>
