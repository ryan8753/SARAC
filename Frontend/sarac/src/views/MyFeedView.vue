<template>
  <div class="myFeed-container">
    <!-- mypage 이동 nav 목록 -->
    <v-navigation-drawer v-model="drawer" absolute temporary width="70%">
      <v-divider></v-divider>

      <v-list-item>
        <v-list-item-avatar>
          <v-img :src="user.imagePath"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{ user.nickname }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          :to="{ path: item.router }"
          class="router"
          link
        >
          <v-list-item-icon>
            <v-icon color="rgba(170, 83, 14, 1)">{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <!-- view에 랜더링할 컴포넌트들 -->
    <v-row>
      <v-col cols="10" align="center">
        <user-search-bar></user-search-bar>
      </v-col>
      <v-col cols="2" align="center">
        <v-icon color="rgba(170, 83, 14, 1)" @click.stop="drawer = !drawer"
          >mdi-reorder-horizontal</v-icon
        >
      </v-col>
    </v-row>
    <v-row @click="goLibrary"
      ><!-- 클릭효과 내기 -->
      <user-info></user-info>
    </v-row>
    <v-row>
      <v-col align="center"
        ><v-btn
          small
          :color="clickR ? 'rgba(170, 83, 14, 1)' : '#F3EDED'"
          :class="clickR ? 'white--text' : 'black--text'"
          @click="showReview"
          >리뷰</v-btn
        ></v-col
      >
      <v-col align="center"
        ><v-btn
          small
          :color="clickS ? 'rgba(170, 83, 14, 1)' : '#F3EDED'"
          :class="clickS ? 'white--text' : 'black--text'"
          @click="showStatistic"
          >통계</v-btn
        ></v-col
      >
    </v-row>
    <router-view />
  </div>
</template>

<script>
import UserSearchBar from "@/components/MyFeedView/UserSearchBar.vue";
import UserInfo from "@/components/MyFeedView/UserInfo.vue";
import { mapState, mapMutations, mapActions } from "vuex";

const myFeedStore = "myFeedStore";
const accountStore = "accountStore";

export default {
  name: "MyFeedView",

  components: {
    UserSearchBar,
    UserInfo,
  },
  data() {
    return {
      currentUser: {
        userId: 0,
      },
      drawer: null,
      items: [
        { title: "회원정보수정", icon: "mdi-cog", router: "/mypage" },
        { title: "내서재", icon: "mdi-bookshelf", router: "/library/me" },
        { title: "뱃지확인", icon: "mdi-police-badge", router: "/badge" },
      ],
      clickR: true,
      clickS: false,
    };
  },
  methods: {
    ...mapActions(myFeedStore, ["getUserInfo"]),
    ...mapMutations(myFeedStore, ["SET_LIBRARY_NUM"]),

    getCurrentUser(currentUser) {
      this.getUserInfo(currentUser);
    },
    goLibrary() {
      // 라이브러리 보내는 로직
      this.SET_LIBRARY_NUM(this.currentUser.userId);
      this.$router.push("/library/" + this.currentUser.userId);
    },
    showReview() {
      this.clickR = true;
      this.clickS = false;
      this.$router.push({ name: "userreview" });
    },
    showStatistic() {
      this.clickR = false;
      this.clickS = true;
      this.$router.push({ name: "userstatistics" });
    },
  },
  computed: {
    ...mapState(accountStore, ["user"]),
  },
  watch: {
    ...mapState(myFeedStore, ["userInfo"]),
  },
  created() {
    if (this.userInfo == null) this.currentUser.userId = this.user.userId;
    else this.currentUser.userId = this.userInfo.userId;

    this.getCurrentUser(this.currentUser);

    this.$router.push({ name: "userreview" });
  },
};
</script>

<style scoped>
.myFeed-container {
  box-sizing: border-box;
  margin: 10%;
}
.router {
  text-decoration: none;
}
</style>
