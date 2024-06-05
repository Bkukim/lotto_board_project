<template>
  <div class="match-form-wrapper">
    <h3 class="form-title">매칭 글 작성</h3>
    <div class="form-container">
      <div class="match-form">
<input type="file" multiple @change="handlePhotoUpload" class="file-input" />
        <div class="match-points">
          <!-- 성별 선택 -->
          <div class="select-wrapper">
            <select v-model="matchDetails.sex" class="half-width-select">
              <option disabled value="">성별 선택</option>
              <option value="남자만">남자만</option>
              <option value="여자만">여자만</option>
              <option value="남녀 모두">남녀 모두</option>
            </select>
            <div class="selected-value">{{ matchDetails.sex }}</div>
          </div>
          <!-- 경기 인원 수 선택 -->
          <div class="select-wrapper">
            <select v-model="matchDetails.peoplesMatch" class="half-width-select">
              <option disabled value="">경기 인원 수 선택</option>
              <option value="5vs5">5vs5</option>
              <option value="6vs6">6vs6</option>
            </select>
            <div class="selected-value">{{ matchDetails.peoplesMatch }}</div>
          </div>
          <!-- 경기 방식 선택 -->
          <div class="select-wrapper">
            <select v-model="matchDetails.matchForm" class="half-width-select">
              <option disabled value="">경기 방식 선택</option>
              <option value="3파전">3파전</option>
              <option value="토너먼트">토너먼트</option>
            </select>
            <div class="selected-value">{{ matchDetails.matchForm }}</div>
          </div>
          <!-- 모집 인원 수 -->
          <input type="text" v-model="matchDetails.minQuota" placeholder="최소 모집 인원 수" class="input-field" />
          <input type="text" v-model="matchDetails.maxQuota" placeholder="최대 모집 인원 수" class="input-field" />
          <!-- 준비물 선택 -->
          <div class="select-wrapper">
            <select v-model="matchDetails.material" class="half-width-select">
              <option disabled value="">준비물 선택</option>
              <option value="풋살화만">풋살화만</option>
              <option value="운동화만">운동화만</option>
              <option value="축구화만">축구화만</option>
              <option value="풋살화/운동화">풋살화/운동화</option>
              <option value="풋살화/축구화">풋살화/축구화</option>
            </select>
            <div class="selected-value">{{ matchDetails.material }}</div>
          </div>
        </div>
        <!-- 시간 설정 -->
        <div class="time-wrapper">
          <div class="time-setting">
            <label for="startTime" class="time-label">시작 시간 설정</label>
            <input type="datetime-local" id="startTime" v-model="matchDetails.startTime" class="time-input" />
          </div>
          <div class="time-setting">
            <label for="endTime" class="time-label">종료 시간 설정</label>
            <input type="datetime-local" id="endTime" v-model="matchDetails.endTime" class="time-input" />
          </div>
          <div class="time-setting">
            <label for="recruitmentDeadline" class="time-label">모집 마감 시간 설정</label>
            <input type="datetime-local" id="recruitmentDeadline" v-model="matchDetails.recruitmentDeadline" class="time-input" />
          </div>
        </div>
        <!-- 장소 및 기타 정보 입력 -->
        <input type="text" v-model="matchDetails.location" placeholder="장소명 입력" class="input-field full-width" />
        <input type="text" v-model="matchDetails.address" placeholder="주소 검색" readonly @click="openAddressSearch" class="input-field full-width" />
        <input type="text" v-model="matchDetails.participationFee" placeholder="참가비 설정" class="input-field full-width" />
        <textarea v-model="matchDetails.title" placeholder="구장 정보 입력" class="textarea-field"></textarea>
        <textarea v-model="matchDetails.content" placeholder="매치 진행 방식 입력" class="textarea-field"></textarea>
        <button @click="submitForm" class="submit-button">제출하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import ClubBoardService from "@/services/board/club/ClubBoardService";
import store from "@/store";

