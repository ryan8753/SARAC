<template>
  <v-container>
    워드클라우드
    <br />
    <br />
    <br />
    내 서재의 총 가격 : {{ statistics.totalPrice }} 원
    <br />
    내가 준 평균 평점 : {{ statistics.totalScore }} 점
    <br />
    소음 측정 횟수 : {{ statistics.totalNoise }} 번
    <br />
    <br />
    <br />
    많이 쓴 해쉬태그
    <cloud
      :data="words"
      :fontSizeMapper="fontSizeMapper"
      width="300"
      height="300"
    />
  </v-container>
</template>

<script>
import Cloud from "vue-d3-cloud";
import { mapActions, mapState } from "vuex";
const statisticsStore = "statisticsStore";
const accountStore = "accountStore";

export default {
  name: "UserStatistics",

  data() {
    return {
      statistics: { totalScore: null, totalPrice: null, totalNoise: null },
      words: [],
      fontSizeMapper: (word) => Math.log2(word.value) * 10,
    };
  },

  computed: {
    ...mapState(accountStore, ["user"]),
  },
  components: {
    Cloud,
  },
  created() {
    if (this.$route.params.userId == null) {
      this.getMyInfo();
      this.getMyHashtagInfo();
    } else {
      this.getOtherInfo();
    }
  },
  methods: {
    ...mapActions(statisticsStore, [
      "getMyStatistics",
      "getOtherStatistics",
      "getMyHashtag",
    ]),

    async getMyInfo() {
      this.statistics = await this.getMyStatistics();
    },
    async getOtherInfo() {
      this.statistics = this.getOtherStatistics(this.user.userId);
    },
    async getMyHashtagInfo() {
      // this.words = JSON.parse(this.getMyHashtag());
      this.words = await this.getMyHashtag();
      console.log(this.words);
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
