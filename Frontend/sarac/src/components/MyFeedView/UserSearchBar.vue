<template>
  <div>
    <v-row>
      <v-text-field
        v-model="keyword"
        solo
        label="친구를 찾아보세요"
        clearable
        color="#E3984B"
        :prepend-inner-icon="backIcon"
        @click:prepend-inner="goBack"
        append-icon="mdi-magnify"
        @click:append="searchUser"
        @keydown.enter.prevent="searchUser"
        @focus="onUserSearch()"
        id="bar"
      ></v-text-field>
    </v-row>
  </div>
</template>

<script>
import { mapActions } from "vuex";

const myFeedStore = "myFeedStore";

export default {
  name: "UserSearchBar",
  data() {
    return {
      keyword: "",
      backIcon: "",
    };
  },
  computed: {},
  watch: {
    $route() {
      let tmp = window.location.pathname.split("/");
      if (tmp[tmp.length - 1] != "search") {
        this.backIcon = "";
        document.getElementById("bar").blur();
      }
    },
  },
  methods: {
    ...mapActions(myFeedStore, ["getSearchUserList"]),

    searchUser() {
      if (this.keyword != null && this.keyword.trim().length != 0) {
        this.getSearchUserList({ keyWord: this.keyword.trim() });
        this.keyword = "";
        document.getElementById("bar").blur();
      }
    },
    onUserSearch() {
      this.$router.push({ name: "usersearch" }).catch(() => {});
      this.backIcon = "mdi-arrow-left-thick";
    },
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped></style>
