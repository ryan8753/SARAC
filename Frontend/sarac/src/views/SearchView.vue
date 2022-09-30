<template>
  <div>
    <search-bar></search-bar>
    <book-search-list
      :type="isSearched"
      :fromWhere="beforeLocation"
    ></book-search-list>
  </div>
</template>
<script>
import SearchBar from "@/components/search/SearchBar.vue";
import BookSearchList from "@/components/search/BookSearchList.vue";

import { mapActions, mapState, mapGetters, mapMutations } from "vuex";
const searchStore = "searchStore";

export default {
  components: { SearchBar, BookSearchList },
  computed: {
    ...mapState(["searchResults"]),
    ...mapGetters(searchStore, ["isSearched"]),
    ...mapMutations(searchStore, { setTypeFalse: "SET_TEXT_FALSE" }),
    beforeLocation() {
      if (this.$route.params.fromLocation === "search") {
        console.log("review");
        return "review";
      } else if (this.$route.params.fromLocation === undefined) {
        console.log("search");
        return "search";
      }
      return "";
    },
  },
  mounted() {
    this.setTypeFalse;
    this.getBestBook();
  },
  methods: {
    ...mapActions(searchStore, ["getBestBook"]),
  },
};
</script>
