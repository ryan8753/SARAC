import axios from "axios";

const likeStore = {
  namespaced: true,

  state: {

  },
  getters: {

  },
  mutations: {

  },
  actions: {
    // async voteUpCafe(commit, userCafePair) {
    voteUpCafe(commit, userCafePair) {
      // const response = await axios({
        axios({
        url: "api/v1/cafeLike/evalCafe",
        method: "PUT",
        headers: {
          // Token 관련 필요
        },
        data: {
          "cafe": {
            "id": userCafePair[0]
          },
          "user": {
            "id": userCafePair[1]
          },
          "goodOrBad": true
        },
      }).then((res) => {
        return res.data;
      });
      // return response;
    },
    // async voteDownCafe(commit, userCafePair) {
    voteDownCafe(commit, userCafePair) {
      // const response = await axios({
        axios({
        url: "api/v1/cafeLike/evalCafe",
        method: "PUT",
        headers: {
          // Token 관련 필요
        },
        data: {
          "cafe": {
            "id": userCafePair[0]
          },
          "user": {
            "id": userCafePair[1]
          },
          "goodOrBad": false
        },
      }).then((res) => {
        console.log(userCafePair);
        console.log(res);
        return res.data;
      });
      // return response;
    },
    // async removeVoteCafe(commit, userCafePair) {
    removeVoteCafe(commit, userCafePair) {
      // const response = await axios({
      axios({
        url: "api/v1/cafeLike/evalCafe",
        method: "DELETE",
        headers: {
          // Token 관련 필요
        },
        data: {
          "cafe": {
            "id": userCafePair[0]
          },
          "user": {
            "id": userCafePair[1]
          }
        },
      }).then((res) => {
        return res.data;
      }).catch((error) => {
        return error;
      });
      // return response;
    },
    // async getCafeLikeInfo(commit, userCafePair) {
    getCafeLikeInfo(commit, userCafePair) {
      // const response = await axios({
      axios({
        url: "api/v1/cafeLike/cafeLikeInfo",
        method: "POST",
        headers: {
          // Token 관련 필요
        },
        data: {
          "cafe": {
            "id": userCafePair[0]
          },
          "user": {
            "id": userCafePair[1]
          }
        }
      }).then((res) => {
        console.log(userCafePair);
        console.log(res);
        return res.data;
      }).catch((error) => {
        return error;
      });
      // return response;
    }
  },
};

export default likeStore;