<template>
  <div v-if="!hideHeader" >
    <div style="border-bottom: 1px solid #cccccc" >
      <nav class="navbar navbar-expand-lg bg-dark-light" >
        <router-link class="navbar-brand" to="/">
          <img
            src="@/assets/img/LOTTO_LOGO.png"
            alt="Logo"
            width="200"
            height="200"
            class="d-inline-block align-text-top"
          />
        </router-link>
        <div class="container" >
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNavDropdown" >
            <ul
              class="navbar-nav ms-auto"
              style="gap: 10px; display: block; display: flex;"
            >
              <li class="nav-item">
                <router-link class="nav-link" to="/notice/notice-board"
                  >공지사항</router-link
                >
              </li>
              <li class="nav-item">
                <router-link
                  class="nav-link"
                  aria-current="page"
                  to="/free/free-board"
                  >자유 게시판</router-link
                >
              </li>
              <li class="nav-item">
                <router-link class="nav-link" to="/club/club-board"
                  >동아리 게시판</router-link
                >
              </li>
              <li class="nav-item">
                <router-link class="nav-link" to="/complaint/complaint-board"
                  >건의 게시판</router-link
                >
              </li>
              <li class="nav-item dropdown">
                <router-link
                  class="nav-link dropdown-toggle"
                  to="#"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  부서 게시판
                </router-link>
                <ul class="dropdown-menu">
                  <li v-for="(data, index) in departments" :key="index">
                    <a class="dropdown-item" :href="`/dept/${data.deptId}`"
                      >{{ data.deptName }} 게시판</a
                    >
                  </li>
                </ul>
              </li>
            </ul>

            <!-- 검색바 -->
            <nav
              class="navbar navbar-light search-bar"
              style="background-color: rgb(255, 255, 255)"
            >
              <div
                class="container-fluid"
                style="background-color: rgb(255, 255, 255); height: 92px"
              >
                <form class="d-flex" style="width: 150px;">
                  <input
                    class="form-control me-2"
                    type="search"
                    placeholder="Search"
                    aria-label="Search"
                  />
                  <router-link
                    class="btn btn-outline-success search-button"
                    type="submit"
                    style="border: none"
                    id="s_all"
                    to="/member/search"
                  >
                    <img
                      src="@/assets/img/serch_icon.png"
                      alt="Loo"
                      width="25"
                      height="25"
                      class="d-inline-block align-text-top"
                      style="margin-left: -10px; margin-right: -10px"
                    />
                  </router-link>
                </form>
              </div>
            </nav>

            
            <!-- 알림 아이콘 -->
            <div
              class="nav-item dropdown notification-dropdown"
              style="position: relative; margin-right: 7px; color: #444"
              v-if="!isAdminLoggedIn"
            >
              <router-link
                class="nav-link"
                id="r_t"
                to="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                @click="getUnreadNotify"
                style="color: #444; text-decoration: none"
              >
                <img
                  src="@/assets/img/Notification_icon.png"
                  alt="Loo"
                  width="30"
                  height="30"
                  class="d-inline-block align-text-top"
                />

                <span
                  v-if="notificationCount > 0"
                  class="badge bg-danger notification-badge"
                >
                  {{ notificationCount }}
                </span>
              </router-link>

              <ul
                class="dropdown-menu dropdown-menu-end"
                style="height: auto; width: 300px; text-align: left"
              >
                <p style="text-align: center">알림</p>

                <table class="table mt-3">
                  <tbody>
                    <!-- 반복문 시작할 행 -->
                    <tr
                      v-for="(data, index) in notificationList"
                      :key="index"
                      style="display: flex; align-items: center"
                    >
                      <td
                        style="
                          width: 2px;
                          height: 2px;
                          background-color: #162b59;
                          border-radius: 50%;
                          display: flex;
                          justify-content: center;
                          align-items: center;
                          margin-left: 10px;
                        "
                      ></td>
                      <td
                        class="col-12"
                        style="
                          word-wrap: break-word;
                          white-space: normal;
                          max-width: 250px;
                        "
                      >
                        <router-link
                          class="notification-link"
                          :to="`/${data.url}`"
                          >{{ data.content }}</router-link
                        >
                      </td>
                    </tr>
                  </tbody>
                </table>

                <li><hr class="dropdown-divider" /></li>

                <li>
                  <router-link class="dropdown-item" to="#"
                    >모든 알림 보기</router-link
                  >
                </li>
              </ul>
            </div>

            <!-- 로그인 아이콘 -->
            <div class="icons-container" v-if="!this.$store.state.loggedIn">
              <router-link to="/member/login">
                <img
                  src="@/assets/img/login_icon.png"
                  alt="Loo"
                  width="30"
                  height="30"
                  class="d-inline-block align-text-top"
                />
              </router-link>
            </div>

            <!-- 로그인 상태일 시 -->
            <div class="icons-container" v-else>
              <div>
                <div v-if="this.$store.state.user.role=='ROLE_USER'">
                  <!-- 마이페이지 아이콘 -->
                  <router-link
                    style="margin-top: -5px; margin-right: 23px"
                    to="/member/mypage"
                    class="d-inline-block align-text-top"
                    v-if="!isAdminLoggedIn"
                  >
                    <img
                      src="@/assets/img/mypage_icon.png"
                      alt="Loo"
                      width="40"
                      height="40"
                      class="d-inline-block align-text-top"
                    />
                  </router-link>
                </div>
                <!-- 관리자 로그인시 : 관리자페이지 이동 -->
                <div v-else-if="this.$store.state.user.role=='ROLE_ADMIN'">
                   <!-- 마이페이지 아이콘 -->
                  <router-link
                    style="margin-top: -5px; margin-right: 30px; margin-left: 10px; color: #444; text-decoration: none; font-size: 15px; text-align: center;"
                    to="/admin/home"
                    class="d-inline-block align-text-top"
                    v-if="!isAdminLoggedIn"
                  >
                  Admin <br>
                  Page
                  </router-link>
                </div>
              </div>

              <!-- 로그아웃 아이콘 -->
              <router-link
                to="#"
                class="d-inline-block align-text-top"
                @click.prevent="handleLogout"
              >
                <img
                  src="@/assets/img/logout_icon.png"
                  alt="Loo"
                  width="30"
                  height="30"
                  class="d-inline-block align-text-top"
                  style="margin-left: -30px;"
                />
              </router-link>
            </div>
          </div>
        </div>
      </nav>
    </div>
  </div>
