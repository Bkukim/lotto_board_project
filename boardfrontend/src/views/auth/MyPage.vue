<template>
  <div style="height: auto">
    <div>
      <!-- <h2 class="text-center mb-5">My Page</h2> -->

      <div class="row">
        <!-- 왼쪽 사이드바 -->

        <div
          class="col-3 text-center text-lg-start sidebar"
          style="background-color: #162b59"
        >
          <!-- <div class="text-center"
            style="
              width: 200px;
              height: 200px;
              border-radius: 50%;
              background-color: #fff;
              text-align: center;
              margin: 50px 0 0 80px;
            "
          ></div> -->
          <div class="circle"></div>

          <p style="color: #fff; margin-top: 20px">ID: {{ userId }}</p>
          <ul
            class="nav flex-column mt-3"
            style="
              gap: 50px;
              font-size: 25px;
              /* border-right: 3px solid #162b59; */
              height: 900px;
              padding-top: 100px;
              text-align: center;
            "
          >
            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                style="border-bottom: 1px solid #cccccc; color: #fff"
                @click="showProfile"
                >프로필</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                style="border-bottom: 1px solid #cccccc; color: #fff"
                @click="showWrittenPosts"
                >작성한 글</a
              >
            </li>
            <!-- <li class="nav-item">
              <a
                class="nav-link"
                href="/member/new-pw"
                style="border-bottom: 1px solid #cccccc; color: #fff"
                >비밀번호 변경</a
              >
            </li> -->
            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                style="border-bottom: 1px solid #cccccc; color: #fff"
                @click="ChangPassword"
                >비밀번호 변경하기</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                style="border-bottom: 1px solid #cccccc; color: #fff"
                @click="showWithdrawal"
                >회원탈퇴</a
              >
            </li>
          </ul>
        </div>

        <!-- ------------------------------- -->
        <!-- 오른쪽 -->
        <!-- 화면 출력 -->
        <div class="col text-center">
          <!-- <div
            class="container text-center"
            style="background-color: #f2f2f2; height: 1000px"
          >
            <div
              class="container text-center"
              style="
                background-color: #fff;
                width: 500px;
                height: 700px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
                margin-top: 10px;
              "
            >
              엥
            </div>
          </div> -->
          <template v-if="displayedContent === 'profile'">
            <!-- 프로필 표시 -->
            <h3 class="mb-5 mt-5">My Profile</h3>

            <div class="container">
              <div
                class="row justify-content-md-center"
                style="
                  background-color: #f2f2f2;
                  text-align: center;
                  padding: 50px 0 50px 0;
                "
              >
                <p
                  style="
                    background-color: #fff;
                    padding: 10px;
                    margin-top: 10px;
                    width: 700px;
                    border-radius: 20px;
                  "
                >
                  사용자 ID: <span style="font-weight: bold">{{ userId }}</span>
                </p>

                <p
                  style="
                    background-color: #fff;
                    padding: 10px;
                    margin-top: 10px;
                    width: 700px;
                    border-radius: 20px;
                  "
                >
                  이름: <span style="font-weight: bold">{{ userName }}</span>
                </p>

                <p
                  style="
                    background-color: #fff;
                    padding: 10px;
                    margin-top: 10px;
                    width: 700px;
                    border-radius: 20px;
                  "
                >
                  휴대폰 번호:
                  <span style="font-weight: bold">{{ phoneNum }}</span>
                </p>
              </div>
            </div>

            <!-- <div
              class="container text-center"
              style="
                border: solid #cccccc 1px;
                width: 1000px;
                height: 300px;
                background-color: #e6e6e6;
                border: none;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                padding: 10px;
              "
            >
              <p style="background-color: #fff; padding: 10px; margin-top: 10px;">
                사용자 ID: <span style="font-weight: bold;">{{ userId }}</span> 
              </p>

              <p style="background-color: #fff; padding: 10px; margin-top: 10px;">
                사용자 이름: {{ userName }}
              </p>

              <p style="background-color: #fff; padding: 10px; margin-top: 10px;">
                사용자 폰번호: {{ phoneNum }}
              </p>
 
            </div> -->

            <!-- 프로필 내용 -->
          </template>
          <template v-else-if="displayedContent === 'writtenPosts'">
            <!-- 작성한 글 표시 -->
            <h3 class="mt-5">내가 작성한 글</h3>

            <div class="container" style="height: 1700px">
   
              <!-- 테이블 옆 버튼 3개 -->
              <!-- <div class="col-12 d-flex align-items-start justify-content-end">
                <div class="d-flex flex-column">
                  <button
                    type="button"
                    class="btn btn-primary mb-2"
                    @click="goNoticeAdd"
                  >
                    공지사항 등록
                  </button>
                  <button type="button" class="btn btn-success">
                    공지사항 전체삭제
                  </button>
                </div>
              </div> -->
              <!-- 게시판별 공지사항  div 총 3개 -->
              <!--자유게시판 공지사항  -->
              <div class="row mt-5 border justify-content-center">
                <h3 style="text-align: left; font-size: 20px; letter-spacing: -1.2px;">자유게시판</h3>
                <!-- 테이블시작, 작게 왼쪽 -->
                <div class="col-11">
                  <table class="table mt-5 text-center">
                    <thead>
                      <tr>
                        <th scope="col">번호</th>
                        <th scope="col">유형</th>
                        <th scope="col">제목</th>
                        <th scope="col">등록일</th>
                        <th scope="col">삭제</th>
                      </tr>
                    </thead>
                    <tbody>
                      <!-- 반복문 시작할 행 -->
                      <tr v-for="(data, index) in noticeFree" :key="index">
                        <td>
                          {{ index + 1 }}
                        </td>
                        <td >{{ data.noticeType }}</td>
                        <td>{{ data.title }}</td>
                        <td>{{ data.insertTime }}</td>
                        <td>
                          <button
                            type="button"
                            class="btn btn-success"
                            @click="deleteNotice(data.noticeId)"
                          >
                            삭제
                          </button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>

              <!-- 부서게시판 공지사항 -->
              <div class="row mt-3 border justify-content-center">
                <h3 style="text-align: left; font-size: 20px; letter-spacing: -1.2px;">부서게시판</h3>
                <!-- 테이블시작, 작게 왼쪽 -->
                <div class="col-11">
                  <table class="table mt-5 text-center">
                    <thead>
                      <tr>
                        <th scope="col">번호</th>
                        <th scope="col">유형</th>
                        <th scope="col">제목</th>
                        <th scope="col">등록일</th>
                        <th scope="col">삭제</th>
                      </tr>
                    </thead>
                    <tbody>
                      <!-- 반복문 시작할 행 -->
                      <tr v-for="(data, index) in noticeDept" :key="index">
                        <td>
                          {{ index + 1 }}
                        </td>
                        <!-- dto에 없어서 noticeType이 안나왔던것 안나오면 dto한번더 확인 -->
                        <td>{{ data.noticeType }}</td>
                        <td>{{ data.title }}</td>
                        <td>{{ data.insertTime }}</td>
                        <td>
                          <button
                            type="button"
                            class="btn btn-success"
                            @click="deleteNotice(data.noticeId)"
                          >
                            삭제
                          </button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>

              <!-- 건의게시판 공지사항 -->
              <div class="row mt-3 border justify-content-center">
                <h3 style="text-align: left; font-size: 20px; letter-spacing: -1.2px;">건의게시판</h3>
                <!-- 테이블시작, 작게 왼쪽 -->
                <div class="col-11">
                  <table class="table mt-5 text-center">
                    <thead>
                      <tr>
                        <th scope="col">번호</th>
                        <th scope="col">유형</th>
                        <th scope="col">제목</th>
                        <th scope="col">등록일</th>
                        <th scope="col">삭제</th>
                      </tr>
                    </thead>
                    <tbody>
                      <!-- 반복문 시작할 행 -->
                      <tr v-for="(data, index) in noticeComplaint" :key="index">
                        <td>
                          {{ index + 1 }}
                        </td>
                        <!-- dto에 없어서 noticeType이 안나왔던것 안나오면 dto한번더 확인 -->
                        <td>{{ data.noticeType }}</td>
                        <td>{{ data.title }}</td>
                        <td>{{ data.insertTime }}</td>
                        <td>
                          <button
                            type="button"
                            class="btn btn-success"
                            @click="deleteNotice(data.noticeId)"
                          >
                            삭제
                          </button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>

              <!-- 동호회 게시판 공지사항 -->
              <div class="row mt-3 border justify-content-center">
                <h3 style="text-align: left; font-size: 20px; letter-spacing: -1.2px;">동호회 게시판</h3>
                <!-- 테이블시작, 작게 왼쪽 -->
                <div class="col-11">
                  <table class="table mt-5 text-center">
                    <thead>
                      <tr>
                        <th scope="col">번호</th>
                        <th scope="col">유형</th>
                        <th scope="col">제목</th>
                        <th scope="col">등록일</th>
                        <th scope="col">삭제</th>
                      </tr>
                    </thead>
                    <tbody>
                      <!-- 반복문 시작할 행 -->
                      <tr v-for="(data, index) in noticeGroup" :key="index">
                        <td>
                          {{ index + 1 }}
                        </td>
                        <!-- dto에 없어서 noticeType이 안나왔던것 안나오면 dto한번더 확인 -->
                        <td>{{ data.noticeType }}</td>
                        <td>{{ data.title }}</td>
                        <td>{{ data.insertTime }}</td>
                        <td>
                          <button
                            type="button"
                            class="btn btn-success"
                            @click="deleteNotice(data.noticeId)"
                          >
                            삭제
                          </button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
                            <!-- 페이징 -->
              <!-- {/* paging 시작 */} -->
              <!-- TODO: 1페이지당 화면에 보일 개수 조정(select태그) -->
              <div class="row justify-content-center mt-4">
                <div class="col-auto">
                  <b-pagination
                    class="col-12 mb-3 custom-pagination"
                    v-model="page"
                    :total-rows="count"
                    :per-page="pageSize"
                    @click="retrieveQnaUserId"
                  ></b-pagination>
                </div>
              </div>
            </div>

            
            <!-- <div class="row" style="gap: 20px;">
            <button class="col">
              자유 게시판 <br>내가 쓴 글 보러가기
            </button>

            <button class="col">
              자유 게시판 <br>내가 쓴 글 보러가기
            </button>

            <button class="col">
              자유 게시판 <br>내가 쓴 글 보러가기
            </button>

            <button class="col">
              자유 게시판 <br>내가 쓴 글 보러가기
            </button>

          </div> -->
            <!-- 작성한 글 목록 -->
          </template>
          <template v-else-if="displayedContent === 'ChangPassword'">
            <!-- 비밀번호 변경 표시 -->
            <!-- <h3>비번번경</h3> -->
            <div class="container">
              <br />
              <br />
              <br />

              <h2 class="text-center mb-5" style="letter-spacing: -1.5px">
                새로운 비밀번호 설정
              </h2>
              <div class="container" style="background-color: #f2f2f2">
                <div class="row justify-content-md-center" v-if="result">
                  <div class="col-8">
                    <div
                      class="mt-5 text-center"
                      id="box"
                      style="height: 300px"
                    >
                      <div style="margin-top: 70px">
                        <h3>비밀번호가 성공적으로 변경되었습니다.</h3>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row justify-content-md-center" v-else>
                  <div class="col-8">
                    <div class="mt-5" id="comment">
                      <p style="text-align: left">
                        ＊새로운 비밀번호를 입력하세요.<br />
                        ＊영문 대소문자/숫자/특수문자 중 3가지 이상 조합,
                        8자~16자 이상으로 입력하세요.
                      </p>
                    </div>
                    <div class="mt-5" id="box">
                      <div class="mt-4" style="font-size: 20px">
                        <label class="form-label">새로운 비밀번호</label>
                        <input
                          class="form-control"
                          type="password"
                          name="id"
                          v-model="newPw"
                          style="height: 50px"
                          @input="checkPasswordMatch"
                        />
                      </div>

                      <div style="margin-top: 70px; font-size: 20px">
                        <label class="form-label">새로운 비밀번호 확인</label>
                        <input
                          class="form-control"
                          type="password"
                          name="pwdAskCheck"
                          v-model="newPwCheck"
                          style="height: 50px; margin-bottom: 50px"
                          @input="checkPasswordMatch"
                        />
                      </div>
                      <div class="mt-5 text-center">
                        <p v-if="passwordMatchError" style="color: red">
                          비밀번호가 일치하지 않습니다.
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-3"></div>
            </div>
            <br />
            <br />
            <div class="container text-center" v-if="result">
              <div class="row justify-content-md-center">
                <div class="col-md-auto">
                  <button
                    class="text-light findPwdBtn btn-sm mt-4"
                    id=""
                    type="submit"
                    @click="goLogin"
                  >
                    로그인 하러 가기
                  </button>
                </div>
              </div>
            </div>
            <div class="container text-center" v-else>
              <div class="row justify-content-md-center">
                <div class="col-md-auto">
                  <button
                    class="text-light findPwdBtn btn-sm"
                    id=""
                    type="submit"
                    @click="updatePw"
                    style="
                      background-color: #162b59;
                      border: none;
                      padding: 10px 20px;
                    "
                  >
                    확인
                  </button>
                </div>
              </div>
            </div>
            <br />
            <br />
            <br />
            <br />
            <br />
            <!-- <div class="container text-center">
    <div class="row justify-content-md-center">
      <div class="col-md-auto">
        <button
          class="text-light FindIdBtn btn-sm mt-4"
          id=""
          type="submit"
          @click="findId"
        >
          확인
        </button>
      </div>
      <div class="col-md-auto">
        <button
          class="text-light btn-sm mt-4"
          id="go-login"
          type="submit"
          @click="goLogin"
         style="border: 1px solid #cccccc;"
        >
          <p>로그인</p>
        </button>
      </div>
    </div>
  </div> -->
            <br />
            <br />
            <br />
          </template>
          <template v-else-if="displayedContent === 'withdrawal'">
            <!-- 회원탈퇴 표시 -->
            <h3>회원탈퇴 화면</h3>
          </template>
        </div>
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
import AuthService from "@/services/auth/AuthService";

