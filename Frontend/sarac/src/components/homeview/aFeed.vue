<template>
  <div>
    <!-- {{ review }} -->
    <br />

    <v-card 
      
      class="mx-2" 
      @click="clickReview()"
      outlined
      elevation="3"
      rounded="lg">
      <!-- 책이름 -->
      <v-row class="ml-auto" align="center">
        <v-col class="c-subtitle">"{{ review.bookTitle }}" 에 대한</v-col>
        <!-- <v-col align="right" class="mr-auto">
          <v-btn x-small text @click="clickReview()" v-if="isHome">
            자세히보기
          </v-btn>
        </v-col> -->
      </v-row
      ><br />
      <!-- 리뷰제목 -->
      <v-row align="center" justify="center" class="c-title"> {{ review.title }}</v-row
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
        ></v-rating> </v-row
      ><hr />
      <!-- 사진 -->
      <v-row align="end" justify="center">
        <v-carousel height=" 30vh" style="width: 80%"
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
      <hr>
      <!-- 좋아요수 -->
      <v-row align="end" justify="end" class="mr-3 c-subtitle">
        <v-icon color="rgba(227,152,75)" small>mdi-heart</v-icon> {{ review.likeCount }} </v-row
      >
      <br>
      <br>
      <!-- 내용 일부? -->
      <v-row class="d-block ml-5 c-content review-box"> {{ review.content }}</v-row>
      <br>
    </v-card>
  </div>
</template>

<script>
export default {
  components: {},
  props: ["review"],
  data() {
    return {
      photoUrl: null,
      isHome: true,
    };
  },
  methods: {
    clickReview() {
      // console.log(this.review.reviewId);
      // window.location.href = `/detailReview/${this.review.reviewId}`;
      if(window.location.pathname.split("/")[1] === "detailReview"){
        const isbn = this.review.isbn
        this.$router.push(`/book/detail/${isbn}`)
      }else{
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
  background-color: rgba(243,237,237,1);
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
  margin-left: 5%;;
}
</style>
