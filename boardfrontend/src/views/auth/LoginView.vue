
<template>
  <div class="container mt-3">
    <div>
      <h3 class="mb-4 mt-5">로그인</h3>
      <hr />
      <!-- 로그인 회원가입 라인 -->
      <div class="row">
        <!-- 로그인 시작 -->
        <div class="col log col-divider">
          <h5 class="mb-4 mt-4 log-form"><strong>회원 로그인</strong></h5>

          <div class="mb-3">
            <label class="insert-id log-form mb-2" for="id">아이디</label>
            <input
              class="form-control log-form"
              type="text"
              placeholder="ID"
              name="id"
              id="id"
              v-model="user.userId"
            />
          </div>
          <div class="mb-3">
            <label class="form-label log-form" for="pwd">비밀번호</label>
            <input
              class="form-control log-form"
              type="password"
              placeholder="PASSWORD"
              name="pwd"
              id="pwd"
              v-model="user.password"
            />
          </div>

          <div class="mb-3">
            <button
              class="btn text-light btn-sm mt-4 log-form"
              id="login-bt"
              type="submit"
              @click="handleLogin"
            >
              로그인
            </button>
          </div>
        </div>
        <!-- 회원가입 시작 -->
        <div class="col join">
          <h5 class="mb-1 mt-4 join-form"><strong>회원가입</strong></h5>

          <div class="mb-3 join-form">
            <button
              class="btn text-light btn-sm mt-4"
              id="login-bt"
              type="submit"
              @click="goJoin"
            >
              회원가입
            </button>
            <div class="mb-4">
              <h5 class="mt-4"><strong>ID/PW 찾기</strong></h5>
            </div>
          </div>
          <div class="join-form">
            <button
              class="btn btn-sm mb-3"
              id="find-idpw"
              type="button"
              @click="goFindId"
            >
              아이디 찾기
            </button>
            <button
              class="btn btn-sm mt-4 mb-3"
              id="find-idpw"
              type="button"
              @click="goFindPwd"
            >
              비밀번호 찾기
            </button>
          </div>
        </div>
      </div>
      <!-- 소셜 로그인 : 네이버 -->
      <div class="row text-center mt-5">
        <div>
          <!-- <button
            class="btn btn-custom naver mt-4 mb-3"
            id="naver-login-btn"
            type="submit"
          >
            <img src="@/assets/img/N.png" /> &nbsp;&nbsp; 네이버 로그인 /
            회원가입
          </button> -->
          <img src="@/assets/img/btnG_완성형.png" style="width:300px; height:auto;"/>
        </div>
      </div>
      <br>
      <!-- 소셜 로그인 : 카카오 -->
      <div class="row text-center">
        <div>
           <img src="@/assets/img/kakao_login_medium_wide.png" style="width: 300px; height:auto;" @click="goToKakaoAuth"/>
          <!-- <div v-if="user">
            <h2>{{user.kakao_account.profile.nickname}}님 환영합니다!</h2>
            </div> -->
        </div>
        <br>
        <br>
        <br>
      </div>
    </div>
  </div>
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
export default {
  data() {
    return {
      user: {
        role: "",
        userId: "",   // 로그인 ID
        password: "",
      },
    };
  },
  methods: {
    // 카카오 로그인
   goToKakaoAuth() {
      const client_id = 	"6a9b8daaeef2609b3db2849d027f6080";         // Rest API 키
      const redirect_uri = 	"http://localhost:8080/auth-redirect";   // Redirect URI
      const kakaoAuthUrl = `https://kauth.kakao.com/oauth/authorize?client_id=${client_id}&redirect_uri=${redirect_uri}&response_type=code`; // response_type=code는 고정
      window.location.href = kakaoAuthUrl;  // 이 페이지는 카카오에서 제공하는 페이지라 따로 페이지 만들 필요 없음
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

    async handleLogin() {
      // 공통 로그인 서비스 함수
      // 로그인 성공 =>
      // 로그인 실패 => 로그인 실패 공유함수 실행
      try {
        let response = await AuthService.login(this.user);
        console.log(response.data); // response.data == jwt, userId, 권한
        localStorage.setItem("user", JSON.stringify(response.data)); // 로칼호스트는 객체를 저장할 수 없기에 이걸 문자열로 바꿔서 진행해야한다.
        this.$store.commit("loginSuccess", response.data);

        if (this.$store.state.user.role == "ROLE_USER") {
          alert("로그인에 성공하였습니다.")
          this.$router.push("/");
        } else if (this.$store.state.user.role == "ROLE_ADMIN") {
          alert("관리자 로그인에 성공하였습니다.")
          this.$router.push("/shop/admin/order");
        }
      } catch (e) {
        // 로그인 실패시 에러가 뜨므로 로그인 실패 공유함수를 실행
        alert("로그인 정보가 일치하지 않습니다.");
        this.$store.commit("loginFailure"); // 공유함수의 mutation함수는commit으로 실행한다.
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
/* #naver-login-btn:hover{
    background-color: #4cbb18;
} */
#kakao-login-btn {
  width: 483px;
  height: 65px;
  background-color: white;
  border-color: black;
  color: black;
  margin-bottom: 7vw;
}
.col-divider {
  border-right: 1px solid #ccc; /* 수직선 스타일 설정 */
  padding-right: 0.7813vw; /* 선택적으로 간격을 추가할 수 있습니다. */
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
  width: 25px !important; /* 이미지의 너비를 원하는 크기로 조절합니다. */
  height: 30px !important;
}
</style>
  