export default {
  data() {
    return {
      // displayedContent: "",
      displayedContent: "profile", // 현재 화면에 표시할 내용을 저장하는 변수

      // 비밀번호 확인이 같으면 true
      passwordMatchError: false,

      userId: this.$store.state.user.userId,
      userName: this.$store.state.user.userName,
      phoneNum: this.$store.state.user.phoneNum,

      newPw: "",
      newPwCheck: "",
      result: false,
    };
  },

  methods: {
    // 프로필 표시 메소드
    showProfile() {
      this.displayedContent = "profile";
    },
    // 작성한 글 표시 메소드
    showWrittenPosts() {
      this.displayedContent = "writtenPosts";
    },
    // 비밀번호 변경 글 표시 메소드
    ChangPassword() {
      this.displayedContent = "ChangPassword";
    },
    // 회원탈퇴 표시 메소드
    showWithdrawal() {
      this.displayedContent = "withdrawal";
    },

    async updatePw() {
      try {
        let data = {
          userId: this.userId,
          newPw: this.newPw,
        };
        console.log(data);
        if (this.newPw == this.newPwCheck) {
          // 비밀번호 확인이 같을때만 실행
          let response = await AuthService.updatePw(
            this.$store.state.user.userId,
            data
          ); // 수정이 되면  true를 반환하고 안되면 false를 반환
          if (response.data) {
            this.$store.state.user.userId = "";
            this.result = true;
          } else {
            alert("세션이 만료되었습니다. 로그인 창으로 이동합니다.");
            this.$router.push("/member/login");
          }
        } else {
          alert("비밀번호가 일치하지 않습니다.");
        }
      } catch (e) {
        console.log(e);
      }
    },
    checkPasswordMatch: function () {
      if (this.newPw !== this.newPwCheck) {
        this.passwordMatchError = true;
        this.passwordMatchError = true;
      } else {
        this.passwordMatchError = false;
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

<style>
.nav-link:hover {
  font-weight: bold;
}
.sidebar {
  background-color: #162b59;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 900px;
}

.circle {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  background-color: #fff;
  margin-top: 50px;
  min-width: 200px;
  min-height: 200px;
  flex-direction: column;
  align-items: center;
}
</style>