<template>
  <div class="club-board-list-wrapper">
    <!-- 일기예보 -->
    <div class="container text-center mb-2" id="fb_all">
      <div class="weather-forecast mb-3">
        <h3 class="mb-3">부산광역시 일주일 날씨 예보</h3>
        <div v-if="loading">로딩 중...</div>
        <div v-if="error">{{ error }}</div>
        <div v-if="forecast">
          <div class="weather-card-container">
            <div v-for="day in forecast" :key="day.date" class="weather-card">
              <div class="weather-date">{{ formatDate(day.date) }}</div>
              <div class="weather-info">
                <i
                  :class="[
                    'weather-icon',
                    getWeatherIcon(day.day.condition.text),
                  ]"
                ></i>
                <div class="temperature-info">
                  <div>최고 온도: {{ day.day.maxtemp_c }}°C</div>
                  <div>최저 온도: {{ day.day.mintemp_c }}°C</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="date-selector">
      <!-- 날짜 선택 영역 -->
      <button @click="prevDateRange" class="arrow-button">◀</button>
      <div class="date-buttons" ref="dateButtonsContainer">
        <button
          v-for="(day, index) in dateRange"
          :key="index"
          :class="['date-button', { active: index === selectedDateIndex }]"
          @click="selectDate(index)"
        >
          {{ day.label }}
        </button>
      </div>
      <button @click="nextDateRange" class="arrow-button">▶</button>
    </div>
    <div class="club-board-list-container">
      <template v-if="sortedClubs.length === 0">
        <div class="club-board-item">
          <div class="no-clubs-message">
            해당 일자에는 모집 게시글이 존재하지 않습니다.
          </div>
        </div>
      </template>
      <template v-else>
        <div
          v-for="club in sortedClubs"
          :key="club.clubBoardId"
          class="club-board-item"
        >
          <div class="club-time">{{ formatTime(club.startTime) }}</div>
          <div class="club-info">
            <div class="club-title" @click="goToDetail(club.clubBoardId)">
              {{ club.title }}
            </div>
            <div class="club-detail-info">
              {{ club.sex + " · " + club.peoplesMatch + " · " + club.matchForm }}
            </div>
          </div>
          <div class="club-status">
            <button :class="['status-button', club.statusClass]">
              {{ club.status }}
            </button>
          </div>
        </div>
      </template>
    </div>
    <div class="write-button-container">
      <router-link to="/club/club-boardDetail">
        <button class="write-button" type="button" id="button-Writing">
          글쓰기
        </button>
      </router-link>
    </div>
  </div>
</template>

<script>
import ClubBoardService from "@/services/board/club/ClubBoardService";
import axios from 'axios';

