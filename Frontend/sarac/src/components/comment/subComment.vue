<template>
  <div>
    <!-- {{ this.childList }} -->
    <div v-if="childList.length != 0">{{ this.childList }}</div>
  </div>
</template>

<script>
export default {
  props: ["childList"],
  data() {
    return {};
  },
  created() {
    console.log(this.childList);
  },
};
</script>

<style></style>
