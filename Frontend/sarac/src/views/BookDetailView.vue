<template>
  <div v-if="book" class="book-detail-container"> 
    <book-search-item
      v-bind="book"
    ></book-search-item>
    <v-btn-toggle
      dense
      borderless
      v-model="book.libraryType"
    >
      <v-btn value="wish">
        읽고싶은책
      </v-btn>

      <v-btn value="reading">
        읽는중
      </v-btn>
      <v-btn value="read">
        다 읽었어요
      </v-btn>
    </v-btn-toggle>

    <v-btn-toggle
      dense
      borderless
      v-model="detailOrReviews"
    >
      <v-btn value="detail">
        상세정보
      </v-btn>

      <v-btn value="review">
        리뷰
      </v-btn>
    </v-btn-toggle>
    <hr>
    <div v-show="detailOrReviews==`detail`">
      책 정보
    </div>
    <div v-show="detailOrReviews==`review`">
      책 리뷰
    </div>
  </div>
  <div v-else>
    책정보가없습니다 다시 시도해주세요
  </div>
</template>

<script>
import BookSearchItem from '@/components/search/BookSearchItem.vue';
import { mapActions, mapState } from 'vuex';

const bookStore = "bookStore";
const accountStore = "accountStore"
export default {
  name: "bookDetail",
  components : { BookSearchItem },
  computed:{
    ...mapState(accountStore,["user"]),
    ...mapState(bookStore,["book"])
  },
  methods : {
    ...mapActions(bookStore,["getBookDetail","editReadStatus", "deleteReadStatus"])
  },
  
  
  data () {
    return {
      detailOrReviews: "detail",
    }
  },
  
  created() {

  }



  }
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
  background-color: rgba(243,237,237,1);
  /* background-color: blue; */
  height: 1px;
  width:100%;
}
</style>