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

    <v-btn value="write" min-width="50px">
      <span>Write</span>

      <v-icon>mdi-pencil</v-icon>
    </v-btn>

    <v-btn value="myFeed" min-width="50px">
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
      this.$router.replace({ path: `/` + newRoute });
    },
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
}
</style>
