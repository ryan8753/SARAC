<template>
  <v-container>
    <v-container v-if="!isOpen" justify="center" class="pa-4">
      비공개 입니다.
    </v-container>
    <div v-else>
      <v-row justify="end" class="mt-1">
      <v-btn text x-small @click="deleteLibrary" v-if="value == '선택해제'" >삭제</v-btn>
      <v-btn text x-small @click="toggle" v-if="person=='me'">{{value}}</v-btn>
    </v-row>
      <b-form-checkbox-group
      v-model="checkList">
      <div><b>읽고 있는 책</b></div>
      <v-sheet width="100%" min-height="15vh">
        <v-slide-group>
          <v-slide-item v-for="lib in library" :key="lib.isbn">
            <div>
            <b-form-checkbox v-show="lib.libraryType == 'READING'" size="sm" v-if="value == '선택해제'" :value="lib.isbn"></b-form-checkbox>
            <v-card
              v-show="lib.libraryType == 'READING'"
              class="mx-2"
              height="15vh"
              width="10vh"
              @click="gotoBookInfo(lib.isbn)"
            >
              <v-img :src="lib.bookImgUrl" class="fill-height" />
            </v-card>
            </div>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>

      <v-divider></v-divider>

      <div><b>완독한 책</b></div>
      <v-sheet width="100%" min-height="15vh">
        <v-slide-group>
          <v-slide-item v-for="lib in library" :key="lib.isbn">
            <div>
            <b-form-checkbox v-show="lib.libraryType == 'READ'" size="sm" v-if="value == '선택해제'" :value="lib.isbn"></b-form-checkbox>
            <v-card
              v-show="lib.libraryType == 'READ'"
              class="mx-2"
              height="15vh"
              width="10vh"
              @click="gotoBookInfo(lib.isbn)"
            >
              <v-img :src="lib.bookImgUrl" class="fill-height" />
            </v-card>
            </div>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>

      <v-divider></v-divider>

      <div><b>읽고 싶은 책</b></div>
      <v-sheet width="100%" min-height="15vh">
        <v-slide-group>
          <v-slide-item v-for="lib in library" :key="lib.isbn">
            <div>
            <b-form-checkbox v-show="lib.libraryType == 'WISH'" size="sm" v-if="value == '선택해제'" :value="lib.isbn"></b-form-checkbox>
            <v-card
              v-show="lib.libraryType == 'WISH'"
              class="mx-2"
              height="15vh"
              width="10vh"
              @click="gotoBookInfo(lib.isbn)"
            >
              <v-img :src="lib.bookImgUrl" class="fill-height" />
            </v-card>
            </div>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
      </b-form-checkbox-group>
    </div>
  </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const myFeedStore = "myFeedStore";

export default {
  name: "Library",

  data() {
    return {
      isOpen: true,
      library: [],
      value: "선택",
      checkList: [],
      person: null,
    };
  },
  computed: {
    ...mapState(myFeedStore, ["libraryList"]),
  },
  watch: {
    libraryList: function () {
      let person = Object.keys(this.libraryList)[0];
      if (person == "private") this.isOpen = false;
      else {
        this.library = this.libraryList[person];
        this.person = person;
      }
    }
  },
  methods: {
    ...mapActions(myFeedStore, ["deleteLibraryList"]),

    gotoBookInfo(isbn) {
      this.$router.push("/book/detail/" + isbn);
    },
    toggle() {
      this.value = this.value !='선택'? '선택' : '선택해제';
      if(this.value == "선택")
        this.checkList = [];
    },
    deleteLibrary() {
      console.log(this.checkList);
      this.deleteLibraryList(this.checkList);
      this.toggle();
    },
  },
  created() {
    let person = Object.keys(this.libraryList)[0];
    if (person == "private") this.isOpen = false;
    else {
      this.library = this.libraryList[person];
      this.person = person;
    }
  },
};
</script>

<style scoped></style>
