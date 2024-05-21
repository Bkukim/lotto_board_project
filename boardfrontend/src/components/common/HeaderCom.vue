<template>
  <div>
    <nav class="navbar navbar-expand-lg bg-dark-light">
      <div class="container">
        <a class="navbar-brand" href="#">
          <img src="@/assets/img/LOTTO_LOGO.png" alt="Logo" width="200" height="200" class="d-inline-block align-text-top" />
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">자유 게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">공지 게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">동아리 매칭 게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">건의 익명 게시판</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Dropdown link
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">동아리 매칭 게시판</a></li>
                <li><a class="dropdown-item" href="#">자유 게시판</a></li>
                <li><a class="dropdown-item" href="#">공지 게시판</a></li>
                <li><a class="dropdown-item" href="#">건의 익명 게시판</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
}
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

.nav-item{
  margin-right: 1.5vw;
}
</style>
