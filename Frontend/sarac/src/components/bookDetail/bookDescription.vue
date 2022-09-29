<template>
  <div>
    <h1>책소개</h1>
    <p>저자: {{ this.book.author }}</p>
    <p>출판사: {{ this.book.publisher }}</p>
    <p>출판날짜: {{}}</p>
    <p>정가: {{ this.book.price }}</p>
    <hr />
    <p>{{ this.book.description }}</p>
    <p></p>
    <p></p>
  </div>
</template>
<script>
export default {
  props: ["book"],
};
</script>
