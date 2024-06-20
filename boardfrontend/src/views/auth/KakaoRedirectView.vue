// 카카오 소셜 로그인 리다이렉트 페이지
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
    async kakaoLogin(code) {
      try {
        let response = await AuthService.socialLogin(code);

        if (response.data.accessToken == null) {
          this.$router.push(
            "/member/login/additional-info/" + response.data.userId
          );
        } else {
          let user = response.data;
          localStorage.setItem("user", JSON.stringify(user));
          this.$store.commit("loginSuccess", user);
          this.connectSse(response.data.accessToken);
          this.$router.push("/");
        }
      } catch (e) {
        console.log(e);
      }
    },
    // sse 연결 함수
    connectSse(jwt) {
      let subscribeUrl = "http://localhost:8000/api/v1/notify/subscribe";

      if (jwt != null) {
        let token = jwt;
        this.eventSource = new EventSource(subscribeUrl + "?token=" + token);
        this.eventSource.onopen = () => {
          console.log("SSE 연결이 열렸습니다.");
          this.isConnected = true;
        };
        // this.eventSource.addEventListener("connect", function(event) {
        //     let message = event.data;
        //     alert(message);
        // })
        
        this.eventSource.addEventListener("COMMENT", function (event) {
          let message = event.data;
          alert(message);
        });
        this.eventSource.onmessage = (event) => {
          console.log("새 알림:", event.data);
          this.messages.push(event.data);
        };
        this.eventSource.onerror = (event) => {
          console.error("SSE 연결 오류:", event);
          if (event.readyState == EventSource.CLOSED) {
            console.log("SSE 연결이 닫혔습니다.");
            this.isConnected = false;
          } else {
            console.log("SSE 연결 오류 발생, 재연결 시도 중...");
            setTimeout(() => this.connectSSE(), 5000); // 5초 후 재연결 시도
          }
        };
      } else {
        console.error("JWT 토큰이 없습니다.");
      }
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
