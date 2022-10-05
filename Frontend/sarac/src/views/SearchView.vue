<template>
  <div>
    <search-bar></search-bar>
    <book-search-list
      :type="isSearched"
      :fromWhere="beforeLocation"
    ></book-search-list>
    <!-- 여기서부터 -->
    <b-pagination
    :total-rows="50"
    :per-page="5"
    pills
    page-class="customPage"
  >
  </b-pagination>
  </div>
</template>
<script>
import SearchBar from "@/components/search/SearchBar.vue";
import BookSearchList from "@/components/search/BookSearchList.vue";

import { mapActions, mapState, mapGetters, mapMutations } from "vuex";
const searchStore = "searchStore";

export default {
  data() {
    return {
      page: null,
    }
  },
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
<style scoped>
.customPage.page-item.active .page-link {
  background-color: red;
  border-color: red;
}
</style>