<template>
  <div style="background-color: #f2f2f2; padding-bottom: 50px">
    <!-- 큰 이미지 배너 -->
    <div class="container d-flex justify-content-center text-center">
      <div
        v-if="clubBoard && !mapView && imgUrls.length"
        style="width: 1000px; position: relative"
      >
        <div class="img-gallery">
          <img
            v-if="imgUrls[currentImageIndex]"
            :src="imgUrls[currentImageIndex]"
            alt="club_photo"
            class="d-inline-block align-text-top"
            style="width: 1000px; height: 500px"
          />
          <button @click="prevImage" class="nav-button prev-button">
            &#10094;
          </button>
          <button @click="nextImage" class="nav-button next-button">
            &#10095;
          </button>
        </div>
      </div>
      <div
        v-else-if="mapView"
        id="map"
        style="width: 1000px; height: 500px"
        ref="map"
      ></div>
      <div
        v-else
        style="
          width: 1000px;
          height: 500px;
          display: flex;
          justify-content: center;
          align-items: center;
        "
      >
        <div>Loading...</div>
      </div>
    </div>

    <div
      class="container text-center d-flex"
      style="width: 1000px; margin: 0 auto"
    >
      <!-- 왼쪽 섹션 -->
      <div style="flex: 1; margin-right: 20px">
        <!-- 매치 포인트 섹션 -->
        <div
          class="row rounded-section"
          style="background-color: #ffffff; margin-bottom: 20px; height: 200px"
        >
          <div class="col-12">
            <h3 class="section-title">매치 포인트</h3>
            <div v-if="clubBoard" class="match-point-content">
              <div class="match-point-item">
                <i class="fas fa-venus-mars"></i>
                <span>{{ clubBoard.sex }}</span>
              </div>
              <div class="match-point-item">
                <i class="fas fa-futbol"></i>
                <span
                  >{{ clubBoard.peoplesMatch }} {{ clubBoard.matchForm }}</span
                >
              </div>
              <div class="match-point-item">
                <i class="fas fa-users"></i>
                <span
                  >{{ clubBoard.minQuota }} ~ {{ clubBoard.maxQuota }}명</span
                >
              </div>
              <div class="match-point-item">
                <i class="fas fa-running"></i>
                <span>{{ clubBoard.material }}</span>
              </div>
            </div>
          </div>
        </div>

        <!-- 매치 진행방식 섹션 -->
        <div
          class="row rounded-section"
          style="
            background-color: #ffffff;
            margin-bottom: 20px;
            min-height: 200px;
          "
        >
          <div class="col-12">
            <h3 class="section-title">매치 진행방식</h3>
            <p
              class="match-details"
              v-if="clubBoard"
              v-html="formattedContent(clubBoard.content)"
            ></p>
          </div>
        </div>

        <!-- 신청 현황 확인 섹션 -->
        <div
          v-if="isAuthor"
          class="row rounded-section"
          style="
            background-color: #ffffff;
            margin-bottom: 20px;
            min-height: 200px;
          "
        >
          <div class="col-12 text-center">
            <h3 class="section-title">신청 현황</h3>
            <table class="table table-bordered table-striped">
              <thead>
                <tr>
                  <th style="text-align: center">참가자</th>
                  <th style="text-align: center">승인 상태</th>
                  <th style="text-align: center">승인하기</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="participant in participants" :key="participant.id">
                  <td>{{ participant.userId }}</td>
                  <td>
                    {{ participant.approval === "N" ? "미승인" : "승인됨" }}
                  </td>
                  <td>
                    <button
                      v-if="participant.approval === 'N'"
                      @click="approveParticipant(participant.userId)"
                      class="approve-button"
                    >
                      승인하기
                    </button>
                    <button
                      v-else
                      @click="cancelApproval(participant.userId)"
                      class="second-cancel-button"
                    >
                      승인취소
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <!-- 뉴스 섹션 -->
      <div
        class="news-container rounded-section"
        style="
          background-color: #ffffff;
          flex-basis: calc(35% + 10px);
          position: sticky;
          top: 5px;
          margin-right: -10px;
          max-height: 40vh;
          overflow-y: auto;
        "
      >
        <div v-if="clubBoard" class="date-container">
          {{ formatDate(clubBoard.startTime) }}
        </div>
        <div v-if="clubBoard" class="club-title">{{ clubBoard.title }}</div>
        <div v-if="clubBoard" class="news-content">
          <div class="address-container">
            <div class="address-text">{{ clubBoard.address }}</div>
            <div class="address-actions">
              <button @click="copyAddress" class="small-button">
                주소 복사
              </button>
              <button @click="toggleMapView" class="small-button">
                지도 보기
              </button>
            </div>
          </div>
          <div class="likes-container">
            <i class="fas fa-heart liked" style="color: red"></i>
            <span style="color: grey; margin-left: 5px">{{
              clubBoard.likes
            }}</span>
          </div>
          <hr class="separator" />
          <div class="fee-time-info">
            <span class="fee-info"
              >{{ formatCurrency(clubBoard.participationFee) }}원</span
            >
            <span class="time-info">
              /
              {{
                calculateMatchDuration(clubBoard.startTime, clubBoard.endTime)
              }}</span
            >
          </div>
          <div class="like-apply-container">
            <div class="like-container">
              <i
                :class="{
                  'fas fa-heart liked': isLiked,
                  'far fa-heart': !isLiked,
                }"
                @click="toggleLike"
              >
              </i>
              <div class="like-text">
                <p>모집 게시글이 마음에 든다면</p>
                <p>좋아요를 눌러보세요!</p>
              </div>
            </div>
            <div v-if="!isAuthor" class="apply-container">
              <button
                v-if="!isApplied"
                @click="applyForClub"
                class="apply-button"
              >
                신청하기
              </button>
              <button
                v-else
                @click="cancelApplication"
                class="first-cancel-button"
              >
                취소하기
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 푸터 -->
    <footer style="background-color: #f2f2f2; padding: 20px">
      <div class="container">
        <p class="text-center">© 2024 LOTTO Company. All Rights Reserved.</p>
      </div>
    </footer>
  </div>
