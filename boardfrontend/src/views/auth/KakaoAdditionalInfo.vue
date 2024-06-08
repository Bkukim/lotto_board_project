// 카카오 소셜 로그인 리다이렉트 페이지
<template>
  <div class="container mt-5" v-if="!UserExist">
    <div class="row">
      <div class="col"></div>
      <div class="col-10">
        <!-- 기본 정보 테이블 시작-->
        <table class="table">
          <thead>
            <tr>
              <td scope="col"><h3>기본 정보</h3></td>
              <td scope="col"></td>
              <td scope="col"></td>
              <td scope="col"></td>
            </tr>
          </thead>
          <tbody>
            <!-- 이름 tr -->
            <tr>
              <td scope="row">
                <label class="form-label" for="userName">이름</label>
              </td>
              <td>
                <input
                  class="form-control"
                  type="text"
                  name="userName"
                  v-model="user.userName"
                />
              </td>
              <td></td>
            </tr>

            <!-- email tr -->
            <tr>
              <td scope="row">
                <label class="form-label" for="email">email</label>
              </td>
              <td>
                <input
                  class="form-control"
                  type="email"
                  name="email"
                  v-model="user.email"
                  @input="validateEmail"
                />
              </td>
              <td>
                <p v-if="!emailIsValid">올바른 이메일 형식이 아닙니다.</p>
              </td>
            </tr>

            <!-- 부서 선택 tr -->
            <tr>
              <td scope="row">
                <label class="form-label" for="department">부서</label>
              </td>
              <td>
                <select
                  class="form-select"
                  aria-label="Default select example"
                  v-model="user.department"
                >
                  <option value="A0001">회계부</option>
                  <option selected value="B0002">재정부</option>
                  <option value="C0003">기획부</option>
                  <option selected value="D0004">홍보부</option>
                </select>
              </td>
              <td></td>
            </tr>
            <!-- 주소 tr -->
            <tr>
              <td scope="row">
                <label class="form-label" for="address">주소</label>
              </td>
              <td>
                <div class="row mb-1">
                  <!-- 우편번호 -->
                  <div class="col">
                    <input
                      class="form-control"
                      type="text"
                      v-model="postcode"
                      placeholder="우편번호"
                      disabled
                    />
                  </div>
                  <!-- 주소검색 버튼 -->
                  <div class="col">
                    <button
                      class="btn"
                      type="button"
                      @click="execDaumPostcode"
                      value="우편번호 찾기"
                      id="addressBtn"
                    >
                      주소 검색
                    </button>
                  </div>
                </div>
                <div class="row mb-1">
                  <div class="col">
                    <input
                      class="form-control"
                      type="text"
                      v-model="address"
                      placeholder="주소"
                      disabled
                    />
                  </div>
                </div>
                <div class="row mb-1">
                  <div class="col">
                    <input
                      class="form-control"
                      type="text"
                      v-model="extraAddress"
                      placeholder=""
                      disabled
                    />
                  </div>
                </div>
                <div class="row mb-1">
                  <div class="col">
                    <input
                      class="form-control"
                      type="text"
                      id="detailAddress"
                      placeholder="상세주소"
                      v-model="user.detailAddress"
                    />
                  </div>
                </div>
              </td>
              <td></td>
            </tr>
            <!-- 휴대폰 번호 tr -->
            <tr>
              <td scope="row">
                <label class="form-label" for="address">휴대폰 번호</label>
              </td>
              <td>
                <div class="row">
                  <div class="col-3">
                    <select
                      class="form-select"
                      aria-label="Default select example"
                      v-model="phoneNum.first"
                    >
                      <option selected value="010">010</option>
                      <option value="011">011</option>
                      <option value="016">016</option>
                      <option value="017">017</option>
                      <option value="018">018</option>
                      <option value="019">019</option>
                    </select>
                  </div>
                  _
                  <div class="col-3">
                    <input
                      class="form-control"
                      type="text"
                      name="call"
                      v-model="phoneNum.second"
                    />
                  </div>
                  _
                  <div class="col-3">
                    <input
                      class="form-control"
                      type="text"
                      name="call"
                      v-model="phoneNum.third"
                    />
                  </div>
                </div>
              </td>
              <td></td>
            </tr>
          </tbody>
        </table>
        <!-- 기본 정보 테이블 끝 -->
        <br />
        <br />
        <!-- 추가 정보 테이블 시작-->
        <table class="table">
          <thead>
            <!-- 생년월일 tr -->
            <tr>
              <td scope="row">
                <label class="form-label" for="userName">생년월일</label>
              </td>
              <td>
                <div class="col">
                  <input
                    class="form-control"
                    type="text"
                    name="pwd"
                    v-model="user.birthday"
                  />
                </div>
              </td>
              <td>주민번호 앞 6자리 ex ) 990115</td>
            </tr>
          </thead>
          <tbody></tbody>
        </table>
        <!-- 추가 정보 테이블 끝 -->
      </div>
      <div class="col"></div>
    </div>
  </div>

  <br />
  <br />
  <div class="container text-center">
    <div class="row justify-content-md-center">
      <div class="col-md-auto">
        <button
          class="text-light singUpBtn btn-sm mt-4"
          id=""
          type="button"
          @click="handleRegister"
        >
          회원가입
        </button>
      </div>
    </div>
  </div>
  <br />
  <br />
  <br />
  <br />
