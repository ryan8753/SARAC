<template>
  <v-container>
    <v-row>
      <v-col cols="3">
        <v-row justify="center">
          <v-avatar size="9vh">
            <v-img :src="userInfo.imagePath" alt="profile"></v-img>
          </v-avatar>
        </v-row>

        <v-row justify="center"
          ><b>{{ userInfo.nickname }}</b></v-row
        >
      </v-col>
      <v-col cols="9">
        <v-sheet color="#F3EDED" class="rounded-lg" height="10vh">
          <v-row>
            <v-col><b>읽은책</b><br />{{ userInfo.read }}</v-col>
            <v-col><b>읽는중</b><br />{{ userInfo.reading }}</v-col>
            <v-col><b>읽을책</b><br />{{ userInfo.wish }}</v-col>
          </v-row>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState } from "vuex";

const myFeedStore = "myFeedStore";

export default {
  name: "UserInfo",
  data() {
    return {
      items: [],
    };
  },
  computed: {
    ...mapState(myFeedStore, ["userInfo"]),
  },
  methods: {
    // ...mapActions(myFeedStore, ["getUserInfo"]),
  },
  created() {
    this.items.push({ title: "읽은 책", subTitle: this.userInfo.read });
    this.items.push({ title: "읽는 중인 책", subTitle: this.userInfo.reading });
    this.items.push({ title: "읽을 책", subTitle: this.userInfo.wish });
  },
};
</script>

<style scoped>
* {
  font-size: 2vh;
  text-align: center;
}
</style>
