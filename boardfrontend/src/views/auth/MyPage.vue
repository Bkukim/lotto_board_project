<template>
  <div class="mt-5" style="height: 900px">
    <div >
      <h2 class="text-center mb-5">마이페이지</h2>

      <div class="row">
        <!-- 왼쪽 사이드바 -->
        <div class="col-3 text-center text-lg-start">
          <ul
            class="nav flex-column mt-5"
            style="gap: 50px; font-size: 25px; border-right: 3px solid #162b59; height: 700px; padding-top: 50px;
            text-align: center;"
          >
            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                style="border-bottom: 1px solid #cccccc; color: #555555;"
                
                >프로필</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                style="border-bottom: 1px solid #cccccc; color: #555555;"
                
                >작성한 글</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="/member/new-pw"
                style="border-bottom: 1px solid #cccccc; color: #555555;"
                
                >비밀번호 변경</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                style="border-bottom: 1px solid #cccccc; color: #555555;"
                
                >회원탈퇴</a
              >
            </li>
            
          </ul>
        </div>

        <!-- 화면 출력 -->
        <div class="col text-center">회원탈퇴</div>
      </div>
    </div>
  </div>
</template>
  
  <script>
export default {};
</script>
  
  <style>
.nav-link:hover {
  font-weight: bold;
}
</style>
  