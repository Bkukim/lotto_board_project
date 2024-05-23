<template>
  <div>
    <h1>카카오 로그인 처리 중...</h1>
  </div>
</template>

<script>
export default {
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
}
</script>