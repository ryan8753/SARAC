<template>
  <v-app class="container">
    <v-main class="footerplease">
      <router-view />
    </v-main>
    <Footer v-if="footerOn" />
  </v-app>
</template>

<script>
// vuex 라이브러리에서 mapActions, mapGetters 함수를 가져옵니다.
import { mapActions, mapGetters } from "vuex";
import Footer from "./components/common/Footer.vue";
// import store from "./store/index.js";

const accountStore = "accountStore";
function setScreenSize() {
  let vh = window.innerHeight * 0.01;
  document.documentElement.style.setProperty("--vh", `${vh}px`);
}
setScreenSize();
window.addEventListener("resize", setScreenSize);

export default {
  name: "App",
  components: {
    Footer: Footer,
  },
  data: () => {
    return {
      vh: window.innerHeight,
      footerOn: null,
    };
  },

  computed: {
    ...mapGetters(accountStore, ["isLoggedIn"]),
  },
  methods: {
    ...mapActions(accountStore, ["getUserInfo"]),
  },
  created() {
    const accessToken = localStorage.getItem("accessToken");
    const refreshToken = localStorage.getItem("refreshToken");
    const payload = {
      accessToken: accessToken,
      refreshToken: refreshToken,
    };
    if (accessToken) {
      this.getUserInfo(payload);
      // this.$router.push('/mypage')
    } else {
      // this.$router.push('/login')
    }

    if (window.location.pathname != "/login") {
      this.footerOn = true;
    }
  },
};
</script>

<style scoped>
@media (min-width: 600px) {
  .container {
    width: 600px;
    padding: 0px;
  }
}
.container {
  background-color: white;
  height: calc(var(--vh, 1vh) * 100);
  padding: 0px;
}

.footerplease {
  overflow: auto;
  height: calc(100% - 3rem);
}
</style>
>
