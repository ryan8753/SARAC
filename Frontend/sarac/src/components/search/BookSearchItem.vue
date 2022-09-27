<template>
  <v-container>
    <v-row>
      <v-col cols="4">
        <v-img :src="getThumbnail"></v-img>
      </v-col>
      <v-spacer></v-spacer>
      <v-col cols="7">
        <v-row> {{ title }} </v-row>
        <v-row> {{ author }} </v-row>
        <v-row>
          <v-rating
            :value="score"
            readonly
            color="rgba(170, 83, 14, 1)"
            background-color="color=rgba(170, 83, 14, 1)"
            dense
            half-increments
          ></v-rating>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: {
    isbn: String,
    author: String,
    title: String,
    thumbnail: String,
    score: Number,
  },
  computed: {
    getThumbnail() {
      return !this.thumbnail
        ? "https://sarac-a505.s3.ap-northeast-2.amazonaws.com/%EC%B1%85%EA%B8%B0%EB%B3%B8.png"
        : this.thumbnail;
    },
  },
};
</script>
