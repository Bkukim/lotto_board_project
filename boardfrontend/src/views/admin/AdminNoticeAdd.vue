<template>
  <HeaderCom :hideHeader="true" />
  <!-- 관리자 페이지에서는 헤더를 숨김 -->
  <div class="main-container d-flex" style="height: auto">
    <!-- <AdminHeaderCom class="sidebar" /> -->
    <AdminHeaderCom class="sidebar" :hideHeader="true" />
    <br />
    <br />
    <br />
    <br />
    <br />
    <div class="container mt-5 mb-5" style="height: auto">
      <h3 class="mb-5" style="font-weight: bold">공지사항 등록</h3>
      <!-- 제목 -->
      <div class="col-10 mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="제목을 적어주세요"
          v-model="notice.title"
        />
      </div>
      <div class="col-12 mt-3 mb-3">
        <div class="row">
          <div class="col-4">
            <!-- 유형선택 -->
            <select class="form-select" v-model="notice.noticeType">
              <option value="전체">전체</option>
              <option value="이벤트">이벤트</option>
              <option value="부서">부서</option>
              <option value="자유">자유</option>
              <option value="건의">건의</option>
              <option value="동호회">동호회</option>
            </select>
          </div>
          <div class="col-4 form-check form-inline">
            <!-- 체크박스 -->
            <input
              type="checkbox"
              class="form-check-input"
              id="event"
              name="event"
            />
            <label class="form-check-label" for="event">이벤트</label>
          </div>
        </div>
      </div>

      <!-- 본문 -->
      <div ref="editor"></div>

      <!-- 버튼 -->
      <div class="row mt-3 container text-center" style="text-align: center">
        <button
          @click="cancel"
          id="button-cancle-Writing"
          class="btn col-3"
          style="margin-right: 10px; background-color: #cccccc"
        >
          취소
        </button>
        <button
          @click="createNotice"
          id="button-cancle-Writing"
          class="btn col-3"
        >
          등록하기
        </button>
      </div>

      <br />
      <br />
      <br />
      <br />
    </div>
  </div>
</template>

<script>
import AdminHeaderCom from "@/components/common/AdminHeaderCom.vue";
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import NoticeService from "@/services/notice/NoticeService";
import Swal from "sweetalert2";  // SweetAlert2 가져오기
import "sweetalert2/dist/sweetalert2.min.css"; // SweetAlert2 CSS 가져오기

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
      editor: undefined,
      notice: {
        title: "",
        noticeType: "",
      },
    };
  },
  methods: {
    async createNotice() {
      if (!this.editor) {
        console.error("에디터가 초기화되지 않았습니다.");
        return;
      }
      try {
        // 에디터의 HTML 내용 가져오기
        const content = this.editor.getHTML();

        // 공지사항 객체 생성
        const notice = {
          title: this.notice.title,
          noticeType: this.notice.noticeType,
          content: content,
        };
        // 백엔드로 공지사항 객체 추가 요청
        let response = await NoticeService.create(notice);
        // 콘솔에 결과 출력
        console.log(response);
        
        // 공지사항 등록 성공 팝업
        Swal.fire({
          title: "등록 완료",
          text: "글이 등록되었습니다.",
          icon: "success",
          confirmButtonText: "확인"
        }).then(() => {
          // 확인 버튼 클릭 시 관리자 홈으로 리다이렉트
          this.$router.push("/admin/notice-master");
        });

        // TODO: 서버 응답에 따른 후속 처리 추가
      } catch (e) {
        console.log(e);
      }
    },
    cancel() {
      // 취소 버튼 클릭 시 이전 페이지로 이동
      this.$router.go(-1);
    },
  },
  mounted() {
    this.editor = new Editor({
      el: this.$refs.editor,
      initialEditType: "wysiwyg",
      height: "500px",
    });
    window.scrollTo(0, 0);
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

.main-content {
  flex: 1;
  padding: 20px;
  background-color: #f8f9fa;
}
#button-cancle-Writing {
  background-color: #162b59;
  color: #ffffff;
  border: none;
  width: 200px;
}
</style>
