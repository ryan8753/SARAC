<template>
  <div>
    <v-container v-if="isOpen" fluid>
      <v-row justify="end" class="mt-1">
        <v-btn text x-small @click="deleteReview" v-if="value == '선택해제'"
          >삭제</v-btn
        >
        <v-btn text x-small @click="toggle" v-if="person == 'me'">{{
          value
        }}</v-btn>
      </v-row>
      <v-row>
        <v-col
          v-for="review in reviews"
          :key="review.reviewId"
          class="px-0 col-card"
          cols="4"
        >
          <b-form-checkbox-group v-model="checkList">
            <b-form-checkbox
              size="sm"
              v-if="value == '선택해제'"
              :value="review.reviewId"
            ></b-form-checkbox>
            <v-card
              class="mx-0"
              width="10vh"
              @click="gotoDetailReview(review.reviewId)"
              flat
            >
              <v-img :src="review.photoUrlList[0]" height="15vh">
                <!-- 비밀글일경우, 자물쇠 아이콘 -->
                <v-icon v-if="review.isSecret" size="sm">mdi-lock</v-icon>
              </v-img>
              <v-card-subtitle id="title">
                {{ review.title }}
              </v-card-subtitle>

              <v-card-text id="subtitle">
                {{ review.bookTitle }}
              </v-card-text>
            </v-card>
          </b-form-checkbox-group>
        </v-col>
        <v-spacer></v-spacer>
      </v-row>
    </v-container>
    <v-row v-else justify="center" class="pa-4"> 비공개 입니다. </v-row>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const myFeedStore = "myFeedStore";

export default {
  name: "UserReview",

  data() {
    return {
      isOpen: true,
      reviews: [],
      value: "선택",
      checkList: [],
      person: null,
    };
  },
  computed: {
    ...mapState(myFeedStore, ["userInfo", "reviewList"]),
  },
  watch: {
    userInfo: function () {
      this.getReviewList({ userId: this.userInfo.userId });
    },
    reviewList: function () {
      let person = Object.keys(this.reviewList)[0];
      this.person = person;
      if (person == "private") this.isOpen = false;
      else this.reviews = this.reviewList[person];
    },
  },
  methods: {
    ...mapActions(myFeedStore, ["getReviewList", "deleteReviewList"]),

    gotoDetailReview(reviewId) {
      this.$router.push("/detailReview/" + reviewId);
    },
    toggle() {
      this.value = this.value != "선택" ? "선택" : "선택해제";
      if (this.value == "선택") this.checkList = [];
    },
    deleteReview() {
      console.log(this.checkList);
      this.deleteReviewList(this.checkList);
      this.toggle();
    },
  },
  created() {
    this.getReviewList({ userId: this.userInfo.userId });
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
.col-card {
  display: flex;
  justify-content: center;
}
</style>
