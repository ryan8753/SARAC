<template>
  <div>
    <!-- <h1 class="text-center">책리뷰</h1> -->
    <div class="d-flex justify-end">
      <v-btn @click="routeToRegist" depressed plain color="black">
        <v-icon size="auto">mdi-pencil</v-icon>
      </v-btn>
    </div>
    <v-container>
      <v-row dense>
        <v-col
          cols="4"
          md="4"
          sm="4"
          v-for="review in reviewList"
          :key="review.reviewId"
          v-model="reviewList"
          class="px-0 col-card"
        >
          <v-card
            class="mx-0"
            max-width="10vh"
            @click="gotoDetailReview(review.reviewId)"
            flat
          >
            <v-img :src="review.photoUrlList[0]" height="15vh"> </v-img>
            <v-card-subtitle id="title">{{ review.title }}</v-card-subtitle>
            <v-card-subtitle id="subtitle">{{
              review.bookTitle
            }}</v-card-subtitle>
          </v-card>
        </v-col>
        <v-spacer></v-spacer>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex";
const bookStore = "bookStore";
const reviewStore = "reviewStore";
export default {
  // props: ["reviewList"],
  computed: {
    ...mapState(bookStore, ["book", "reviewList"]),
  },
  data() {
    return {
      // reviewList: null,
    };
  },
  methods: {
    ...mapActions(bookStore, ["getReviewList"]),
    ...mapActions(reviewStore, ["saveBookData"]),
    routeToRegist() {
      this.$router.push("/review/regist");
      this.saveBookData(this.book);
    },
  },
  // async created() {
  //   const reviewList = await this.getReviewList(1234);
  //   console.log("여기", reviewList);
  //   if (reviewList) {
  //     this.reviewList = reviewList;
  //   }
  // },
  created() {
    const bookId = this.$route.params.bookId;
    this.getReviewList(bookId);
  },
};
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

h1 {
  font-size: 3vh;
  font-weight: bold;
}
.col-card {
  display: flex;
  justify-content: center;
}
</style>
