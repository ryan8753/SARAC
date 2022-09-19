<template>
  <div class="container">
    <!-- <v-app>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        />
      </div>

      <v-spacer></v-spacer>

      <v-btn
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
        text
      >
        <span class="mr-2">Latest Release</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

  </v-app> -->
    <v-main>
      <router-view />
    </v-main>
  </div>
</template>

<script>
// vuex 라이브러리에서 mapActions, mapGetters 함수를 가져옵니다.
import { mapActions, mapGetters } from "vuex";

const accountStore = "accountStore";
function setScreenSize(newVal) {
    newVal = newVal * 0.01;
    document.documentElement.style.setProperty("--vh", `${newVal}px`);
  }

export default {
  name: "App",
  data: () => {
    return {vh: window.innerHeight};
  },
  watch: {
    vh(newVal){ setScreenSize(newVal); }
  },
  computed: {
    ...mapGetters(accountStore, []),
  },
  methods: {
    ...mapActions(accountStore, ["getUserInfo"]),
  },
  created() {
    const accessToken = localStorage.getItem("accessToken")
    const refreshToken = localStorage.getItem("refreshToken")
    const payload = {
      accessToken: accessToken,
      refreshToken: refreshToken,
    };
    if (accessToken) {
      this.getUserInfo(payload);
    }
    setScreenSize(this.vh);
  }
};
</script>

<style scoped>
  @media(min-width: 600px) {
    .container {
      width: 600px;
      padding: 0px;
      position: static;
    
    }
  }
  .container {
    background-color: white;
    height: calc(var(--vh, 1vh)*100);
  }
  :root {
    height: calc(var(--vh, 1vh)*100);
    
  }
</style>>
