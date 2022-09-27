<template>
  <v-container>
    <v-row>
      <v-col cols="4">
        <v-img
          src="https://bookthumb-phinf.pstatic.net/cover/120/221/12022171.jpg?type=m1&udate=20170502"
        ></v-img>
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
  data: () => ({
    thumbnailExistance: true,
    thumbnailAddr: "",
  }),
  props: {
    isbn: String,
    author: String,
    title: String,
    thumbnail: String,
    score: Number,
  },
};
</script>
