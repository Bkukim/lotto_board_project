<template>
    <Editor ref="editor" />
  </template>
   
  <script>
  import Editor from '@/components/common/Editor'
   
  export default {
    components: {
      Editor,
    },
    methods: {
      async save() {
        var content = this.$refs.editor.getContent()
        console.log(content);
      },
    },
  }
  </script>