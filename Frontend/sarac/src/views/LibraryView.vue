<template>
  <div class="library-container">
    <v-row>
      <v-icon color="rgba(170, 83, 14, 1)" @click="goBack" type="button"
        >mdi-arrow-left-thick</v-icon
      >
    </v-row>
    <v-row>
      <v-col cols="10"
        ><b> {{ libraryName }} 서재 </b></v-col
      >
      <v-col cols="2">
        <v-icon color="rgba(170, 83, 14, 1)" @click="toggleBar()"
          >mdi-magnify</v-icon
        >
      </v-col>
    </v-row>

    <!-- 검색바(토글) -->
    <v-row id="showBar" style="display: none">
      <v-text-field
        :value="keyword"
        solo
        label="서재 내 검색"
        clearable
        append-icon="mdi-arrow-right-circle-outline"
        @click:append="searchInLibrary(keyword)"
        color="rgba(170, 83, 14, 1)"
      ></v-text-field>
    </v-row>
    <v-row>
      <library></library>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import Library from "@/components/LibraryView/Library.vue";

const myFeedStore = "myFeedStore";
const accountStore = "accountStore";

export default {
  name: "LibararyView",

  components: {
    Library,
  },
  data() {
    return {
      libraryName: "",
      keyword: "",
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
      let e = document.getElementById("showBar");
      e.style.display = e.style.display != "none" ? "none" : "block";
    },
    searchInLibrary() {
      
    },
    goBack() {
      this.$router.go(-1);
    },
  },
      created() {
        let person = Object.keys(this.libraryList)[0];
        if (person == "me") this.libraryName = "내";
        else this.libraryName = `${this.userInfo.nickname}님의`;
      },
};
</script>

<style scoped>
.library-container {
  box-sizing: border-box;
  margin: 10%;
}
</style>
