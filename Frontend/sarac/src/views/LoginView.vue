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
  </div>
</template>

<script>
export default {
  name: "Home",

  components: {},
  data() {
    return {
      URL: "http://localhost:8080/oauth2/authorization/kakao",
    };
  },
};
</script>
<style scoped>

img {
  width: 80%;
}

</style>