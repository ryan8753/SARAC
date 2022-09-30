<template>
  <div>
    <v-row>
      <v-text-field
        v-model="keyword"
        solo
        label="친구를 찾아보세요"
        clearable
        append-icon="mdi-magnify"
        @click:append="searchUser"
        color="rgba(170, 83, 14, 1)"
      ></v-text-field>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "UserSearchBar",
  data() {
    return {
      keyword: "",
    };
  },
  methods: {
    searchUser() {
      console.log("aaa");
    },
  },
};
</script>

<style scoped>
</style>
