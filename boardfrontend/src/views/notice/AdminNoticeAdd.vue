<!-- AdminNoticeAdd :: toast 에디터 씀 -->
<template>
  <div class="container mt-5 mb-5">
    <!-- 나중에 디자인 서식 통일예정 -->
    <h3 class="mb-5">공지사항 등록</h3>
    <div ref="editor"></div>
    <div v-html="testHtml"></div>
    <button @click="testValid">Get HTML</button>

    <div class="row">
      <button @click="cancel" class="col-3">취소</button>
      <button @click="register" class="col-3">등록하기</button>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, defineProps, defineEmits } from "vue";
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";

const props = defineProps({
  modelValue: {
    type: String,
    required: false,
    default: "",
  },
});

const emit = defineEmits(["update:modelValue"]);
const editor = ref();
const editorValid = ref();
const testHtml = ref("");

onMounted(() => {
  editorValid.value = new Editor({
    el: editor.value,
    height: "58vh",
    initialEditType: "wysiwyg",
    initialValue: props.modelValue,
    events: {
      change: () => emit("update:modelValue", editorValid.value.getMarkdown()),
    },
  });
});

// 작성한 내용 불러와서 html 적용
// 작성한 내용을 가져와서 HTML 적용
const testValid = () => {
  testHtml.value = editorValid.value.getHtml();
};

// 취소 버튼 클릭 시 실행되는 함수
const cancel = () => {
  // 취소 로직을 여기에 작성하세요.
};

// 등록하기 버튼 클릭 시 실행되는 함수
const register = () => {
  // 등록 로직을 여기에 작성하세요.
};
</script>
