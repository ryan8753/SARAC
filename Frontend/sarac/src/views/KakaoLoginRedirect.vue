<template>
  <div>
    <div>로그인리다이렉트</div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const accountStore = "accountStore";

export default {
  name: "Home",

  components: {},
  data() {
    // return {
    //   accessToken: accessToken,
    //   refreshToken: refreshToken,
    // };
    return {};
  },
  methods: {
    ...mapActions(accountStore, ["getUserInfo"]),
  },
  computed: {
    ...mapState(accountStore, ["user"]),
  },

  created() {
    const href = window.location.href;
    const params = new URL(href).searchParams;
    const accessToken = params.get("accessToken");
    const refreshToken = params.get("refreshToken");
    localStorage.setItem("accessToken", accessToken);
    localStorage.setItem("refreshToken", refreshToken);

    const payload = {
      accessToken: accessToken,
      refreshToken: refreshToken,
    };

    this.getUserInfo(payload).then(() => this.$router.push('/home'));
  },
};
</script>
