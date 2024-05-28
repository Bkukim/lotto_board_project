<template>
  <div class="container mt-5 mb-5">
    <h3 class="mb-5">공지사항 등록</h3>
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
    
    <!-- 버튼 -->
    <div class="row mt-3">
      <button @click="cancel" class="btn col-3">취소</button>
      <button @click="createNotice" class="btn col-3">등록하기</button>
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
          noticeType : this.notice.noticeType,
          content: content,
        };
        // 벡엔드로 공지사항 객체 추가 요청
        let response = await NoticeService.create(notice);
        // 콘솔에 결과 출력
        console.log(response);
        // TODO: 서버 응답에 따른 후속 처리 추가
      } catch (e) {
        console.log(e);
      }
    },
    // cancel() {
    //   // 취소 버튼 클릭 시 실행될 메서드
    //   // },
    //   // async uploadImage(blob) {
    //   //   try {
    //   //     // 이미지를 업로드하고 반환된 이미지 URL을 가져오는 로직 작성
    //   //     // 예: NoticeService.uploadImage(blob);
    //   //     const imageUrl = await NoticeService.uploadImage(blob);
    //   //     return imageUrl;
    //   //   } catch (error) {
    //   //     console.error("이미지 업로드 오류:", error);
    //   //     throw error;
    //   //   }
    // }, 
  },
  mounted() {
    this.editor = new Editor({
      el: this.$refs.editor,
      initialEditType: "wysiwyg",
      height: "500px",
      // hooks: {
      //   addImageBlobHook: async (blob, callback) => {
      //     const imageUrl = await this.uploadImage(blob);
      //     callback(imageUrl, blob.name);
      //   },
      // },
    });
  },
};
</script>
