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
            <option value="동아리">동아리</option>
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
      editor: null,
      // notice: {
      //   title: "",
      //   noticeType: "",
      // },
      notice: {},
    };
  },
  methods: {
    async getNotice(noticeId) {
      try {
        let response = await NoticeService.getNotice(noticeId);
        this.notice = response.data;
        // 에디터가 초기화된 후에 데이터를 설정
        if (this.editor) {
          this.editor.setMarkdown(this.notice.content || ""); // 에디터에 불러온 내용 설정
        }
      } catch (e) {
        console.log(e);
      }
    },
    async updateNotice() {
      try {
        this.notice.content = this.editor.getMarkdown(); // 에디터 내용을 notice.content에 설정
        let response = await NoticeService.update(
          this.notice.noticeId,
          this.notice
        );
        console.log(response.data);
        this.message = "수정에 성공했습니다.";
      } catch (e) {
        console.log(e);
      }
    },
  },
  mounted() {
    this.editor = new Editor({
      el: this.$refs.editor,
      initialEditType: "wysiwyg",
      height: "500px",
      events: {
        load: () => {
          // 에디터가 로드된 후에만 getNotice를 호출
          this.getNotice(this.$route.params.noticeId); // 임의의 noticeId로 초기 호출 (적절한 noticeId로 변경)
        },
      },
    });

    // this.getNotice(this.$route.params.noticeId);
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
