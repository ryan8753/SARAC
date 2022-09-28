<template>
  <div>
    <!-- {{ this.childList }} -->
    <div v-for="(child, index) in childList" :key="index">
      <v-row>
        <!-- 이미지 -->
        <v-col cols="2">
          <v-avatar>
            <img :src="child.userImagePath" />
          </v-avatar>
        </v-col>
        <!-- 닉네임 및 코멘트 -->
        <v-col cols="7">
          <v-rol>{{ child.userNickname }}</v-rol>
          <tr>
            {{
              child.content
            }}
          </tr>
        </v-col>
        <!-- 수정 및 삭제 버튼 -->
        <v-col cols="2">
          <!-- {{ child.kakaoId }} -->
          <v-btn
            v-if="kakaoId === child.kakaoId"
            small
            class="mx-2"
            @click="deleteComment(child.commentId)"
          >
            삭제
          </v-btn>
        </v-col>
      </v-row>
      <!-- 답글 작성 폼 -->
    </div>
    <v-row v-if="this.toggle">
      <create-comment
        :reviewid="this.reviewId"
        :parentId="this.parentId"
        v-on:commentChanged="updateInfo()"
      ></create-comment>
    </v-row>
  </div>
</template>

<script>
import createComment from "@/components/comment/createComment.vue";
import { mapActions } from "vuex";
const reviewStore = "reviewStore";
export default {
  components: { createComment },
  props: ["childList", "toggle", "parentId", "reviewId"],
  data() {
    return { kakaoId: Number };
  },
  created() {
    console.log(this.childList);
    console.log(this.$store.state.accountStore.user.kakaoId);
    this.kakaoId = this.$store.state.accountStore.user.kakaoId;
  },
  watch: {
    toggle: function () {
      //   console.log(this.toggle);
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
  },
};
</script>

<style></style>
