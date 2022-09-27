<template>
  <v-container>
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
  components: { BookSearchItem },
  computed: {
    ...mapState(["searchResults"]),
    ...mapGetters(searchStore, ["searchResults"]),
  },
};
</script>