export default {
  data() {
    return {
      clubs: [],
      selectedDateIndex: 7, // 디폴트 날짜를 오늘 날짜로 설정
      dateRange: this.generateDateRange(new Date()),

      // 날씨 api 데이터
      forecast: null,
      loading: true,
      error: null,
      freeList: [1, 2, 3, 4, 5],
    };
  },
  created() {
    this.fetchWeatherForecast();
    this.fetchClubs();
  },
  computed: {
    filteredClubs() {
      // 필터링 로직을 추가하여 선택한 날짜의 경기만 필터링합니다.
      const selectedDate = this.dateRange[this.selectedDateIndex].date;
      return this.clubs.filter((club) =>
        club.startTime.startsWith(selectedDate)
      );
    },
    sortedClubs() {
      // 필터링된 클럽들을 시간순으로 정렬합니다.
      return [...this.filteredClubs].sort(
        (a, b) => new Date(a.startTime) - new Date(b.startTime)
      );
    }
  },
  methods: {
    async fetchClubs() {
      try {
        const response = await ClubBoardService.getAllClub();
        this.clubs = response.data.map((club) => ({
          ...club,
          status: this.determineStatus(club),
          statusClass: this.determineStatusClass(club),
        }));
      } catch (e) {
        console.error(e);
      }
    },
    determineStatus(club) {
      const now = new Date();
      const endTime = new Date(club.recruitmentDeadline); // 모집 마감 시간

      if (now >= endTime) {
        return "신청마감";
      } else if (endTime - now <= 30 * 60 * 1000) {
        // 30분 전
        return "마감임박!";
      } else {
        return "신청가능";
      }
    },
    determineStatusClass(club) {
      const now = new Date();
      const endTime = new Date(club.recruitmentDeadline); // 모집 마감 시간

      if (now >= endTime) {
        return "closed";
      } else if (endTime - now <= 30 * 60 * 1000) {
        // 30분 전
        return "closing-soon";
      } else {
        return "open";
      }
    },
    formatTime(datetime) {
      const date = new Date(datetime);
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");
      return `${hours}:${minutes}`;
    },
    generateDateRange(referenceDate) {
      const dates = [];
      for (let i = -7; i <= 7; i++) {
        const date = new Date(referenceDate);
        date.setDate(referenceDate.getDate() + i);
        dates.push({
          label: `${date.getMonth() + 1}/${date.getDate()}`,
          date: date.toISOString().split("T")[0],
        });
      }
      return dates;
    },
    selectDate(index) {
      this.selectedDateIndex = index;
      this.scrollToSelectedDate();
    },
    prevDateRange() {
      const newStartDate = new Date(this.dateRange[0].date);
      newStartDate.setDate(newStartDate.getDate() - 7);
      this.dateRange = this.generateDateRange(newStartDate);
      this.selectedDateIndex = 7; // 새로운 날짜 범위에서 중간 날짜를 선택
    },
    nextDateRange() {
      const newStartDate = new Date(this.dateRange[this.dateRange.length - 1].date);
      newStartDate.setDate(newStartDate.getDate() + 1);
      this.dateRange = this.generateDateRange(newStartDate);
      this.selectedDateIndex = 7; // 새로운 날짜 범위에서 중간 날짜를 선택
    },
    async fetchWeatherForecast() {
      const apiKey = '8dd072cece1b405883082135242105'; // API 키
      const city = 'Busan';
      const url = `http://api.weatherapi.com/v1/forecast.json?key=${apiKey}&q=${city}&days=7`;

      try {
        const response = await axios.get(url);
        this.forecast = response.data.forecast.forecastday;
      } catch (error) {
        this.error = 'Error occurred while fetching weather information.';
      } finally {
        this.loading = false;
      }
    },
    getWeatherIcon(conditionText) {
      if (conditionText.includes("Clear") || conditionText.includes("Sunny")) {
        return 'fas fa-sun';
      } else if (conditionText.includes("Partly cloudy") || conditionText.includes("Partly Cloudy")) {
        return 'fas fa-cloud-sun';
      } else if (conditionText.includes("Cloudy") || conditionText.includes("Overcast")) {
        return 'fas fa-cloud';
      } else if (conditionText.includes("Thunderstorm")) {
        return 'fas fa-bolt';
      } else if (conditionText.includes("Rain") || conditionText.includes("Drizzle") || conditionText.includes("Patchy rain")) {
        return 'fas fa-cloud-showers-heavy';
      } else if (conditionText.includes("Snow")) {
        return 'fas fa-snowflake';
      } else {
        return 'fas fa-cloud';
      }
    },
    getIconColor(conditionText) {
      if (conditionText.includes("Thunderstorm") || conditionText.includes("Rain") || conditionText.includes("Drizzle") || conditionText.includes("Patchy rain")) {
        return 'icon-rainy';
      } else if (conditionText.includes("Snow")) {
        return 'icon-snowy';
      } else {
        return 'icon-default';
      }
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      const day = date.getDate();
      const dayOfWeek = ['일', '월', '화', '수', '목', '금', '토'][date.getDay()];
      return `${day}일 ${dayOfWeek}요일`;
    },
    scrollToSelectedDate() {
      this.$nextTick(() => {
        const selectedButton = this.$el.querySelector(".date-button.active");
        if (selectedButton) {
          const offsetLeft = selectedButton.offsetLeft - (this.$refs.dateButtonsContainer.clientWidth / 2 - selectedButton.clientWidth / 2);
          this.$refs.dateButtonsContainer.scrollLeft = offsetLeft;
        }
      });
    },
    goToDetail(clubBoardId) {
      this.$router.push({ path: `/club/club-boardRecruitment/${clubBoardId}` });
    },
    async preloadImages(imgUrls) {
      const imagePromises = imgUrls.map(async (uuid) => {
        const response = await ClubBoardService.getStadiumImg(uuid);
        const url = URL.createObjectURL(new Blob([response.data]));
        const img = new Image();
        img.src = url;
      });

      await Promise.all(imagePromises);
    }
  }
};
</script>

