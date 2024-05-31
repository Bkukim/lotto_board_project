<template>
  <div class="container">
    <br />
    <br />
    <br />
    <br />
    <br />
    <h2 class="text-center">비밀번호 찾기</h2>
    <div class="container">
      <div class="row justify-content-md-center">
        <div class="col-8">
          <div class="mt-5" id="comment">
            <p>
              ＊회원가입시 입력한 비밀번호 확인 질문과 답변으로 비밀번호 찾기가
              가능합니다.<br />
              ＊회원 ID와, 비밀번호 확인 질문 및 답변을 입력해주세요.
            </p>
          </div>
          <div class="mt-5" id="box">
            <div class="col-11">
              <label class="form-label" for="role">회원 유형</label>
              <select
                class="form-select"
                aria-label="Default select example"
                v-model="role"
              >
                <option selected value="ROLE_USER">회원</option>
                <option value="ROLE_ADMIN">관리자</option>
              </select>
            </div>
            <div class="mt-4 col-11">
              <label class="form-label" for="id">회원 ID</label>
              <input
                class="form-control"
                type="text"
                name="id"
                v-model="userId"
                placeholder="회원 ID를 입력하세요"
              />
            </div>
            <!-- <div class="mt-4 col-11">
              <label class="form-label" for="address">비밀번호 확인 질문</label>
              <select
                class="form-select"
                aria-label="Default select example"
                v-model="pwQuestion"
              >
                <option selected value="자신이 가장 존경하는 인물은">
                  자신이 가장 존경하는 인물은?
                </option>
                <option value="기억에 남는 추억의 장소는">
                  기억에 남는 추억의 장소는?
                </option>
                <option value="자신의 인생 좌우명은">
                  자신의 인생 좌우명은?
                </option>
                <option value="인상깊게 읽은 책 이름은">
                  인상깊게 읽은 책 이름은?
                </option>
              </select>
            </div>
            <div class="mt-4 col-11">
              <label class="form-label" for="pwdAskCheck"
                >비밀번호 질문 확인</label
              >
              <input
                class="form-control"
                type="text"
                name="pwdAskCheck"
                v-model="pwAnswer"
              />
            </div> -->
            <div class="mt-4 col-11">
              <label class="form-label" for="pwdAskCheck">이메일 입력</label>
              <input
                class="form-control"
                type="text"
                name="pwdAskCheck"
                v-model="email"
                placeholder="이메일을 입력하세요"
              />
            </div>
            <!-- <div class="mt-4 col-11">
              <label class="form-label" for="pwdAskCheck">인증코드 입력</label>
              <br>
              <input 
                type="text"
                v-model="code" 
                placeholder="인증코드를 입력하세요" />
              <button @click="verifyCode">코드 확인</button>
            </div> -->
            <div class="mt-5 text-center" v-if="message">
              <h5>{{ message }}</h5>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-3"></div>
  </div>
  <br />
  <br />

  <div class="container text-center">
    <div class="row justify-content-md-center">
      <div class="col-md-auto">
        <button
          class="text-light findPwdBtn btn-sm mt-4"
          id=""
          type="submit"
          @click="sendCode"
        >
          인증 코드 보내기
        </button>
      </div>
    </div>
  </div>
  <br />
  <br />
  <br />
</template>
<script>
// import AuthService from "@/services/auth/AuthService";
import PasswordResetService from "@/services/user/PasswordResetService";

export default {
  data() {
    return {
      role: "",
      userId: "",
      // pwQuestion: "",
      // pwAnswer: "",
      message: "",
      email: "",
    };
  },
  methods: {
    // async findPw() {
    //   console.log(this.role,
    //       this.userId,
    //       this.pwQuestion,
    //       this.pwAnswer);
    //   try {
    //     let response = await AuthService.getForPw(
    //       this.role,
    //       this.userId,
    //       this.email
    //     );
    //     if (response.data) {
    //       this.$store.state.userId = this.userId;
    //       this.$router.push("/member/new-pw")
    //     } else {
    //       this.message =
    //         "존재하지 않는 회원이거나 \n" + "비밀번호 질문확인이 올바르지 않습니다.";
    //     }
    //   } catch (e) {
    //     alert("회원정보를 입력해주세요")
    //     console.log(e);
    //   }
    // },
    async sendCode() {
      try {
        await PasswordResetService.sendCode(this.role, this.email, this.userId);
        alert("인증 코드가 이메일로 전송되었습니다.");
      } catch (error) {
        console.log(error);
        alert("인증 코드를 보내는 데 실패했습니다.");
      }
    },
  },
  mounted() {
    window.scrollTo(0, 0);
  },
};
</script>

<style>
#box {
  border: 1px solid #cccccc;
  padding: 60px 100px 60px 80px;
}
.findPwdBtn {
  background-color: #162b59;
  color: white;
  font-size: 20px;
  width: 200px;
  height: 50px;
  border: none;
}
</style>
