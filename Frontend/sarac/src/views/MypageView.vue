<template>
  <div>
    
    <!-- <img
      class="profile"
      :src="require('${path}')"
      alt=""
    /> -->
    <div>{{user.imagePath}}</div>

     <img
      class="profile"
      :src="path"
      alt=""
    />

    <div>
      {{user.nickname}}
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
    ...mapState(accountStore,["user"]),
  },
  methods: {
    ...mapActions(accountStore, ["logout", "signout"]),
    logoutAndRefresh() {
      this.logout();
    },
  },
  data(){
    return{
      path:this.user.imagePath
    }
  }
};
</script>

<style>
.profile {
  width: 200px;
  height: 200px;
  border-radius: 70%;
}
</style>