<style>
.club-board-list-wrapper {
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.date-selector {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.arrow-button {
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
}

.date-buttons {
  display: flex;
  overflow-x: auto;
  scroll-behavior: smooth;
  gap: 10px;
  width: 80%;
  white-space: nowrap;
  padding: 10px 0;
}

.date-button {
  width: 80px;
  height: 60px;
  padding: 10px 0;
  border: 1px solid #ccc;
  border-radius: 30%;
  cursor: pointer;
  background-color: white;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-size: 1rem;
}

.date-button.active {
  background-color: #162b59;
  color: white;
}

.club-board-list-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 70%;
  max-width: 1000px; /* 최대 너비를 설정하여 큰 화면에서도 정렬 */
}

.no-clubs-message {
  padding: 10px;
  font-size: 1.2rem;
  color: #888;
  text-align: center;
  width: 100%; /* 너비를 100%로 설정하여 중앙 정렬 */
}

.club-board-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 10px 0;
  border-bottom: 1px solid #ccc; /* 아래쪽에만 선 추가 */
  margin-bottom: 10px;
}

.club-time {
  width: 10%;
  text-align: center;
}

.club-info {
  width: 70%;
  cursor: pointer; /* 제목 클릭 가능 표시 */
}

.club-title {
  font-weight: bold;
}

.club-detail-info {
  color: gray;
}

.club-status {
  width: 20%;
  text-align: right;
}

.status-button {
  padding: 10px 20px;
  border: none;
  border-radius: 10px; /* 부드러운 라운드 처리 */
  cursor: pointer;
  font-size: 1rem;
}

.status-button.open {
  background-color: blue;
  color: white;
}

.status-button.closing-soon {
  background-color: red; /* 빨간색으로 변경 */
  color: white;
}

.status-button.closed {
  background-color: gray; /* 회색으로 변경 */
  color: white;
}

/* 글쓰기 버튼 스타일 */
.write-button-container {
  display: flex;
  justify-content: flex-end; /* 오른쪽 끝으로 이동 */
  width: 70%; /* club-board-item과 같은 너비로 설정 */
  max-width: 1000px; /* 최대 너비를 설정하여 큰 화면에서도 정렬 */
  margin-top: 20px; /* 페이지네이션 위의 여백 */
}

.write-button {
  background-color: #162b59;
  color: #ffffff;
  border: none;
  padding: 10px 20px;
  border-radius: 10px; /* 부드러운 라운드 처리 */
  cursor: pointer;
  font-size: 1rem;
}

.write-button:hover {
  background-color: #0d1f3c; /* 호버 시 색상 변경 */
}

/* 날씨 부분 */
.weather-forecast {
  font-family: Arial, sans-serif;
  margin: 0 20px 10px 20px; /* 위쪽 간격 제거 */
}

.weather-forecast h3 {
  font-size: 24px;
  margin-bottom: 10px;
}

.weather-forecast ul {
  list-style: none;
  padding: 0;
}

.weather-forecast li {
  margin-bottom: 10px;
}

.weather-forecast strong {
  font-weight: bold;
}

.weather-forecast i {
  margin-left: 10px;
  font-size: 1.5em;
}

.weather-forecast i.icon-default {
  color: #ffcc00; /* 기본 색상 */
}

.weather-forecast i.icon-rainy {
  color: #007bff; /* 비 오는 날 색상 */
}

.weather-forecast i.icon-snowy {
  color: #ffffff; /* 눈 오는 날 색상 */
}

/* 날씨 카드 스타일 */
.weather-card-container {
  display: flex;
  justify-content: center; /* 가로 방향 중앙 정렬 */
  align-items: center; /* 세로 방향 중앙 정렬 */
  flex-wrap: wrap;
}
.weather-card {
  width: calc(100% / 7 - 20px); /* 한 줄에 7개가 나오도록 조절 */
  max-width: 170px; /* 카드의 최대 너비 지정 */
  background-color: #f8f9fa;
  /* padding: 20px; */
  margin-right: 10px; /* 오른쪽 마진 추가 */
  margin-bottom: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.weather-card:last-child {
  margin-right: 0; /* 마지막 카드의 마진을 없앰 */
}

.weather-date {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
}

.weather-info {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 10px;
}

.temperature-info {
  margin-left: 10px;
}

.weather-icon {
  font-size: 36px;
  margin-right: 10px;
}

/* 추가적인 아이콘 스타일링 */
.fas.fa-sun {
  color: #ffce00; /* 해 뜨는 날 아이콘 색상 */
}

.fas.fa-cloud-sun {
  color: #6c757d; /* 구름 조금 낀 날 아이콘 색상 */
}

.fas.fa-cloud {
  color: #6c757d; /* 흐린 날 아이콘 색상 */
}

.fas.fa-bolt {
  color: #1e90ff; /* 천둥 번개 날 아이콘 색상 */
}

.fas.fa-cloud-showers-heavy {
  color: #007bff; /* 비 오는 날 아이콘 색상 */
}

.fas.fa-snowflake {
  color: #ffffff; /* 눈 오는 날 아이콘 색상 */
}
</style>
