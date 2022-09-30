<template>
  <v-container fluid>
      <v-row>
        <v-col
         v-for="review in reviewList" :key="review.reviewId" class="px-0">
    <v-card
    class="mx-0"
    max-width="10vh"
    @click="gotoDetailReview(review.reviewId)"
    flat
  >
    <v-img
      :src="review.photoUrlList[0]"
      height="15vh"
    ></v-img>
    <v-card-subtitle id="title">
      {{review.title}}
    </v-card-subtitle>

    <v-card-text id="subtitle">
      {{review.bookTitle}}
    </v-card-text>
    </v-card>
        </v-col>
        <v-spacer></v-spacer>
      </v-row>
    </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const myFeedStore = "myFeedStore";

export default {
  name: "UserReview",

  data() {
    return {
    }
  },
  computed: {
    ...mapState(myFeedStore, ["userInfo", "reviewList"]),
  },
  watch: {
    userInfo : function() {
      this.getReviewList({userId: this.userInfo.userId});
    }
  },
  methods: {
    ...mapActions(myFeedStore, ["getReviewList"]),

    gotoDetailReview(reviewId) {
    this.$router.push("/detailReview/" + reviewId);

    }
  },
  created() {
    this.getReviewList({userId: this.userInfo.userId});
  },
}
</script>

<style scoped>
  #title {
    padding: 0;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 10vh;
    font-size: 1vh;
    font-weight: bold;
    color: black;
  }
  #subtitle {
    padding: 0;
    padding-left: 3vh;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 10vh;
    font-size: 1vh;
    color: black;
  }
</style>