export default {
  data() {
    return {
      matchDetails: {
        content: "",
        location: "",
        address: "",
        participationFee: undefined,
        startTime: "",
        endTime: "",
        recruitmentDeadline: "",
        maxQuota: "",
        minQuota: "",
        peoplesMatch: "",
        material: "",
        matchForm: "",
        title: "",
        sex: "",
      },
      imgFiles: [], // 여러 파일을 저장하기 위한 배열
    };
  },
  methods: {
    handlePhotoUpload(event) {
      this.imgFiles = Array.from(event.target.files);
      console.log("Selected files:", this.imgFiles);
       // 파일 크기 확인 로직 추가
      const maxSize = 10 * 1024 * 1024; // 10MB
      for (let file of this.imgFiles) {
        if (file.size > maxSize) {
          alert(`파일 크기가 너무 큽니다: ${file.name}`);
          return;
        }
      }
    },
    openAddressSearch() {
      if (typeof daum === "undefined") {
        alert("Daum 우편번호 서비스를 로드할 수 없습니다.");
        return;
      }
      new daum.Postcode({
        oncomplete: (data) => {
          this.matchDetails.address = data.address;
        },
      }).open();
    },
    async submitForm() {
      try {
        let data = {
          ...this.matchDetails,
          userId: store.state.user.userId,
        };

        let formData = new FormData();
        formData.append("data", JSON.stringify(data));
        if (this.imgFiles.length > 0) {
          this.imgFiles.forEach((file) => {
            formData.append(`imgFiles`, file);
          });
        }

        for (var pair of formData.entries()) {
          console.log(pair[0] + ', ' + pair[1]);
        }

        let response = await ClubBoardService.createClub(formData);
        console.log("Response data:", response.data);
        // this.$router.push("/club/club-board");
      } catch (e) {
        console.error("Error:", e);
        if (e.response) {
          console.error("Response data:", e.response.data);
          console.error("Response status:", e.response.status);
          console.error("Response headers:", e.response.headers);
        } else if (e.request) {
          console.error("Request data:", e.request);
        } else {
          console.error("Error message:", e.message);
        }
        console.error("Config:", e.config);
      }
    },
  },
  mounted() {
    console.log("Store state user:", store.state.user.userId);
  },
};
</script>

<style>
.match-form-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  padding: 20px;
  background-color: #f5f5f5;
}

.form-title {
  margin-bottom: 20px;
  letter-spacing: 1.5px;
  color: #999999;
  font-weight: bold;
}

.form-container {
  width: 100%;
  max-width: 1000px;
  background-color: #ffffff;
  box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
  padding: 40px;
  border-radius: 10px;
}

.match-form {
  display: flex;
  flex-direction: column;
}

.file-input {
  color: #999999;
  margin-bottom: 20px;
}

.select-wrapper {
  display: flex;
  align-items: center;
  margin: 10px 0;
}

.half-width-select {
  flex: 1;
  padding: 10px;
  border: 1px solid #cccccc;
  border-radius: 4px;
}

.selected-value {
  margin-left: 10px;
  padding: 10px;
  border: 1px solid #162b59;
  border-radius: 4px;
  color: #162b59;
  font-weight: bold;
  text-align: center;
  width: 150px;
}

.input-field {
  height: 50px;
  padding: 0 20px;
  border: 1px solid #cccccc;
  border-radius: 4px;
  margin-bottom: 10px;
  box-sizing: border-box;
}

.input-field.full-width {
  width: 100%;
}

.input-field::placeholder {
  text-align: center;
}

.time-wrapper {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.time-setting {
  display: flex;
  flex-direction: column;
  width: 32%;
}

.time-label {
  font-size: 14px;
  color: #999999;
  font-weight: bold;
  margin-bottom: 5px;
}

.time-input {
  height: 50px;
  padding-left: 20px;
  border: 1px solid #cccccc;
  border-radius: 4px;
}

.textarea-field {
  height: 100px;
  padding: 10px 20px;
  border: 1px solid #cccccc;
  border-radius: 4px;
  margin-bottom: 10px;
  box-sizing: border-box;
}

.submit-button {
  height: 50px;
  font-weight: bold;
  color: #ffffff;
  font-size: 20px;
  background-color: #162b59;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 20px;
}

.submit-button:hover {
  background-color: #0d1f3c;
}
</style>
