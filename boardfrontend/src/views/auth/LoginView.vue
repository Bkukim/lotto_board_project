<template>
  <div class="container mt-3" style="height: auto;">
    <div class="login-container">
      <div class="login-form">
        <!-- <h3 class="mb-4 mt-5" style="letter-spacing: -2px">Welcome</h3> -->
        <div class="row" style="text-align: center">
          <div class="col log col-divider">
            <h5 class="mb-4 mt-4 log-form" style="color: #333">
              <strong>회원 로그인</strong>
            </h5>
            <div class="mb-3 container text-center">
              <!-- <label class="insert-id log-form mb-2" for="id">아이디</label> -->
              <input
                class="form-control log-form"
                type="text"
                placeholder="ID"
                name="id"
                id="id"
                v-model="user.userId"
                style="width: 300px; height: 30px"
              />
            </div>
            <div class="mb-3">
              <!-- <label class="form-label log-form" for="pwd">비밀번호</label> -->
              <input
                class="form-control log-form"
                type="password"
                placeholder="PASSWORD"
                name="pwd"
                id="pwd"
                v-model="user.password"
                style="width: 300px; height: 30px"
              />
            </div>
            <div class="mb-3">
              <button
                class="btn text-light btn-sm mt-4 log-form"
                id="login-bt"
                type="submit"
                @click="handleLogin"
                style="width: 300px; height: 30px"
              >
                로그인
              </button>
            </div>

            <!-- 아이디/비밀번호 찾기 -->
            <div style="margin-left: 140px">
              <button
                class="btn btn-sm"
                type="button"
                @click="goFindId"
                style="font-size: 12px"
              >
                아이디 찾기
              </button>
              <button
                class="btn btn-sm"
                type="button"
                @click="goFindPwd"
                style="font-size: 12px"
              >
                비밀번호 찾기
              </button>
            </div>

            <!-- 회원가입 -->
            <div class="mb-3 text-center" style="text-align: center">
              <button
                class="colbtn btn-sm mt-4 text-center"
                id="login-bt22"
                type="submit"
                @click="goJoin"
                style="
                  color: #333;
                  border: none;
                  background-color: #fff;
                  height: auto;
                  font-size: 13px;
                  letter-spacing: -1px;
                "
              >
                아직 회원이 아니신가요?
                <span
                  style="
                    font-weight: bold;
                    color: #162b59;
                    font-size: 18px;
                    margin-left: 7px;
                  "
                  >Sign Up
                </span>
              </button>
            </div>

            
          </div>

        </div>
        <!-- api 로그인 -->
        <div class="api-login row container text-center mt-5">
          <div class="api-login-icons col text-center">
            <img
              src="@/assets/img/btnG_축약형.png"
              class="login-icon"
              @click="goToNaverLogin"
              style="width: 110px; margin-right: 7px;"
            />
            <img
              src="@/assets/img/kakao_login_medium.png"
              class="login-icon"
              @click="goToKakaoAuth"
              style="width: 90px;"
            />
          </div>
        </div>
      </div>

      <!-- -------------------------오른쪽 이미지 -->
      <div class="login-image">
        <img src="@/assets/img/ROUTTO.png" alt="Login Image" />
      </div>
    </div>
  </div>
  <br />
  <br />
  <br />
  <br />
  <br />
  <br />

</template>

