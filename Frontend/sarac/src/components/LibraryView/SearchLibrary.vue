<template>
<div>
<div>검색 결과</div>
  <v-container fluid>
    <v-row v-if="searchList != null">
      <v-col v-for="book in searchList" :key="book.isbn" class="px-0">
        <v-card
          class="mx-2"
          height="15vh"
          width="10vh"
          @click="gotoBookInfo(book.isbn)"
          flat
        >
          <v-img :src="book.bookImgUrl" class="fill-height"> </v-img>
        </v-card>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
  </v-container>
</div>
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

<style></style>
