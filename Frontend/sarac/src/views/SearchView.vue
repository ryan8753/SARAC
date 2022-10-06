<template>
  <div>
    <search-bar :page="currentPage-1"></search-bar>
    <book-search-list
      :isSearched="isSearched"
      :fromWhere="beforeLocation"
    ></book-search-list>
    <b-pagination v-if="isSearched" v-model="currentPage" pills :total-rows="rows" :per-page="perPage" size="sm" align="center"></b-pagination>
  </div>
</template>
<script>
import SearchBar from "@/components/search/SearchBar.vue";
import BookSearchList from "@/components/search/BookSearchList.vue";
import "@/styles/variable.sass";

import { mapActions, mapState, mapMutations } from "vuex";
const searchStore = "searchStore";

export default {
  data() {
    return {
      rows: null,
      currentPage: 1,
      perPage: 10,
      isSearched: null,
    }
  },
  components: { SearchBar, BookSearchList },
  computed: {
    ...mapState(searchStore, ["searchResults"]),
    // ...mapGetters(searchStore, ["isSearched"]),
    ...mapMutations(searchStore, { setTypeFalse: "SET_TEXT_FALSE" }),
    beforeLocation() {
      if (this.$route.params.fromLocation === "search") {
        console.log("review");
        return "review";
      } 
      else if (this.$route.params.fromLocation === undefined) {
        console.log("search");
        return "search";
      }
      return "";
    },
  },
  watch:{
    $route() {
      console.log(this.$route.query);
      let path = this.$route.query;
      console.log(path);
      if(path.keyword) {
        this.isSearched = true;
        this.getBookResults({keyword: path.keyword, page: path.page, type: path.type});
      }
      else {
        this.isSearched = false;
        this.getBestBook();
      }
    },
    searchResults(newVal) {
      this.rows = newVal.totalElements;
      this.currentPage = newVal.currentPage + 1;
    }
  },
  created() {
    let path = this.$route.query;
    console.log(path.keyword + "-----------------------");
    if(path.keyword) {
      this.isSearched = true;
      this.getBookResults({keyword: path.keyword, page: path.page});
    }
    else {
      this.isSearched = false;
      this.getBestBook();
    }
  },
  methods: {
    ...mapActions(searchStore, ["getBestBook", "getBookResults"]),
  },
};
</script>
