<template>
  <div>
    <div>검색 결과</div>
    <v-list>
      <v-list-item
        v-for="user in userList"
        :key="user.userId"
        class="ma-0 pa-0"
        @click="goUserInfo(user.userId)"
      >
        <v-list-item-avatar>
          <v-img :src="user.imagePath"> </v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title
            v-text="`${user.nickname}#${user.userId}`"
          ></v-list-item-title>
          <v-list-item-subtitle>
            <span v-for="(tag, index) in user.userHashtag" :key="index"
              >#{{ tag }}
            </span>
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

const myFeedStore = "myFeedStore";

export default {
  name: "UserSearchResult",

  data() {
    return {};
  },
  computed: {
    ...mapState(myFeedStore, ["userList"]),
  },
  methods: {
    ...mapMutations(myFeedStore, ["GET_USER_LIST"]),
    ...mapActions(myFeedStore, ["getSearchUserInfo"]),

    goUserInfo(userId) {
      this.getSearchUserInfo({ userId: userId });
      this.$router.push("/myfeed");
    },
  },
  destroyed() {
    // store에 userList 초기화 로직
    this.GET_USER_LIST([]);
  },
};
</script>

<style scoped></style>
