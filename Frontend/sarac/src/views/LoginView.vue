<template>
  <div>
    <div class="title">사詞 락樂</div>
    <div class="subtitle">글을 즐기다</div>
    <img src="" alt="">

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