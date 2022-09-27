<template>
  <div class="myFeed-container">
    <v-row>
      <v-col cols="10">
        <user-search-bar></user-search-bar>
      </v-col>
      <v-col cols="2"> 마이페이지 네비게이션 </v-col>
    </v-row>
    <!-- <user-info :userInfo="userInfo"></user-info> -->
  </div>
</template>

<script>
import UserSearchBar from "@/components/MyFeedView/UserSearchBar.vue";
// import UserInfo from "@/components/MyFeedView/UserInfo.vue";
import { mapState, mapActions } from "vuex";

const myFeedStore = "myFeedStore";
const accountStore = "accountStore";

export default {
  name: "MyFeedView",

  components: {
    UserSearchBar,
    // UserInfo,
  },
  data() {
    return {
      currentUser: {
        userId: 0,
      },
    };
  },
  methods: {
    ...mapActions(myFeedStore, ["getUserInfo"]),

    getCurrentUser(currentUser) {
      this.getUserInfo(currentUser);
    },
  },
  computed: {},
  watch: {
    ...mapState(myFeedStore, ["userInfo"]),
    ...mapState(accountStore, ["user"]),
  },
  created() {
    this.getCurrentUser(this.currentUser);
    console.log("sss");
  },
};
</script>

<style scoped>
.myFeed-container {
  padding: 5%;
}
</style>
