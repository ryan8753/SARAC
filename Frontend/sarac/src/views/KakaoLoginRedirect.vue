<template>
  <div>
    <div>로그인페이지</div>
    <a :href="URL">
      <img
        className="kakao-box-login block"
        alt="kakaoLoginBtn"
        src="icons/kakaoLogo.png"
      />
    </a>
    <button @click="redirectClick">클릭클릭</button>
  </div>
</template>

<script>
import { mapActions } from "vuex";
const accountStore = "accountStore";

export default {
  name: "Home",

  components: {},
  data() {
    // return {
    //   accessToken: accessToken,
    //   refreshToken: refreshToken,
    // };
  },
  methods: {
    ...mapActions(accountStore, ["login"]),
  },

  created() {
    const href = window.location.href;
    const params = new URL(href).searchParams;
    const accessToken = params.get("accessToken");
    const refreshToken = params.get("refreshToken");

    const payload = {
      accessToken: accessToken,
      refreshToken: refreshToken,
    };

    this.login(payload);
  },
};
</script>
