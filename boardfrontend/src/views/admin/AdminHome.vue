<template>

  <!-- <HeaderCom :hideHeader="false" /> -->
  <!-- 관리자 페이지에서는 헤더를 숨김 -->
  <div class="main-container d-flex" style="height: auto; ">
    <!-- <AdminHeaderCom class="sidebar" /> -->
    <AdminHeaderCom class="sidebar" :hideHeader="true" />
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="main-content" style="padding-top: 50px;">
      <h2 class="container mb-5 text-center my-4" style="letter-spacing: -1.5px;">관리자 대시보드</h2>

      <div class="container">
        <!-- 대시보드 카드 섹션 -->
        <div class="row">
          <div class="col-lg-3 col-md-6 mb-4">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">총 사용자</h5>
                <p class="card-text">2,245명</p>
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
                <h5 class="card-title">월간 매출</h5>
                <p class="card-text">$59,690</p>
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
                <h5 class="card-title">매출 그래프</h5>
                <canvas id="salesChart"></canvas>
              </div>
            </div>
          </div>
          <div class="col-lg-6">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">주문 현황</h5>
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
                <h5 class="card-title">최근 활동</h5>
                <ul class="list-group">
                  <li class="list-group-item">
                    사용자 A가 자유게시판에 새글을 등록하였습니다.
                  </li>
                  <li class="list-group-item">
                    사용자 B가 건의게시판에 댓글을 등록하였습니다 .
                  </li>
                  <li class="list-group-item">
                    사용자 C가 동아리 신청을 하였습니다.
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div class="col-lg-6 mb-4">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">알림</h5>
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

export default {
  components: {
    AdminHeaderCom,
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