</template>

<script>
import AuthService from "@/services/auth/AuthService";
import DeptBoardService from "@/services/board/dept/DeptBoardService";
import NotifyService from "@/services/notify/NotifyService";

export default {
  data() {
    return {
      departments: [],
      notificationList: [],
      notificationCount: undefined,
    };
  },
  // props: {
  //   hideHeader: {
  //     type: Boolean,
  //     default: true, // 기본값을 true로 설정
  //   },
  // },
  methods: {
    async getAllDepartment() {
      try {
        let response = await DeptBoardService.findAllDepartment();
        console.log("부서", response.data);
        this.departments = response.data;
      } catch (error) {
        console.log(error);
      }
    },
    async getUnreadNotify() {
      try {
        let response = await NotifyService.getUnreadNotify(
          this.$store.state.user.userId
        );
        this.notificationList = response.data;
        // console.log("알림들", response.data);
      } catch (error) {
        console.log(error);
      }
    },
    async countUnreadNotify() {
      try {
        let notifyCount = await NotifyService.countNotify(
          this.$store.state.user.userId
        );
        // console.log("백엔드에서 받아온 알림", notifyCount);
        this.notificationCount = notifyCount.data;
      } catch (error) {
        console.log(error);
      }
    },

    handleLogout() {
      let result = confirm("정말로 로그아웃 하시겠습니까?");
      if (result) {
        AuthService.logout(); // LOCAL저장소에서 USER객체 삭제해주기
        this.$store.commit("logout"); //
        this.$store.state.notifyCount = 0;
        this.$router.push("/member/login");
      } else {
        return;
      }
    },

    computed: {
      isAdminLoggedIn() {
        // 관리자 로그인 여부 계산
        return this.$store.state.user.role === "admin";
      },
    },
  },
  mounted() {
    this.getAllDepartment();
    this.countUnreadNotify();
    console.log("알림 갯수", this.notificationCount);
  },
};
</script>

