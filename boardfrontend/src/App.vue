<template>
  <div>
    <!-- 머리말 -->
    <HeaderCom v-if="$route.meta.showHeader"  />


    <!-- 본문 -->
    <div class="qhsans">
      <router-view />
    </div>

    <!-- 꼬리말 -->
    <!-- <FoterView v-if="$route.meta.showFoter" /> -->
    <FoterView />
  </div>
</template>
<script>
import HeaderCom from "@/components/common/HeaderCom.vue";
import FoterView from '@/components/common/FoterView.vue';

export default {
  components: {
    HeaderCom,
    FoterView,

  },
};
</script>
<style >
/* .qhsans{
  height: 50vw;
} */
</style>
