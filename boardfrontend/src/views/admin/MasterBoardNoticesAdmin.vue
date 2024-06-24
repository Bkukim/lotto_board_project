<template>
  <HeaderCom :hideHeader="true" />
  <!-- 관리자 페이지에서는 헤더를 숨김 -->
  <div class="main-container d-flex" style="height: 900px">
    <AdminHeaderCom class="sidebar" :hideHeader="true" />
    <div class="content-wrapper" style="flex: 1; padding: 20px; height: auto">
      <h2
        class="text-center my-5 font-weight-bold"
        style="letter-spacing: -1.5px"
      >
        공지사항 통합관리
      </h2>
      <div class="d-flex justify-content-between align-items-center mb-2">
        <h3
          class="section-title mt-5 mb-5"
          style="letter-spacing: -3px; font-size: 25px"
        >
          공지사항 목록
        </h3>

        <div class="d-flex justify-content align-items-center">
          <button
            type="button"
            class="btn btn-primary"
            @click="goNoticeAdd"
            style="margin-right: 10px"
          >
            공지사항 등록
          </button>
         
        </div>
      </div>

      <div class="tab-container">
        <ul class="nav nav-tabs">
          <li class="nav-item" @click="activeTab = 'all'">
            <a class="nav-link" :class="{ active: activeTab === 'all' }"
              >전체 게시판</a
            >
          </li>
          <li class="nav-item" @click="activeTab = 'free'">
            <a class="nav-link" :class="{ active: activeTab === 'free' }"
              >자유게시판</a
            >
          </li>
          <li class="nav-item" @click="activeTab = 'dept'">
            <a class="nav-link" :class="{ active: activeTab === 'dept' }"
              >부서게시판</a
            >
          </li>
          <li class="nav-item" @click="activeTab = 'complaint'">
            <a class="nav-link" :class="{ active: activeTab === 'complaint' }"
              >건의게시판</a
            >
          </li>
          <li class="nav-item" @click="activeTab = 'group'">
            <a class="nav-link" :class="{ active: activeTab === 'group' }"
              >동호회 게시판</a
            >
          </li>
        </ul>
        <div class="tab-content">
          <div v-show="activeTab === 'free'">
            <div class="table-responsive shadow">
              <table class="table table-hover">
                <thead class="thead-dark">
                  <tr>
                    <th scope="col">번호</th>
                    <th scope="col">유형</th>
                    <th scope="col">제목</th>
                    <th scope="col">등록일</th>
                    <th scope="col">삭제</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(data, index) in noticeFree" :key="index">
                    <td>{{ index + 1 }}</td>
                    <td>{{ data.noticeType }}</td>
                    <td>{{ data.title }}</td>
                    <td>{{ data.insertTime }}</td>
                    <td>
                      <button
                        type="button"
                        class="btn btn-danger"
                        @click="deleteNotice(data.noticeId)"
                      >
                        삭제
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!-- 부서  -->
          <div v-show="activeTab === 'dept'">
            <div class="table-responsive shadow">
              <table class="table table-hover">
                <thead class="thead-dark">
                  <tr>
                    <th scope="col">번호</th>
                    <th scope="col">유형</th>
                    <th scope="col">제목</th>
                    <th scope="col">등록일</th>
                    <th scope="col">삭제</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(data, index) in noticeDept" :key="index">
                    <td>{{ index + 1 }}</td>
                    <td>{{ data.noticeType }}</td>
                    <td>{{ data.title }}</td>
                    <td>{{ data.insertTime }}</td>
                    <td>
                      <button
                        type="button"
                        class="btn btn-danger"
                        @click="deleteNotice(data.noticeId)"
                      >
                        삭제
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!-- 건의사항 -->
          <div v-show="activeTab === 'complaint'">
            <div class="table-responsive shadow">
              <table class="table table-hover">
                <thead class="thead-dark">
                  <tr>
                    <th scope="col">번호</th>
                    <th scope="col">유형</th>
                    <th scope="col">제목</th>
                    <th scope="col">등록일</th>
                    <th scope="col">삭제</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(data, index) in noticeComplaint" :key="index">
                    <td>{{ index + 1 }}</td>
                    <td>{{ data.noticeType }}</td>
                    <td>{{ data.title }}</td>
                    <td>{{ data.insertTime }}</td>
                    <td>
                      <button
                        type="button"
                        class="btn btn-danger"
                        @click="deleteNotice(data.noticeId)"
                      >
                        삭제
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!-- 그룹 -->
          <div v-show="activeTab === 'group'">
            <div class="table-responsive shadow">
              <table class="table table-hover">
                <thead class="thead-dark">
                  <tr>
                    <th scope="col">번호</th>
                    <th scope="col">유형</th>
                    <th scope="col">제목</th>
                    <th scope="col">등록일</th>
                    <th scope="col">삭제</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(data, index) in noticeGroup" :key="index">
                    <td>{{ index + 1 }}</td>
                    <td>{{ data.noticeType }}</td>
                    <td>{{ data.title }}</td>
                    <td>{{ data.insertTime }}</td>
                    <td>
                      <button
                        type="button"
                        class="btn btn-danger"
                        @click="deleteNotice(data.noticeId)"
                      >
                        삭제
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div> 
          <!-- 전체공지 -->
          <div v-show="activeTab === 'all'">
            <div class="table-responsive shadow">
              <table class="table table-hover">
                <thead class="thead-dark">
                  <tr>
                    <th scope="col">번호</th>
                    <th scope="col">유형</th>
                    <th scope="col">제목</th>
                    <th scope="col">등록일</th>
                    <th scope="col">삭제</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(data, index) in noticeAll" :key="index">
                    <td>{{ index + 1 }}</td>
                    <td>{{ data.noticeType }}</td>
                    <td>{{ data.title }}</td>
                    <td>{{ data.insertTime }}</td>
                    <td>
                      <button
                        type="button"
                        class="btn btn-danger"
                        @click="deleteNotice(data.noticeId)"
                      >
                        삭제
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AdminHeaderCom from "@/components/common/AdminHeaderCom.vue";
import NoticeService from "@/services/notice/NoticeService";

