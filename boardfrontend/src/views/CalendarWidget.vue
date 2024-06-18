<!-- src/components/CalendarWidget.vue -->
<template>
  <br>
  <br>
  <div class="calendar-widget mt-5">
    <vue-cal style="height: 400px" default-view="month" />
  </div>
</template>

<script>
import VueCal from 'vue-cal';
import 'vue-cal/dist/vuecal.css';

export default {
  components: { VueCal },
};
</script>

<style scoped>
.calendar-widget {
  padding: 20px;
  background: white;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
