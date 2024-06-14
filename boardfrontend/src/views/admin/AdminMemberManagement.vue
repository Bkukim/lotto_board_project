<template>
  <div class="main-container d-flex" style="height: auto">
    <AdminHeaderCom />

    <div class="main-content">
      <div class="container text-center" id="fb_all">
        <h3 class="mb-5 mt-5">회원 관리</h3>

        <!-- 검색 박스 -->
        <div class="container text-center" style="gap: 5px" id="search_box">
          <div class="row">
            <div class="col">
              <!-- <button
            class="btn btn-primary dropdown-toggle"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
            id="search_ck"
          >
            
          </button> -->
              <!-- <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul> -->
            </div>
            <div class="col">
              <!-- 검색어를 입력하세요 -->
              <div class="input-group mb-3">
                <input
                  type="text"
                  class="form-control"
                  aria-label="Sizing example input"
                  aria-describedby="inputGroup-sizing-default"
                  placeholder="검색할 회원명을 입력하세요."
                  v-model="searchName"
                />
              </div>
            </div>
            <!-- 검색 버튼 -->
            <div class="col-auto">
              <!-- col-auto로 변경하여 너비를 자동 조정 -->
              <button
                class="btn btn-outline-secondary"
                type="button"
                id="button-search"
                @click="searchUserName"
              >
                검색
              </button>
            </div>
          </div>
        </div>
        <!-- 검색박스 끝 -->

        <!-- 테이블 -->
        <table class="table mt-5">
          <thead>
            <tr>
              <th scope="col">번호</th>
              <th scope="col">아이디</th>
              <th scope="col">이름</th>
              <th scope="col">생년월일</th>
              <th scope="col">이메일</th>
              <th scope="col">전화번호</th>
              <!-- <th scope="col">주소</th> -->
              <th scope="col">부서</th>
              <th scope="col">수정</th>
              <th scope="col">삭제</th>
            </tr>
          </thead>
          <tbody>
            <!-- 반복문 시작할 행 -->
            <tr v-for="(data, index) in userList" :key="index">
              <td>{{ (page - 1) * pageSize + index + 1 }}</td>
              <td>{{ data.userId }}</td>
              <td>{{ data.userName }}</td>
              <td>{{ data.birthday }}</td>
              <td>{{ data.email }}</td>
              <td>{{ data.phoneNum }}</td>
              <!-- <td>{{ data.normalAddress + " " + data.detailAddress }}</td> -->
              <td>{{ data.deptId }}</td>
              <td>
                <button
                  class="btn btn-outline-secondary"
                  type="button"
                  id="button-search"
                  data-bs-toggle="modal"
                  data-bs-target="#exampleModal"
                  data-bs-whatever="@mdo"
                  @click="getUser(data.userId)"
                >
                  수정
                </button>
                <!-- 회원 수정 모달 -->
                <div
                  class="modal fade"
                  id="exampleModal"
                  tabindex="-1"
                  aria-labelledby="exampleModalLabel"
                  aria-hidden="true"
                >
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">
                          회원수정
                        </h1>
                        <button
                          type="button"
                          class="btn-close"
                          data-bs-dismiss="modal"
                          aria-label="Close"
                        ></button>
                      </div>
                      <div class="modal-body">
                        <form>
                          <!-- 1. 아이디 -->
                          <div class="mb-3 row">
                            <label for="recipient-name" class="col-form-label text-start"
                              ><strong style="font-size: 18px;">아이디</strong></label
                            >
                            <input
                              type="text"
                              class="form-control"
                              id="recipient-name"
                              v-model="user.userId"
                            />
                          </div>
                          <!-- 2. 이름 -->
                          <div class="mb-3 row">
                            <label for="message-text" class="col-form-label text-start"
                              ><strong style="font-size: 18px;">이름</strong></label
                            >
                            <input
                              type="text"
                              class="form-control"
                              id="recipient-name"
                              v-model="user.userName"
                            />
                          </div>
                          <!-- 3. 생년월일 -->
                          <div class="mb-3 row">
                            <label for="message-text" class="col-form-label text-start"
                              ><strong style="font-size: 18px;">생년월일</strong></label
                            >
                            <input
                              type="text"
                              class="form-control"
                              id="recipient-name"
                              v-model="user.birthday"
                            />
                          </div>
                          <!-- 4. 이메일 -->
                          <div class="mb-3 row">
                            <label for="message-text" class="col-form-label text-start"
                              ><strong style="font-size: 18px;">이메일</strong></label
                            >
                            <input
                              type="text"
                              class="form-control"
                              id="recipient-name"
                              v-model="user.email"
                            />
                          </div>
                          <!-- 5. 전화번호 -->
                          <div class="mb-3 row">
                            <label for="message-text" class="col-form-label text-start"
                              ><strong style="font-size: 18px;">전화번호</strong></label
                            >
                            <input
                              type="text"
                              class="form-control"
                              id="recipient-name"
                              v-model="user.phoneNum"
                            />
                          </div>
                          <!-- 6. 기본 주소 -->
                          <!-- <div class="mb-3 row">
                            <label for="message-text" class="col-form-label text-start"
                              ><strong style="font-size: 18px;">기본 주소</strong></label
                            >
                            <input
                              type="text"
                              class="form-control"
                              id="recipient-name"
                              v-model="user.normalAddress"
                            />
                          </div> -->
                          <!-- 6. 상세 주소 -->
                          <!-- <div class="mb-3 row">
                            <label for="message-text" class="col-form-label text-start"
                              ><strong style="font-size: 18px;">상세 주소</strong></label
                            >
                            <input
                              type="text"
                              class="form-control"
                              id="recipient-name"
                              v-model="user.detailAddress"
                            />
                          </div> -->
                          <!-- 7. 부서 -->
                          <div class="mb-3 row">
                            <label for="message-text" class="col-form-label text-start"
                              ><strong style="font-size: 18px;">부서</strong></label
                            >
                            <select
                              class="form-select"
                              id="deptId"
                              v-model="user.deptId"
                            >
                              <option value="A0001">회계부</option>
                              <option value="B0002">재정부</option>
                              <option value="C0003">기획부</option>
                              <option value="D0004">홍보부</option>
                            </select>
                          </div>
                        </form>
                      </div>
                      <div class="modal-footer">
                        <button
                          type="button"
                          class="btn btn-secondary"
                          data-bs-dismiss="modal"
                        >
                          닫기
                        </button>
                        <button
                          type="button"
                          class="btn"
                          id="button-search"
                          @click="updateUser"
                        >
                          저장
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- 회원 수정 모달 끝 -->
              </td>
              <td>
                <button
                  class="btn btn-secondary"
                  type="button"
                  @click="deleteUser(data.userId)"
                >
                  삭제
                </button>
              </td>
            </tr>
          </tbody>
        </table>

        <!-- 페이징 -->
        <!-- {/* paging 시작 */} -->
        <div class="row justify-content-center mt-5">
          <div class="col-auto" style="margin-top: 50px">
            <b-pagination
              class="custom-pagination col-12 mb-3"
              v-model="page"
              :total-rows="count"
              :per-page="pageSize"
              @click="retrieveUser"
            ></b-pagination>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 자유게시판 중앙정렬 전체박스 끝 -->
