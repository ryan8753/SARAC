<template>
  <div class="detail-review-container">
    <!-- {{ review }} -->
    <!-- 리뷰 -->
    <aFeed :review="review"></aFeed><br />
    <!-- hashtag -->
    <v-row>
      <p v-for="(hashtag, i) in review.reviewHashtagList" :key="i">
        #{{ hashtag }}
      </p> </v-row
    ><br />
    <!-- 댓글 -->
    <v-row>
      <div v-if="review.reviewCommentCount">
        댓글 {{ review.reviewCommentCount }} 개 모두 보기
      </div>
      <div v-else>등록된 댓글이 없습니다.</div> </v-row
    ><br />
    <!-- 댓글작성폼 -->
    <create-comment
      :reviewid="review.reviewId"
      v-on:commentChanged="updateInfo()"
    ></create-comment>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import aFeed from "@/components/homeview/aFeed.vue";
import createComment from "@/components/comment/createComment.vue";
const reviewStore = "reviewStore";

export default {
  name: "detailReview",
  components: { aFeed, createComment },
  //   props: {
  //     reviewId: {
  //       type: Number,
  //       default: 0,
  //     },
  //   },
  data() {
    return {
      review: null,
      reviewId: 0,
    };
  },
  methods: {
    ...mapActions(reviewStore, ["getDetailReview"]),
    async getReview(reviewId) {
      this.review = await this.getDetailReview(reviewId);
    },
    updateInfo() {
      this.getReview(this.reviewId);
    },
  },
  created() {
    this.reviewId = this.$route.params.reviewId;
    this.getReview(this.reviewId);
  },
};
</script>

<style scoped>
.detail-review-container {
  padding: 5%;
}
</style>
