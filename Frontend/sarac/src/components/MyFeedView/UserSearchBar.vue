<template>
  <div>
    <v-row>
      <v-text-field
        v-model="keyword"
        solo
        label="사용자 닉네임, 코드를 검색하세요"
        clearable
        append-icon="fa-search"
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
