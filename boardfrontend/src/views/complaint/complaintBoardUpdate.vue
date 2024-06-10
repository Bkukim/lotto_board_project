<template>
  <div class="container mt-5 mb-5">
    <h3 class="mb-5">건의 게시판 수정</h3>
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
      <div class="row"></div>
    </div>

    <!-- 본문 -->
    <div id="editor"></div>

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
        @click="updateComplaintBoard"
        class="btn col-3"
        id="button-cancle-Writing"
      >
        수정하기
      </button>
    </div>
  </div>
</template>

<script>
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import ComplaintBoardService from "@/services/board/complaint/ComplaintBoardService";

export default {
  data() {
    return {
      editor: undefined,
      complaintBoard: {
        userId: this.$store.state.user.userId, // 로그인 된 userId
        title: "",
        complaintBoardId: "",
        content: "",
      },
    };
  },
  methods: {
    // complaintBoardId로 상세조회
    async retrieveComplaintBoard(complaintBoardId) {
      try {
        let response = await ComplaintBoardService.getComplaintBoardId(complaintBoardId);
        this.complaintBoard = response.data;
        console.log(response.data);
      } catch (e) {
        alert("에러");
        console.log(e);
      }
    },
   // toastUi 에디터 생성
    createComplaintBoardEditor(content) {
      this.editor = new Editor({
        el: document.querySelector("#editor"),
        initialEditType: "wysiwyg",
        initialValue: content, // TODO: 중요 : 여기 content 넣기 , 에디터에 content 보임 =>내용
        height: 'auto', // 에디터 높이 설정
      });
    },
    // 글 수정 취소 함수
    cancelComplaintBoard() {
      if (confirm("글 수정을 취소하시겠습니까?")) {
        // alert(this.complaintBoard.complaintBoardId);
        this.$router.push(
          "/complaint/complaint-boardDetail/" +
            this.complaintBoard.complaintBoardId
        );
      }
    },
    // 수정 함수
    async updateComplaintBoard() {
      try {
          let temp = {
          userId: this.$store.state.user.userId,
          complaintBoardId: this.complaintBoard.complaintBoardId,
          title: this.complaintBoard.title,
          content: this.editor.getHTML(), // TODO: 중요 : 에디터의 글 content 가져오기
        };
        // 로깅
        console.log(temp);
        let response = await ComplaintBoardService.updateComplaintBoard(this.complaintBoard.complaintBoardId, temp);
        console.log(response.data);
        alert("게시글이 수정되었습니다.");
        // 다시 상세조회
        this.retrieveComplaintBoard(this.complaintBoard.complaintBoardId);
        this.$router.push(`/complaint/complaint-boardDetail/` + this.complaintBoard.complaintBoardId);
      } catch (e) {
        console.log(e);
      }
    },
  },
  async mounted() {
    // complaintBoardId 를 먼저 가져오고
    await this.retrieveComplaintBoard(this.$route.params.complaintBoardId);
    // 에디터 생성
    this.createComplaintBoardEditor(this.complaintBoard.content);
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
