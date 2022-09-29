<template>
  <v-container>
    <div><b>읽고 있는 책</b></div>
    <v-sheet width="100%" min-height="15vh">
      <v-slide-group>
        <v-slide-item v-for="lib in library" :key="lib.isbn" link>
          <v-card class="mx-2" height="15vh" width="10vh" @click="gotoBookInfo(lib.isbn)">
            <v-img :src="lib.bookImgUrl" class="fill-height" />
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>

    <v-divider></v-divider>
    
    <div><b>완독한 책</b></div>
    <v-sheet width="100%" min-height="15vh">
      <v-slide-group>
        <v-slide-item v-for="lib in library" :key="lib.isbn" link>
          <v-card class="mx-2" height="15vh" width="10vh" @click="gotoBookInfo(lib.isbn)">
            <v-img :src="lib.bookImgUrl" class="fill-height" />
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
    
    <v-divider></v-divider>
    
    <div><b>읽고 싶은 책</b></div>
    <v-sheet width="100%" min-height="15vh">
      <v-slide-group>
        <v-slide-item v-for="lib in library" :key="lib.isbn" link>
          <v-card class="mx-2" height="15vh" width="10vh" @click="gotoBookInfo(lib.isbn)">
            <v-img :src="lib.bookImgUrl" class="fill-height" />
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>

  </v-container>
</template>

<script>
export default {
  name: "Library",

  data() {
    return {
      isOpen: true,
      library: [],
    };
  },
  props: ["libraryList", "keyword"],
  methods: {
    gotoBookInfo(isbn) {
      console.log(isbn);
    }
  },
  created() {
    let person = Object.keys(this.libraryList)[0];
    if (person == "private") this.isOpen = false;
    else this.library = this.libraryList[person];
  },
};
</script>

<style scoped>
</style>