export default {
  components: {
    AdminHeaderCom,
  },
  props: {
    hideHeader: {
      type: Boolean,
      default: true, // 기본값을 false로 설정
    },
  },
  data() {
    return {
      // 받아올 각 부서의 공지사항 목록들
      noticeAll: [], //전체
      noticeDept: [], //부서
      noticeFree: [], //자유
      noticeComplaint: [], //건의
      noticeGroup: [], //동호회
      // 공통속성
      pageSize: 25, // 1페이지당개수(select태그)
      activeTab: "all", // 추가된 부분: 활성화된 탭 상태
    };
  },
  // TODO:  전체
  methods: {
    async retrieveNoticeAll() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await NoticeService.getAllNotice(
          this.pageSize // 1페이지당개수(size) - 5개 이상 등록못하게 할것
        );
        const { noticeAll } = response.data; // 부서배열(벡엔드 전송)
        this.noticeAll = noticeAll; // 부서배열(벡엔드 전송)
        console.log("전체조회", noticeAll);
      } catch (e) {
        console.log(e);
      }
    },

    // TODO:  부서
    async retrieveNoticeDept() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await NoticeService.getDept(
          this.pageSize // 1페이지당개수(size) - 5개 이상 등록못하게 할것
        );
        const { noticeDept } = response.data; // 부서배열(벡엔드 전송)
        this.noticeDept = noticeDept; // 부서배열(벡엔드 전송)
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // TODO:Free
    async retrieveNoticeFree() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await NoticeService.getFree(
          this.pageSize // 1페이지당개수(size) - 5개 이상 등록못하게 할것
        );
        const { noticeFree } = response.data; // 부서배열(벡엔드 전송)
        this.noticeFree = noticeFree; // 부서배열(벡엔드 전송)
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // TODO:Complaint
    async retrieveNoticeComplaint() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await NoticeService.getComplaint(
          this.pageSize // 1페이지당개수(size) - 5개 이상 등록못하게 할것
        );
        const { noticeComplaint } = response.data; // 부서배열(벡엔드 전송)
        this.noticeComplaint = noticeComplaint; // 부서배열(벡엔드 전송)
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // TODO:Complaint
    async retrieveNoticeGroup() {
      try {
        // TODO: 1) 공통 전체조회 함수 실행
        let response = await NoticeService.getGroup(
          this.pageSize // 1페이지당개수(size) - 5개 이상 등록못하게 할것
        );
        const { noticeGroup } = response.data; // 부서배열(벡엔드 전송)
        this.noticeGroup = noticeGroup; // 부서배열(벡엔드 전송)
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },

    // dept지만 전체 공지사항 다 삭제할 수있는것임 나중에 수정 요망
    async deleteNotice(noticeId) {
      if (confirm("공지사항을 삭제하시겠습니까?")) {
        try {
          // todo: 공통 장바구니 삭제 서비스 함수 실행
          let response = await NoticeService.delete(noticeId);
          // 로깅
          console.log(response.data);
          // alert 대화상자
          this.retrieveNoticeAll();
          this.retrieveNoticeDept();
          this.retrieveNoticeFree();
          this.retrieveNoticeComplaint();
          this.retrieveNoticeGroup();
          alert("정상적으로 삭제되었습니다.");
        } catch (e) {
          console.log(e);
        }
      } else {
        return;
      }
    },
    async deleteNoticeAll() {
      if (confirm("모든 공지사항을 삭제하시겠습니까?")) {
        try {
          // todo: 공통 장바구니 삭제 서비스 함수 실행
          let response = await NoticeService.deleteAll();
          // 로깅
          console.log(response.data);
          // alert 대화상자
          alert("정상적으로 삭제되었습니다.");
          this.retrieveNoticeAll();
          this.retrieveNoticeDept();
          this.retrieveNoticeFree();
          this.retrieveNoticeComplaint();
          this.retrieveNoticeGroup();
        } catch (e) {
          console.log(e);
        }
      } else {
        return;
      }
    },

    // gonotice
    goNoticeAdd() {
      switch (true) {
        case this.noticeDept.length === 10:
          alert(
            "[부서] 공지사항이 10개 이상입니다. 오래된 공지사항을 지워주세요"
          );
          this.$router.push("/admin/notice-add");

          break;
        case this.noticeComplaint.length == 10:
          alert(
            "[건의] 공지사항이 10개 이상입니다. 오래된 공지사항을 지워주세요"
          );
          this.$router.push("/admin/notice-add");

          break;
        case this.noticeFree.length == 10:
          alert(
            "[자유] 공지사항이 10개 이상입니다. 오래된 공지사항을 지워주세요"
          );
          this.$router.push("/admin/notice-add");

          break;
        case this.noticeGroup.length == 10:
          alert(
            "[동호회] 공지사항이 10개 이상입니다. 오래된 공지사항을 지워주세요"
          );
          this.$router.push("/admin/notice-add");

          break;
        default:
          this.$router.push("/admin/notice-add");

          break;
      }

      // if (this.noticeDept.length == 10) {
      //   alert("[부서]공지사항이10개 이상입니다. 오래된 공지사항을 지워주세요");
      // } else {
      //   this.$router.push("/admin/notice-add");
      // }
    },
    //   if (this.pageSize == 25) {
    //     alert("공지사항이 25개 이상입니다");
    //   }
    //   else{

    // },
  },
  // 마운티드
  mounted() {
    this.retrieveNoticeAll();
    this.retrieveNoticeDept();
    this.retrieveNoticeFree();
    this.retrieveNoticeComplaint();
    this.retrieveNoticeGroup();
  },
};
</script>

<style scoped>
.main-container {
  display: flex;
}

.sidebar {
  width: 250px;
  background-color: #343a40;
  color: white;
}

.content-wrapper {
  background-color: #f8f9fa;
  padding: 20px;
}

.table {
  background-color: #fff;
  border: 1px solid #dee2e6;
}

.table thead {
  background-color: #343a40;
  color: #fff;
}

.table tbody tr:hover {
  background-color: #f1f1f1;
}

.table th,
.table td {
  vertical-align: middle;
}

.tab-container {
  margin-top: 20px;
}

.nav-tabs .nav-link {
  border: 1px solid #dee2e6;
  border-bottom-color: transparent;
}

.nav-tabs .nav-link.active {
  border-color: #dee2e6 #dee2e6 #fff;
}
</style>
