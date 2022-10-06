<template>
  <div>
    <div class="description-container">
      <h2>책 소개</h2>
      <p>저자 : {{ book.author }}</p>
      <p>출판사 : {{ book.publisher }}</p>
      <p v-if="book.price != 0">정가 : {{ book.price }} 원</p>
      <p v-if="book.genre">장르 : {{ book.genre }}</p>
      <p v-if="book.page != 0">페이지 수 : {{ book.page }} 장</p>
    </div>
    <hr />
    <div class="description-container">
      <h2>줄거리</h2>
      <p>{{ book.description }}</p>
    </div>
    <div :v-if="book.toc">
      <hr />
      <div class="description-container">
        <h2>목차</h2>
        <div id="toc"></div>
      </div>
    </div>

    <p></p>
    <p></p>
  </div>
</template>
<script>
import { mapState } from 'vuex';
export default {
  computed: {
    ...mapState("bookStore",["book"])
  },
  watch : {
    book(newVal) {
      if(newVal.toc) {
      const a = document.getElementById("toc")
      a.innerHTML = newVal.toc;
    }}
  }
};
</script>
<style scoped>
h1 {
  font-size: 3vh;
  font-weight: bold;
}
h2 {
  font-size: 2vh;
  font-weight: bold;
}
p {
  font-size: 1vh;
}
.description-container {
  padding-left: 5%;
}
#toc {
  font-size: 1vh;
}
</style>
