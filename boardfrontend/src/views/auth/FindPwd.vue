<template>
  <div class="container">
    <h2 class="text-center">비밀번호 찾기</h2>
    <div class="container">
      <div class="row justify-content-md-center">
        <div class="col-md-8 col-12">
          <div class="mt-5" id="comment">
            <p>
              ＊회원가입시 입력한 비밀번호 확인 질문과 답변으로 비밀번호 찾기가
              가능합니다.<br />
              ＊회원 ID와, 비밀번호 확인 질문 및 답변을 입력해주세요.
            </p>
          </div>
          <div class="mt-5 shadow p-4 rounded" id="box">
            <div class="col-12 mb-3">
              <label class="form-label" for="role">회원 유형</label>
              <select class="form-select" v-model="role">
                <option selected value="ROLE_USER">회원</option>
                <option value="ROLE_ADMIN">관리자</option>
              </select>
            </div>
            <div class="col-12 mb-3">
              <label class="form-label" for="id">회원 ID</label>
              <input
                class="form-control"
                type="text"
                name="id"
                v-model="userId"
                placeholder="회원 ID를 입력하세요"
              />
            </div>
            <div class="col-12 mb-3">
              <label class="form-label" for="email">이메일 입력</label>
              <input
                class="form-control"
                type="text"
                name="email"
                v-model="email"
                placeholder="이메일을 입력하세요"
              />
            </div>
            <div class="mt-5 text-center" v-if="message">
              <h5>{{ message }}</h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container text-center" style="margin-top: -60px;">
    <div class="row justify-content-md-center">
      <div class="col-md-auto">
        <button
          class="btn btn-primary findPwdBtn"
          type="submit"
          @click="sendCode"
        >
          인증 코드 보내기
        </button>
      </div>
    </div>
  </div>

  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
</template>

<script>
import PasswordResetService from "@/services/user/PasswordResetService";

export default {
  data() {
    return {
      role: "ROLE_USER", // 기본값 설정
      userId: "",
      message: "",
      email: "",
    };
  },
  methods: {
    async sendCode() {
      try {
        await PasswordResetService.sendCode(this.email, this.userId);
        alert("인증 코드가 이메일로 전송되었습니다.");
        this.$router.push("/member/login");
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

<style scoped>
.container {
  max-width:1000px;
  margin: auto;
  padding-top: 50px;
  padding-bottom: 50px;
}

h2 {
  margin-bottom: 30px;
  font-weight: bold;
}

#comment p {
  font-size: 1rem;
  color: #666666;
}

#box {
  background-color: #ffffff;
}

.btn {
  font-size: 1.2rem;
  padding: 10px 20px;
}

.findPwdBtn {
  background-color: #162b59;
  color: white;
  border: none;
}

.btn-secondary {
  background-color: white;
  color: #162b59;
  border: 1px solid #cccccc;
}

.btn-secondary:hover {
  background-color: #cccccc;
}

.text-center h5 {
  color: #162b59;
  font-weight: bold;
}

.form-select, .form-control {
  height: 40px;
  font-size: 1rem;
}
</style>
