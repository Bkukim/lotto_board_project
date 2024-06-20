<template>
  <div>
    <h1>Login Success</h1>
    <p>Redirecting...</p>
  </div>
</template>

<script>
export default {
  mounted() {
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');
    
    if (token) {
      localStorage.setItem('jwtToken', token);
      this.$router.push('/');
    } else {
      // 토큰이 없을 경우 에러 처리
      console.error('No token found in URL');
      this.$router.push('/member/login');
    }
  }
}
</script>
