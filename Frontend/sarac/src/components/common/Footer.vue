<template>
  <v-bottom-navigation
    v-model="value"
    color="rgba(227,152,75)"
    background-color="white"
    height="10vh"
    shift
    grow
  >
    <v-btn value="home" min-width="50px" plain>
      <span>home</span>

      <v-icon size="auto">mdi-home</v-icon>
    </v-btn>

    <v-btn value="search" min-width="50px"  plain>
      <span>Search</span>

      <v-icon>mdi-book-search</v-icon>
    </v-btn>

    <v-btn value="review" min-width="50px"  plain>
      <span>Write</span>

      <v-icon>mdi-pencil</v-icon>
    </v-btn>

    <v-btn value="myfeed" min-width="50px"  plain>
      <span>myFeed</span>

      <v-icon>mdi-account-circle</v-icon>
    </v-btn>

    <v-btn value="nearby" min-width="50px"  plain>
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
      this.$router.replace({ path: `/` + newRoute }).catch(() => {});
    },
  },
  created() {
    // 새로고침시 footer 상태 유지 (value 값에 현재 url path 설정)
    const a = window.location.pathname.split("/")[1];
    const b = ["home","search","review","myfeed","nearby"]
    if(b.includes(a)){
      this.value = window.location.pathname.split("/")[1];
    }
  },
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
  height: 90% !important;
}
</style>
