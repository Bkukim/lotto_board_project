<template>
  <div class="container mt-5 mb-5">
    <h3 class="mb-5">건의 게시판 수정</h3>
    <!-- 제목 -->
    <div class="col-10 mb-3">
      <input
        type="text"
        class="form-control"
        placeholder="제목을 적어주세요"
        v-model="complaintBoardList.title"
      />
    </div>
    <div class="col-12 mt-3 mb-3">
      <div class="row"></div>
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
import ComplaintBoardService from '@/services/board/complaint/ComplaintBoardService';

export default {
  data() {
    return {
      editor: null,
      complaintBoardList: {
        userId: this.$store.state.user.userId, // 로그인 된 userId
        title: "",
        complaintBoardId: "",
        content: "",
      },
    };
  },
  methods: {
    // complaintBoardId로 상세조회
    async retrieveGetComplaintBoard(complaintBoardId) {
      try {
        let response = await ComplaintBoardService.getComplaintBoardId(complaintBoardId);
        this.complaintBoardList = response.data;
        console.log(response.data);
      } catch (e) {
        alert("에러");
        console.log(e);
      }
    },
    // 글 수정 취소 함수
    cancelFreeBoard() {
      if (confirm("글 수정을 취소하시겠습니까?")) {
        alert(this.complaintBoardList.complaintBoardId);
        this.$router.push(
          "/complaint/complaint-boardDetail/" + this.complaintBoardList.complaintBoardId
        );
      }
    },
    // 수정 함수
    async updateComplaintBoard() {
      try {
        let response = await ComplaintBoardService.updateComplaintBoard(
          this.complaintBoardList.complaintBoardId
        );
        // 로깅
        console.log(response.data);
        alert("게시글이 수정되었습니다.");
        this.$router.push("/complaint/complaint-board/:complaintBoardId");
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
      // hooks: {
      //   addImageBlobHook: async (blob, callback) => {
      //     const imageUrl = await this.uploadImage(blob);
      //     callback(imageUrl, blob.name);
      //   },
      // },
    });
    this.retrieveGetComplaintBoard(this.$route.params.complaintBoardId);
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
