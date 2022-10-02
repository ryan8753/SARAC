<template>
  <div v-if="book" class="book-detail-container">
    <book-search-item v-bind="book"></book-search-item>
    <v-btn-toggle dense borderless v-model="readStatus">
      <v-btn value="wish"> 읽고싶은책 </v-btn>

      <v-btn value="reading"> 읽는중 </v-btn>
      <v-btn value="read"> 다 읽었어요 </v-btn>
    </v-btn-toggle>

    <v-btn-toggle dense borderless v-model="detailOrReviews">
      <v-btn value="detail"> 상세정보 </v-btn>

      <v-btn value="review"> 리뷰 </v-btn>
    </v-btn-toggle>
    <hr />
    <BookDescription v-show="detailOrReviews == `detail`" v-bind:book="book"
      >책 정보</BookDescription
    >
    <BookReviews v-show="detailOrReviews == `review`">책 리뷰</BookReviews>
  </div>
  <div v-else>책정보가없습니다 다시 시도해주세요</div>
</template>

<script>
import BookSearchItem from "@/components/search/BookSearchItem.vue";
import BookDescription from "@/components/bookDetail/bookDescription.vue";
import BookReviews from "@/components/bookDetail/bookReviews.vue";
import { mapActions, mapGetters, mapState } from "vuex";

const bookStore = "bookStore";
const accountStore = "accountStore";
export default {
  name: "bookDetail",
  components: { BookSearchItem, BookDescription, BookReviews },
  computed: {
    ...mapState(accountStore, ["user"]),
    ...mapState(bookStore, ["book"]),
    ...mapGetters(bookStore, ["bookReadStatus"]),
    // readStatus: {
    //   get() {
    //     return this.bookReadStatus;
    //   },
    //   set(newStatus) {
    //     return newStatus;
    //   },
    // },
  },
  methods: {
    ...mapActions(bookStore, [
      "getBookDetail",
      "editReadStatus",
      "deleteReadStatus",
      "getReviewList",
    ]),
    onChangeReadStatus(event) {
      console.log("change");
      console.log(event.target.value);
    },
  },

  data() {
    return {
      detailOrReviews: "detail",
      readStatus: "",
    };
  },
  created() {
    // const bookId = this.$route.params.bookId;
    // const userId = this.user;
    this.getBookDetail({ bookId: 1234, userId: 2430262127 });
    this.getReviewList(1234);
    this.readStatus = this.book.libraryType;
  },
  watch: {
    readStatus: function (newVal, oldVal) {
      console.log(newVal, oldVal);
      if (newVal === undefined && oldVal != null) {
        console.log("올", oldVal);
        this.deleteReadStatus(1234);
      } else {
        this.editReadStatus({ bookId: 1234, newStatus: newVal });
      }
    },
  },
};
</script>

<style>
.book-detail-container {
  position: inherit;
  display: flex;
  flex-direction: column;
  width: 90%;
  left: 5%;
  align-items: center;
  /* justify-content: space-around; */
  padding: 0px;
  margin: 0px;
}

hr {
  background-color: rgba(243, 237, 237, 1);
  /* background-color: blue; */
  height: 1px;
  width: 100%;
}
</style>
