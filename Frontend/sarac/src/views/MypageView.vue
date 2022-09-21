<template>
  <div>
    <img
      src="https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/0e3f7785-e374-4013-b268-601cc2b5881a-50.png"
      alt=""
    />
    <div>
      닉네임
      <v-btn>
        <v-icon> mdi-pencil</v-icon>
      </v-btn>
    </div>

    <v-checkbox label="서재공개" color="indigo darken-3"></v-checkbox>
    <v-checkbox label="리뷰공개" color="indigo darken-3"></v-checkbox>
    <v-btn color="warning" large dark @click="logoutAndRefresh">
      로그아웃
    </v-btn>
    <v-btn color="error" large dark @click="signout">회원탈퇴</v-btn>
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
    ...mapActions(accountStore, ["logout", "signout"]),
    logoutAndRefresh() {
      this.logout();
    },
  },
};
</script>

<style></style>
