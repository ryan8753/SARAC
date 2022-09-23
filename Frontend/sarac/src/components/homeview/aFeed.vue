<template>
  <div class="container">
    <!-- {{ review }} -->
    <br />

    <v-card class="mx-2">
      <!-- 책이름 -->
      <v-row class="ml-auto"> "{{ review.bookTitle }}" 에 대한 </v-row><br />
      <!-- 리뷰제목 -->
      <v-row align="center" justify="center"> {{ review.title }}</v-row
      ><br />
      <!-- 평점 -->
      <v-row align="center" justify="center">
        평점
        <v-rating
          :value="review.bookScore"
          readonly
          color="orange"
        ></v-rating> </v-row
      ><br />
      <!-- 사진 -->
      <v-row align="right" justify="center">
        <v-carousel height="auto" style="width: 80%">
          <v-carousel-item
            v-for="(url, i) in photoUrl"
            :key="i + 'p'"
            align="center"
            justify="center"
          >
            <v-img :src="url"> </v-img>
          </v-carousel-item>
        </v-carousel> </v-row
      ><br />
      <!-- 좋아요수 -->
      <v-row align="right" justify="end" class="mr-auto">
        <v-icon color="orange">mdi-heart</v-icon> {{ review.likeCount }} </v-row
      ><br />
      <!-- 내용 일부? -->
      <v-row class="ml-auto"> {{ review.content }}</v-row>
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
    };
  },

  created() {
    if (this.review.photoUrl.length == 0) {
      this.photoUrl = [
        // 기본이미지 저장 *** 변경필요 ***
        "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        "https://cdn.vuetifyjs.com/images/carousel/sky.jpg",
      ];
    } else {
      this.photoUrl = this.review.photoUrl;
    }
    console.log(this.photoUrl);
  },
};
</script>

<style scoped></style>