</template>

<script>
import store from "@/store"; // Vuex store import
import ClubBoardService from "@/services/board/club/ClubBoardService";
import ClubBoardLikeService from "@/services/board/club/ClubBoardLikeService";
import ParticipantsService from "@/services/board/club/ParticipantsService";

export default {
  data() {
    return {
      clubBoard: null,
      imgUrls: [],
      currentImageIndex: 0,
      mapView: false,
      imageLoading: true,
      isLiked: false,
      isApplied: false, // 신청 여부 상태 추가
      isAuthor: false, // 작성자 여부 상태 추가
      participants: [], // 신청자 목록 추가
      likeId: null, // 좋아요 ID 상태 추가
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
          this.imgUrls = data.map((item) => item.imgUrl);
          this.isAuthor = this.clubBoard.userId === store.state.user.userId; // 작성자 여부 확인
        }

        this.imageLoading = false;
        await this.checkParticipant(); // 신청 여부 확인
        await this.checkLike(); // 좋아요 여부 확인
        if (this.isAuthor) {
          await this.fetchParticipants(); // 작성자인 경우 신청자 목록 조회
        }
      } catch (error) {
        console.error("Error fetching club board details:", error);
        this.imageLoading = false;
      }
    },
    async checkParticipant() {
      const clubBoardId = this.$route.params.clubBoardId;
      const userId = store.state.user.userId; // Vuex store에서 로그인한 유저 ID 가져오기
      try {
        const response = await ParticipantsService.checkParticipant(
          clubBoardId,
          userId
        );
        this.isApplied = response.data.isApplied;
      } catch (error) {
        console.error("Error checking participant:", error);
      }
    },
    async fetchParticipants() {
      const clubBoardId = this.$route.params.clubBoardId;
      try {
        const response = await ParticipantsService.getParticipants(clubBoardId);
        this.participants = response.data;
      } catch (error) {
        console.error("Error fetching participants:", error);
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
      if (!kakao || !kakao.maps) {
        console.error("Kakao Maps API를 사용할 수 없습니다.");
        return;
      }
      const container = this.$refs.map;
      if (!container) {
        console.error(
          "지도를 초기화할 수 없습니다: 컨테이너 요소를 찾을 수 없습니다."
        );
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
            content:
              '<div style="width:150px;text-align:center;padding:6px 0;">경기장소</div>',
          });
          infowindow.open(map, marker);
          map.setCenter(coords);
        }
      });
    },
    formatDate(datetime) {
      const date = new Date(datetime);
      const day = date.getDate();
      const dayOfWeek = ["일", "월", "화", "수", "목", "금", "토"][
        date.getDay()
      ];
      const month = date.getMonth() + 1;
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");
      return `${month}월 ${day}일 ${dayOfWeek}요일 ${hours}:${minutes}`;
    },
    copyAddress() {
      const address = this.clubBoard.address;
      const textarea = document.createElement("textarea");
      textarea.value = address;
      document.body.appendChild(textarea);
      textarea.select();
      try {
        document.execCommand("copy");
        alert("주소가 복사되었습니다.");
      } catch (err) {
        console.error("주소 복사 실패:", err);
      } finally {
        document.body.removeChild(textarea);
      }
    },
    calculateMatchDuration(startTime, endTime) {
      const start = new Date(startTime);
      const end = new Date(endTime);
      const diff = (end - start) / (1000 * 60 * 60); // 차이를 시간 단위로 계산
      const hours = Math.floor(diff);
      return `${hours}시간`;
    },
    formatCurrency(value) {
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    async applyForClub() {
      const clubBoardId = this.$route.params.clubBoardId;
      const userId = store.state.user.userId; // Vuex store에서 로그인한 유저 ID 가져오기
      try {
        const response = await ParticipantsService.createParticipant(
          clubBoardId,
          userId
        );
        alert("신청이 성공적으로 완료되었습니다.");
        this.isApplied = true; // 신청 완료 후 상태 업데이트
        console.log("신청 성공:", response.data);
      } catch (error) {
        alert("신청에 실패했습니다. 다시 시도해주세요.");
        console.error("신청 실패:", error);
      }
    },
    async cancelApplication() {
      const clubBoardId = this.$route.params.clubBoardId;
      const userId = store.state.user.userId; // Vuex store에서 로그인한 유저 ID 가져오기
      try {
        await ParticipantsService.deleteParticipant(clubBoardId, userId);
        alert("신청이 성공적으로 취소되었습니다.");
        this.isApplied = false; // 신청 취소 후 상태 업데이트
        console.log("신청 취소 성공");
      } catch (error) {
        alert("신청 취소에 실패했습니다. 다시 시도해주세요.");
        console.error("신청 취소 실패:", error);
      }
    },
    async approveParticipant(userId) {
      const clubBoardId = this.$route.params.clubBoardId;
      try {
        await ParticipantsService.toggleApproval(clubBoardId, userId);
        alert("승인 상태가 변경되었습니다.");
        await this.fetchParticipants(); // 승인 후 신청자 목록 갱신
      } catch (error) {
        alert("승인 상태 변경에 실패했습니다. 다시 시도해주세요.");
        console.error("승인 상태 변경 실패:", error);
      }
    },
    async cancelApproval(userId) {
      const clubBoardId = this.$route.params.clubBoardId;
      try {
        await ParticipantsService.toggleApproval(clubBoardId, userId);
        alert("승인 상태가 변경되었습니다.");
        await this.fetchParticipants(); // 승인 후 신청자 목록 갱신
      } catch (error) {
        alert("승인 상태 변경에 실패했습니다. 다시 시도해주세요.");
        console.error("승인 상태 변경 실패:", error);
      }
    },
    async toggleLike() {
      const clubBoardId = this.$route.params.clubBoardId;
      const userId = store.state.user.userId; // Vuex store에서 로그인한 유저 ID 가져오기

      if (this.isLiked) {
        if (!this.likeId) {
          console.error("Like ID is not defined");
          return;
        }
        try {
          await ClubBoardLikeService.deleteClubBoardLike(this.likeId);
          this.isLiked = false;
          this.likeId = null;
          this.clubBoard.likes -= 1;
        } catch (error) {
          console.error("Error deleting like:", error);
        }
      } else {
        try {
          const response = await ClubBoardLikeService.createClubBoardLike({
            userId,
            clubBoardId,
          });
          this.isLiked = true;
          this.likeId = response.data.likeId;
          this.clubBoard.likes += 1;
        } catch (error) {
          console.error("Error creating like:", error);
          if (
            error.response &&
            error.response.data === "Already liked by this user"
          ) {
            // 이미 좋아요가 존재하는 경우 상태를 유지
            this.isLiked = true;
          }
        }
      }
    },
    async checkLike() {
      const clubBoardId = this.$route.params.clubBoardId;
      const userId = store.state.user.userId; // Vuex store에서 로그인한 유저 ID 가져오기
      try {
        const response =
          await ClubBoardLikeService.findLikeIdByUserIdAndClubBoardId(
            userId,
            clubBoardId
          );
        if (response.status === 204) {
          // NO_CONTENT 상태 코드 처리
          this.isLiked = false;
          this.likeId = null;
        } else {
          this.isLiked = true;
          this.likeId = response.data;
        }
      } catch (error) {
        console.error("Error checking like:", error);
        this.isLiked = false;
        this.likeId = null; // 에러 발생 시 likeId 초기화
      }
    },
    formattedContent(content) {
      return content.replace(/\n/g, "<br/>");
    },
  },
  async mounted() {
    window.scrollTo(0, 0);
    await this.fetchClubBoardDetails();
    if (typeof window.kakao === "undefined" || !window.kakao.maps) {
      console.error("Kakao Maps API를 로드할 수 없습니다.");
      return;
    }
  },
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
  margin-top: 45px;
  gap: 10px;
}

