<template>
  <v-container>
    <v-form @submit.prevent="onSubmit">
      <v-row class="title ma-0 pa-0">
        <v-col cols="3" class="pa-0" align-self="center">
          <img
            class="reviewImage"
            :src="img"
            alt="이미지검색"
            @click="getBookFromSearch"
          />
        </v-col>
        <v-spacer></v-spacer>
        <v-col cols="9" class="pa-0" align-self="center">
          <v-text-field
            label="루피님의 리뷰입니다"
            solo
            v-model="review.title"
            hide-details
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row justify="center" class="ma-0 pa-0">
        <v-rating
          class="ma-0 pa-0"
          v-model="review.bookScore"
          background-color="#E3984B"
          color="#E3984B"
          size="4vh"
          id="rating"
        ></v-rating>
      </v-row>
      <v-row>
        <v-col class="py-0">
          <v-textarea
            solo
            no-resize
            background-color="#F3EDED"
            label="내용을 입력하세요"
            v-model="review.content"
            flat
          ></v-textarea>
        </v-col>
      </v-row>
      <v-row justify="end" class="ma-0">
        <v-checkbox
          class="checkbox"
          label="비밀글로 작성하기"
          color="#E3984B"
          v-model="review.isSecret"
          hide-details
        ></v-checkbox>
      </v-row>

      <v-row class="ma-0">
        <v-img
          v-for="(item, i) in uploadimageurl"
          :key="i"
          :src="item.url"
          contain
          height="50px"
          width="50px"
          style="solid black; margin-left: 10px"
        />
      </v-row>
      <v-row class="ma-0 pe-10">
        <v-file-input
          label=" 사진 업로드"
          accept="image/png, image/jpeg, image/bmp"
          multiple
          prepend-icon="mdi-camera"
          @change="onImageChange"
          v-model="files"
        />
      </v-row>
        
      <v-row class="ma-0">
        <v-col cols="9" class="pa-0"
          ><v-text-field
            prepend-icon="mdi-pound"
            v-model="hashtag"
            label="해시태그 입력"
            type="text"
            @keyup.space="inputHashtag(hashtag)"
          ></v-text-field>
        </v-col>
        <v-spacer></v-spacer>
        <v-col cols="2" class="pa-0" align-self="center">
          <v-btn small elevation="none" color="white" @click="inputHashtag(hashtag)" class="orange--text">Add</v-btn>
        </v-col>
      </v-row>
      <span v-for="(item, j) in review.hashtag" :key="j">
          #{{ item }}
          <v-btn x-small icon color="red" @click="deleteHashtag(j)">x</v-btn></span
        >     
      <v-row class="ma-5">
        <v-col>
          <v-btn align color="#E3984B" class="white--text" type="submit" v-if="this.type === 'modify'"> 리뷰 수정 </v-btn>
          <v-btn width="100%" color="#E3984B" class="white--text" type="submit" v-else> 리뷰 작성 </v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<script>
import { mapActions, mapState, mapMutations } from "vuex";
const reviewStore = "reviewStore";
const accountStore = "accountStore";

export default {
  name: "ReviewRegist",

  data() {
    return {
      img: "https://sarac-a505.s3.ap-northeast-2.amazonaws.com/search.png",
      uploadimageurl: [], // 업로드한 이미지의 미리보기 기능을 위해 url 저장하는 객체
      hashtag: "",
      review: {
        id: null,
        writer: null,
        isbn: null,
        title: null,
        content: null,
        bookScore: 3,
        isSecret: false,
        hashtag: [],
      },
      files: [],
      reviewId: null,
      type: null,
      detailReview: {
        reviewId: null,
        title: null,
        content: "",
        bookScore: null,
        isSecret: null,
        reviewHashtagList: [],
      },
    };
  },

  watch: {
    review(newreview) {
      newreview;
    },
  },
  computed: {
    ...mapState(accountStore, ["user"]),
    ...mapState(reviewStore, ["book"]),
    ...mapMutations(reviewStore, ["CLEAR_BOOK_DATA"]),
  },
  created() {
    if (this.$route.params.reviewId != null) {
      this.reviewId = this.$route.params.reviewId;
      this.getReview(this.reviewId);
      this.type = "modify";
    } else {
      this.review.title = this.user.nickname + "님의 리뷰입니다.";
    }

    if (this.book.isbn != "") {
      this.review.isbn = this.book.isbn;
      this.img = this.book.bookImgUrl;
      this.CLEAR_BOOK_DATA;
    }
    this.review.writer = this.user.userId;
  },

  components: {},
  methods: {
    ...mapActions(reviewStore, [
      "registReview",
      "updateReview",
      "getDetailReview",
    ]),

    async getReview(reviewId) {
      this.detailReview = await this.getDetailReview(reviewId);
      this.review.title = this.detailReview.title;
      this.review.content = this.detailReview.content;
      this.review.isSecret = this.detailReview.isSecret;
      this.review.bookScore = this.detailReview.bookScore;
      this.review.hashtag = this.detailReview.reviewHashtagList;
      this.review.id = this.detailReview.reviewId;
    },

    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.review.content && ((msg = "내용을 입력해주세요"), (err = false));
      err && !this.review.isbn && ((msg = "책을 검색해주세요"), (err = false));

      if (!err) alert(msg);
      else this.type === "modify" ? this.modify() : this.regist();
    },
    deleteHashtag(j) {
      let hashtagIndex = this.review.hashtag[j];
      let temp = this.review.hashtag.filter(function (data) {
        return data != hashtagIndex;
      });
      this.review.hashtag = temp;
    },

    inputHashtag(hashtag) {
      if (!hashtag) {
        return;
      }
      this.review.hashtag.push(hashtag.replaceAll(" ", ""));
      this.hashtag = "";
    },

    onImageChange(file) {
      // v-file-input 변경시
      if (!file) {
        return;
      }
      const formData = new FormData(); // 파일을 전송할때는 FormData 형식으로 전송
      this.uploadimageurl = []; // uploadimageurl은 미리보기용으로 사용
      file.forEach((item) => {
        formData.append("files", item); // formData의 key: 'files', value: 이미지
        const reader = new FileReader();
        reader.onload = (e) => {
          this.uploadimageurl.push({ url: e.target.result });
          // e.target.result를 통해 이미지 url을 가져와서 uploadimageurl에 저장
        };
        reader.readAsDataURL(item);
      });
      this.formData = formData;
    },
    async regist() {
      const review = this.review;
      const files = this.files;
      this.registReview({ review, files });
    },
    async modify() {
      const review = this.review;
      const files = this.files;
      const reviewId = this.reviewId;
      this.updateReview({ review, files, reviewId });
      this.$router.push({ name: "home" });
    },
    getBookFromSearch() {
      this.$router.push({ path: "/review/search" });
    },
  },
};
</script>

<style scoped>
.title {
  text-align: center;
  vertical-align: middle;
}
.reviewImage {
  width: 50px;
}
.preImage {
  width: 50px;
}
</style>
