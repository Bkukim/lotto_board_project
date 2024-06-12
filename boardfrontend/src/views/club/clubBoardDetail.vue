<template>
  <div style="background-color: #f2f2f2; padding-bottom: 50px;">
    <!-- 큰 이미지 배너 -->
    <div class="container d-flex justify-content-center text-center">
      <div v-if="clubBoard && !mapView && imgUrls.length" style="width: 1000px; position: relative;">
        <div class="img-gallery">
          <img 
            v-if="imgUrls[currentImageIndex]"
            :src="imgUrls[currentImageIndex]"
            alt="club_photo" 
            class="d-inline-block align-text-top" 
            style="width: 1000px; height: 500px;" 
          />
          <button @click="prevImage" class="nav-button prev-button">&#10094;</button>
          <button @click="nextImage" class="nav-button next-button">&#10095;</button>
        </div>
      </div>
      <div v-else-if="mapView" id="map" style="width: 1000px; height: 500px;" ref="map"></div>
      <div v-else style="width: 1000px; height: 500px; display: flex; justify-content: center; align-items: center;">
        <div>Loading...</div>
      </div>
    </div>

    <div class="container text-center d-flex" style="width: 1000px; margin: 0 auto;">
      <!-- 왼쪽 섹션 -->
      <div style="flex: 1; margin-right: 20px;">
        <!-- 매치 포인트 섹션 -->
        <div class="row rounded-section" style="background-color: #ffffff; margin-bottom: 20px; height: 200px;">
          <div class="col-12">
            <h3 class="section-title">매치 포인트</h3>
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
            <h3 class="section-title">매치 데이터</h3>
            <p v-if="clubBoard">{{ clubBoard.location }}</p>
          </div>
        </div>

        <!-- 매치 진행방식 섹션 -->
        <div class="row rounded-section" style="background-color: #ffffff; margin-bottom: 20px; height: 500px;">
          <div class="col-12">
            <h3 class="section-title">매치 진행방식</h3>
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
        <div v-if="clubBoard" class="date-container">{{ formatDate(clubBoard.startTime) }}</div>
        <div v-if="clubBoard" class="news-title">{{ clubBoard.title }}</div>
        <div v-if="clubBoard" class="news-content">
          <div class="address-container">
            <div>{{ clubBoard.address }}</div>
            <div class="address-actions">
              <button @click="copyAddress" class="small-button">주소 복사</button>
              <button @click="toggleMapView" class="small-button">지도 보기</button>
            </div>
          </div>
        </div>
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
      imgUrls: [],
      currentImageIndex: 0,
      mapView: false,
      imageLoading: true
    };
  },
  methods: {
    async fetchClubBoardDetails() {
      const clubBoardId = this.$route.params.clubBoardId;
      try {
        const response = await ClubBoardService.getClubOnce(clubBoardId);
        const data = response.data;
        
        if (data.length > 0) {
          this.clubBoard = data[0];
          this.imgUrls = data.map(item => item.imgUrl);
        }

        this.imageLoading = false;
      } catch (error) {
        console.error("Error fetching club board details:", error);
        this.imageLoading = false;
      }
    },
    prevImage() {
      if (this.currentImageIndex > 0) {
        this.currentImageIndex--;
      } else {
        this.currentImageIndex = this.imgUrls.length - 1;
      }
    },
    nextImage() {
      if (this.currentImageIndex < this.imgUrls.length - 1) {
        this.currentImageIndex++;
      } else {
        this.currentImageIndex = 0;
      }
    },
    toggleMapView() {
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
      if (!container) {
        console.error("지도를 초기화할 수 없습니다: 컨테이너 요소를 찾을 수 없습니다.");
        return;
      }
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
    formatDate(datetime) {
      const date = new Date(datetime);
      const day = date.getDate();
      const dayOfWeek = ['일', '월', '화', '수', '목', '금', '토'][date.getDay()];
      const month = date.getMonth() + 1;
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      return `${month}월 ${day}일 ${dayOfWeek}요일 ${hours}:${minutes}`;
    },
    copyAddress() {
      const address = this.clubBoard.address;
      navigator.clipboard.writeText(address).then(() => {
        alert('주소가 복사되었습니다.');
      });
    }
  },
  async mounted() {
    await this.fetchClubBoardDetails();
  }
};
</script>

<style>
.match-point-container {
  padding: 20px;
}

.section-title {
  font-size: 20px;
  letter-spacing: -1px;
  font-weight: 600;
  margin-bottom: 10px;
  text-align: left;
  padding-left: 20px;
}

.match-point-content {
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: center;
  margin-top: 40px;
  gap: 0;
}

.match-point-item {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 48%;
  margin: 2px;
  margin-bottom: 2px;
}

.match-point-item i {
  margin-right: 2px;
  width: 30px;
  height: 30px;
  font-size: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.news-container {
  padding: 20px;
  position: relative;
}

.date-container {
  position: absolute;
  top: 0;
  left: 0;
  font-size: 15px;
  font-weight: bold;
  margin-top: 20px;
  margin-left: 20px;
}

.news-title {
  text-align: left;
  margin: 20px 0 0 0;
  font-size: 20px;
  letter-spacing: -1px;
  font-weight: 600;
}

.news-content {
  margin-top: 10px;
}

.address-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 14px;
}

.address-actions {
  display: flex;
  gap: 10px;
}

.small-button {
  background: none;
  border: none;
  color: gray;
  text-decoration: underline;
  cursor: pointer;
  font-size: 12px;
  padding: 0;
}
  
.rounded-section {
  border-radius: 15px;
}

.img-gallery {
  position: relative;
}

.nav-button {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  border: none;
  font-size: 24px;
  padding: 10px;
  cursor: pointer;
}

.prev-button {
  left: 10px;
}

.next-button {
  right: 10px;
}
</style>