</template>

<script>
import UserService from "@/services/user/UserService";
import AdminHeaderCom from "@/components/common/AdminHeaderCom.vue";
import AuthService from "@/services/auth/AuthService";

export default {
  components: {
    AdminHeaderCom,
  },
  data() {
    return {
      userList: [],
      user: {
        userId: "",
        userName: "",
        birthday: "",
        email: "",
        phoneNum: "",
        normalAddress: "",
        detailAddress: "",
        deptId: "",
      },
      searchName: "",

      page: 1, // 현재페이지번호
      count: 0, // 전체데이터개수
      pageSize: 10, // 1페이지당개수(select태그)
    };
  },
  methods: {
    // 유저정보 불러오는 함수
    async getUser(userId) {
      try {
        let response = await UserService.get(userId);
        console.log(response.data);
        this.user = response.data;
      } catch (e) {
        console.log(e);
      }
    },

    // 유저 정보 수정 함수
    async updateUser() {
      let data = {
        userId: this.user.userId,
        userName: this.user.userName,
        birthday: this.user.birthday,
        email: this.user.email,
        phoneNum: this.user.phoneNum,
        normalAddress: this.address + "," + this.extraAddress,
        detailAddress: this.user.detailAddress,
        deptId: this.deptId,
      };
      try {
        let response = await UserService.updateUser(data);
        console.log(response.data);
        alert("회원 정보가 수정되었습니다.");
        // this.closeModal(); // 모달 닫기
        this.retrieveUser(); // 사용자 정보 다시 불러오기
        // this.$router.push("/admin/member/manage");
      } catch (e) {
        alert("회원 정보 수정에 실패하였습니다.");
        console.log(e); // 에러 출력
      }
    },
    // // 모달을 닫는 메서드
    // closeModal() {
    //   this.showModal = false;
    // },
    // 회원 탈퇴 소프트 삭제 함수
    async deleteUser(userId) {
      try {
        let result = confirm("정말로 이 회원을 삭제 하시겠습니까?");
        if (result) {
          alert(userId)
          let response = await UserService.deleteUser(userId);
          console.log(response);
          AuthService.logout(); // LOCAL저장소에서 USER객체 삭제해주기
          this.$store.commit("logout"); //
          alert("회원 삭제가 완료되었습니다");
          // this.$router.push("/admin/home");
          this.retrieveUser();
        } else {
          return;
        }
      } catch (e) {
        console.log(e);
      }
    },

    // 전체조회 함수
    async retrieveUser() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await UserService.getAllUsersbyUserName(
          this.searchName, // 검색어
          this.page - 1, // 현재페이지번호-1
          this.pageSize // 1페이지당개수(size)
        );
        // TODO: 복습 : 2) 객체분할 할당
        const { userList, totalItems } = response.data; // 부서배열(벡엔드 전송)
        // TODO: 3) 바인딩변수(속성)에 저장
        this.userList = userList; // 부서배열(벡엔드 전송)
        this.count = totalItems; // 전체페이지수(벡엔드 전송)
        // TODO: 4) 프론트 로깅 : console.log
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // 검색 함수
    async searchUserName() {
      // alert("검색 함수 호출");
      await this.retrieveUser();
    },
  },
  mounted() {
    this.retrieveUser();
    this.getUser(this.user.userId);
    window.scrollTo(0, 0);
  },
};
</script>

