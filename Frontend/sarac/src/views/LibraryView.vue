<template>
  <div class="library-container">

    <v-row>
        <v-col>
            {{libraryName}} 서재
        </v-col>
    <!-- 검색바 토글 -->
    </v-row>
    <v-row>
        안나올거야...?

    </v-row>
        <router-view />
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const myFeedStore = "myFeedStore";
const accountStore = "accountStore";

export default {
    name: "LibararyView",

    components: {
    },
    data() {
        return {
            libraryName: "",
        };
    },
    methods: {
        ...mapActions(myFeedStore, ["getUserInfo"]),
    },
    computed: {
        ...mapState(accountStore, ["user"]),
        ...mapState(myFeedStore, ["userInfo", "libraryNum"]),
    },
    watch: {
    },
    created() {
        if(this.user.userId == this.libraryNum)
            this.libraryName = "내";
        else
            this.libraryName = `${this.userInfo.nickname}님의`;
    },
};
</script>

<style scoped>
.library-container {
  box-sizing: border-box;
  margin: 10%;
}
</style>