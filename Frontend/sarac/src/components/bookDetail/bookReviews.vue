<template>
  <div>
    <div>책리뷰</div>
    <!-- <div v-for="review in reviewLvist"> -->
    <div></div>
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