</template>

<script>
import AuthService from "@/services/auth/AuthService";
// import UserService from '@/services/user/UserService';
export default {
  data() {
    return {
      UserExist:false,

      confirmPassword: "", // 비밀번호 확인
      passwordMatchError: false, // 비밀번호 확인이 다르면 true

      // 비밀번호 정규화 확인 : 확인 되면 true
      isValid: false,
      // email 정규화 확인 : 확인 되면 true
      emailIsValid: false,


      // 주소검색 변수들
      postcode: "",
      address: "",
      extraAddress: "",

      phoneNum: {
        first: "", // 폰 번호 첫자리
        second: "",
        third: "",
      },
      callNum: {
        first: "", // 일반전화 첫 자리
        second: "",
        third: "",
      },
      user: {
        userId: "",
        password: "",
        userName: "",
        birthday: "",
        email: "",
        role: "",
        department: "",
        detailAddress: "",
      },
    };
  },
  methods: {
    // async checkUserExist(){
    //   let response = AuthService.existUserById()
    //   if (this.UserExist) {
        
    //   } else {
        
    
    // async kakaoLogin(code) {
    //   try {
    //     let response = await AuthService.socialLogin(code);
    //     let user = response.data;
    //     localStorage.setItem("user", JSON.stringify(user));
    //     this.$store.commit("loginSuccess", user);
    //     this.connectSse(response.data.accessToken);

    //     // this.$router.push("/");
    //   } catch (e) {
    //     console.log(e);
    //   }
    // },
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
        this.eventSource.addEventListener("UNSENT_MESSAGE", function (event) {
          let message = event.data;
          alert(message);
        });
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
    /* 회원가입 버튼 누르면 실행될 함수 */
    async handleRegister() {
      if (this.user.userName == "") {
        alert("이름을 입력해주세요");
        return;
      }
      if (this.user.email == "") {
        alert("email을 입력해주세요");
        return;
      }
      if (!this.emailIsValid) {
        alert("올바른 email형식이 아닙니다");
        return;
      }
      if (this.user.department == "") {
        alert("부서를 선택해주세요");
        return;
      }
      if (this.address == "") {
        alert("주소를 입력해주세요");
        return;
      }
      if (this.user.detailAddress == "") {
        alert("상세주소를 입력해주세요");
        return;
      }
      if (
        this.phoneNum.first == "" ||
        this.phoneNum.second == "" ||
        this.phoneNum.third == ""
      ) {
        alert("휴대전화번호를 입력해주세요");
        return;
      }
      if (this.user.birthday == "") {
        alert("생년월일을 입력해주세요");
        return;
      }
      let data = {
        userName: this.user.userName,
        birthday: this.user.birthday,
        phoneNum:
          this.phoneNum.first + this.phoneNum.second + this.phoneNum.third,
        // callNum: this.callNum.first + this.callNum.second + this.callNum.third,
        email: this.user.email,
        role: "ROLE_USER",
        deptId: this.user.department,
        normalAddress: this.address + "," + this.extraAddress,
        detailAddress: this.user.detailAddress,
      };
      try {
        let response = await AuthService.registerSocialUser(this.$route.params.userId, data);
        let user = response.data;
        localStorage.setItem("user", JSON.stringify(user));
        this.$store.commit("loginSuccess", user);
        this.connectSse(response.data.accessToken);
        this.$router.push("/");
      } catch (e) {
        // 공유 저장소의 register 실행함수 실행
        this.$store.commit("registerFailure");
        this.message = "에러 :" + e;
        console.log(e); // 에러 출력
      }
    },

    // email 정규화함수
    validateEmail() {
      // 이메일에 대한 정규식 패턴
      const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

      // 정규식 패턴과 이메일이 맞는지 확인
      this.emailIsValid = pattern.test(this.user.email);
    },

    /* 비밀번호 확인이 일치하지 않으면 메세지가 뜨게 할  */
    checkPasswordMatch: function () {
      if (this.user.password !== this.confirmPassword) {
        this.passwordMatchError = true;
        alert;
      } else {
        this.passwordMatchError = false;
      }
    },

    // 주소 api 함수
    execDaumPostcode() {
      console.log("진입");
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
          // 우편번호를 입력한다.
          this.postcode = data.zonecode;
        },
      }).open();
    },
    },
  mounted() {
   
    // console.log(code);
    // this.handleRegister(code);
    window.scrollTo(0, 0);
  },
};
</script>
<style>
#addressBtn {
  /* 주소 검색 버튼 */
  width: 100px;
  height: 37px;
  background-color: #162b59;
  color: white;
}
.singUpBtn {
  background-color: #515358;
  color: white;
  font-size: 20px;
  width: 200px;
  height: 50px;
  border: none;
}
.singUpBtn:hover {
  background-color: #162b59;
}
</style>
