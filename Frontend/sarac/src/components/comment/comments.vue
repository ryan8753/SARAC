<template>
  <div>
    <!-- {{ CommentsList }} -->
    <div v-for="(comments, index) in CommentsList" :key="index">
      <!-- {{ comments }} -->
      <v-row v-if="comments[0].comment.content !== `deleted`">
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
        <!-- 수정 및 삭제 또는 답글 작성 토글 버튼 -->
        <v-col cols="2">
          <div>
            <!-- <v-btn small class="mx-2" @click="modifyComment()">
              <v-icon dark> mdi-pencil </v-icon>
            </v-btn>
            <v-btn small class="mx-2" @click="deleteComment()">
              <v-icon dark> mdi-delete </v-icon>
            </v-btn> -->
          </div>
          <div>
            <v-btn
              small
              class="mx-2"
              @click="
                comments[0].toggleRegistSubComment =
                  !comments[0].toggleRegistSubComment
              "
            >
              답글
            </v-btn>
            <v-btn
              v-if="kakaoId === comments[0].comment.kakaoId"
              small
              class="mx-2"
              @click="deleteComment(comments[0].comment.commentId)"
            >
              삭제
            </v-btn>
          </div>
        </v-col>
      </v-row>
      <v-row v-else>
        <v-col cols="2"></v-col>
        <v-col cols="10">삭제된 댓글입니다.</v-col>
      </v-row>
      <!-- 대댓글 -->
      <!-- {{ comments[0].childList }} -->
      <v-row>
        <v-col cols="2"></v-col>
        <v-col cols="10">
          <sub-comment
            :childList="comments[0].childList"
            :toggle="comments[0].toggleRegistSubComment"
            :parentId="comments[0].comment.commentId"
            :reviewId="reviewId"
            v-on:commentChanged="updateInfo()"
          ></sub-comment>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import subComment from "@/components/comment/subComment.vue";
import { mapActions } from "vuex";
const reviewStore = "reviewStore";
export default {
  components: { subComment },
  props: ["reviewCommentList", "reviewId"],
  data() {
    return {
      Cs: [],
      CommentsList: [],
      kakaoId: Number,
    };
  },
  watch: {
    reviewCommentList: function () {
      this.getCommentData();
      // console.log(this.CommentsList);
    },
  },
  methods: {
    ...mapActions(reviewStore, ["deleteCommentApi"]),
    async deleteComment(commentId) {
      console.log(await this.deleteCommentApi(commentId));
      this.updateInfo();
    },
    updateInfo() {
      this.$emit("commentChanged");
    },
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
          this.CommentsList.push([
            { comment, childList: [], toggleRegistSubComment: false },
          ]);
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
    this.kakaoId = this.$store.state.accountStore.user.kakaoId;
    this.getCommentData();
  },
};
</script>

<style></style>
