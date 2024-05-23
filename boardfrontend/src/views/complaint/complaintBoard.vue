
<template>
    <div class="container text-center" id="fb_all">
      <h3
        class="mb-5 mt-5"
        style="color: #535353; font-family: Inter-Light, sans-serif; font-weight: 600;"
        id="nt_h3"
      >
        건의/ 신고사항
      </h3>
  
      <!-- 검색 박스 -->
      <div class="container text-center" style="background-color: #e2e2e28c">
        <div
          class="container text-center"
          style="gap: 5px; width: 650px"
          id="search_box"
        >
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
  
  .nt_h3 {
    color: #535353;
    text-align: left;
    font-family: "Inter-Light", sans-serif;
    font-size: 30px;
    font-weight: bold;
  }
  
  /* 검색버튼 */
  .btn {
    margin: 0 2px; /* 버튼 간 간격을 줄이기 위해 여백을 조정 */
  }
  
  /* 검색 전체 배경 */
  #search_box {
    /* background-color: #e2e2e28c; */
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
      
    
    