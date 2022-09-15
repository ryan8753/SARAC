<template>
  <div>
  <div>로그인페이지</div>
  <a :href="URL">
        <img className="kakao-box-login block" alt="kakaoLoginBtn" src="icons/kakaoLogo.png">
  </a>
  <button @click="redirectClick">클릭클릭</button>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'

  export default {
    name: 'Home',

    components: {
      
    },
    data() {
       const href = window.location.href
        const params = new URL(href).searchParams
        const accessToken = params.get("accessToken")
        const refreshToken = params.get("refreshToken")
      return {
        accessToken : accessToken,
        refreshToken: refreshToken,

      }
    },
    methods:{
       ...mapActions([
          'login'
       ]),
       redirectClick(){
        this.login(this.accessToken,this.refreshToken)
       }

    },
    // created() {
    //   const href = window.location.href
    //   const params = new URL(href).searchParams
    //   const accessToken = params.get("accessToken")
    //   const refreshToken = params.get("refreshToken")
    // }
  }

</script>
