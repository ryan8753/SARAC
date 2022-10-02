<template>
  <div>
    <div>책리뷰</div>

    <v-container>
      <v-row>
        <v-col
          cols="4"
          md="4"
          sm="4"
          v-for="review in reviewList"
          :key="review.reviewId"
        >
          <v-card>
            <v-img :src="review.photoUrlList[0]"> </v-img>
            <v-card-title>{{ review.bookTitle }}</v-card-title>
            <v-card-subtitle>{{ review.title }}</v-card-subtitle>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import { mapState } from "vuex";
const bookStore = "bookStore";
export default {
  computed: {
    ...mapState(bookStore, ["reviewList"]),
  },
};
</script>
