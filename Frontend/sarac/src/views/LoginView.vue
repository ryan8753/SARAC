<template>
  <v-container class="login">
    <div class="title">사詞 락樂</div>
    <div class="subtitle">글을 즐기다</div>
    <div><img :src="img" alt="로고" /></div>

    <a :href="URL">
      <img
        className="kakao-box-login block"
        alt="kakaoLoginBtn"
        src="icons/kakaoLogo.png"
      />
    </a>
  </v-container>
</template>

<script>
export default {
  name: "Home",

  components: {},
  data() {
    return {
      URL: process.env.VUE_APP_API_URL_KAKAO,
      img: require('../assets/img/sarac_logo.png'),
    };
  },
};
</script>
<style scoped>
img {
  width: 80%;
}
div {
  text-align: center;
}
.login {
  display: table-cell;
  vertical-align: middle;
}
/* .title {
  font-weight: bold;
  font-size: 30vh;
} */
.top {
  box-sizing: border-box;
}
</style>
