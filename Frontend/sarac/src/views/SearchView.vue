<template>
  <div>
    <search-bar></search-bar>
    <book-search-list
      :isSearched="isSearched"
      :fromWhere="beforeLocation"
    ></book-search-list>
    <b-pagination class="py-7" v-if="isSearched" v-model="currentPage" pills :total-rows="rows" :per-page="perPage" size="sm" align="center"></b-pagination>
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
    ...mapMutations(searchStore, { setTypeFalse: "SET_TEXT_FALSE" }),
    beforeLocation() {
      if (this.$route.params.fromLocation === "search") {
        return "review";
      } 
      else if (this.$route.params.fromLocation === undefined) {
        return "search";
      }
      return "";
    },
  },
  watch:{
    $route() {
      let path = this.$route.query;
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
    },
    currentPage(newVal) {
      if(window.location.href.includes("/review/search"))
        this.$router.push("/review/search?keyword=" + this.$route.query.keyword + "&page=" + (newVal-1) + "&type=" + this.$route.query.type).catch(() => {});
      else
        this.$router.push("/search?keyword=" + this.$route.query.keyword + "&page=" + (newVal-1) + "&type=" + this.$route.query.type).catch(() => {});
    },
  },
  created() {
    let path = this.$route.query;
    if(path.keyword) {
      this.isSearched = true;
      this.getBookResults({keyword: path.keyword, page: path.page, type: path.type});
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