<style>
/* 예시: 반응형 디자인 */
@media (max-width: 992px) {
  .navbar {
    height: auto;
    flex-direction: column;
    align-items: flex-start;
  }
  /* 추가적인 반응형 스타일 조정 */
}

@media (max-width: 576px) {
  .navbar {
    flex-direction: column;
    align-items: center;
    padding: 5px 0;
  }
  /* 추가적인 반응형 스타일 조정 */
}

/* 헤더 색상 변경 / 높이 설정 */
.navbar {
  background-color: rgb(255, 255, 255);
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 1rem;
}

/* 로고 이동 */
.navbar-brand {
  margin-left: 2vw;
}

.navbar-brand img {
  width: 200px; /* 고정된 크기 설정 */
  height: 200px; /* 고정된 크기 설정 */
}

/* 메뉴 */
.nav-item {
  margin-right: 10px;
}

.nav-link {
  letter-spacing: -1.8px;
  font-weight: 500;
}

.nav-link:hover {
  font-weight: bolder;
  color: #ffffff;
}

.nav-item {
  display: block;
}

.nav-item:hover {
  border-bottom: 3px #162b59 solid;
}

/* 검색 돋보기 아이콘 */
#s_all:hover {
  background-color: #2d61d056;
}

.search-button {
  border: none;
  background-color: transparent;
  padding: 0;
}

.search-button:hover {
  background-color: #162b59;
}

/* 드롭다운 토글 아이콘 숨기기 */
.notification-dropdown .nav-link::after {
  display: none !important;
}
.notification-dropdown:hover {
  border: none;
}

/* 알림 배지 */
.notification-badge {
  position: absolute;
  top: -1px;
  right: -8px;
  font-size: 12px;
  padding: 2px 5px;
  border-radius: 50%;
  background-color: red;
  color: white;
}
.router-link > #r_t {
  color: #162b59;
}

/* 반응형 스타일 */
@media (max-width: 1200px) {
  /* 로고 크기 변경 없음 */
}

@media (max-width: 992px) {
  .navbar {
    height: auto;
    flex-direction: column;
    align-items: flex-start;
  }
  .search-bar {
    width: 100%;
    margin-top: 10px;
  }
  .navbar-toggler {
    display: block;
    margin-left: auto;
  }
  .nav-item {
    margin-right: 0;
    margin-bottom: 10px;
  }
}

@media (max-width: 768px) {
  .navbar-nav {
    flex-direction: column;
    gap: 10px;
    width: 100%;
  }
  .navbar-toggler {
    margin-left: auto;
  }
  .navbar {
    padding: 10px 0;
  }
  .nav-item {
    margin-bottom: 5px;
  }
  .search-bar {
    width: 100%;
    margin-top: 10px;
  }
}

@media (max-width: 576px) {
  .navbar {
    flex-direction: column;
    align-items: center;
    padding: 5px 0;
  }
  .nav-item {
    margin-bottom: 5px;
  }
  .search-bar {
    width: 100%;
    margin-top: 10px;
  }
  .navbar-nav {
    gap: 5px;
    width: 100%;
    justify-content: center;
  }
  .nav-link {
    font-size: 14px;
  }
}
.notification-link {
  color: black;
  text-decoration: none;
}
.notification-link:hover {
  color: black;
  text-decoration: underline;
}
.admin-page-btn {
  margin-left: 10px;
  background-color: #2d61d0;
  color: white;
}

.icons-container {
  display: flex;
  align-items: center;
  gap: 15px;
}
</style>
