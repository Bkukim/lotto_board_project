<template>
  <div>
    <div class="container text-center mt-5 mb-5" id="fb_all">
      <div class="weather-forecast mb-5">
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
                    getWeatherIcon(day.day.condition.text)
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

    <div class="container text-center mt-5 mb-5">
      <div class="club-board">
        <h3 class="mb-5">동아리 게시판</h3>
        <p class="mb-5">
          동아리 게시판은 자유로운 공간으로 건의관련 답변은 드리지
          않습니다. <br />
          건의관련 문의사항은 건의게시판을 이용해주시길 바랍니다.
        </p>

        <!-- 검색 박스 -->
        <div class="container" style="gap: 5px" id="search_box">
          <div class="row">
            <div class="col">
              <button
                class="btn btn-primary dropdown-toggle"
                type="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                id="search_ck"
              >
                -- 검색 선택 ---
              </button>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li><a class="dropdown-item" href="#">Something else here</a></li>
              </ul>
            </div>
            <div class="col">
              <!-- 검색어를 입력하세요 -->
              <div class="input-group mb-3">
                <input
                  type="text"
                  class="form-control"
                  aria-label="Sizing example input"
                  aria-describedby="inputGroup-sizing-default"
                  placeholder="검색어를 입력하세요."
                />
              </div>
            </div>
            <!-- 검색 버튼 -->
            <div class="col-auto">
              <!-- col-auto로 변경하여 너비를 자동 조정 -->
              <button
                class="btn btn-outline-secondary"
                type="button"
                id="button-search"
              >
                검색
              </button>
            </div>
            <!--  초기화 버튼 -->
            <div class="col-auto">
              <!-- col-auto로 변경하여 너비를 자동 조정 -->
              <button
                class="btn btn-outline-secondary"
                type="button"
                id="button-reset"
              >
                초기화
              </button>
            </div>
          </div>
        </div>
        <!-- 검색박스 끝 -->

        <!-- 테이블 -->
        <div class="club-board-table">
          <table class="table mt-5">
            <thead>
              <tr>
                <th scope="col">시간</th>
                <th scope="col">제목</th>
                <th scope="col"></th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <!-- 반복문 시작할 행 -->
              <tr v-for="(data, index) in freeList" :key="index">
                <td>
                  {{ index + 1 }}
                </td>
                <td class="col-8"></td>
                <!-- 마감임박 버튼 -->
                <td>
                  <button
                    type="button"
                    class="btn btn-danger"
                    style="background-color: #ff5542; border: none"
                  >
                    마감임박!
                  </button>
                </td>
                <!-- 신청가능 버튼 -->
                <td>
                  <button
                    type="button"
                    class="btn btn-danger"
                    style="background-color: #1570ff; border: none"
                  >
                    신청가능
                  </button>
                </td>
                <!-- 마감 버튼 -->
                <td>
                  <button
                    type="button"
                    class="btn btn-danger"
                    style="background-color: #cccccc; border: none"
                  >
                    마감
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
            <!-- 글쓰기 버튼-->
    <div class="mt-5">
        <router-link to="/club/club-boardDetail">
        <button
          class="btn btn-outline-secondary"
          type="button"
          id="button-Writing"
          style="margin-left: 1220px;"
        >
          글쓰기
        </button>
      </router-link>
    </div>

        <!-- 페이징 -->
        <!-- {/* paging 시작 */} -->
        <div class="row justify-content-center mt-4">
          <div class="col-auto">
            <b-pagination
              class="custom-pagination col-12 mb-3"
              v-model="page"
              :total-rows="count"
              :per-page="pageSize"
              @click="retrieveQna"
            ></b-pagination>
          </div>
        </div>
        <!-- 자유게시판 중앙정렬 전체박스 끝 -->
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      forecast: null,
      loading: true,
      error: null,
      freeList: [1,2,3,4,5]
    };
  },
  created() {
    this.fetchWeatherForecast();
  },
  methods: {
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
    navigateToWrite() {
      this.$router.push('/club/club-boardDetail');
    },
  }
};
</script>

<style scoped>
h3,
p {
  letter-spacing: -1px;
}

/* 페이징 번호 디자인 */
.custom-pagination .page-item.active .page-link {
  background-color: #342a26;
  border-color: #ffffff;
  color: white;
}

.custom-pagination .page-link {
  color: #342a26;
}

.custom-pagination .page-link:hover {
  background-color: #ffffff;
  border-color: 1px solid#8f8f8f;
  color: #342a26;
  /* border: none; */
}

.custom-pagination .page-link:focus {
  outline: none;
  box-shadow: 0 0 0 0.2rem #342a26bf;
  border-color: #342a26bf;
}

/* 검색버튼 */
.btn {
  margin: 0 2px; /* 버튼 간 간격을 줄이기 위해 여백을 조정 */
}

/* 검색 전체 배경 */
#search_box {
  background-color: #e2e2e28c;
  height: 75PX;
  padding: 20px;
  margin-bottom: 20px; /* 날씨 부분과의 간격 조정 */
}
#search_ck {
  background-color: #ffffff;
  color: #212121;
  border: none;
}
#button-search {
  background-color: #162b59;
  color: #ffffff;
  border: none;
}

#button-reset {
  background-color: #162b59;
  color: #ffffff;
  border: none;
}

/* 날씨 부분 */
.weather-forecast {
  font-family: Arial, sans-serif;
  margin: 20px;
  margin-top: 0; /* 위쪽 간격 제거 */
}

.weather-forecast h3 {
  font-size: 24px;
  margin-bottom: 20px;
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

#button-search, #button-Writing {
  background-color: #162b59;
  color: #ffffff;
  border: none;
}
</style>
