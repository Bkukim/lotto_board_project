<template>
  <div>
    <!-- 머리말 -->
    <AdminHeaderCom />

    <!-- 본문 -->
    <div class="qhsans">
      <router-view />
    </div>

    <!-- 꼬리말 -->
    <FoterView />
  </div>
</template>
  <script>
import AdminHeaderCom from "@/components/common/AdminHeaderCom.vue";

import FoterView from "@/components/common/FoterView.vue";

export default {
  components: {
    
    // 현재 라우트의 메타 데이터에서 hideHeader 속성을 가져옴
    shouldHideHeader() {
      return this.$route.meta.hideHeader;
    },
    AdminHeaderCom,
    FoterView
  },
};
</script>
  <style >
/* .qhsans{
    height: 50vw;
  } */
</style>
  