<template>
  <div v-if="book" class="book-detail-container">
    <book-search-item v-bind="book"></book-search-item>
    <v-btn-toggle
      dense
      borderless
      v-bind:value="bookReadStatus"
      background-color="rgba(243,237,237,1)"
      color="white"
      light
      @change="onChangeReadStatus"
      class="d-flex flex-row toggle-con"
    >
      <v-btn
        x-small
        value="WISH"
        class="flex-grow-1"
        v-bind:color="
          bookReadStatus === 'WISH'
            ? 'rgba(227,152,75,1)'
            : 'rgba(243,237,237,1)'
        "
      >
        읽고싶은책
      </v-btn>

      <v-btn
        x-small
        value="READING"
        class="flex-grow-1"
        v-bind:color="
          bookReadStatus === 'READING'
            ? 'rgba(227,152,75,1)'
            : 'rgba(243,237,237,1)'
        "
      >
        읽는중
      </v-btn>
      <v-btn
        x-small
        value="READ"
        class="flex-grow-1"
        v-bind:color="
          bookReadStatus === 'READ'
            ? 'rgba(227,152,75,1)'
            : 'rgba(243,237,237,1)'
        "
      >
        다 읽었어요
      </v-btn>
    </v-btn-toggle>

    <v-btn-toggle
      dense
      borderless
      v-model="detailOrReviews"
      class="d-flex flex-row toggle-con"
      background-color="rgba(243,237,237,1)"
      color="white"
    >
      <v-btn
        x-small
        value="detail"
        class="flex-grow-1"
        v-bind:color="
          detailOrReviews === 'detail'
            ? 'rgba(227,152,75,1)'
            : 'rgba(243,237,237,1)'
        "
      >
        상세정보
      </v-btn>

      <v-btn
        x-small
        value="review"
        class="flex-grow-1"
        v-bind:color="
          detailOrReviews === 'review'
            ? 'rgba(227,152,75,1)'
            : 'rgba(243,237,237,1)'
        "
      >
        리뷰
      </v-btn>
    </v-btn-toggle>
    <hr />
    <BookDescription
      v-show="detailOrReviews == `detail`"
      v-bind:book="book"
      class="descript-review"
      >책 정보</BookDescription
    >
    <BookReviews v-show="detailOrReviews == `review`" class="descript-review"
      >책 리뷰</BookReviews
    >
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
    ...mapState(bookStore, ["book", "reviewList"]),
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
      const newStatus = event;
      const bookId = this.$route.params.bookId;
      if (event === undefined) {
        this.deleteReadStatus(bookId);
      } else {
        this.editReadStatus({ bookId, newStatus });
      }
    },
  },

  data() {
    return {
      detailOrReviews: "detail",
      abc: this.bookReadStatus,
      // bookReadStatus :
      // readStatus: this.user,
    };
  },
  created() {
    // const bookId = this.$route.params.bookId;
    // const userId = this.user;
    this.getBookDetail(1234);
    // this.getReviewList(1234);
    // this.readStatus = this.book.libraryType;
  },
  watch: {
    // readStatus: function (newVal, oldVal) {
    //   console.log(newVal, oldVal);
    //   if (oldVal != "") {
    //     this.editReadStatus({ bookId: 1234, newStatus: newVal });
    //     } else {
    //       this.editReadStatus({ bookId: 1234, newStatus: newVal });
    //   }
    // },
    reviewList: function () {},
  },
};
</script>

<style scoped>
.book-detail-container {
  position: inherit;
  display: flex;
  flex-direction: column;
  top: 5%;
  width: 90%;
  left: 5%;
  align-items: center;
  /* justify-content: space-around; */
  padding: 0px;
  margin: 0px;
  gap: 15px;
}

hr {
  background-color: rgba(243, 237, 237, 1);
  /* background-color: blue; */
  height: 2px;
  width: 100%;
  margin: 0px;
}
.descript-review {
  width: 100%;
}

.v-btn--active .v-btn__content {
  color: white;
}
.toggle-con {
  width: 90%;
}
</style>
