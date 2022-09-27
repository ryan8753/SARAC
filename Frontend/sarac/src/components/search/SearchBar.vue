<template>
  <v-form>
    <v-container>
      <v-row>
        <v-col cols="2">
          <v-img src="../../../public/icons/logo.png"></v-img>
        </v-col>
        <v-col cols="9">
          <v-text-field
            v-model="keyword"
            append-icon="mdi-magnify"
            append-outer-icon="mdi-camera"
            filled
            clear-icon="mdi-close-circle"
            clearable
            type="text"
            placeholder="Search Keyword"
            @click:append="searchBook"
            @click:append-outer="getPicture"
            @click:clear="clearKeyword"
            color="rgba(170, 83, 14, 1)"
          ></v-text-field>
        </v-col>
        <v-col cols="1"> </v-col>
      </v-row>
    </v-container>
  </v-form>
</template>

<script>
import { mapActions, mapState } from "vuex";
const searchStore = "searchStore";

export default {
  data: () => ({
    keyword: "",
  }),
  computed: {
    ...mapState(["searchResults"]),
  },
  methods: {
    ...mapActions(searchStore, ["getBookResults"]),
    searchBook() {
      if (this.keyword === undefined || this.keyword.length < 2) {
        alert("2글자 이상 입력해주세요.");
        return;
      }
      this.getBookResults(this.keyword);
    },
    getPicture() {},
    clearKeyword() {
      this.keyword = "";
    },
  },
};
</script>
