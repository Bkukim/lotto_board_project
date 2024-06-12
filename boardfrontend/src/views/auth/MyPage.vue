<template>
  <div style="height: auto">
    <div>
      <div class="row">
        <!-- 왼쪽 사이드바 -->

        <div
          class="col-3 text-center text-lg-start sidebar"
          style="background-color: #162b59"
        >
          <div class="circle"></div>

          <p style="color: #fff; margin-top: 20px; font-size: 20px">
            {{ userId }}
          </p>

          <ul
            class="nav flex-column mt-3"
            style="
              gap: 50px;

              font-size: 20px;

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

        <!-- 오른쪽 화면 출력 -->
        <div class="col text-center">
          <!-- 1. 프로필 -->
          <template v-if="displayedContent === 'profile'">
            <h3 class="mb-5 mt-5">My Profile</h3>


            <div
              class="container"
              style="
                display: flex;
                justify-content: center;
                align-items: center;
              "
            >

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
                  이름:
                  <span style="font-weight: bold">{{ user.userName }}</span>
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
                  <span style="font-weight: bold">{{ user.phoneNum }}</span>

                </p>
              </div>
            </div>
          </template>

          <!-- 2. 작성한 글 -->
          <template v-else-if="displayedContent === 'writtenPosts'">
            <h3 class="mt-5">내가 작성한 글</h3>
            <div class="container" style="height: auto">
              <div class="row mt-5 border justify-content-center">

                <h3
                  style="
                    text-align: left;
                    font-size: 20px;
                    letter-spacing: -1.2px;
                  "
                >
                  자유게시판
                </h3>

                <div class="col-11">
                  <table class="table mt-5 text-center">
                    <thead>
                      <tr>
                        <th scope="col">제목</th>
                        <th scope="col">등록일</th>
                        <th scope="col">수정</th>
                        <th scope="col">삭제</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="(data, index) in freeBoardList" :key="index">
                        <td class="col-8">
                          <router-link
                            :to="'/free/free-boardDetail/' + data.freeBoardId"

                            style="text-decoration: none; color: #333"
                            class="alltext router-link-exact-active custom-pagination"
                            >{{ data.title }}</router-link
                          >
                        </td>
                        <td>{{ data.insertTime }}</td>
                        <td>
                          <button
                            type="button"
                            class="btn btn-success"
                            @click="goUpdateFreeBoard(data.freeBoardId)"
                          >
                            수정
                          </button>
                        </td>
                        <td>
                          <button
                            type="button"
                            class="btn btn-success"
                            @click="deleteFreeBoard(data.freeBoardId)"
                          >
                            삭제
                          </button>

                        </td>
                      </tr>
                    </tbody>
                  </table>
                  <div class="row justify-content-center mt-4">
                    <div class="col-auto">

                      <b-pagination
                        class="col-12 mb-3 custom-pagination"
                        v-model="freeBoardPage"
                        :total-rows="freeBoardCount"
                        :per-page="freeBoardPageSize"
                      ></b-pagination>

                    </div>
                  </div>
                </div>
              </div>

              <div class="row mt-3 border justify-content-center">

                <h3
                  style="
                    text-align: left;
                    font-size: 20px;
                    letter-spacing: -1.2px;
                  "
                >
                  부서게시판
                </h3>

                <div class="col-11">
                  <table class="table mt-5 text-center">
                    <thead>
                      <tr>
                        <th scope="col">제목</th>
                        <th scope="col">등록일</th>
                        <th scope="col">수정</th>
                        <th scope="col">삭제</th>
                      </tr>
                    </thead>
                    <tbody>

                      <tr v-for="(data, index) in deptBoardList" :key="index">
                        <td class="col-8">
                          <router-link
                            :to="'/dept/board/detail/' + data.deptBoardId"
                            style="text-decoration: none"
                            class="alltext router-link-exact-active custom-pagination"
                            >{{ data.title }}</router-link
                          >
                        </td>
                        <td>{{ data.insertTime }}</td>
                        <td>
                          <button
                            type="button"
                            class="btn btn-success"
                            @click="goUpdatedeptBoard(data.deptBoardId)"
                          >
                            수정
                          </button>
                        </td>
                        <td>
                          <button
                            type="button"
                            class="btn btn-success"
                            @click="deleteDeptBoard(data.deptBoardId)"
                          >
                            삭제
                          </button>

                        </td>
                      </tr>
                    </tbody>
                  </table>
                  <div class="row justify-content-center mt-4">
                    <div class="col-auto">

                      <b-pagination
                        class="col-12 mb-3 custom-pagination"
                        v-model="deptBoardPage"
                        :total-rows="deptBoardCount"
                        :per-page="deptBoardPageSize"
                      ></b-pagination>

                    </div>
                  </div>
                </div>
              </div>

              <!-- 건의게시판 공지사항 -->
              <div class="row mt-3 border justify-content-center">
                <h3
                  style="
                    text-align: left;
                    font-size: 20px;
                    letter-spacing: -1.2px;
                  "
                >
                  건의게시판
                </h3>
                <!-- 테이블시작, 작게 왼쪽 -->
                <div class="col-11">
                  <table class="table mt-5 text-center">
                    <thead>
                      <tr>
                        <th scope="col">제목</th>
                        <th scope="col">등록일</th>
                        <th scope="col">수정</th>
                        <th scope="col">삭제</th>
                      </tr>
                    </thead>
                    <tbody>
                      <!-- 반복문 시작할 행 -->
                      <tr
                        v-for="(data, index) in complaintBoardList"
                        :key="index"
                      >
                        <td class="col-8">
                          <router-link
                            :to="
                              '/complaint/complaint-boardDetail/' +
                              data.complaintBoardId
                            "
                            style="text-decoration: none"
                            class="alltext router-link-exact-active custom-pagination"
                            >{{ data.title }}</router-link
                          >
                        </td>
                        <td>{{ data.insertTime }}</td>
                        <td>
                          <button
                            type="button"
                            class="btn btn-success"
                            @click="
                              goUpdateComplaintBoard(data.complaintBoardId)
                            "
                          >
                            수정
                          </button>
                        </td>
                        <td>
                          <button
                            type="button"
                            class="btn btn-success"
                            @click="deleteComplaintBoard(data.complaintBoardId)"
                          >
                            삭제
                          </button>
                        </td>
                      </tr>
                      <!-- {/* paging 시작 */} -->
                      <!-- TODO: 1페이지당 화면에 보일 개수 조정(select태그) -->
                      <div class="row justify-content-center mt-4">
                        <div class="col-auto">
                          <b-pagination
                            class="col-12 mb-3 custom-pagination"
                            v-model="complaintBoardPage"
                            :total-rows="complaintBoardCount"
                            :per-page="complaintBoardPageSize"
                          ></b-pagination>
                        </div>
                      </div>
                    </tbody>
                  </table>
                </div>
              </div>

              <div class="row mt-3 border justify-content-center">
                <h3
                  style="
                    text-align: left;
                    font-size: 20px;
                    letter-spacing: -1.2px;
                  "
                >
                  동호회 게시판
                </h3>
                <div class="col-11">
                  <table class="table mt-5 text-center">
                    <thead>
                      <tr>
                        <th scope="col">제목</th>
                        <th scope="col">등록일</th>
                        <th scope="col">수정</th>
                        <th scope="col">삭제</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="(data, index) in clubBoardList" :key="index">

                        <td class="col-8">
                          <router-link

                            :to="'/complaint/complaint-boardDetail/' + data.complaintBoardId"

                            style="text-decoration: none"
                            class="alltext router-link-exact-active custom-pagination"
                            >{{ data.title }}</router-link
                          >
                        </td>
                        <td>{{ data.insertTime }}</td>
                        <td>
                          <button
                            type="button"
                            class="btn btn-success"

                            @click="
                              goUpdateComplaintBoard(data.complaintBoardId)
                            "

                          >
                            수정
                          </button>
                        </td>
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
                      <!-- {/* paging 시작 */} -->
                      <!-- TODO: 1페이지당 화면에 보일 개수 조정(select태그) -->
                      <div class="row justify-content-center mt-4">
                        <div class="col-auto">
                          <b-pagination
                            class="col-12 mb-3 custom-pagination"
                            v-model="page"
                            :total-rows="count"
                            :per-page="pageSize"
                            @click="retrieveComplaintBoardListUserId"
                          ></b-pagination>
                        </div>
                      </div>

                    </tbody>
                  </table>
                  <div class="row justify-content-center mt-4">
                    <div class="col-auto">

                      <b-pagination
                        class="col-12 mb-3 custom-pagination"
                        v-model="clubBoardPage"
                        :total-rows="clubBoardCount"
                        :per-page="clubBoardPageSize"
                      ></b-pagination>

                    </div>
                  </div>
                </div>
              </div>
            </div>
          </template>

          <!-- 3. 비밀번호 변경하기 -->
          <template v-else-if="displayedContent === 'ChangPassword'">
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
            <br />
            <br />
            <br />
          </template>

          <!-- 4. 회원탈퇴 -->
          <template v-else-if="displayedContent === 'withdrawal'">

            <!-- <div class="container withdrawal-container">
              <div
                class="card mt-5 container text-center shadow"
                style="width: 800px"
              >
                <h5
                  class="card-header"
                  style="font-size: 45px; font-weight: bold; color: #162b59"
                >
                  LOTTO COMPANY
                </h5>
                <div
                  class="card-body"
                  style="height: 500px; padding-top: 100px"
                >
                  <h5 class="card-title mb-3" style="font-weight: bold">
                    회원 탈퇴
                  </h5>
                  <p class="card-text" style="padding-top: 30px">
                    회원 탈퇴 시 계정정보 삭제 및 공지사항 확인이 불가합니다.
                    <br />
                    정말로 삭제하시겠습니까?
                  </p>
                  <a href="#" class="btn btn-primary" style="margin-top: 70px"
                    >탈퇴하기</a
                  >
                </div>
              </div>
            </div> -->

            <div class="withdrawal-container" style="height: 800px">
              <div class="withdrawal-box">
                <h5
                  class="card-header"
                  style="font-size: 45px; font-weight: bold; color: #162b59"
                >
                  LOTTO COMPANY
                </h5>
                <p class="card-text" style="padding-top: 30px">
                  회원 탈퇴 시 계정정보 삭제 및 공지사항 확인이 불가합니다.
                  <br />
                  정말로 삭제하시겠습니까?
                </p>
                <p>
                  사용자 ID:
                  <span style="font-weight: bold; text-align: left">{{
                    userId
                  }}</span>
                </p>

                <div>
                  <div class="row">
                    <label for="password" class="col-sm-4 col-form-label"
                      >비밀번호:</label
                    >
                    <div class="col-sm-8">
                      <input
                        type="password"
                        class="form-control"
                        id="password"
                        v-model="password"
                      />
                    </div>
                  </div>
                  <button
                    type="button"
                    class="btn btn-success mt-3"
                    @click="withdraw"
                    style="background-color: #162b59"
                  >
                    회원 탈퇴
                  </button>
                </div>
              </div>
            </div>

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
import FreeBoardService from "@/services/board/free/FreeBoardService";
import UserService from "@/services/user/UserService";

