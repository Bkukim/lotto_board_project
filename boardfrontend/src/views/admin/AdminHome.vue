<template>
  <!-- <HeaderCom :hideHeader="false" /> -->
  <!-- 관리자 페이지에서는 헤더를 숨김 -->
  <div class="main-container d-flex" style="height: auto">
    <!-- <AdminHeaderCom class="sidebar" /> -->
    <AdminHeaderCom class="sidebar" :hideHeader="true" />
    <br />
    <br />
    <br />
    <br />
    <br />
    <div class="main-content" style="padding-top: 50px">
      <h2
        class="container mb-5 text-center my-4"
        style="letter-spacing: -1.5px"
      >
        관리자 대시보드
      </h2>

      <div class="container">
        <!-- 대시보드 카드 섹션 -->
        <div class="row">
          <div class="col-lg-3 col-md-6 mb-4">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">총 사용자</h5>
                <p class="card-text">9명</p>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-6 mb-4">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">1일 방문자</h5>
                <p class="card-text">4,865명</p>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-6 mb-4">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">게시판 수</h5>
                <p class="card-text">59,690개</p>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-6 mb-4">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">평균 방문자</h5>
                <p class="card-text">38,500명</p>
              </div>
            </div>
          </div>
        </div>

        <!-- 차트 섹션 -->
        <div class="row mb-4">
          <div class="col-lg-6">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">게시판 수</h5>
                <canvas id="salesChart"></canvas>
              </div>
            </div>
          </div>
          <div class="col-lg-6">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">평균 방문자</h5>
                <canvas id="ordersChart"></canvas>
              </div>
            </div>
          </div>
        </div>

        <!-- 최근 활동 및 알림 -->
        <div class="row">
          <div class="col-lg-6 mb-4">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">최근 알림</h5>
                <ul
                  class="list-group"
                  v-for="(data, index) in notificationList"
                  :key="index"
                >
                  <li
                    class="list-group-item"
                    @click="goToUrl(data.notificationType, data.url)"
                  >
                    {{ data.content }}
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div class="col-lg-6 mb-4">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">공지</h5>
                <ul class="list-group">
                  <li class="list-group-item">시스템 점검 예정 (6/15)</li>
                  <li class="list-group-item">신규 기능 업데이트 (6/20)</li>
                  <li class="list-group-item">보안 패치 적용 (6/25)</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AdminHeaderCom from "@/components/common/AdminHeaderCom.vue";
import { onMounted } from "vue";
import {
  Chart,
  BarController,
  BarElement,
  CategoryScale,
  LinearScale,
  LineController,
  LineElement,
  PointElement,
} from "chart.js";
import NotifyService from "@/services/notify/NotifyService";
// import NoticeService from '@/services/notice/NoticeService';

export default {
  components: {
    AdminHeaderCom,
  },
  data() {
    return {
      notificationList: [],
    };
  },
  methods: {
    // async getAllNotice() {
    //   try {
    //     let response = NoticeService.
    //   } catch (error) {}
    // },
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
    connectSse(jwt) {
      try {
        // let subscribeUrl = "http://localhost:8000/api/v1/notify/subscribe";
      // let subscribeUrl = "http://13.209.24.76:8000/api/v1/notify/subscribe";
      const subscribeUrl =
        "http://" + this.$store.state.backendIp + "/api/v1/notify/subscribe";

      if (jwt != null) {
        let token = jwt;
        this.eventSource = new EventSource(subscribeUrl + "?token=" + token);
        this.eventSource.onopen = () => {
          console.log("SSE 연결이 열렸습니다.");
          this.isConnected = true;
        };
        // this.eventSource.addEventListener("connect", function(event) {
        //     let message = event.data;
        //     alert(message);
        // })

        this.eventSource.addEventListener("COMMENT", function (event) {
          let message = event.data;
          alert(message);
        });
        this.eventSource.addEventListener("REPORT", function (event) {
          let message = event.data;
          alert(message);
        });
        this.eventSource.addEventListener("CLUB_APPLICATION", function (event) {
          let message = event.data;
          alert(message);
        });
        this.eventSource.addEventListener("CLUB_APPROVAL", function (event) {
          let message = event.data;
          alert(message);
        });
        this.eventSource.addEventListener("COMPLAINT", function (event) {
          let message = event.data;
          alert(message);
        });
        this.eventSource.addEventListener("COMPLAINT_STATUS", function (event) {
          let message = event.data;
          alert(message);
        });
        this.eventSource.onmessage = (event) => {
          console.log("새 알림:", event.data);
          this.messages.push(event.data);
        };
        this.eventSource.onerror = (event) => {
          console.error("SSE 연결 오류:", event);
          if (event.readyState == EventSource.CLOSED) {
            console.log("SSE 연결이 닫혔습니다.");
            this.isConnected = false;
          } else {
            console.log("SSE 연결 오류 발생, 재연결 시도 중...");
          }
        };
      } else {
        console.error("JWT 토큰이 없습니다.");
      }
      } catch (error) {
        console.log("sse연결에러")
      } 
      
    },
    goToUrl(notificationType, url) {
      if (notificationType == "REPORT") {
        this.$router.push("/admin/report");
      } else if (notificationType == "COMPLAINT") {
        this.$router.push("/" + url);
      }
    },
  },
  props: {
    hideHeader: {
      type: Boolean,
      default: true, // 기본값을 false로 설정
    },
  },
  setup() {
    onMounted(() => {
      // Chart.js에 필요한 컴포넌트 등록
      Chart.register(
        BarController,
        BarElement,
        CategoryScale,
        LinearScale,
        LineController,
        LineElement,
        PointElement
      );

      const ctxSales = document.getElementById("salesChart").getContext("2d");
      const ctxOrders = document.getElementById("ordersChart").getContext("2d");

      new Chart(ctxSales, {
        type: "bar",
        data: {
          labels: [
            "1월",
            "2월",
            "3월",
            "4월",
            "5월",
            "6월",
            "7월",
            "8월",
            "9월",
            "10월",
            "11월",
            "12월",
          ],
          datasets: [
            {
              label: "매출",
              data: [
                12000, 15000, 8000, 17000, 20000, 23000, 15000, 18000, 22000,
                25000, 27000, 30000,
              ],
              backgroundColor: "rgba(75, 192, 192, 0.6)",
            },
          ],
        },
      });

      new Chart(ctxOrders, {
        type: "line",
        data: {
          labels: [
            "1월",
            "2월",
            "3월",
            "4월",
            "5월",
            "6월",
            "7월",
            "8월",
            "9월",
            "10월",
            "11월",
            "12월",
          ],
          datasets: [
            {
              label: "주문 수",
              data: [
                300, 400, 200, 500, 600, 700, 400, 500, 600, 700, 800, 900,
              ],
              backgroundColor: "rgba(153, 102, 255, 0.6)",
              borderColor: "rgba(153, 102, 255, 1)",
              fill: false,
            },
          ],
        },
      });
    });
  },
  mounted() {
    this.getUnreadNotify();
    this.connectSse(this.$store.state.user.accessToken);
  },
};
</script>

<style scoped>
.main-container {
  display: flex;
}

.sidebar {
  width: 250px;
  background-color: #343a40;
  color: white;
}

.main-content {
  flex: 1;
  padding: 20px;
  background-color: #f8f9fa;
}

.card {
  border: none;
  box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
}

.card-title {
  font-size: 1.25rem;
}

.card-text {
  font-size: 1.5rem;
  font-weight: bold;
}
</style>
