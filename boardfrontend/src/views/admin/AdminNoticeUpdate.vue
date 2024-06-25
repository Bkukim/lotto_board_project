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
    <!-- todo: ref가 아니라 id로 가져와야함, ref로 가져오면 뭐가 안되던데 이유는 모르겠음, 
              editor data에 넣어줬는데로 되는지 한번더 확인하기 -->
    <div id="editor"></div>
    <!-- 버튼 -->
    <div class="row mt-3">
      <button @click="cancel" id="button-cancle-Writing" class="btn col-3">
        취소
      </button>
      <button
        @click="updateNotice"
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
      editor: undefined, // null보다 에러가 적음 이거 사용
      notice: {}, // 공지사항 정보를 저장할 객체
    };
  },
  methods: {
    // 상세조회 요청함수
    async getNotice(noticeId) {
      try {
        let response = await NoticeService.getAdminNotice(noticeId);
        this.notice = response.data;
        console.log(response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // toastUi 에디터 생성
    createEditor(content) {
      this.editor=new Editor({
        el: document.querySelector("#editor"),
        initialEditType: "wysiwyg",
        initialValue: content, // TODO: 중요 : 여기 content 넣기 , 에디터에 content 보임 =>내용
      });
    },
    // 수정함수
    async updateNotice() {
      try {
        let temp = {
          noticeId: this.notice.noticeId,
          title: this.notice.title,
          noticeType:this.notice.noticeType,
          content: this.editor.getHTML(), // TODO: 중요 : 에디터의 글 content 가져오기
          // 선생님 예시에는 
          // loc: this.toastEditor.getHTML() 되어있었으나 
          // 여기서 toastEditor가 없어서 editor를 create해줄때랑 같이 undefined로 해서 넣어줌
        };
        console.log(temp);
        let response = await NoticeService.update(this.notice.noticeId, temp);
        console.log(response.data);
        alert("수정이 성공했습니다.");
        // 다시 상세조회
        this.getNotice(this.notice.noticeId);
        this.$router.push(`/notice/notice-check/`+this.notice.noticeId+'/'+this.notice.eventYN);

      } catch (e) {
        console.log(e);
      }
    },
  },
  async mounted() {
    // 비동기
    await this.getNotice(this.$route.params.noticeId);
    this.createEditor(this.notice.content);
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