<script>
// TODO: 1) spring 보내준 user 객체(웹토큰있음)를 로컬스토리지에 저장
// TODO:   사용법 :  localStorage.setItem(키, 값);
// TODO:     => 단, 값은 문자열만 저장됨
// TODO:   사용법 : JSON.stringify(객체) => 문자열로 바뀐 객체가 리턴됨
// TODO: 2) 공유저장소의 state / mutations 함수 접근법
// TODO:   mutations 사용법 : this.$store.commit("함수명", 저장할객체)
// TODO:     => 로그인성공 공유함수(loginSuccess(state, 유저객체)) 실행
// TODO:   state 사용법 : this.$store.state.공유속성명
// TODO:     => 공유저장소의 공유속성 접근법
// TODO: 3) 뷰의 라이프사이클
// TODO:   - mounted() : 화면이 뜰때 자동 실행 (생명주기 함수)
// TODO:   - created() : 뷰가 생성될대 자동 실행
// TODO:   - created()(1번, 뷰만 생성되면 실행) -> mounted()(2번, html 태그까지 모두 뜰때)
// TODO:     예) destoryed() : 뷰가 삭제될때 실행 (거의 사
import AuthService from "@/services/auth/AuthService";
import NotifyService from "@/services/notify/NotifyService";
// import NotifyService from "@/services/notify/NotifyService";
export default {
  data() {
    return {
      eventSource: null,
      user: {
        role: "",
        userId: "", // 로그인 ID
        password: "",
      },
    };
  },
  methods: {
    // 카카오 로그인
    goToKakaoAuth() {
      const client_id = "6a9b8daaeef2609b3db2849d027f6080"; // Rest API 키
      const redirect_uri = "http://" + this.$store.state.frontendIp + "/auth-redirect"; // Redirect URI
      // const redirect_uri = "http://13.209.24.76:8000/auth-redirect"; // Redirect URI
      const kakaoAuthUrl = `https://kauth.kakao.com/oauth/authorize?client_id=${client_id}&redirect_uri=${redirect_uri}&response_type=code`; // response_type=code는 고정
      window.location.href = kakaoAuthUrl; // 이 페이지는 카카오에서 제공하는 페이지라 따로 페이지 만들 필요 없음
    },
    // 네이버 로그인
    goToNaverLogin() {
      const clientId = "Ipydix8nXe2V9m6KRDom"; // 네이버 개발자 센터에서 발급받은 Client ID
      const redirectUri = "http://" + this.$store.state.frontendIp + "/login/ouath2/code/naver"; // 네이버 개발자 센터에 등록한 Redirect URI
      // const redirectUri = "http://13.209.24.76:8000/login/ouath2/code/naver"; // 네이버 개발자 센터에 등록한 Redirect URI
      const state = this.generateRandomState(); // CSRF 공격 방지를 위한 랜덤 상태 값
      const naverAuthUrl = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${clientId}&redirect_uri=${encodeURIComponent(
        redirectUri
      )}&state=${state}`;
      window.location.href = naverAuthUrl;
    },
    generateRandomState() {
      const characters =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
      let state = "";
      for (let i = 0; i < 16; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        state += characters.charAt(randomIndex);
      }
      return state;
    },
    // 아이디 찾기
    goFindId() {
      this.$router.push("/member/find-id");
    },
    // 회원가입
    goJoin() {
      this.$router.push("/member/join");
    },
    // 비밀번호 찾기
    goFindPwd() {
      this.$router.push("/member/find-pwd");
    },
    // sse 연결 함수
    connectSse(jwt) {
      // let subscribeUrl = "http://localhost:8000/api/v1/notify/subscribe";
        let subscribeUrl = "http://" + this.$store.state.backendIp + "/api/v1/notify/subscribe";

      if (jwt != null) {
        let token = jwt;
        this.eventSource = new EventSource(subscribeUrl + "?token=" + token);
        this.eventSource.onopen = () => {
          console.log("SSE 연결이 열렸습니다.");
          this.isConnected = true;
        };
        this.eventSource.addEventListener("COMMENT", function (event) {
          let message = event.data;
          alert(message);
        });
        this.eventSource.addEventListener("REPORT", function (event) {
          let message = event.data;
          alert(message);
        });
        this.eventSource.addEventListener("CLUB_APPLICATION", function (event) {
          let message = event.data;
          alert(message);
        });
        this.eventSource.addEventListener("CLUB_APPROVAL", function (event) {
          let message = event.data;
          alert(message);
        });
        this.eventSource.addEventListener("COMPLAINT", function (event) {
          let message = event.data;
          alert(message);
        });
        this.eventSource.addEventListener("COMPLAINT_STATUS", function (event) {
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

    // connectSse(jwt) {
    //   alert(jwt);
    //   let subscribeUrl = "http://localhost:8000/api/v1/notify/subscribe";

    //   if (jwt != null) {
    //     let token = jwt;
    //     let eventSource = new EventSource(subscribeUrl + "?token=" + token);

    //     alert()
    //     eventSource.onmessage = function(event) {
    //     alert("새 알림:", event.data);
    // };
    //     // eventSource.addEventListener("connect", (e) => {
    //     //   const { data: receivedConnectData } = e;
    //     //   alert("connect event data: ", receivedConnectData); // "connected!"
    //     // });
    //     // eventSource.addEventListener('connect', function (event) {
    //     //   let message = event.data;
    //     //   alert(message);
    //     // });
    //     eventSource.addEventListener("UNSENT_MESSAGE", function (event) {
    //       let message = event.data;
    //       alert(message);
    //     });

    //     eventSource.addEventListener("error", function (event) {
    //       console.log(event);
    //       eventSource.close();
    //     });
    //   }
    // },
    async handleLogin() {
      // 공통 로그인 서비스 함수
      // 로그인 성공 =>
      // 로그인 실패 => 로그인 실패 공유함수 실행
      try {
        let response = await AuthService.login(this.user);
        console.log(response.data); // response.data == jwt, userId, 권한
        localStorage.setItem("user", JSON.stringify(response.data)); // 로칼호스트는 객체를 저장할 수 없기에 이걸 문자열로 바꿔서 진행해야한다.
        this.$store.commit("loginSuccess", response.data);
        this.connectSse(response.data.accessToken);
        if (this.$store.state.user.role == "ROLE_USER") {
          alert("로그인에 성공하였습니다.");
          this.$store.state.notifyCount = await NotifyService.countNotify(
            this.$store.state.user.userId
          );
          this.$router.push("/");
        } else if (this.$store.state.user.role == "ROLE_ADMIN") {
          alert("관리자 로그인에 성공하였습니다.");
          this.$router.push("/admin/home");
        } else {
          alert("회원가입 승인이 완료되지 않았습니다.");
        }
      } catch (e) {
        // 로그인 실패시 에러가 뜨므로 로그인 실패 공유함수를 실행
        alert("로그인 정보가 일치하지 않습니다.");
        this.$store.commit("loginFailure");
        console.log(e);
      }
    },
  },
  created() {
    // dept와 emp사이에서는 서로 접근이 불가능하여서(의존성을 낮추기 위해) 속성을 공유해서 사용할 수 없다. 그래서 풀옵스와 공유저장소를 이용해서 통신해야한다.
    // vue의 공유저장소인 vuex를 사용하자, 만약 vuex에 로그인이 true이면 로그인이 되어있는상태이므로 login을 할 필요가 없다. 그래서 강제로 home으로 이동시킨다.
    try {
      if (this.$store.state.loggedIn) {
        this.$router.push("/"); // 로그인이 되어있으므로 강제이동
      }
    } catch (error) {
      alert("error");
      console.log(error);
    }
    window.scrollTo(0, 0);
  },
};
</script>
<style>
.login-container {
  display: flex;
  height: auto;
  align-items: center;
  justify-content: center;
}

.login-form {
  flex: 1;
  padding: 20px;
}

.login-image {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

.login-image img {
  max-width: 75%;
  height: auto;
}

/* 스타일 기존 유지 */
#login-role {
  width: 483px;
}
#id {
  width: 483px;
  height: 65px;
}
#pwd {
  width: 483px;
  height: 65px;
}
#login-bt {
  width: 483px;
  height: 65px;
  background-color: #162b59;
}
#login-bt22 {
  color: #333;
}

#find-idpw {
  width: 483px;
  height: 65px;
  background-color: white;
  border-color: black;
  color: black;
}
#find-idpw:hover {
  background-color: #3639404e;
  color: #ffffff;
  border: none;
}
#naver-login-btn {
  width: 483px;
  height: 65px;
  background-color: white;
  border-color: black;
  color: black;
}
#kakao-login-btn {
  width: 483px;
  height: 65px;
  background-color: white;
  border-color: black;
  color: black;
  margin-bottom: 7vw;
}
.col-divider {
  border-right: 1px solid #ccc;
  padding-right: 0.7813vw;
}
.log {
  margin-top: 3.6458vw;
}
.join {
  margin-top: 3.6458vw;
}
.log-form {
  margin-left: 3.6458vw;
}
.join-form {
  margin-left: 3.6458vw;
}
.naver {
  color: #4cbb18;
  font-size: 20px;
}
.naver img {
  width: 25px !important;
  height: 30px !important;
}
.text-center {
  text-align: center;
}
.log-form {
  display: block;
  margin: 0 auto;
}
span:hover {
  text-decoration: underline;
}
</style>
