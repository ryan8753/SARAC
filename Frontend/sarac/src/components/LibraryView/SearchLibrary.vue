<template>
  <v-container>
    <div>검색 결과</div>
    <br />
    <v-container fluid>
      <v-row v-if="searchList != null">
        <v-col
          v-for="book in searchList"
          :key="book.isbn"
          class="px-0 col-card"
          cols="4"
        >
          <v-spacer></v-spacer>
          <v-card
            class="mx-0"
            height="15vh"
            width="10vh"
            @click="gotoBookInfo(book.isbn)"
            flat
          >
            <v-img :src="!book.bookImgUrl ? defaultImgUrl : book.bookImgUrl" class="fill-width fill-height">
            </v-img>
          </v-card>
          <v-spacer></v-spacer>
        </v-col>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
import { mapState } from "vuex";

const myFeedStore = "myFeedStore";

export default {
  name: "SearchLibrary",

  data() {
    return {
      library: [],
      searchList: [],
      defaultImgUrl: "https://sarac-a505.s3.ap-northeast-2.amazonaws.com/%EC%B1%85%EA%B8%B0%EB%B3%B8.png",
    };
  },
  props: ["keyword"],
  watch: {
    keyword(newVal) {
      this.searchList = [];
      if (newVal != null && newVal.trim().length != 0) {
        let add = [];
        for (let book of this.library) {
          if (
            book.bookTitle.toLowerCase().includes(newVal.toLowerCase()) ||
            book.author.toLowerCase().includes(newVal.toLowerCase())
          )
            add.push(book);
        }
        this.searchList = add;
      }
    },
  },
  computed: {
    ...mapState(myFeedStore, ["libraryList"]),
  },
  methods: {
    gotoBookInfo(isbn) {
      this.$router.push("/book/detail/" + isbn);
    },
  },
  created() {
    let person = Object.keys(this.libraryList)[0];
    this.library = this.libraryList[person];
  },
};
</script>

<style scoped>
.col-card {
  display: flex;
  justify-content: center;
}
</style>
