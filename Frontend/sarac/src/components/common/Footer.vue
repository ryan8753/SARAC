<template>
  <v-bottom-navigation
    v-model="value"
    color="rgba(170, 83, 14, 1)"
    background-color="rgb(245,245,245)"
    height="4rem"
    shift
  >
    <v-btn value="home" min-width="50px">
      <span>home</span>

      <v-icon size="auto">mdi-home</v-icon>
    </v-btn>

    <v-btn value="search" min-width="50px">
      <span>Search</span>

      <v-icon>mdi-book-search</v-icon>
    </v-btn>

    <v-btn value="review" min-width="50px" >
      <span>Write</span>

      <v-icon>mdi-pencil</v-icon>
    </v-btn>

    <v-btn value="myfeed/review" min-width="50px">
      <span>myFeed</span>

      <v-icon>mdi-account-circle</v-icon>
    </v-btn>

    <v-btn value="nearby" min-width="50px">
      <span>Nearby</span>

      <v-icon size="auto">mdi-map-marker</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  data: () => {
    return { value: "home" };
  },
  watch: {
    value: function (newRoute) {
      // 라우터 중복 오류 무시
      this.$router.replace({ path: `/` + newRoute }).catch(()=>{});
    },
  },
  created() {
    // 새로고침시 footer 상태 유지 (value 값에 현재 url path 설정)
    this.value = window.location.pathname.substring(1);
  }
};
</script>

<style scoped>
@media (min-width: 600px) {
  button {
    /* padding: 0px !important;s */
    width: 120px !important;
  }
}
button {
  align-self: center;
}
</style>
