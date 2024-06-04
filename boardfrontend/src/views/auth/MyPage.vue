<template>
  <div class="mt-5" style="height: 900px">
    <div >
      <h2 class="text-center mb-5">My Page</h2>

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
                @click="showProfile"
                >프로필</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                style="border-bottom: 1px solid #cccccc; color: #555555;"
                @click="showWrittenPosts"
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
                @click="showWithdrawal"
                >회원탈퇴</a
              >
            </li>
          </ul>
        </div>

        <!-- 화면 출력 -->
        <div class="col text-center">
          <template v-if="displayedContent === 'profile'">
            <!-- 프로필 표시 -->
            <h3>프로필 화면</h3>
            <!-- 프로필 내용 -->
          </template>
          <template v-else-if="displayedContent === 'writtenPosts'">
            <!-- 작성한 글 표시 -->
            <h3 class="mt-5">내가 작성한 글</h3>
            <!-- 작성한 글 목록 -->
          </template>
          <template v-else-if="displayedContent === 'withdrawal'">
            <!-- 회원탈퇴 표시 -->
            <h3>회원탈퇴 화면</h3>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      displayedContent: '', // 현재 화면에 표시할 내용을 저장하는 변수
    };
  },
  methods: {
    // 프로필 표시 메소드
    showProfile() {
      this.displayedContent = 'profile';
    },
    // 작성한 글 표시 메소드
    showWrittenPosts() {
      this.displayedContent = 'writtenPosts';
    },
    // 회원탈퇴 표시 메소드
    showWithdrawal() {
      this.displayedContent = 'withdrawal';
    },
  },
};
</script>

<style>
.nav-link:hover {
  font-weight: bold;
}
</style>
