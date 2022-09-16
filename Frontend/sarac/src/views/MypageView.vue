<template>
  <div>
    <v-btn color="warning" large dark @click="logoutAndRefresh"> 로그아웃 </v-btn>
    <v-btn color="error" large dark @click="signout">회원탈퇴</v-btn>
    <img src="https://sarac-a505.s3.ap-northeast-2.amazonaws.com/8d369aae-2831-45b7-9d0c-469912ad19c1-%EB%A3%A8%ED%94%BC.png" alt="">  
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const accountStore = "accountStore";

export default {
    name: "MypageView",
    computed: {
        ...mapState(["user"]),
    },
    methods: {
        ...mapActions(accountStore,['logout','signout']),
        logoutAndRefresh() {
          this.logout();
        }
    }
};
</script>

<style></style>
