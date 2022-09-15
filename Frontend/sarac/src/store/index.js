import Vue from 'vue'
import Vuex from 'vuex'
// import router from '@/router'
import axios from 'axios'
// import drf from '@/api/drf'


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  getters: {
  },
  mutations: {
  },



  actions: {
    async login({accessToken, refreshToken}) {
      try {
        const response = await axios({
          method: 'get',
          url: 'api/v1/user/me',
          headers: {
            // Authorization: `Bearer ${getState().auth.token}`
            Authorization: `Bearer ${accessToken}`
          },
        })
        return response.data
      } catch (err) {
        console.log("에러에러")
        console.log(refreshToken)
      }}
    },



  modules: {
  }
})
