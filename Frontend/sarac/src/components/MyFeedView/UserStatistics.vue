<template>
  <v-container>
    워드클라우드
    <br/>
    <br/>
    <br/>
    내 서재의 총 가격 : {{statistics.totalPrice}} 원
    <br/>
    내가 준 평균 평점 : {{statistics.totalScore}} 점
    <br/>
    소음 측정 횟수 :
    <br/>
    <br/>
    <br/>
    많이 쓴 해쉬태그
  </v-container>
</template>

<script>
import { mapActions,mapState  } from "vuex";
const statisticsStore = "statisticsStore";
const accountStore = "accountStore";

export default {
 name: "UserStatistics",
  
 data() {
    return {
      statistics:{totalScore:null,totalPrice:null},
    };
  },
    computed: {
    ...mapState(accountStore, ["user"]),
  },
  created() {
    if (this.$route.params.userId == null) {
    this.getMyInfo()  
    }else{
      this.getOtherInfo()
    }
  },

  methods: {
    ...mapActions(statisticsStore, ["getMyStatistics","getOtherStatistics"
    ]),

  async getMyInfo() {
      this.statistics = await this.getMyStatistics();
    },
    async getOtherInfo() {
      this.statistics = await this.getOtherStatistics(this.user.userId);
    },
  },


}
</script>

<style>

</style>