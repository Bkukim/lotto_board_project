<template>
  <div class="main-container">
    <AdminHeaderCom />

    <div class="main-content">
      <h2 class="text-center mt-5 mb-5">회원 관리</h2>
      <!-- 검색 박스 -->
      <div class="search-box container text-center">
        <div class="row">
          <div class="col">
            <!-- 검색어 입력 -->
            <div class="input-group mb-3">
              <input
                type="text"
                class="form-control"
                placeholder="검색할 회원명을 입력하세요."
                v-model="searchName"
              />
            </div>
          </div>
          <div class="col-auto">
            <!-- 검색 버튼 -->
            <button
              class="btn btn-primary"
              type="button"
              @click="searchUserName"
              style="margin-left: -600px"
            >
              검색
            </button>
          </div>
        </div>
      </div>
      <!-- 검색박스 끝 -->

      <!-- 테이블 -->
      <table class="table table-hover mt-5">
        <thead>
          <tr>
            <th scope="col">번호</th>
            <th scope="col">아이디</th>
            <th scope="col">권한</th>
            <th scope="col">이름</th>
            <th scope="col">생년월일</th>
            <th scope="col">이메일</th>
            <th scope="col">전화번호</th>
            <th scope="col">주소</th>
            <th scope="col">부서</th>
            <th scope="col">수정</th>
            <th scope="col">삭제</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(data, index) in userList" :key="index">
            <td>{{ (page - 1) * pageSize + index + 1 }}</td>
            <td>{{ data.userId }}</td>
            <td>{{ data.role }}</td>
            <td>{{ data.userName }}</td>
            <td>{{ data.birthday }}</td>
            <td>{{ data.email }}</td>
            <td>{{ data.phoneNum }}</td>
            <td>{{ data.normalAddress + " " + data.detailAddress }}</td>
            <td>{{ data.deptId }}</td>
            <td>
              <button
                class="btn btn-outline-primary"
                type="button"
                data-bs-toggle="modal"
                data-bs-target="#exampleModal"
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
                      <h5 class="modal-title">회원수정</h5>
                      <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                      ></button>
                    </div>
                    <div class="modal-body">
                      <form>
                        <div class="mb-3">
                          <label class="form-label">아이디</label>
                          <input
                            type="text"
                            class="form-control"
                            v-model="user.userId"
                          />
                        </div>
                        <div class="mb-3">
                          <label class="form-label">권한</label>
                          <!-- <input
                            type="text"
                            class="form-control"
                            v-model="user.role"
                          /> -->
                          <select class="form-select" v-model="user.role">
                            <option value="ROLE_USER">ROLE_USER</option>
                          </select>
                        </div>
                        <div class="mb-3">
                          <label class="form-label">이름</label>
                          <input
                            type="text"
                            class="form-control"
                            v-model="user.userName"
                          />
                        </div>
                        <div class="mb-3">
                          <label class="form-label">생년월일</label>
                          <input
                            type="text"
                            class="form-control"
                            v-model="user.birthday"
                          />
                        </div>
                        <div class="mb-3">
                          <label class="form-label">이메일</label>
                          <input
                            type="text"
                            class="form-control"
                            v-model="user.email"
                          />
                        </div>
                        <div class="mb-3">
                          <label class="form-label">전화번호</label>
                          <input
                            type="text"
                            class="form-control"
                            v-model="user.phoneNum"
                          />
                        </div>
                        <div class="mb-3">
                          <label class="form-label">기본 주소</label>
                          <input
                            type="text"
                            class="form-control"
                            v-model="user.normalAddress"
                          />
                        </div>
                        <div class="mb-3">
                          <label class="form-label">상세 주소</label>
                          <input
                            type="text"
                            class="form-control"
                            v-model="user.detailAddress"
                          />
                        </div>
                        <div class="mb-3">
                          <label class="form-label">부서</label>
                          <select class="form-select" v-model="user.deptId">
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
                        class="btn btn-primary"
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
                class="btn btn-danger"
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
      <div class="pagination-container">
        <b-pagination
          class="custom-pagination"
          v-model="page"
          :total-rows="count"
          :per-page="pageSize"
          @click="retrieveUser"
        ></b-pagination>
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
</template>

<script>
import UserService from "@/services/user/UserService";
import AdminHeaderCom from "@/components/common/AdminHeaderCom.vue";
import Swal from "sweetalert2"; // SweetAlert2 가져오기

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
        role: "",
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
        normalAddress: this.user.normalAddress,
        detailAddress: this.user.detailAddress,
        deptId: this.user.deptId,
        role: this.user.role,
      };
      try {
        console.log("부서명", data);
        let response = await UserService.updateUser(data);
        console.log(response.data);
        Swal.fire("회원 정보가 수정되었습니다.");
        // this.closeModal(); // 모달 닫기
        this.retrieveUser(); // 사용자 정보 다시 불러오기
        // this.$router.push("/admin/member/manage");
      } catch (e) {
        Swal.fire({
          title: "수정 실패",
          text: "회원 정보 수정에 실패하였습니다.",
          icon: "error",
          confirmButtonText: "확인",
        });
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
        let result = await Swal.fire({
          title: "삭제 확인",
          text: "정말로 이 회원을 삭제 하시겠습니까?",
          icon: "warning",
          showCancelButton: true,
          confirmButtonText: "삭제",
          cancelButtonText: "취소",
        });
        if (result.isConfirmed) {
          let response = await UserService.deleteUser(userId);
          // alert(userId);
          console.log(response);
          Swal.fire({
            title: "삭제 완료",
            text: "회원 삭제가 완료되었습니다.",
            icon: "success",
            confirmButtonText: "확인",
          }).then(() => {
            this.retrieveUser(); // 사용자 정보 다시 불러오기
          });
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
    // this.getUser(this.user.userId);
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
.search-box .input-group {
  max-width: 600px;
  margin: 0 auto;
}

.table {
  margin-top: 20px;
}

.table thead th {
  background-color: #007bff;
  color: white;
}

.table tbody tr:hover {
  background-color: #f1f1f1;
}

.btn-outline-primary {
  border-color: #007bff;
  color: #007bff;
}

.btn-outline-primary:hover {
  background-color: #007bff;
  color: white;
}

.modal-header {
  background-color: #007bff;
  color: white;
}

.modal-footer .btn-secondary {
  background-color: #6c757d;
}

.modal-footer .btn-primary {
  background-color: #007bff;
}

.pagination-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.custom-pagination .page-link {
  color: #007bff;
}

.custom-pagination .page-link:hover {
  background-color: #007bff;
  color: white;
}

.custom-pagination .page-item.active .page-link {
  background-color: #007bff;
  border-color: #007bff;
}
</style>
