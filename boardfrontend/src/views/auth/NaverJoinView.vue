<template>
    <div>
        
    </div>
</template>
<script>
import AuthService from '@/services/auth/AuthService';
export default {
  data() {
    return {
      user: {},
    };
  },
  methods: {
      async NaverLogin(code) {
      let response = await AuthService.naverLogin(code);
      let user = response.data;
      localStorage.setItem("user", JSON.stringify(user));
      this.$store.commit("loginSuccess", user);
      this.$router.push("/");
    },
  },
  mounted() {
    let code = this.$route.query.code;
    console.log(code);
    this.NaverLogin(code);
  },
};
</script>
<style lang="">
    
</style>