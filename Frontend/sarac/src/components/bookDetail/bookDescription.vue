<template>
  <div>
    <div class="description-container">
      <h1>책소개</h1>
      <p>저자: {{ this.book.author }}</p>
      <p>출판사: {{ this.book.publisher }}</p>
      <p>정가: {{ this.book.price }} 원</p>
    </div>
    <hr />
    <div class="description-container">
      <p>{{ this.book.description }}</p>
    </div>
    <p></p>
    <p></p>
  </div>
</template>
<script>
export default {
  props: ["book"],
};
</script>
<style scoped>
h1 {
  font-size: 3vh;
  font-weight: bold;
}
p {
  font-size: 1vh;
}
.description-container {
  padding-left: 5%;
}
</style>
