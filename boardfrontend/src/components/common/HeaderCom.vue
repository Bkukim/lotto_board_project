<template>
  <div style="border-bottom: 1px solid #cccccc">
    <nav class="navbar navbar-expand-lg bg-dark-light">
      <a class="navbar-brand" href="/">
        <img
          src="@/assets/img/LOTTO_LOGO.png"
          alt="Logo"
          width="200"
          height="200"
          class="d-inline-block align-text-top"
        />
      </a>
      <div class="container">
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
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
          <ul
            class="navbar-nav ms-auto"
            style="gap: 10px; display: block; display: flex"
          >
            <li class="nav-item">
              <a class="nav-link" href="/notice/notice-board">공지사항</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="/free/free-board">자유 게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/club/club-board">동아리 게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/complaint/complaint-board">건의 게시판</a>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                부서 게시판
              </a>
              <ul class="dropdown-menu">
                <li>
                  <a class="dropdown-item" href="#">동아리 매칭 게시판</a>
                </li>
                <li><a class="dropdown-item" href="#">자유 게시판</a></li>
                <li><a class="dropdown-item" href="#">공지 게시판</a></li>
                <li><a class="dropdown-item" href="#">건의 익명 게시판</a></li>
              </ul>
            </li>
          </ul>

          <!-- 검색바 -->
          <nav
            class="navbar navbar-light bg-light search-bar"
            style="background-color: rgb(255, 255, 255)"
          >
            <div
              class="container-fluid"
              style="background-color: rgb(255, 255, 255); height: 90px"
            >
              <form class="d-flex">
                <input
                  class="form-control me-2"
                  type="search"
                  placeholder="Search"
                  aria-label="Search"
                />
                <button
                  class="btn btn-outline-success search-button"
                  type="submit"
                  style="border: none"
                  id="s_all"
                >
                  <img
                    src="@/assets/img/serch_icon.png"
                    alt="Loo"
                    width="25"
                    height="25"
                    class="d-inline-block align-text-top"
                    style="margin-left: -10px; margin-right: -10px"
                  />
                </button>
              </form>
            </div>
          </nav>

          <!-- 알림 아이콘 -->
          <div class="nav-item dropdown notification-dropdown" style="position: relative; margin-right: 7px;">
            <a 
              class="nav-link"
              href="#"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              @click="getUnreadtNotify"
            >
              <img 
                src="@/assets/img/Notification_icon.png"
                alt="Loo"
                width="30"
                height="30"
                class="d-inline-block align-text-top"
              />
              <span v-if="notificationCount > 0" class="badge bg-danger notification-badge">
                {{ notificationCount }}
              </span>
            </a>
            <ul class="dropdown-menu dropdown-menu-end" style="height: auto; width: 300px;">
              <table class="table mt-5">
                <p style="text-align: center;">알림</p>

                <tbody>
                  <tr v-for="(data, index) in NotificationList" :key="index">
                    <td style="font-size: 15px">
                      {{ index + 1 }}
                    </td>
                    <td class="col-8"></td>
                  </tr>
                </tbody>
              </table>


              <li><hr class="dropdown-divider" /></li>
              <li><a class="dropdown-item" href="#">모든 알림 보기</a></li>
            </ul>
          </div>

          <!-- 로그인 아이콘 -->
          <div class="hd_r" v-if="!this.$store.state.loggedIn">
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
          <div class="hd_r" style="text-align: center;" v-else>
            <!-- 마이페이지 아이콘 -->
            <router-link style="margin-top: -5px; margin-right: 3px;" to="/member/mypage" class="d-inline-block align-text-top">
              <img 
                src="@/assets/img/mypage_icon.png"
                alt="Loo"
                width="40"
                height="40"
                class="d-inline-block align-text-top"
              />
            </router-link>

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
              />
            </router-link>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import AuthService from "@/services/auth/AuthService";
import NotifyService from "@/services/notify/NotifyService";

export default {
  data() {
    return {


      notificationList: [],
      notifyCount:this.$store.state.notifyCount

    };
  },
  methods: {
   async getUnreadtNotify(){
    try {
      let response = await NotifyService.getUnreadNotify(this.$store.state.user.userId);
      this.notificationList = response.data
      console.log("알림들",response.data)
    } catch (error) {
      console.log(error);
    }
  },
    handleLogout() {
      let result = confirm("정말로 로그아웃 하시겠습니까?");
      if (result) {
        AuthService.logout(); // LOCAL저장소에서 USER객체 삭제해주기
        this.$store.commit("logout"); //
        this.$router.push("/member/login");
      } else {
        return;
      }
    },
  },
};
</script>

<style>
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
  margin-right: 1.5vw;
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
</style>