<style>
.main-container {
  display: flex;
}

.sidebar {
  width: 350px; /* 사이드바의 고정 너비 */
}

.main-content {
  flex: 1; /* 남은 공간을 차지 */
  padding: 0 20px; /* 콘텐츠와 사이드바 사이의 간격 설정 */
}
/* 페이지 전체 높이 */
#fb_all {
  height: 100vw;
}
h3,
p {
  letter-spacing: -1px;
}

/* 페이징 번호 디자인 */
.custom-pagination .page-item.active .page-link {
  background-color: #162b59;
  border-color: #ffffff;
  color: white;
}

.custom-pagination .page-link {
  color: #162b59;
}

.custom-pagination .page-link:hover {
  background-color: #ffffff;
  border-color: 1px solid#8f8f8f;
  color: #162b59;
  /* border: none; */
}

.custom-pagination .page-link:focus {
  outline: none;
  box-shadow: 0 0 0 0.2rem #162b59;
  border-color: #162b59;
}

/* 검색버튼 */
.btn {
  margin: 0 2px; /* 버튼 간 간격을 줄이기 위해 여백을 조정 */
}

/* 검색 전체 배경 */
#search_box {
  background-color: #e2e2e28c;
  height: 75px;
  padding: 20px;
}
#search_ck {
  background-color: #ffffff;
  color: #212121;
  border: none;
}
#button-search,
#button-Writing {
  background-color: #162b59;
  color: #ffffff;
  border: none;
}

#button-reset {
  background-color: #162b59;
  color: #ffffff;
  border: none;
}
#router_hv:hover {
  text-decoration: underline 1px solid;
}
</style>
