<template>
  <div>
    <!-- 클릭하면 서재로 -->
    <v-row @click="goLibrary(userInfo.userId)">
      <user-info></user-info>
    </v-row>

    <!-- 리뷰/통계 전환 탭 -->
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
import UserInfo from "@/components/MyFeedView/UserInfo.vue";
import { mapState, mapActions } from "vuex";

const myFeedStore = "myFeedStore";

export default {
  name: "UserActivity",

  components: {
    UserInfo,
  },
  data() {
    return {
      clickR: true,
      clickS: false,
    };
  },
  computed: {
    ...mapState(myFeedStore, ["userInfo"]),
  },
  watch: {
    $route() {
      let tmp = window.location.pathname.split("/");
      if (tmp[tmp.length - 1] == "review") {
        this.clickR = true;
        this.clickS = false;
      }
      else if(tmp[tmp.length - 1] == "statistics") {
        this.clickR = false;
        this.clickS = true;
      }
    }
  },
  methods: {
    ...mapActions(myFeedStore, ["getLibrary"]),

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
  },
  created() {
    this.$router.push({ name: "userreview" }).catch(() => {});
    this.toggle = "review";
  },
};
</script>

<style scoped></style>
