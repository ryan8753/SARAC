<template>
  <v-container>
    <!-- 1. 상세 페이지에서 접근 할 시 action X
          2. 리뷰 작성을 통해 접근 시 책의 isbn과 썸네일을 review쪽에 보내주는 action(state 이용할 지 논의 필요)
          3. 검색을 통해 진입 시 지금과 같이 상세 페이지로 이동
    -->
    <v-row @click="getActionFromType">
      <v-col cols="4">
        <v-img :src="getThumbnail"></v-img>
      </v-col>
      <v-spacer></v-spacer>
      <v-col cols="7">
        <v-row> {{ bookTitle }} </v-row>
        <v-row> {{ author }} </v-row>
        <v-row>
          <v-rating
            :value="bookScore"
            readonly
            color="rgba(170, 83, 14, 1)"
            background-color="color=rgba(170, 83, 14, 1)"
            dense
            half-increments
          ></v-rating>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: {
    isbn: String,
    author: String,
    bookTitle: String,
    bookImgUrl: String,
    bookScore: Number,
    type: String,
  },
  computed: {
    getThumbnail() {
      return !this.bookImgUrl
        ? "https://sarac-a505.s3.ap-northeast-2.amazonaws.com/%EC%B1%85%EA%B8%B0%EB%B3%B8.png"
        : this.bookImgUrl;
    },
  },
  // created() {
  //   console.log(this.$route.params.fromLocation);
  //   if (this.$route.params.fromLocation === "search") {
  //     console.log("from search" + this.$route.params.fromLocation);
  //   } else {
  //     this.$router.go(-1);
  //   }
  // },
  methods: {
    getActionFromType() {
      // if(this.type === "") {
      this.moveDetailPage();
      // }
    },
    moveDetailPage() {
      this.$router.push({ path: "/book/detail/" + this.isbn });
    },
  },
};
</script>
