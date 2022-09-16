<template>
  <div>
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

export default {
  name: "App",
  data: () => {
    return {};
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
  }
};
</script>
