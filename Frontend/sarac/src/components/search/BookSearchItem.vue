<template>
  <v-container>
    <hr />
    <v-row @click="getActionFromType">
      <v-col cols="4" class="align-self-stretch pa-0">
        <v-img :src="getThumbnail"></v-img>
      </v-col>
      <v-spacer></v-spacer>
      <v-col class="d-flex flex-column pa-0 justify-space-between" cols="7">
        <div>
          <h1>{{ bookTitle }}</h1>
          <h2>{{ author }}</h2>
        </div>

        <v-rating
          :value="bookScore"
          readonly
          color="#E3984B"
          background-color="#E3984B"
          dense
          half-increments
          size="2vh"
        ></v-rating>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const reviewStore = "reviewStore";

export default {
  props: {
    isbn: String,
    author: String,
    bookTitle: String,
    bookImgUrl: String,
    bookScore: Number,
    type: String,
    fromWhere: String,
  },
  computed: {
    ...mapGetters(reviewStore, ["book"]),
    getThumbnail() {
      return !this.bookImgUrl
        ? "https://sarac-a505.s3.ap-northeast-2.amazonaws.com/%EC%B1%85%EA%B8%B0%EB%B3%B8.png"
        : this.bookImgUrl;
    },
  },
  methods: {
    ...mapActions(reviewStore, ["saveBookData"]),
    getActionFromType() {
      if (this.fromWhere === "search") {
        this.moveDetailPage();
      } else if (this.fromWhere === "review") {
        this.saveBookData({
          isbn: this.isbn,
          bookTitle: this.bookTitle,
          bookImgUrl: this.getThumbnail,
        });
        console.log(this.book);
        this.$router.push({ path: "/review/regist" });
      }
    },
    moveDetailPage() {
      this.$router.push({ path: "/book/detail/" + this.isbn });
    },
  },
};
</script>
<style scoped>
h1 {
  font-size: 2vh;
  font-weight: bold;
  overflow: hidden;
  white-space: wrap;
  /* width: 100%; */
}
h2 {
  font-size: 1.5vh;
  /* font-weight: bold;  */
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
  width: 70%;
  height: 2vh;
}
hr {
  margin-top: 0px;
  margin-bottom: 1.5rem;
  width: calc(100% + 30px);
  margin-left: -15px;
}
</style>
