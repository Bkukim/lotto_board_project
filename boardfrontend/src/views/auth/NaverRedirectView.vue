<template>
  <div>
    <p>네이버 로그인 중...</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  async mounted() {
    const urlParams = new URLSearchParams(window.location.search);
    const code = urlParams.get('code');
    const state = urlParams.get('state');

    if (code) {
      try {
        // 서버로 인증 코드를 보내어 액세스 토큰을 요청합니다.
        const response = await axios.get(`http://localhost:8000/api/auth/naver/callback?code=${code}&state=${state}`);
        const { token } = response.data;

        // 토큰을 로컬 스토리지에 저장
        localStorage.setItem('jwtToken', token);

        // 홈 페이지로 리디렉션
        this.$router.push('/');
      } catch (error) {
        console.error('네이버 로그인 실패:', error);
        this.$router.push('/member/login');
      }
    } else {
      console.error('인증 코드가 없습니다.');
      this.$router.push('/member/login');
    }
  }
}
</script>
