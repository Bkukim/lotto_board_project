<template>
  <div class="d-flex flex-column flex-shrink-0 p-3" style="width: 300px; height: auto; padding: 150px 0 150px 0;background: linear-gradient(to right, #ffffff 0%, #99ccff 170%);">
    <router-link to="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none" style="padding: 50px 0;">
      <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
      <h2 class="fs-4" style="font-weight: bold; color: #0d6efd; text-align: center;">LOTTO ADMIN</h2>
    </router-link>

    <div class="dropdown d-flex align-items-center" style="margin-left: 60px;">
      <a href="#" class="d-flex align-items-center link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
        <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
        <strong>로또 관리자</strong>
      </a>
      <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser1">
        <li><router-link class="dropdown-item" to="/">홈페이지 바로가기</router-link></li>
        <li><a class="dropdown-item" href="#">설정</a></li>
        <li><a class="dropdown-item" href="#">프로필?</a></li>
        <li><hr class="dropdown-divider"></li>
        <li><a class="dropdown-item" href="#">로그아웃</a></li>
      </ul>
    </div>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-itemmm">
        <router-link to="/admin/home" class="nav-link" exact-active-class="active">
          <svg class="bi me-2" width="16" height="16"><use xlink:href="#home"/></svg>
          Home
        </router-link>
      </li>
      <li>
        <router-link to="/admin/member/manage" class="nav-link" exact-active-class="active">
          <svg class="bi me-2" width="16" height="16"><use xlink:href="#speedometer2"/></svg>
          회원관리
        </router-link>
      </li>
      <li>
        <router-link to="/admin/notice-master" class="nav-link" exact-active-class="active">
          <svg class="bi me-2" width="16" height="16"><use xlink:href="#speedometer2"/></svg>
          공지사항 관리
        </router-link>
      </li>
      <!-- <li>
        <router-link to="/admin/club-posts" class="nav-link" exact-active-class="active">
          <svg class="bi me-2" width="16" height="16"><use xlink:href="#table"/></svg>
          동아리게시글 관리
        </router-link>
      </li> -->
      <li>
        <router-link to="/admin/report" class="nav-link" exact-active-class="active">
          <svg class="bi me-2" width="16" height="16"><use xlink:href="#grid"/></svg>
          신고게시글 관리
        </router-link>
      </li>
      <li>
        <router-link to="/admin/other" class="nav-link" exact-active-class="active">
          <svg class="bi me-2" width="16" height="16"><use xlink:href="#people-circle"/></svg>
          모하징
        </router-link>
      </li>
    </ul>
    <hr>
  </div>
</template>

<script>
export default {
  // name: 'Sidebar'
};
</script>

<style scoped>
.active {
  background-color: #0d6efd;
  color: white !important;
}
</style>
