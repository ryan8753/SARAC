<template>
  <div class="myFeed-container">
    <!-- mypage 이동 nav 목록 -->
    <v-navigation-drawer v-model="drawer" absolute temporary width="70%">
      <v-divider></v-divider>

      <v-list-item v-if="user != null">
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

    <!-- 사용자 정보 (router로 뺄지 고민해보기!) -->
    <v-row @click="goLibrary(userInfo.userId)"
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
import { mapState, mapActions } from "vuex";

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
      vm: this,
      drawer: null,
      items: [
        { title: "회원정보수정", icon: "mdi-cog", router: "/mypage" },
        { title: "내서재", icon: "mdi-bookshelf", router: "/library" },
        // 통계 어떻게 할지 고민좀 해보기
        { title: "통계", icon: "mdi-chart-bar", router: "/myfeed/statistics" },
      ],
      clickR: true,
      clickS: false,
    };
  },
  computed: {
    ...mapState(accountStore, ["user"]),
    ...mapState(myFeedStore, ["userInfo"]),
  },
  watch: {},
  methods: {
    ...mapActions(myFeedStore, ["getUserInfo", "getLibrary"]),

    goLibrary(userId) {
      this.getLibrary({ userId: userId });
    },
    showReview() {
      this.clickR = true;
      this.clickS = false;
      this.$router.push({ name: "userreview" }).catch(() => {});
    },
    showStatistic() {
      this.clickR = false;
      this.clickS = true;
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
  },
  created() {
    // 라우터 중복 무시
    this.$router.push({ name: "userreview" }).catch(() => {});
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
