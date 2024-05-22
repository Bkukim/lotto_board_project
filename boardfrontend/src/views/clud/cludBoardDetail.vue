<template>
  <div class="match-form-wrapper">
    <div class="match-form">
      <!-- 경기장 사진 첨부 -->
      <input type="file" @change="handlePhotoUpload" />

      <!-- 매치 포인트 설정 -->
      <div class="match-points">
        <!-- 성별 선택 -->
        <div class="select-wrapper">
          <select v-model="matchDetails.gender" class="half-width-select">
            <option disabled value="">성별 선택</option>
            <option value="남자만">남자만</option>
            <option value="여자만">여자만</option>
            <option value="남녀 모두">남녀 모두</option>
          </select>
          <div class="selected-value fixed-size">{{ matchDetails.gender }}</div>
        </div>

        <!-- 팀 인원 수 설정 -->
        <div class="select-wrapper">
          <select v-model="matchDetails.teamSize" class="half-width-select">
            <option disabled value="">팀 인원 수 선택</option>
            <option value="5vs5">5vs5</option>
            <option value="6vs6">6vs6</option>
          </select>
          <div class="selected-value fixed-size">{{ matchDetails.teamSize }}</div>
        </div>

        <!-- 경기 형식 설정 -->
        <div class="select-wrapper">
          <select v-model="matchDetails.matchType" class="half-width-select">
            <option disabled value="">경기 형식 선택</option>
            <option value="3파전">3파전</option>
            <option value="토너먼트">토너먼트</option>
          </select>
          <div class="selected-value fixed-size">{{ matchDetails.matchType }}</div>
        </div>

        <!-- 전체 모집 인원 수 -->
        <input type="text" v-model="matchDetails.totalPlayers" placeholder="전체 모집 인원 수" />

        <!-- 준비물 선택 -->
        <div class="select-wrapper">
          <select v-model="matchDetails.equipment" class="half-width-select">
            <option disabled value="">준비물 선택</option>
            <option value="풋살화만">풋살화만</option>
            <option value="운동화만">운동화만</option>
            <option value="축구화만">축구화만</option>
            <option value="풋살화/운동화">풋살화/운동화</option>
            <option value="풋살화/축구화">풋살화/축구화</option>
          </select>
          <div class="selected-value fixed-size">{{ matchDetails.equipment }}</div>
        </div>
      </div>

      <!-- 날짜와 시간 설정 -->
      <input type="datetime-local" v-model="matchDetails.dateTime" />

      <!-- 장소명 입력 -->
      <input type="text" v-model="matchDetails.locationName" placeholder="장소명 입력" />

      <!-- 주소 설정 -->
      <div>
        <input type="text" v-model="matchDetails.address" placeholder="주소 검색" readonly @click="openAddressSearch" />
      </div>

      <!-- 참가비 설정 -->
      <input type="text" v-model="matchDetails.entryFee" placeholder="참가비 설정" />

      <!-- 구장 정보 입력 -->
      <textarea v-model="matchDetails.stadiumInfo" placeholder="구장 정보 입력"></textarea>

      <!-- 매치 진행 방식 입력 -->
      <textarea v-model="matchDetails.matchProgress" placeholder="매치 진행 방식 입력"></textarea>

      <!-- 제출 버튼 -->
      <button @click="submitForm">제출</button>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const matchDetails = ref({
      photo: null,
      gender: '',
      teamSize: '',
      matchType: '',
      totalPlayers: '',
      equipment: '',
      dateTime: '',
      locationName: '',
      address: '',
      entryFee: '',
      stadiumInfo: '',
      matchProgress: '',
    });

    function handlePhotoUpload(event) {
      matchDetails.value.photo = event.target.files[0];
    }

    function openAddressSearch() {
      if (typeof daum === 'undefined') {
        alert('Daum 우편번호 서비스를 로드할 수 없습니다.');
        return;
      }
      new daum.Postcode({
        oncomplete: function (data) {
          matchDetails.value.address = data.address;
        },
      }).open();
    }

    function submitForm() {
      console.log(matchDetails.value);
      // 실제 애플리케이션에서는 이 데이터를 서버에 전송하는 로직이 필요합니다.
    }

    return {
      matchDetails,
      handlePhotoUpload,
      openAddressSearch,
      submitForm,
    };
  },
};
</script>

<style>
.match-form-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: calc(100vh - 100px);
  padding: 20px;
}

.match-form {
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 600px;
}

.match-form > div, 
.match-form > input, 
.match-form > select, 
.match-form > textarea, 
.match-form > button {
  margin: 10px 0;
}

.match-points .select-wrapper {
  display: flex;
  align-items: center;
  margin: 10px 0;
}

.match-points .select-wrapper .half-width-select {
  flex: 1;
  width: 50%; /* 셀렉트 박스의 가로 길이를 절반으로 줄임 */
}

.match-points .select-wrapper .fixed-size {
  margin-left: 10px;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
  min-height: 25px;
  min-width: 150px; /* 선택된 값의 표시 영역 사이즈 고정 */
  text-align: center;
}
</style>