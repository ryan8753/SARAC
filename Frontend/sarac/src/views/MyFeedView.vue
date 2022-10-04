<template>
  <div class="myFeed-container">
    <!-- mypage 이동 nav 목록 -->
    <v-navigation-drawer v-model="drawer" absolute temporary width="70%">
      <v-divider></v-divider>

      <v-list-item v-if="user != null" @click="goMyInfo()">
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
          class="router"
          @click="clickNav(item.title)"
        >
          <v-list-item-icon>
            <v-icon color="#E3984B">{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <!-- 상단 검색바, 네비게이션 버튼 랜더링 -->
    <v-row>
      <v-col cols="10" align="center">
        <user-search-bar></user-search-bar>
      </v-col>
      <v-col cols="2" align="center">
        <v-icon color="#E3984B" @click.stop="drawer = !drawer"
          >mdi-reorder-horizontal</v-icon
        >
      </v-col>
    </v-row>

    <!-- 사용자 정보 or 사용자 검색 목록 -->
    <router-view />
  </div>
</template>

<script>
import UserSearchBar from "@/components/MyFeedView/UserSearchBar.vue";
import { mapState, mapActions } from "vuex";

const myFeedStore = "myFeedStore";
const accountStore = "accountStore";

export default {
  name: "MyFeedView",

  components: {
    UserSearchBar,
  },
  data() {
    return {
      drawer: null,
      items: [
        { title: "회원정보수정", icon: "mdi-cog", router: "/mypage" },
        { title: "내서재", icon: "mdi-bookshelf", router: "/library" },
        // 통계 어떻게 할지 고민좀 해보기
        { title: "통계", icon: "mdi-chart-bar", router: "/myfeed/statistics" },
      ],
    };
  },
  computed: {
    ...mapState(accountStore, ["user"]),
    ...mapState(myFeedStore, ["userInfo"]),
  },
  watch: {},
  methods: {
    ...mapActions(myFeedStore, ["getLibrary", "getSearchUserInfo"]),

    goLibrary(userId) {
      this.getLibrary({ userId: userId });
    },
    // 통계 관련 함수..... 바꿔야됨
    showStatistic(userId) {
      this.getSearchUserInfo({ userId: userId });
      this.drawer = false;
      this.$router.push({ name: "userstatistics" }).catch(() => {});
    },
    clickNav(title) {
      if (title === "회원정보수정")
        this.$router.push({ name: "mypage" }).catch(() => {});
      else if (title === "내서재") {
        this.goLibrary(this.user.userId);
      } else if (title === "통계") {
        // userInfo 바꾸고 기존 통계 페이지??? 그냥 새로운 통계페이지?????

        this.showStatistic(this.user.userId);
      }
    },
    goMyInfo() {
      this.getSearchUserInfo({ userId: this.user.userId });
      this.drawer = false;
      this.$router.push({ name: "myfeed" }).catch(() => {});
    },
  },
  created() {},
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
