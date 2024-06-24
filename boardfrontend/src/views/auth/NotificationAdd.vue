<template>
    <div>
        NotificationAdd.vue
        <span
                  v-if="notificationCount > 0"
                  class="badge bg-danger notification-badge"
                >
                  {{ notificationCount }}
                </span>
        <div
              class="nav-item dropdown notification-dropdown"
              style="position: relative; margin-right: 7px; color: #444"
              v-if="!isAdminLoggedIn"
            >
              <router-link
                class="nav-link"
                id="r_t"
                to="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                @click="getUnreadNotify"
                style="color: #444; text-decoration: none"
              >
                <img
                  src="@/assets/img/Notification_icon.png"
                  alt="Loo"
                  width="30"
                  height="30"
                  class="d-inline-block align-text-top"
                />

                <span
                  v-if="notificationCount > 0"
                  class="badge bg-danger notification-badge"
                >
                  {{ notificationCount }}
                </span>
              </router-link>

              <ul
                
                style="height: auto; width: 300px; text-align: left; max-height: 400px; overflow-y: auto;"
                @scroll="handleScroll"
              >
                <p style="text-align: center">알림</p>

                <table class="table mt-3">
                  <tbody>
                    <!-- 반복문 시작할 행 -->
                    <tr
                      v-for="(data, index) in notificationList"
                      :key="index"
                      style="display: flex; align-items: center"
                    >
                      <td
                        style="
                          width: 2px;
                          height: 2px;
                          background-color: #162b59;
                          border-radius: 50%;
                          display: flex;
                          justify-content: center;
                          align-items: center;
                          margin-left: 10px;
                        "
                      ></td>
                      <!-- <td
                        class="col-12"
                        style="
                          word-wrap: break-word;
                          white-space: normal;
                          max-width: 250px;
                        "
                        @click="markAsRead(data.notifyId)"
                      >  -->
                      <td
                        class="col-12"
                        :style="{
                          wordWrap: 'break-word',
                          whiteSpace: 'normal',
                          maxWidth: '250px',
                          backgroundColor: data.isRead == 'Y' ? 'white' : '#c1ffcc',
                        }"
                        @click="markAsRead(data.notifyId)"
                      >
                        <router-link
                          class="notification-link"
                          :to="`/${data.url}`"
                          >{{ data.content }}</router-link
                        >
                      </td>
                    </tr>
                  </tbody>
                </table>

                <li><hr class="dropdown-divider" /></li>

                <li>
                  <router-link class="dropdown-item" to="/member/notification"
                    >모든 알림 보기</router-link
                  >
                </li>
              </ul>
            </div>
        <table class="table mt-3">
                  <tbody>
                    <!-- 반복문 시작할 행 -->
                    <tr
                      v-for="(data, index) in notificationList"
                      :key="index"
                      style="display: flex; align-items: center"
                    >
                      <td
                        style="
                          width: 2px;
                          height: 2px;
                          background-color: #162b59;
                          border-radius: 50%;
                          display: flex;
                          justify-content: center;
                          align-items: center;
                          margin-left: 10px;
                        "
                      ></td>
                      <!-- <td
                        class="col-12"
                        style="
                          word-wrap: break-word;
                          white-space: normal;
                          max-width: 250px;
                        "
                        @click="markAsRead(data.notifyId)"
                      >  -->
                      <td
                        class="col-12"
                        :style="{
                          wordWrap: 'break-word',
                          whiteSpace: 'normal',
                          maxWidth: '250px',
                          backgroundColor: data.isRead == 'Y' ? 'white' : '#c1ffcc',
                        }"
                        @click="markAsRead(data.notifyId)"
                      >
                        <router-link
                          class="notification-link"
                          :to="`/${data.url}`"
                          >{{ data.content }}</router-link
                        >
                      </td>
                    </tr>
                  </tbody>
                </table>
    </div>
</template>
<script>
import NotifyService from "@/services/notify/NotifyService";

export default {
    data() {
        return {
      notificationList: [],
      notificationCount: undefined,
            
        }
    },
    methods: {

    async getUnreadNotify() {
      try {
        let response = await NotifyService.getUnreadNotify(
          this.$store.state.user.userId
        );
        this.notificationList = response.data;
        // console.log("알림들", response.data);
      } catch (error) {
        console.log(error);
      }
    },
    async countUnreadNotify() {
      try {
        let notifyCount = await NotifyService.countNotify(
          this.$store.state.user.userId
        );
        // console.log("백엔드에서 받아온 알림", notifyCount);
        this.notificationCount = notifyCount.data;
      } catch (error) {
        console.log(error);
      }
    },

  },
    
}
</script>
<style>
    
</style>