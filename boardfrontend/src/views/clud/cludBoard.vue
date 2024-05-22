<template>
  <div class="container text-center" id="fb_all">
    <div
      class="container text-center mt-5"
      style="background-color: #cccccc; height: 300px"
    >
      이 부분에 이런 이미지 말고 날씨 API 넣을 예정입니다. <br>
      공간만 만들어두면 될
      것 같아요. 
      <br><br>라고 민식씨가 말씀하셨습니다.
    </div>

    <h3 class="mb-5 mt-5">동아리 게시판</h3>
    <p class="mb-5">
      자유게시판은 자유로운 의견을 남기는 공간으로 건의관련 답변은 드리지
      않습니다. <br />
      건의관련 문의사항은 건의게시판을 이용해주시길 바랍니다.
    </p>

    <!-- 검색 박스 -->
    <div class="container text-center" style="gap: 5px" id="search_box">
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
    <table class="table mt-5">
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">등록일</th>
          <th scope="col">조회수</th>
        </tr>
      </thead>
      <tbody>
        <!-- 반복문 시작할 행 -->
        <tr v-for="(data, index) in freeList" :key="index">
          <td>
            {{ index + 1 }}
          </td>
          <td class="col-8"></td>
          <td></td>
          <td></td>
        </tr>
      </tbody>
    </table>

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
  </div>
  <!-- 자유게시판 중앙정렬 전체박스 끝 -->
</template>
    
    <script>
export default {
  data() {
    return {
      freeList: [1, 2, 3, 4],
    };
  },
};
</script>
    
    <style>
/* 페이지 전체 높이 */
#fb_all {
  height: 100vw;
}
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
  height: 4vw;
  padding: 20px;
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
</style>
    