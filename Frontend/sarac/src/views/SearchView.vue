<template>
  <div>
    <search-bar></search-bar>
    <book-search-list></book-search-list>
  </div>
</template>
<script>
import SearchBar from "@/components/search/SearchBar.vue";
import BookSearchList from "@/components/search/BookSearchList.vue";

import { mapActions, mapState } from "vuex";
const searchStore = "searchStore";

export default {
  components: { SearchBar, BookSearchList },
  computed: {
    ...mapState(["searchResults"]),
  },
  mounted() {
    this.getBestBook();
  },
  methods: {
    ...mapActions(searchStore, ["getBestBook"]),
  },
};
</script>
