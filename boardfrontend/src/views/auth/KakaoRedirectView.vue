// 카카오 소셜 로그인 리다이렉트 페이지
<template>
  <div></div>
</template>

<script>
import AuthService from '@/services/auth/AuthService';
export default {
  data() {
    return {
      user: {},
    };
  },
  methods: {
      async kakaoLogin(code) {
      let response = await AuthService.socialLogin(code);
      let user = response.data;
      localStorage.setItem("user", JSON.stringify(user));
      this.$store.commit("loginSuccess", user);
      this.$router.push("/");
    },
  },
  mounted() {
    let code = this.$route.query.code;
    console.log(code);
    this.kakaoLogin(code);
  },
  // async created() {
  //   try {
  //     await Kakao.Auth.login({
  //       success: async (authObj) => {
  //         const response = await Kakao.API.request({
  //           url: '/v2/user/me'
  //         });
  //         console.log(response.data);
  //         this.$router.push('/'); // 로그인 후 홈으로 리디렉션
  //       },
  //       fail: (err) => {
  //         console.error(err);
  //       }
  //     });
  //   } catch (error) {
  //     console.error(error);
  //   }
  // }
};
</script>
