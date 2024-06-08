<template>
  <div class="container mt-5 mb-5">
    <h3 class="mb-5">자유 게시판 수정</h3>
    <!-- 제목 -->
    <div class="col-10 mb-3">
      <input
        type="text"
        class="form-control"
        placeholder="제목을 적어주세요"
        v-model="freeBoard.title"
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
        @click="cancelFreeBoard"
        class="btn col-3"
        id="button-cancle-Writing"
      >
        취소
      </button>
      <button
        @click="updateFreeBoard"
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
import FreeBoardService from "@/services/board/free/FreeBoardService";

export default {
  data() {
    return {
      editor: undefined, // null보다 에러가 적음 이거 사용
      freeBoard: {
        userId: this.$store.state.user.userId, // 로그인 된 userId
        title: "",
        freeBoardId: "",
        content: "",
      },
    };
  },
  methods: {
    // freeBoardId로 상세조회
    async retrieveFreeBoard(freeBoardId) {
      try {
        let response = await FreeBoardService.getFreeBoardId(freeBoardId);
        this.freeBoard = response.data;
        console.log(response.data);
      } catch (e) {
        alert("에러");
        console.log(e);
      }
    },
    // toastUi 에디터 생성
    createFreeBoardEditor(content) {
      this.editor = new Editor({
        el: document.querySelector("#editor"),
        initialEditType: "wysiwyg",
        initialValue: content, // TODO: 중요 : 여기 content 넣기 , 에디터에 content 보임 =>내용
        height: 'auto', // 에디터 높이 설정
      });
    },
    // 글 수정 취소 함수
    cancelFreeBoard() {
      if (confirm("글 수정을 취소하시겠습니까?")) {
        // alert(this.freeBoard.freeBoardId);
        this.$router.push(
          "/free/free-boardDetail/" + this.freeBoard.freeBoardId
        );
      }
    },
    // 수정 함수
    async updateFreeBoard() {
      try {
        let temp = {
          userId: this.$store.state.user.userId,
          freeBoardId: this.freeBoard.freeBoardId,
          title: this.freeBoard.title,
          content: this.editor.getHTML(), // TODO: 중요 : 에디터의 글 content 가져오기
          // 선생님 예시에는 
          // loc: this.toastEditor.getHTML() 되어있었으나 
          // 여기서 toastEditor가 없어서 editor를 create해줄때랑 같이 undefined로 해서 넣어줌
        };
        console.log(temp);
        let response = await FreeBoardService.updateFreeBoard(this.freeBoard.freeBoardId, temp);
        console.log(response.data);
        alert("게시글이 수정되었습니다.");
        // 다시 상세조회
        this.retrieveFreeBoard(this.freeBoard.freeBoardId);
        this.$router.push(`/free/free-boardDetail/` + this.freeBoard.freeBoardId);
      } catch (e) {
        console.log(e);
      }
    },
  },
  async mounted() {
    // 비동기
    await this.retrieveFreeBoard(this.$route.params.freeBoardId);
    this.createFreeBoardEditor(this.freeBoard.content);
    window.scrollTo(0, 0);
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
