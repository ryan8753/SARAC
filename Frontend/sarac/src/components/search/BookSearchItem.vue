<template>
  <v-container>
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
