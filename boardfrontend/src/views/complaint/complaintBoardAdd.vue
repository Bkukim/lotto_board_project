<template>
  <div class="container mt-5 mb-5">
    <h3 class="mb-5">건의 게시판 작성</h3>
    <!-- 제목 -->
    <div class="col-10 mb-3">
      <input
        type="text"
        class="form-control"
        placeholder="제목을 적어주세요"
        v-model="complaintBoard.title"
      />
    </div>
    <div class="col-12 mt-3 mb-3">
      <div class="row">
      </div>
    </div>

    <!-- 본문 -->
    <div ref="editor"></div>

    <!-- 버튼 -->
    <div class="row mt-3">
      <button
        @click="cancelComplaintBoard"
        class="btn col-3"
        id="button-cancle-Writing"
      >
        취소
      </button>
      <button
        @click="createComplaintBoard"
        class="btn col-3"
        id="button-cancle-Writing"
      >
        등록하기
      </button>
    </div>
  </div>
</template>

<script>
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import ComplaintBoardService from '@/services/board/complaint/ComplaintBoardService';

export default {
  data() {
    return {
      editor: null,
      complaintBoard: {
        userId: this.$store.state.user.userId,  // 로그인 된 userId
        title:"",
      },
    };
  },
  methods: {
    async createComplaintBoard() {
      if (!this.editor) {
        console.error("에디터가 초기화되지 않았습니다.");
        return;
      }
      try {
        // 에디터의 HTML 내용 가져오기
        const content = this.editor.getHTML();

        // 공지사항 객체 생성
        const complaintBoard = {
          userId: this.$store.state.user.userId, // Vuex 스토어에서 userId를 직접 가져오기
          title: this.complaintBoard.title,
          content: content,
        };
        // 벡엔드로 공지사항 객체 추가 요청
        let response = await ComplaintBoardService.createComplaintBoard(complaintBoard);
        // 콘솔에 결과 출력
        console.log(response);
        alert("게시글이 저장되었습니다.")
        this.$router.push("/complaint/complaint-board");
        // TODO: 서버 응답에 따른 후속 처리 추가
      } catch (e) {
        console.log(e);
      }
    },
    // 글 작성 취소 함수
    cancelComplaintBoard() {
      if (confirm("글 작성을 취소하시겠습니까?")) {
        this.$router.push("/complaint/complaint-board");
      }
    },
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
        window.scrollTo(0, 0);
  },
};
</script>

<style>
#button-cancle-Writing {
  background-color: #162b59;
  color: #ffffff;
  border: none;
}
</style>
