<template>
  <div class="container">
    <h2 class="text-center">아이디 찾기</h2>
    <div class="container">
      <div class="row justify-content-md-center">
        <div class="col-md-8 col-12">
          <div class="mt-5" id="comment">
            <p>
              ＊휴대폰 번호를 입력하여 아이디 찾기가 가능합니다.<br />
              ＊회원 유형과 이름, 휴대폰 번호를 입력해주세요.
            </p>
          </div>
          <div class="mt-5 shadow p-4 rounded" id="box">
            <div class="col-12 mb-3">
              <p>회원 유형</p>
              <select class="form-select" v-model="role">
                <option selected value="ROLE_USER">회원</option>
                <option value="ROLE_ADMIN">관리자</option>
              </select>
            </div>
            <div class="col-12 mb-3">
              <label class="form-label" for="userName">이름</label>
              <input class="form-control" type="text" name="userName" v-model="userName" />
            </div>
            <div class="col-12 mb-3">
              <label class="form-label" for="address">휴대폰 번호</label>
              <div class="d-flex">
                <div class="me-2">
                  <select class="form-select" v-model="phoneNum.first">
                    <option selected>010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="017">017</option>
                    <option value="018">018</option>
                    <option value="019">019</option>
                  </select>
                </div>
                <div class="me-2">
                  <input class="form-control" type="text" v-model="phoneNum.second" />
                </div>
                <div>
                  <input class="form-control" type="text" v-model="phoneNum.third" />
                </div>
              </div>
            </div>
            <div class="mt-5 text-center">
              <h4 style="font-size: 17px;">{{ message }}</h4>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container text-center mt-4">
      <div class="row justify-content-md-center">
        <div class="col-md-auto">
          <button class="btn btn-primary FindIdBtn" @click="findId">확인</button>
        </div>
        <div class="col-md-auto">
          <button class="btn btn-secondary" @click="goLogin">로그인</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AuthService from '@/services/auth/AuthService';

export default {
  data() {
    return {
      phoneNum: {
        first: "010", // 폰 번호 첫자리 기본값
        second: "",
        third: "",
      },
      userName: "",
      role: "ROLE_USER", // 기본값 설정

      // 결과가 나오면 id를, 결과가 없으면 "존재하지 않는 회원입니다."를 보여줄 메세지
      message: "",
    };
  },
  methods: {
    async findId() {
      try {
        let data = {
          role: this.role,
          userName: this.userName,
          phoneNum:
            this.phoneNum.first + this.phoneNum.second + this.phoneNum.third,
        };
        let response = await AuthService.findId(
          data.role,
          data.userName,
          data.phoneNum
        );
        console.log(response.data);
        if (!response.data) {
          this.message = "존재하지 않는 회원입니다!";
        } else {
          this.message = `"${response.data.userName}"님의 회원 ID는 "${response.data.userId}" 입니다.`;
        }
      } catch (e) {
        alert("회원 정보를 입력해주세요");
        console.log(e);
      }
    },
    goLogin() {
      this.$router.push("/member/login");
    },
  },
  mounted() {
    window.scrollTo(0, 0);
  },
};
</script>

<style scoped>
.container {
  max-width: 900px;
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

.FindIdBtn {
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

.text-center h4 {
  color: #162b59;
  font-weight: bold;
}

.form-select, .form-control {
  height: 40px;
  font-size: 1rem;
}
</style>
