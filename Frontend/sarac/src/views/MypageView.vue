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
        ></v-text-field>
        <v-btn
          class="edit-btn"
          plain
          elevation="0"
          small
          @click="confirmEdit"
          color="rgb(223, 137, 49)"
          >확인</v-btn
        >
      </div>
    </div>
    <div v-else class="edit-box">
      <p align="center" class="text-title">닉네임</p>
      <div class="d-flex justify-center nickname-field">
        <div class="flex-grow-1 text-center">{{ user.nickname }}</div>
        <v-btn
          class="edit-btn"
          small
          plain
          elevation="0"
          @click="changeEditing"
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
        class="text-content"
        v-model="user.libraryOpen"
        @change="editOpenInfo"
      ></v-checkbox>
      <v-checkbox
        label="리뷰공개"
        color="black"
        v-model="user.reviewOpen"
        @change="editOpenInfo"
      ></v-checkbox>
    </div>

    <div class="btn-box">
      <v-btn color="rgba(245, 193, 108, 1)" @click="logoutAndRefresh">
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
  width: 150px;
  height: 150px;
  border-radius: 70%;
}

.profile-img {
  position: relative;
}
.input-btn-camera {
  position: absolute;
  padding: 0px;
  margin: 0px;
  top: 118px;
  left: 118px;
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
  height: 54px;
  align-items: center;
}

.v-text-field {
  padding: 0px;
  margin: 0px;
  width: 50%;
}

.edit-btn {
  position: absolute;
  left: 100%;
}

.v-icon {
  color: rgb(223, 137, 49) !important;
}
.text-title {
  font-size: 2.5vh;
  font-weight: bold;
}
.text-content {
  font-size: 1.5vh !important;
}
.nickname-field {
  position: relative;
  width: 50%;
}
.btn-box button {
  margin: 0.5rem;
}
</style>
