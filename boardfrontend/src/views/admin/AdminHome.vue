<template>
 
  <div class="main-container d-flex" style="height: auto;">
    <AdminHeaderCom />

    <div class="main-content">
      <h2 class="container text-center">어도민</h2>
      <!-- 메인 화면에서의 내용 -->
    </div>
  </div>
</template>
  
  
  <script>
import AdminHeaderCom from "@/components/common/AdminHeaderCom.vue";

export default {
  components: {
    AdminHeaderCom,
  },
};
</script>
  
<style scoped>
.main-container {
  display: flex;
}

.sidebar {
  width: 350px; /* 사이드바의 고정 너비 */
}

.main-content {
  flex: 1; /* 남은 공간을 차지 */
  padding: 0 20px; /* 콘텐츠와 사이드바 사이의 간격 설정 */
}
</style>

  