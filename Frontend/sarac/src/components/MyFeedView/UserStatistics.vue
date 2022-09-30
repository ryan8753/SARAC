<template>
  <v-container>
    워드클라우드
    <br/>
    <br/>
    <br/>
    내 서재의 총 가격 : {{totalPrice}}
    <br/>
    내가 준 평균 평점 : {{totalScore}}
    <br/>
    소음 측정 횟수 :
    <br/>
    <br/>
    <br/>
    많이 쓴 해쉬태그
  </v-container>
</template>

<script>
import { mapActions,  } from "vuex";
const statisticsStore = "statisticsStore";



export default {
 name: "UserStatistics",
  
 data() {
    return {
      totalPrice: "",
      totalScore: ""

    };
  },
  computed: {
    
  },
  methods: {
    ...mapActions(statisticsStore, ["getStatistics"
    ]),

  
  },


}
</script>

<style>

</style>