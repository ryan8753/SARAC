<template>
  <v-container>
    <!-- <v-list-item
        class="ma-0 pa-0"
        @click="getActionFromType"
      >
        
          <v-img :src="getThumbnail" width="3vh"> </v-img>
        

        <v-list-item-content>
          <v-list-item-title
            v-text="bookTitle"
          ></v-list-item-title>
          <v-list-item-subtitle v-text="author"> 
          </v-list-item-subtitle>
          <v-rating
            :value="bookScore"
            readonly
            color="#E3984B"
            background-color="#E3984B"
            dense
            half-increments
            size="2vh"
          ></v-rating>
        </v-list-item-content>
      </v-list-item> -->


    <v-row @click="getActionFromType">
      <v-col cols="4">
        <v-img :src="getThumbnail"></v-img>
      </v-col>
      <v-spacer></v-spacer>
      <v-col cols="7">
        <v-row><h1> {{ bookTitle }} </h1></v-row>
        <v-row><h2> {{ author }} </h2></v-row>
        <v-row>
          <v-rating
            :value="bookScore"
            readonly
            color="#E3984B"
            background-color="#E3984B"
            dense
            half-increments
            size="2vh"
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
<style scoped>
h1 {
  font-size: 3vh;
  font-weight: bold;
}
h2 {
  font-size: 2vh;
  font-weight: bold; 
}
</style>
