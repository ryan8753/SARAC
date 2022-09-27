<template>
  <div>
    <!-- {{ CommentsList }} -->
    <div v-for="(comments, index) in CommentsList" :key="index">
      {{ comments }}
    </div>
  </div>
</template>

<script>
export default {
  props: ["reviewCommentList"],
  data() {
    return {
      Cs: [],
      CommentsList: [],
    };
  },
  created() {
    this.Cs = this.reviewCommentList;
    this.Cs.sort(function (a, b) {
      if (a.depth > b.depth) return 1;
      else if (a.depth === b.depth) {
        if (a.commentId > b.commentId) return 1;
        else if (a.commentId === b.commentId) return 0;
        else return -1;
      } else return -1;
    }).forEach((comment) => {
      if (comment.depth === 0) {
        this.CommentsList.push([{ comment, childList: [] }]);
      } else {
        this.CommentsList.forEach((p) => {
          //   console.log(p[0].comment.commentId);
          //   console.log(comment.parentId);
          if (p[0].comment.commentId === comment.parentId) {
            console.log(p[0]);
            p[0].childList.push(comment);
            // return true;
          }
        });
      }
    });
  },
};
</script>

<style></style>
