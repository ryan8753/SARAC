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
  import  axios from 'axios'

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
        const href = window.location.href
        const params = new URL(href).searchParams
        const accessToken = params.get("accessToken")
        const refreshToken = params.get("refreshToken")
        this.login(this.accessToken,this.refreshToken)
        console.log(accessToken, refreshToken)
       }

    },
    async created() {
       axios.defaults.baseURL = 'http://localhost:8080'
        const href = window.location.href
        const params = new URL(href).searchParams
        const accessToken = params.get("accessToken")
        const refreshToken = params.get("refreshToken")
      
      if (accessToken) {
        try {
        const response = await axios({
          method: 'get',
          url: 'api/v1/user/me',
          headers: {
            // Authorization: `Bearer ${getState().auth.token}`
            Authorization: `Bearer ${accessToken}`
          },
        })
        console.log('응답:', response.data)
        return response.data
      } catch (err) {
        console.log("에러에러")
        console.log(accessToken)
        console.log(refreshToken)
      }
      }

     }
  }

</script>
