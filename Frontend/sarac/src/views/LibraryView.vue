<template>
  <div class="library-container">
    <v-row>
      <v-icon color="#E3984B" @click="goBack" type="button"
        >mdi-arrow-left-thick</v-icon
      >
    </v-row>
    <v-row>
      <v-col cols="10"
        ><b> {{ libraryName }} 서재 </b></v-col
      >
      <v-col cols="2">
        <v-icon color="#E3984B" @click="toggleBar()">mdi-magnify</v-icon>
      </v-col>
    </v-row>

    <!-- 검색바(토글) -->
    <v-row id="showBar" style="display: none">
      <v-text-field
        v-model="keyword"
        solo
        label="서재 내 검색"
        clearable
        color="#E3984B"
      ></v-text-field>
    </v-row>

    <v-row v-if="isSearchBarOn">
      <search-library :keyword="keyword"></search-library>
    </v-row>
    <v-row v-else>
      <library></library>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import Library from "@/components/LibraryView/Library.vue";
import SearchLibrary from "@/components/LibraryView/SearchLibrary.vue";

const myFeedStore = "myFeedStore";
const accountStore = "accountStore";

export default {
  name: "LibararyView",

  components: {
    Library,
    SearchLibrary,
  },
  data() {
    return {
      libraryName: "",
      keyword: "",
      isSearchBarOn: false,
      isOpen: true,
    };
  },
  computed: {
    ...mapState(accountStore, ["user"]),
    ...mapState(myFeedStore, ["userInfo", "libraryList"]),
  },
  watch: {},
  methods: {
    ...mapActions(myFeedStore, ["getUserInfo"]),

    toggleBar() {
      if (this.isOpen) {
        let e = document.getElementById("showBar");
        e.style.display = e.style.display != "none" ? "none" : "block";
        this.isSearchBarOn = this.isSearchBarOn != false ? false : true;
      }
    },
    goBack() {
      this.$router.go(-1);
    },
  },
  created() {
    let person = Object.keys(this.libraryList)[0];
    if (person == "me") this.libraryName = "내";
    else this.libraryName = `${this.userInfo.nickname}님의`;

    if (person == "private") this.isOpen = false;
  },
};
</script>

<style scoped>
.library-container {
  box-sizing: border-box;
  margin: 10%;
}
</style>
