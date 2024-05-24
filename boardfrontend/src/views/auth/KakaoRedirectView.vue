// 카카오 소셜 로그인 리다이렉트 페이지
<template>
  <div>
    <h1>카카오 로그인 처리 중...</h1>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {},
    };
  },
  mounted() {
    let url = new URL(window.location.href);
    console.log(url);

    const urlParams = url.searchParams; // uri 정보 가져오기
    const accessToken = urlParams.get("accessToken");
    const email = urlParams.get("email");
    const codeName = urlParams.get("codeName");

    this.user = {
      accessToken: accessToken,
      email: email,
      codeName: codeName,
    };
    console.log("social user", this.user);

    localStorage.setItem("user", JSON.stringify(this.user));
    this.$store.commit("loginSuccess", this.user);
    this.$router.push("/");
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
