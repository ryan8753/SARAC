<template>
  <v-container>
    <v-row>
      <cloud
      :data="words"
      :fontSizeMapper="fontSizeMapper"
      width="200"
      height="200"
    />
    </v-row>
    <v-row>
      
    </v-row>
    <b>내가 읽은 총 페이지 수 :</b> {{ statistics.totalPage }} 쪽
    <br />
    <b>내 서재의 총 가격 :</b> {{ statistics.totalPrice }} 원
    <br />
    <b>내가 준 평균 평점 : </b>{{ statistics.totalScore }} 점
    <br />
    <b> 소음 측정 횟수 :</b> {{ statistics.totalNoise }} 번
    <br />
    <br />
    <br />
    <b> 많이 쓴 해쉬태그 </b>
    
  </v-container>
</template>

<script>
import Cloud from "vue-d3-cloud";
import { mapActions, mapState } from "vuex";
const statisticsStore = "statisticsStore";
const accountStore = "accountStore";
const myFeedStore = "myFeedStore";

export default {
  name: "UserStatistics",

  data() {
    return {
      statistics: {},
      words: [],
      fontSizeMapper: (word) => Math.log2(word.value) * 10,
    };
  },

  computed: {
    ...mapState(accountStore, ["user"]),
    ...mapState(myFeedStore, ["userInfo"]),
  },
  components: {
    Cloud,
  },
  created() {
    if (this.userInfo.userId == this.user.userId) {
      this.getMyInfo();
      this.getMyHashtagInfo();
    } else {
      this.getOtherInfo();
      this.getOtherHashtagInfo();
    }
  },
  watch: {
    userInfo() {
      if (this.userInfo.userId == this.user.userId) {
        this.getMyInfo();
        this.getMyHashtagInfo();
      } else {
        this.getOtherInfo();
        this.getOtherHashtagInfo();
      }
    },
  },
  methods: {
    ...mapActions(statisticsStore, [
      "getMyStatistics",
      "getOtherStatistics",
      "getMyHashtag",
      "getOtherHashtag",
    ]),

    async getMyInfo() {
      this.statistics = await this.getMyStatistics();
    },
    async getOtherInfo() {
      this.statistics = await this.getOtherStatistics(this.userInfo.userId);
    },
    async getMyHashtagInfo() {
      // this.words = JSON.parse(this.getMyHashtag());
      this.words = await this.getMyHashtag();
    },
    async getOtherHashtagInfo() {
      // this.words = JSON.parse(this.getMyHashtag());
      this.words = await this.getOtherHashtag(this.userInfo.userId);
    },
  },
};
</script>

<style>
.wordCloud {
  width: 30px;
  height: 30px;
}
</style>
