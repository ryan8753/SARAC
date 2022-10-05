<template>
  <div v-if="user" class="mypage-container">
    <div class="profile-img">
      <label for="img-input"
        ><img class="profile" :src="user.imagePath" alt=""
      /></label>
      <v-file-input
        hide-input
        prepend-icon="mdi-camera"
        class="input-btn-camera"
        id="img-input"
        @change="selectFile"
      ></v-file-input>
    </div>
    <div v-if="isEditing" class="edit-box">
      <p align="center" class="text-title">닉네임 수정중</p>
      <div class="d-flex justify-center nickname-field">
        <v-text-field
          :placeholder="user.nickname"
          v-model="newNickName"
          class="centered-input text-center"
        ></v-text-field>
        <v-btn
          class="edit-btn"
          plain
          elevation="0"
          small
          @click="confirmEdit"
          color="rgba(227,152,75)"
          min-width="0px"
          >확인</v-btn
        >
      </div>
    </div>
    <div v-else class="edit-box">
      <p align="center" class="text-title">닉네임</p>
      <div class="d-flex justify-center nickname-field">
        <div class="d-inline-flex  flex-grow-1 text-center text-title">{{ user.nickname }}</div>
        <v-btn
          class="edit-btn"
          small
          plain
          elevation="0"
          @click="changeEditing"
          min-width="0px"
        >
          <v-icon> mdi-pencil</v-icon>
        </v-btn>
      </div>
    </div>

    <div>
      <p align="center" class="text-title">공개설정</p>
      <v-checkbox
        label="서재공개"
        color="black"
        prepend
        class="text-content m-bot "
        v-model="user.libraryOpen"
        @change="editOpenInfo"
        dense
        hide-details
        style="padding:0px text-align:center;"
         
      ></v-checkbox>
      <v-checkbox
        class="text-content m-bot"
        label="리뷰공개"
        color="black"
        v-model="user.reviewOpen"
        @change="editOpenInfo"
        dense
        hide-details
        style="padding:0px text-align:center;"
        
      ></v-checkbox>
    </div>

    <div class="btn-box">
      <v-btn color="rgba(227,152,75)" dark @click="logoutAndRefresh">
        로그아웃
      </v-btn>
      <v-btn color="rgba(255, 2, 2, 0.6)" dark @click="signout">회원탈퇴</v-btn>
    </div>
  </div>

  <div v-else>유저정보를 불러 올 수 없어요 다시 시도 해주세요.</div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const accountStore = "accountStore";

export default {
  name: "MypageView",
  data() {
    return {
      image: "",
      isEditing: false,
      newNickName: "",
      libraryOpen: "",
      // reviewOpen: this.user.reviewOpen,
    };
  },
  computed: {
    ...mapState(accountStore, ["user"]),
  },
  methods: {
    ...mapActions(accountStore, [
      "logout",
      "signout",
      "editImg",
      "editUserNickName",
      "editOpenInfo",
    ]),
    logoutAndRefresh() {
      this.logout();
    },
    selectFile(file) {
      this.image = file;
      console.log("11", file);
      this.editImg({ file });
    },
    changeEditing() {
      this.isEditing = !this.isEditing;
    },
    confirmEdit() {
      this.isEditing = !this.isEditing;
      const nickname = this.newNickName;
      this.editUserNickName({ nickname: nickname });
    },
  },
};
</script>

<style scoped>
.profile {
  width: 20vh;
  height: 20vh;
  border-radius: 70%;
}

.profile-img {
  position: relative;
}
.input-btn-camera {
  position: absolute;
  padding: 0px;
  margin: 0px;
  top: 16vh;
  left: 15vh;
}
.mypage-container {
  /* width: calc(1vw * 100 * 2 / 3); */
  position: inherit;
  display: flex;
  flex-direction: column;
  height: 90%;
  top: 5%;
  align-items: center;
  justify-content: space-around;
  padding: 0px;
  margin: 0px;
}

.edit-box {
  display: flex;
  flex-direction: column;
  width: 50%;
  height: auto;
  align-items: center;
  justify-content: space-between;
}

.v-text-field {
  padding: 0px;
  margin: 0px;
  width: 50%;
}

.edit-btn {
  position: absolute;
  left: 110%;
  display:flex;
  padding: 0px !important;
}

.v-icon {
  color: rgba(227,152,75) !important;
}
.text-title {
  padding-top: 5px;
  font-size: 2.5vh;
  font-weight: bold;
}
.text-content {
  font-size: 1.5vh !important;
}
.nickname-field {
  position: relative;
  align-content: end;
  /* text-align: center; */
  /* width: 100%; */
}
.btn-box button {
  margin: 0.5rem;
}
p {
  padding: 0px;
  margin: 0px;
}
.v-input > label {
  margin: 0px !important;
}

.centered-input >>> input {
      text-align: center
    }

.m-bot >>> label {
  margin-bottom: 0px !important;
}
.m-bot >>> .v-label {
  font-size: 2vh !important;
  font-weight: bold;

}
</style>
