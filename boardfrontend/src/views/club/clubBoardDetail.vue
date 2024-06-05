<template>
  <div style="background-color: #f2f2f2; height: 5000px">
    <div class="container d-flex justify-content-center text-center">
      <div v-if="clubBoard && !mapView" style="width: 80%; height: 500px; background-color: #4480fe">
        <img :src="clubBoard.imgUrl" alt="club_photo" width="80%" height="500" class="d-inline-block align-text-top" />
      </div>
      <div id="map" style="width: 80%; height: 500px" ref="map" v-else></div>
    </div>

    <div class="container text-center mt-1">
      <div class="container text-center">
        <div class="lotto_new row row-cols-lg-2 gap-2 justify-content-center">
          <div class="col match-point-container" style="background-color: #ffffff; height: 400px; max-width: 40%">
            <p class="container text-left match-point-title">
              매치 포인트
            </p>
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
          <div class="col" style="background-color: #ffffff; height: 400px; max-width: 40%">
            news 2
            <button @click="popUpMap">장소 사진 보기</button>
          </div>
        </div>

        <div class="d-flex justify-content-center">
          <div class="row mt-3" style="background-color: #ffffff; width: 80%; height: 700px">
            <p class="container text-left" style="text-align: left; margin: 20px 0 0 10px; font-size: 20px; letter-spacing: -1px; font-weight: 600;">
              매치 데이터
            </p>
            <p v-if="clubBoard">{{ clubBoard.location }}</p>
          </div>
        </div>

        <div class="d-flex justify-content-center">
          <div class="row mt-3" style="background-color: #ffffff; width: 80%; height: 700px; text-align: left">
            <p class="container text-left" style="text-align: left; margin: 20px 0 0 10px; font-size: 20px; letter-spacing: -1px; font-weight: 600;">
              매치 진행방식
            </p>
            <ul style="padding: 0 0 0 30px; font-size: 20px; padding-left: 50px">
              <b>매치 규칙</b>
              <li>모든 파울은 사이드라인에서 킥인</li>
              <li>골키퍼에게 백패스 가능 손으로는 잡으면 안 돼요</li>
              <li>사람을 향한 태클 금지</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ClubBoardService from "@/services/board/club/ClubBoardService";

export default {
  data() {
    return {
      clubBoard: null,
      options: {
        center: { lat: 33.450701, lng: 126.570667 },
        level: 3,
      },
      address: "",
      mapView: true,
    };
  },
  methods: {
    async fetchClubBoardDetails() {
      const clubBoardId = this.$route.params.clubBoardId;
      try {
        console.log("Fetching details for clubBoardId:", clubBoardId);
        const response = await ClubBoardService.getClubOnce(clubBoardId);
        console.log("Response data:", response.data);
        this.clubBoard = response.data[0]; // 첫 번째 요소를 clubBoard에 할당
        this.address = this.clubBoard.address;
        this.retrieveMap(this.address);
      } catch (error) {
        console.error("Error fetching club board details:", error);
      }
    },
    popUpMap() {
      this.mapView = !this.mapView;
      if (this.mapView) {
        this.$nextTick(() => {
          setTimeout(() => {
            this.retrieveMap(this.address);
          }, 0);
        });
      }
    },
    retrieveMap(address) {
      let kakao = window.kakao;
      var container = this.$refs.map;
      const { center, level } = this.options;

      var map = new kakao.maps.Map(container, {
        center: new kakao.maps.LatLng(center.lat, center.lng),
        level,
      });

      var geocoder = new kakao.maps.services.Geocoder();

      geocoder.addressSearch(address, function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          var marker = new kakao.maps.Marker({
            map: map,
            position: coords,
          });

          var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">경기장소</div>',
          });
          infowindow.open(map, marker);

          map.setCenter(coords);
        }
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

.match-point-title {
  text-align: left;
  margin: 20px 0 0 10px;
  font-size: 20px;
  letter-spacing: -1px;
  font-weight: 600;
}

.match-point-content {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin-top: 20px;
}

.match-point-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.match-point-item i {
  margin-right: 10px;
  width: 30px;
  height: 30px;
  font-size: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
