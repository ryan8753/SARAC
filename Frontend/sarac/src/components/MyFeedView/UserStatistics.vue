<template>
  <v-container>
    <div>많이 본 장르</div>
    <p />
    <div>
      <v-row justify="center" v-if="statistics.topGenre.length > 0">
        <v-col
          cols="4"
          class="d-flex align-center flex-column"
          v-for="(item, j) in statistics.topGenre"
          :key="j"
        >
          <v-img
            justify="center"
            align="center"
            :src="rank[j]"
            contain
            height="35px"
            width="35px"
            style="solid black; margin-left: 10px"
          />

          <p class="p" align="center">
            &nbsp;
            {{
              item.genre
                .split(">")
                [item.genre.split(">").length - 1].replace("일반", "")
                .replaceAll(" ", "")
            }}
          </p>
          ({{ item.cnt }})
        </v-col>
      </v-row>

      <v-row justify="center" v-else
        ><br />
        <br />
        <h1>완독한 책이 없습니다!</h1>
      </v-row>
    </div>

    <br />

    <v-row>
      <v-simple-table class="pa-0 ma-0 table" dense>
        <tbody>
          <tr>
            <td><b>읽은 총 페이지 수</b></td>
            <td>{{ statistics.totalPage }} 쪽</td>
          </tr>
          <tr>
            <td><b>서재의 총 가격</b></td>
            <td>{{ statistics.totalPrice }} 원</td>
          </tr>
          <tr>
            <td><b>리뷰의 평균 평점</b></td>
            <td>{{ statistics.totalScore }} 점</td>
          </tr>
          <tr>
            <td><b>소음 측정 횟수</b></td>
            <td>{{ statistics.totalNoise }} 번</td>
          </tr>
          <tr>
            <td />
            <td />
          </tr>
        </tbody>
      </v-simple-table>
    </v-row>
    많이 쓴 해시태그
    <cloud
      :data="words"
      :fontSizeMapper="fontSizeMapper"
      width="250"
      height="145"
    />
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
      rank: [
        require("@/assets/img/1.png"),
        require("@/assets/img/2.png"),
        require("@/assets/img/3.png"),
      ],
      statistics: {},
      words: [],
      fontSizeMapper: (word) => Math.log2(word.value) * 7,
    };
  },

  computed: {
    getrank(j) {
      return this.rank[j];
    },
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
      console.log(this.statistics);
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

<style scoped>
.wordCloud {
  width: 30px;
  height: 30px;
}
td,
tr {
  font-size: 2vh !important;
  vertical-align: middle !important;
}
h1 {
  font-size: 3vh !important;
  font-weight: bold;
  margin: 0px;
  text-align: center;
  color: #e3984b;
}
.p {
  font-size: 1vh;
  margin-bottom: 0px;
}
</style>
