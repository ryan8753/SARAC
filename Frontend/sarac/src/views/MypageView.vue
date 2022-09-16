<template>
  <div>
    <v-btn color="warning" large dark @click="logout"> 로그아웃 </v-btn>
    <v-btn color="error" large dark>회원탈퇴</v-btn>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
    name: "MypageView",
    computed: {
        ...mapState(["user"]),
    },
    methods: {
        logout() {
            this.$store.dispatch("logout");
        }
    }
};
</script>

<style></style>
