<template>
  <div>

    <img class="reviewImage" src="https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/0e3f7785-e374-4013-b268-601cc2b5881a-50.png" alt="">
    
         누구누구님의 리뷰
     <v-rating
          v-model="rating"
          color="yellow darken-3"
          background-color="grey darken-1"
          empty-icon="$ratingFull"
          half-increments
          hover
          large
        ></v-rating>

        <form>
  <input type="file" name="photo" id="photo" />
</form>
  </div>
</template>

<script>
export default {
  name: "ReviewRegist",

  components: {},
};
</script>

<style>
.reviewImage{
    width: 100px;
}

</style>