.match-point-item {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 48%;
  margin: 2px;
}

.match-point-item i {
  margin-right: 8px;
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
  font-size: 20px;
  font-weight: bold;
  margin-top: 20px;
  margin-left: 20px;
}

.club-title {
  text-align: left;
  margin: 30px 0 0 0;
  font-size: 30px;
  letter-spacing: -1px;
  font-weight: 600;
  word-wrap: break-word;
  width: 100%;
}

.news-content {
  margin-top: 10px;
}

.address-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  font-size: 16px;
}

.address-text {
  text-align: left;
  margin-bottom: 10px;
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

.separator {
  border: none;
  height: 2px;
  background-color: #888; /* 더 진한 색상으로 변경 */
  margin: 20px 0;
  width: 100%; /* 전체 너비보다 조금 짧게 */
}

.fee-time-info {
  font-size: 20px;
  font-weight: bold;
  margin-top: 10px;
  text-align: left; /* 왼쪽 정렬 */
}

.fee-info {
  font-size: 20px;
  font-weight: bold;
}

.time-info {
  font-size: 14px;
  color: gray;
}

.closing-info {
  font-size: 14px;
  color: gray;
  margin-top: 5px;
  text-align: left; /* 왼쪽 정렬 */
}

.like-apply-container {
  display: flex;
  align-items: center;
  margin-top: 40px;
  text-align: left;
}

.like-container {
  display: flex;
  align-items: center;
  gap: 10px;
}

.like-container i {
  font-size: 24px;
  cursor: pointer;
}

.like-container .liked {
  color: red;
}

.like-text {
  font-size: 12px;
  color: gray;
  text-align: left;
}

.like-text p {
  margin: 0;
  padding: 0;
}

.apply-container {
  display: flex;
  justify-content: center;
  margin-left: auto;
}

.apply-button {
  background-color: #162b59;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
}

.apply-button:hover {
  background-color: #0d1f3c;
}

.first-cancel-button {
  background-color: red;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
}

.first-cancel-button:hover {
  background-color: darkred;
}

.second-cancel-button {
  background-color: red;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.second-cancel-button:hover {
  background-color: darkred;
}

.approve-button {
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.approve-button:hover {
  background-color: #218838;
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

.likes-container {
  display: flex;
  align-items: center;
  font-size: 14px;
  margin-top: 10px;
}

.likes-container .small-heart {
  color: red;
  margin-right: 5px;
}

.participant-status {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.participant-item {
  display: flex;
  align-items: center;
  margin: 10px 0;
  width: 100%;
}

.participant-item span {
  margin-right: 10px;
}

.participant-item div {
  margin-left: 10px;
}

.match-details {
  white-space: pre-wrap;
  text-align: left;
  margin-left: 20px;
}

.table {
  width: 100%;
  margin-bottom: 1rem;
  color: #212529;
  border-collapse: collapse;
}

.table th,
.table td {
  padding: 0.75rem;
  vertical-align: top;
  border-top: 1px solid #dee2e6;
  text-align: center;
}

.table thead th {
  vertical-align: bottom;
  border-bottom: 2px solid #dee2e6;
}

.table tbody + tbody {
  border-top: 2px solid #dee2e6;
}

.table-bordered {
  border: 1px solid #dee2e6;
}

.table-bordered th,
.table-bordered td {
  border: 1px solid #dee2e6;
}

.table-bordered thead th,
.table-bordered thead td {
  border-bottom-width: 2px;
}

.table-striped tbody tr:nth-of-type(odd) {
  background-color: rgba(0, 0, 0, 0.05);
}
</style>
