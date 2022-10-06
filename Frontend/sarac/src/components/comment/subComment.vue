<template>
  <div>
    <div v-if="childList.length > 0">
      <!-- {{ this.childList }} -->
      <v-btn text @click="subCommentToggle = !subCommentToggle"
        >답글 {{ childList.length }}개 보기</v-btn
      >
      <div v-if="subCommentToggle">
        <div v-for="(child, index) in childList" :key="index">
          <v-row>
            <!-- 답글아이콘 -->
            <v-col cols="1" align-self="center" class="pa-0 d-flex justify-end">
              <v-icon>mdi-chevron-right</v-icon>
            </v-col>
            <v-col cols="2" class="pa-0 pl-2 d-flex justify-center">
              <v-avatar>
                <img :src="child.userImagePath" />
              </v-avatar>
            </v-col>
            <!-- 닉네임 및 코멘트 -->
            <v-col cols="8" class="pa-0 pl-5">
              <v-rol class="t-comment">{{ child.userNickname }}</v-rol>
              <tr class="subt-comment">
                {{
                  child.content
                }}
              </tr>
            </v-col>
            <!-- 수정 및 삭제 버튼 -->
            <v-col cols="1" class="pa-0">
              <!-- {{ child.kakaoId }} -->
              <v-btn
                v-if="kakaoId === child.kakaoId"
                x-small
                icon
                class="mx-2"
                @click="deleteComment(child.commentId)"
              >
                <v-icon dark size="2vh"> mdi-delete </v-icon>
              </v-btn>
            </v-col>
          </v-row>
          <!-- 답글 작성 폼 -->
        </div>
      </div>
    </div>
    <v-row v-if="this.toggle" style="width: 100%">
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
    return { kakaoId: Number, subCommentToggle: false };
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
      this.subCommentToggle = true;
    },
  },
};
</script>

<style scoped>
.row {
  margin: 0;
}
.t-comment {
  font-size: 1.5vh;
  font-weight: bold;
}
.subt-comment {
  font-size: 1.5vh;
}
</style>
