<template>
  <div class="container mt-5 mb-5">
    <h3 class="mb-5">공지사항 수정</h3>
    <!-- 제목 -->
    <div class="col-10 mb-3">
      <input type="text" class="form-control" v-model="notice.title" />
    </div>
    <div class="col-12 mt-3 mb-3">
      <div class="row">
        <div class="col-4">
          <!-- 유형선택 -->
          <select class="form-select" v-model="notice.noticeType">
            <option value="전체">전체</option>
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
    <div v-html="notice.content"></div>
    <!-- 버튼 -->
    <div class="row mt-3">
      <button @click="cancel" id="button-cancle-Writing" class="btn col-3">
        취소
      </button>
      <button
        @click="createNotice"
        id="button-cancle-Writing"
        class="btn col-3"
      >
        수정하기
      </button>
    </div>
  </div>
</template>

<script>
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import NoticeService from "@/services/notice/NoticeService";

export default {
  data() {
    return {
      editor: null, // 에디터 인스턴스를 참조할 변수
      notice: {}, // 공지사항 정보를 저장할 객체
    };
  },
  methods: {
    // 공지사항 ID로 상세 정보를 가져오는 메서드
    async getNotice(noticeId) {
      try {
        this.editor = new Editor({
          el: this.$refs.editor, // HTML 요소 참조
          initialEditType: "wysiwyg", // 초기 에디터 타입 설정 (WYSIWYG)
          height: "500px", // 에디터 높이 설정
        });
          console.log("dataddd", this.editor);
        // 공지사항 데이터를 가져옴
        let response = await NoticeService.getNotice(noticeId);
        console.log("Notice data:", response.data); // 가져온 데이터 로그 출력
        this.notice = response.data; // 가져온 데이터를 notice 객체에 할당
        console.log("this.notice", this.notice); // 가져온 데이터 로그 출력

        this.editor.setHTML(this.notice.content, false); // 에디터에 불러온 내용 설정

        // Vue의 nextTick을 사용하여 다음 렌더링 사이클에서 데이터를 설정
        // this.$nextTick(() => {
        //   if (this.editor && this.editor.isLoaded) {
        //     // 에디터가 로드되었는지 확인(isLoaded는 일반적으로 특정 객체나 요소가 완전히 로드되었는지를 나타내는 플래그(flag))
        //     console.log("Setting markdown:", this.notice.content); // 에디터에 설정할 내용 로그 출력
        //     this.editor.setHTML(this.notice.content); // 에디터에 불러온 내용 설정
        //   } else {
        //     console.log("에디터 준비되지 않음"); // 에디터가 준비되지 않은 경우 로그 출력
        //   }
        // });
      } catch (e) {
        console.log(e); // 에러 발생 시 로그 출력
      }
    },
    // 공지사항을 업데이트하는 메서드
    async updateNotice() {
      try {
        this.notice.content = this.editor.getHTML(); // 에디터 내용을 notice.content에 설정
        let response = await NoticeService.update(
          this.notice.noticeId, // 공지사항 ID
          this.notice // 업데이트할 공지사항 데이터
        );
        console.log(response.data); // 응답 데이터 로그 출력
        this.message = "수정에 성공했습니다."; // 수정 성공 메시지 설정
      } catch (e) {
        console.log(e); // 에러 발생 시 로그 출력
      }
    },
  },
  // 컴포넌트가 마운트된 후 호출되는 라이프사이클 훅
  mounted() {
    // 지정된 옵션으로 에디터 초기화

    this.getNotice(this.$route.params.noticeId); // 라우트 파라미터에서 noticeId를 사용해 공지사항 정보 가져오기

    // 페이지가 로드되면 즉시 getNotice를 호출
  },
};
</script>
<style>
#button-cancle-Writing {
  background-color: #162b59;
  color: #ffffff;
  border: none;
  width: 200px;
}
</style>
