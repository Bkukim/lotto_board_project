<template>
  <div class="container mt-5 mb-5">
    <h3 class="mb-5">자유 게시판 수정</h3>
    <!-- 제목 -->
    <div class="col-10 mb-3">
      <input
        type="text"
        class="form-control"
        placeholder="제목을 적어주세요"
        v-model="deptBoard.title"
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
        @click="cancelDeptBoard"
        class="btn col-3"
        id="button-cancle-Writing"
      >
        취소
      </button>
      <button
        @click="updateDeptBoard"
        class="btn col-3"
        id="button-cancle-Writing"
      >
        수정하기
      </button>
    </div>
  </div>
</template>

<script>
import DeptBoardService from "@/services/board/dept/DeptBoardService";
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";

export default {
  data() {
    return {
      editor: null,
      deptBoard: {
        deptBoardId:0,
        deptId:0,
        userId: this.$store.state.user.userId, // 로그인 된 userId
        title: "",
        content: "",
      },
    };
  },
  methods: {
    // deptBoardId로 상세조회
    async retrieveDeptBoard(deptBoardId) {
      try {
        let response = await DeptBoardService.getDeptBoardId(deptBoardId);
        this.deptBoard = response.data;
        console.log(response.data);
      } catch (e) {
        alert("에러");
        console.log(e);
      }
    },
    // 글 수정 취소 함수
    cancelDeptBoard() {
      if (confirm("글 수정을 취소하시겠습니까?")) {
        alert(this.deptBoard.deptBoardId);
        this.$router.push("/dept/board/detail/" + this.deptBoard.deptBoardId);
      }
    },
    // 수정 함수
    async updateDeptBoard() {
      try {
        let data = {
          deptBoardId:this.deptBoard.deptBoardId,
          deptId:this.deptBoard.deptId,
          userId:this.deptBoard.userId,
          noticeId: this.deptBoard.deptBoardId,
          title: this.deptBoard.title,
          content: this.editor.getHTML(), // 에디터의 글 content 가져오기
          
        };
        let response = await DeptBoardService.updateDeptBoard(
          this.deptBoard.deptBoardId,
          data
        );
        // 로깅
        console.log(response.data);
        alert("게시글이 수정되었습니다.");
        this.$router.push("/dept/board/detail/"+this.deptBoard.deptBoardId);
      } catch (e) {
        console.log(e);
      }
    },
    // toastUi 에디터 생성
    createEditor(content) {
      this.editor = new Editor({
        el: document.querySelector("#editor"),
        initialEditType: "wysiwyg",
        initialValue: content, // TODO: 중요 : 여기 content 넣기 , 에디터에 content 보임 =>내용
      });
    },
  },
  async mounted() {
    // 비동기
    await this.retrieveDeptBoard(this.$route.params.deptBoardId);
    this.createEditor(this.deptBoard.content);
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
