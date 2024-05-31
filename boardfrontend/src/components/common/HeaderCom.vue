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
          <ul class="navbar-nav ms-auto" style="gap: 30px">
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
            <!-- <li class="nav-item">
              <a class="nav-link" href="#">이벤트</a>
            </li> -->
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
        </div>

        <!-- 검색바 -->
        <nav class="navbar navbar-light bg-light search-bar" style="background-color: rgb(255, 255, 255);">
          <div class="container-fluid" style="background-color: rgb(255, 255, 255); height: 90px;" >
            <form class="d-flex"> 
              <input
                class="form-control me-2"
                type="search"
                placeholder="Search"
                aria-label="Search"
              />
              <button class="btn btn-outline-success search-button" type="submit" style="border: none;" id="s_all">
                <img
                  src="@/assets/img/serch_icon.png"
                  alt="Loo"
                  width="40"
                  height="40"
                  class="d-inline-block align-text-top"
                  style="margin-right: 15px;" 
                />
              </button>
            </form>
          </div>
        </nav>

        <!-- 로그인 아이콘 -->
        <router-link to="/member/login">
          <img
            src="@/assets/img/login_icon.png"
            alt="Loo"
            width="40"
            height="40"
            class="d-inline-block align-text-top"
          />
        </router-link>
      </div>
    </nav>
  </div>
</template>

<script>
export default {};
</script>
<style>
/* 헤더 색상 변경 / 높이 설정 */
.navbar {
  background-color: rgb(255, 255, 255);
  height: 100px;
}

/* 로고 이동 */
.navbar-brand {
  margin: 0 0 0 2vw;
}

.nav-item {
  margin-right: 1.5vw;
}

/* 메뉴 */
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

/* 반응형 스타일 */
@media (max-width: 1200px) {
  .navbar-brand img {
    width: 180px;
    height: 180px;
  }
}

@media (max-width: 992px) {
  .navbar {
    height: auto;
  }
  .navbar-brand img {
    width: 150px;
    height: 150px;
  }
  .search-bar {
    display: none;
  }
  .navbar-toggler {
    display: block;
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
  }
  .navbar-toggler {
    margin-left: auto;
  }
  .navbar {
    padding: 10px 0;
  }
  .navbar-brand img {
    width: 120px;
    height: 120px;
  }
  .nav-item {
    margin-bottom: 5px;
  }
}

@media (max-width: 576px) {
  .navbar-brand img {
    width: 100px;
    height: 100px;
  }
  .nav-item {
    margin-bottom: 5px;
  }
  .search-bar {
    display: none;
  }
  .navbar {
    padding: 5px 0;
  }
  .navbar-nav {
    gap: 5px;
  }
  .nav-link {
    font-size: 14px;
  }
}

</style>