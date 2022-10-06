<template>
  <div>
    <!-- {{ review }} -->
    <br />

    <v-card
      class="mx-2"
      @click="clickReview()"
      outlined
      elevation="3"
      rounded="lg"
    >
      <!-- 책이름 -->
      <v-row class="ma-5" align="center">
        <v-col cols="3" class="pa-0">
          <v-img :src="getThumbnail" @click="mvBook"></v-img>
        </v-col>
        <v-col cols="8" class="c-subtitle c-bold pl-3" @click="mvBook">
          "{{ review.bookTitle }}"
        </v-col>
        <v-col
          cols="1"
          align="center"
          v-if="
            review.authorKakaoId === this.$store.state.accountStore.user.kakaoId
          "
          class="pa-0 trick"
        >
          <v-btn icon @click="modifyReview"><v-icon>mdi-pencil</v-icon></v-btn>
        </v-col>
      </v-row>
      <br />
      <!-- 리뷰제목 -->
      <v-row align="center" justify="center" class="c-title mx-auto px-2">
        {{ review.title }}</v-row
      ><br />
      <!-- 평점 -->
      <v-row align="center" justify="center" class="c-subtitle">
        평점
        <v-rating
          :value="review.bookScore"
          readonly
          color="rgba(227,152,75)"
          background-color="rgba(227,152,75)"
          dense
          half-increments
          size="2vh"
        ></v-rating>
      </v-row>
      <hr />
      <!-- 사진 -->
      <v-row align="end" justify="center">
        <v-carousel
          height=" 30vh"
          style="width: 80%"
          :show-arrows="false"
          hide-delimiters
        >
          <v-carousel-item
            v-for="(url, i) in photoUrl"
            :key="i + 'p'"
            align="center"
            justify="center"
          >
            <v-img :src="url" contain max-height="30vh"> </v-img>
          </v-carousel-item>
        </v-carousel>
      </v-row>
      <hr />
      <!-- 좋아요수 -->
      <v-row align="end" justify="end" class="mr-3 c-subtitle">
        <v-icon
          v-if="review.like == true"
          color="rgba(227,152,75)"
          small
          @click="toggleLike()"
          >mdi-heart</v-icon
        >
        <v-icon v-else color="rgba(227,152,75)" small @click="toggleLike()"
          >mdi-heart-outline</v-icon
        >
        {{ review.likeCount }}
      </v-row>
      <br />
      <!-- 해시태그 -->
      <v-row style="width: 100%" class="px-1 mx-auto">
        <v-col>
          <span class="c-subtitle" v-for="(hashtag, i) in hashtagList" :key="i">
            #{{ hashtag }}
          </span>
        </v-col>
      </v-row>
      <br />
      <!-- 내용 일부? -->
      <v-row class="d-block ml-5 c-content review-box">
        {{ review.content }}</v-row
      >
      <br />
      <br />
    </v-card>
  </div>
</template>

<script>
import { mapActions } from "vuex";
const reviewStore = "reviewStore";
export default {
  components: {},
  props: ["review", "hashtagList"],
  data() {
    return {
      photoUrl: null,
      isHome: true,
    };
  },
  computed: {
    getThumbnail() {
      return !this.review.bookImgUrl
        ? "https://sarac-a505.s3.ap-northeast-2.amazonaws.com/%EC%B1%85%EA%B8%B0%EB%B3%B8.png"
        : this.review.bookImgUrl;
    },
  },
  methods: {
    ...mapActions(reviewStore, ["toggleReviewLike", "saveBookData"]),
    async toggleLike() {
      if (window.location.pathname.split("/")[1] === "detailReview") {
        // console.log("togglelike");
        await this.toggleReviewLike(this.review.reviewId);
        this.$emit("toggleLike");
      }
    },

    modifyReview() {
      const Book = {
        isbn: this.review.isbn,
        bookTitle: this.review.bookTitle,
        bookImgUrl: this.getThumbnail,
      };
      console.log(Book);
      this.saveBookData(Book);
      this.$router.push({
        path: `/review/regist/${this.review.reviewId}`,
        params: { reviewId: this.review.reviewId },
      });
    },
    mvBook() {
      if (window.location.pathname.split("/")[1] === "detailReview") {
        const isbn = this.review.isbn;
        this.$router.push(`/book/detail/${isbn}`);
      }
    },
    clickReview() {
      // console.log(this.review.reviewId);
      // window.location.href = `/detailReview/${this.review.reviewId}`;
      if (window.location.pathname.split("/")[1] === "detailReview") {
        // const isbn = this.review.isbn
        // this.$router.push(`/book/detail/${isbn}`)
      } else {
        this.$router.push({
          path: `detailReview/${this.review.reviewId}`,
          params: { reviewId: this.review.reviewId },
        });
      }
    },
  },

  created() {
    this.photoUrl = this.review.photoUrl;
    if (document.location.href.indexOf("home") < 0) {
      this.isHome = false;
    }

    // console.log(this.photoUrl);
  },
};
</script>

<style scoped>
.aa {
  background-color: rgba(243, 237, 237, 1);
}
.c-title {
  font-size: 3vh;
}
.c-subtitle {
  font-size: 2vh;
}
.c-content {
  font-size: 1vh;
}
.review-box {
  /* text-overflow: ellipsis; */
  /* overflow: hidden; */
  white-space: pre-line;
  width: 85%;
  /* height: 8vh; */
}
p {
  font-size: 1vh;
}

hr {
  background-color: rgba(243, 237, 237, 1);
  width: 90%;
  margin-left: 5%;
}
.c-bold {
  font-weight: bold;
}
.trick {
  position: relative;
  bottom: 3vh;
}
</style>
