<template>
  <div>
    <v-row>
    <v-col>
        <v-avatar>
      <img
        :src="this.userInfo.imagePath"
        alt="profile"
        size="128"
      >
    </v-avatar>
    </v-col>
    <v-col>
        상세정보
    </v-col>
    </v-row>
  </div>
</template>

<script>
// import { mapState } from 'vuex';
// import myFeedStore from '../../store/modules/myFeedStore';

export default {
    name: "UserInfo",
    data() {
      return {
        // userId: 1,

      }
    },
    props: ["userInfo"],
    computed: {
        // ...mapState(myFeedStore, ["userInfo"]),
    },
    methods: {
        // ...mapActions(myFeedStore, ["getUserInfo"]),

    },
    created() {
      // this.$router.go(this.$router.currentRoute);
        // this.$store.dispatch(myFeedStore, "getUserInfo", 1); // 우선 내 정보
        // console.log("sss")
    }
}
</script>

<style>

</style>