import ComplaintBoardService from "@/services/board/complaint/ComplaintBoardService";
import ClubBoardService from "@/services/board/club/ClubBoardService";
import DeptBoardService from "@/services/board/dept/DeptBoardService";

export default {
  data() {
    return {

      freeBoardList: [],
      complaintBoardList: [],
      clubBoardList: [],
      deptBoardList: [],


      userId: this.$store.state.user.userId,

      // 자유게시판 페이지네이션 상태
      freeBoardPage: 1,
      freeBoardCount: 0,
      freeBoardPageSize: 3,

      // 부서게시판 페이지네이션 상태

      deptBoardPage: 1,
      deptBoardCount: 0,
      deptBoardPageSize: 3,

      // 건의게시판 페이지네이션 상태
      complaintBoardPage: 1,
      complaintBoardCount: 0,
      complaintBoardPageSize: 3,


      // 동호회게시판 페이지네이션 상태
      clubBoardPage: 1,
      clubBoardCount: 0,
      clubBoardPageSize: 3,

      displayedContent: "profile",

      passwordMatchError: false,

      // 프로필에 사용 : user 객체 초기화
      user: {
        userName: "",
        phoneNum: "",
      },

      newPw: "",
      newPwCheck: "",
      result: false,
    };
  },

  methods: {
    // 회원 탈퇴 소프트 삭제 함수
    async withdraw(userId) {
      if (this.user.password !== this.confirmPassword) {
        alert("비밀번호가 일치하지 않습니다");
        return;
      }
      try {
        let result = confirm("정말로 탈퇴 하시겠습니까?");
        if (result) {
          let response = UserService.deleteUser(userId);
          console.log(response);
          AuthService.logout(); // LOCAL저장소에서 USER객체 삭제해주기
          this.$store.commit("logout"); //
          alert("회원 탈퇴가 완료되었습니다");
          this.$router.push("/");
        } else {
          return;
        }
      } catch (e) {
        console.log(e);
      }
    },

    // 유저 상세조회 : 프로필
    async findUserInfo(userId) {
      try {
        let response = await UserService.get(userId);
        this.user = response.data;
        console.log("유저", response.data);
      } catch (e) {
        alert("에러");
        console.log(e);
      }
    },


    // 1. 자유게시판 : 내가 쓴 글

    async retrieveFreeBoardListUserId() {
      try {
        let response = await FreeBoardService.getAllFreeBoardUserId(
          this.$store.state.user.userId,
          this.freeBoardPage - 1,
          this.freeBoardPageSize

        );
        const { freeBoardList, totalItems } = response.data;
        this.freeBoardList = freeBoardList;
        this.freeBoardCount = totalItems;

        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },


    // 1. 자유게시판 : 내가 쓴 글 삭제
    async deleteFreeBoard(freeBoardId) {
      try {
        if (confirm("정말로 삭제하시겠습니까?")) {
          let response = await FreeBoardService.deleteFreeBoard(freeBoardId);
          console.log(response.data);
          alert("게시글이 삭제되었습니다.");
          // 현재 페이지 다시 로그(삭제 작업 후 같은 페이지에서 업데이트된 내용 확인 가능)
          this.$router.go(0);
        } else {
          return;
        }
      } catch (e) {
        console.log(e);
      }
    },

    // 1. 자유게시판 : 내가 쓴 글 수정
    async goUpdateFreeBoard(freeBoardId) {
      this.$router.push(`/free/free-board/Update/` + freeBoardId);
    },

    // 2. 부서게시판 : 내가 쓴 글
    async retrieveDeptBoardListUserId() {
      try {
        let response = await DeptBoardService.getAllDeptBoardUserId(
          this.$store.state.user.userId,
          this.deptBoardPage - 1,
          this.deptBoardPageSize
        );
        const { deptBoardList, totalItems } = response.data;
        this.deptBoardList = deptBoardList;
        this.deptBoardCount = totalItems;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // 2. 부서게시판 : 내가 쓴 글 삭제
    async deleteDeptBoard(deptBoardId) {
      try {
        if (confirm("정말로 삭제하시겠습니까?")) {
          let response = await DeptBoardService.deleteDeptBoard(deptBoardId);
          console.log(response.data);
          alert("게시글이 삭제되었습니다.");
          // 현재 페이지 다시 로그(삭제 작업 후 같은 페이지에서 업데이트된 내용 확인 가능)
          this.$router.go(0);
        } else {
          return;
        }
      } catch (e) {
        console.log(e);
      }
    },

    // 2. 부서게시판 : 내가 쓴 글 수정
    async goUpdatedeptBoard(deptBoardId) {
      this.$router.push(`/dept/board/Update/` + deptBoardId);
    },

    // 3. 건의게시판 : 내가 쓴 글
    async retrieveComplaintBoardListUserId() {
      try {
        let response = await ComplaintBoardService.getAllComplaintBoardUserId(
          this.$store.state.user.userId,
          this.complaintBoardPage - 1,
          this.complaintBoardPageSize
        );
        const { complaintBoardList, totalItems } = response.data;
        this.complaintBoardList = complaintBoardList;
        this.complaintBoardCount = totalItems;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // 3. 건의게시판 : 내가 쓴 글 삭제

    async deleteComplaintBoard(complaintBoardId) {
      try {
        if (confirm("정말로 삭제하시겠습니까?")) {
          let response = await ComplaintBoardService.deleteComplaintBoard(
            complaintBoardId
          );
          // 로깅
          console.log(response.data);
          alert("게시글이 삭제되었습니다.");
          // 현재 페이지 다시 로그(삭제 작업 후 같은 페이지에서 업데이트된 내용 확인 가능)
          this.$router.go(0);
        } else {
          return;
        }
      } catch (e) {
        console.log(e);
      }
    },


    // 3. 건의게시판 : 내가 쓴 글 수정

    async goUpdateComplaintBoard(complaintBoardId) {
      this.$router.push(
        `/complaint/complaint-board/Update/` + complaintBoardId
      );
    },

    // 4. 동호회게시판 : 내가 쓴 글
    async retrieveClubBoardListUserId() {
      try {
        let response = await ClubBoardService.getAllClubBoardUserId(
          this.$store.state.user.userId,
          this.clubBoardPage - 1,
          this.clubBoardPageSize
        );
        const { clubBoardList, totalItems } = response.data;
        this.clubBoardList = clubBoardList;
        this.clubBoardCount = totalItems;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },


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
          let response = await AuthService.updatePw(
            this.$store.state.user.userId,
            data
          );
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
      } else {
        this.passwordMatchError = false;
      }
    },
    goLogin() {
      this.$router.push("/member/login");
    },
  },
  watch: {
    freeBoardPage() {
      this.retrieveFreeBoardListUserId();
    },

    deptBoardPage() {
      this.retrieveDeptBoardListUserId();
    },
    complaintBoardPage() {
      this.retrieveComplaintBoardListUserId();

    },
    clubBoardPage() {
      this.retrieveClubBoardListUserId();
    },
  },
  mounted() {
    window.scrollTo(0, 0);
    this.findUserInfo(this.$store.state.user.userId);
    this.retrieveFreeBoardListUserId();
    this.retrieveComplaintBoardListUserId();
    this.retrieveDeptBoardListUserId();
    this.retrieveClubBoardListUserId();
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

.withdrawal-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.withdrawal-box {
  background-color: #fff;
  padding: 20px;
  border-radius: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  width: 100%;
}
</style>
