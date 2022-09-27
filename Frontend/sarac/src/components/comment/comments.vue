<template>
  <div>
    <!-- {{ CommentsList }} -->
    <div v-for="(comments, index) in CommentsList" :key="index">
      <!-- {{ comments }} -->
      <v-row>
        <!-- 이미지 -->
        <v-col cols="2">
          <v-avatar>
            <img :src="comments[0].comment.userImagePath" />
          </v-avatar>
        </v-col>
        <!-- 닉네임 및 코멘트 -->
        <v-col cols="8">
          <v-rol>{{ comments[0].comment.userNickname }}</v-rol>
          <tr>
            {{
              comments[0].comment.content
            }}
          </tr>
        </v-col>
        <!-- 수정 및 삭제 또는 대댓글 작성 토글 버튼 -->
        <v-col cols="2"></v-col>
      </v-row>
      <!-- 대댓글 -->
      <!-- {{ comments[0].childList }} -->
      <sub-comment :childList="comments[0].childList"></sub-comment>
    </div>
  </div>
</template>

<script>
import subComment from "@/components/comment/subComment.vue";
export default {
  components: { subComment },
  props: ["reviewCommentList"],
  data() {
    return {
      Cs: [],
      CommentsList: [],
    };
  },
  watch: {
    reviewCommentList: function () {
      this.getCommentData();
      // console.log(this.CommentsList);
    },
  },
  methods: {
    getCommentData() {
      this.CommentsList = [];
      this.Cs = JSON.parse(JSON.stringify(this.reviewCommentList));
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
            if (p[0].comment.commentId === comment.parentId) {
              p[0].childList.push(comment);
            }
          });
        }
      });
    },
  },
  created() {
    this.getCommentData();
  },
};
</script>

<style></style>
