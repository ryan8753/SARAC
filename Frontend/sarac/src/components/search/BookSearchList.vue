<template>
  <v-container>
    {{ typeString }}
    <book-search-item
      v-for="book in searchResults.results"
      :key="book.isbn"
      v-bind="book"
    ></book-search-item>
  </v-container>
</template>

<script>
import { mapGetters, mapState } from "vuex";
const searchStore = "searchStore";

import BookSearchItem from "./BookSearchItem.vue";

export default {
  props: {
    type: { type: Boolean },
  },
  components: { BookSearchItem },
  computed: {
    ...mapState(["searchResults"]),
    ...mapGetters(searchStore, ["searchResults"]),
    typeString() {
      return this.type ? "검색 결과" : "인기 도서";
    },
  },
};
</script>
