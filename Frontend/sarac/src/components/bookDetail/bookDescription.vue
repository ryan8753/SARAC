<template>
  <div>
    <div class="description-container">
      <h2>책 소개</h2>
      <p>저자 : {{ this.book.author }}</p>
      <p>출판사 : {{ this.book.publisher }}</p>
      <p v-if="this.book.price != 0">정가 : {{ this.book.price }} 원</p>
      <p v-if="this.book.genre">장르 : {{ this.book.genre }}</p>
      <p v-if="this.book.page != 0">페이지 수 : {{ this.book.page }} 장</p>
    </div>
    <hr />
    <div class="description-container">
      <h2>줄거리</h2>
      <p>{{ this.book.description }}</p>
    </div>
    <div v-if="this.book.toc">
      <hr />
      <div class="description-container">
        <h2>목차</h2>
        <div id="toc"></div>
        <!-- <div>{{this.book.toc}}</div> -->
      </div>
    </div>

    <p></p>
    <p></p>
  </div>
</template>
<script>
// import { mapState } from 'vuex';
export default {
  props: ["book"],
  // computed: {
  //   ...mapState("bookStore",["book"])
  // },
  // mounted() {
  //   // const toc = document.querySelector("#toc");
  //   // toc.innerHTML = this.book.toc;
  //   console.log(this.book)
  //   if(this.book.toc) {
  //     const a = document.getElementById("toc")
  //     console.log(a)
  //     a.innerHTML = this.book.toc;
  //     // a.appendChild(this.book.toc)
  //   }
  // },
  // watch : {
  //   book(newVal) {
  //     if(newVal.toc) {
  //     const a = document.getElementById("toc")
  //     console.log("a",a)
  //     a.innerHTML = newVal.toc;
  //   }}
  // }
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
