<template>
  <div style="background-color: #f2f2f2; padding-bottom: 50px;">
    <!-- 큰 이미지 배너 -->
    <div class="container d-flex justify-content-center text-center">
      <div v-if="clubBoard && !mapView" style="width: 1000px; height: 500px;">
        <img :src="clubBoard.imgUrl" alt="club_photo" width="1000px" height="500" class="d-inline-block align-text-top" />
      </div>
      <div id="map" style="width: 1000px; height: 500px;" ref="map" v-else></div>
    </div>

    <div class="container text-center d-flex" style="width: 1000px; margin: 0 auto;">
      <!-- 왼쪽 섹션 -->
      <div style="flex: 1; margin-right: 20px;">
        <!-- 매치 포인트 섹션 -->
        <div class="row rounded-section" style="background-color: #ffffff; margin-bottom: 20px; height: 300px; position: relative;">
          <div class="col-12">
            <h3 class="match-point-title">매치 포인트</h3>
            <div v-if="clubBoard" class="match-point-content">
              <div class="match-point-item">
                <i class="fas fa-venus-mars"></i>
                <span>{{ clubBoard.sex }}</span>
              </div>
              <div class="match-point-item">
                <i class="fas fa-futbol"></i>
                <span>{{ clubBoard.peoplesMatch }} {{ clubBoard.matchForm }}</span>
              </div>
              <div class="match-point-item">
                <i class="fas fa-users"></i>
                <span>{{ clubBoard.minQuota }} ~ {{ clubBoard.maxQuota }}명</span>
              </div>
              <div class="match-point-item">
                <i class="fas fa-running"></i>
                <span>{{ clubBoard.material }}</span>
              </div>
            </div>
          </div>
        </div>

        <!-- 매치 데이터 섹션 -->
        <div class="row rounded-section" style="background-color: #ffffff; margin-bottom: 20px; height: 500px;">
          <div class="col-12">
            <h3 class="text-left mt-3">매치 데이터</h3>
            <p v-if="clubBoard">{{ clubBoard.location }}</p>
          </div>
        </div>

        <!-- 매치 진행방식 섹션 -->
        <div class="row rounded-section" style="background-color: #ffffff; margin-bottom: 20px; height: 500px;">
          <div class="col-12">
            <h3 class="text-left mt-3">매치 진행방식</h3>
            <ul>
              <li>모든 파울은 사이드라인에서 킥인</li>
              <li>골키퍼에게 백패스 가능 손으로는 잡으면 안 돼요</li>
              <li>사람을 향한 태클 금지</li>
            </ul>
          </div>
        </div>
      </div>

      <!-- 뉴스 섹션 -->
      <div class="news-container rounded-section" style="background-color: #ffffff; height: 400px; flex-basis: calc(35% + 10px); position: sticky; top: 5px; margin-right: -10px;">
        <h3 class="text-left mt-3">뉴스</h3>
        <button @click="popUpMap">장소 사진 보기</button>
      </div>
    </div>

    <!-- 푸터 -->
    <footer style="background-color: #f2f2f2; padding: 20px;">
      <div class="container">
        <p class="text-center">© 2024 Your Company. All Rights Reserved.</p>
      </div>
    </footer>
  </div>
</template>

<script>
import ClubBoardService from "@/services/board/club/ClubBoardService";

export default {
  data() {
    return {
      clubBoard: null,
      mapView: true,
    };
  },
  methods: {
    async fetchClubBoardDetails() {
      const clubBoardId = this.$route.params.clubBoardId;
      try {
        const response = await ClubBoardService.getClubOnce(clubBoardId);
        this.clubBoard = response.data[0];
        this.retrieveMap(this.clubBoard.address);
      } catch (error) {
        console.error("Error fetching club board details:", error);
      }
    },
    popUpMap() {
      this.mapView = !this.mapView;
      if (this.mapView) {
        this.$nextTick(() => {
          this.retrieveMap(this.clubBoard.address);
        });
      }
    },
    retrieveMap(address) {
      const kakao = window.kakao;
      const container = this.$refs.map;
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };
      const map = new kakao.maps.Map(container, options);
      const geocoder = new kakao.maps.services.Geocoder();
      geocoder.addressSearch(address, (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
          const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          const marker = new kakao.maps.Marker({
            map: map,
            position: coords,
          });
          const infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">경기장소</div>',
          });
          infowindow.open(map, marker);
          map.setCenter(coords);
        }
      });
    },
  },
  async mounted() {
    await this.fetchClubBoardDetails();
  },
};
</script>

<style>
.match-point-container {
  padding: 20px;
}

.match-point-title {
  position: absolute;
  top: 5px; /* 더 위로 이동 */
  left: 10px;
  font-size: 20px;
  letter-spacing: -1px;
  font-weight: 600;
}

.match-point-content {
  display: flex;
  flex-wrap: wrap; /* 2열 배치를 위해 wrap 설정 */
  justify-content: center; /* 중앙에 배치 */
  align-items: center; /* 중앙에 배치 */
  height: 100%; /* 부모 div의 높이 전체를 사용 */
  gap: 2px; /* 간격을 줄여서 더 붙게 만듦 */
}

.match-point-item {
  display: flex;
  align-items: center;
  width: 48%; /* 2열 배치를 위해 너비 조정 */
  justify-content: center; /* 중앙에 배치 */
}

.match-point-item i {
  margin-right: 2px; /* 간격을 줄여서 더 붙게 만듦 */
  width: 30px;
  height: 30px;
  font-size: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.news-container {
  padding: 20px;
}

.news-title {
  text-align: left;
  margin: 20px 0 0 10px;
  font-size: 20px;
  letter-spacing: -1px;
  font-weight: 600;
}

.rounded-section {
  border-radius: 15px; /* 외곽선을 둥글게 만듦 */
}
</style>
