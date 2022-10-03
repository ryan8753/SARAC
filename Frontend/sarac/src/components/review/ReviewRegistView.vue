<template>
  <div>
    <v-container>
      <v-form @submit.prevent="onSubmit">
        <v-row align-content-center class="title">
          <v-col cols="3">
            <img
              class="reviewImage"
              fas
              fa-search
              :src="img"
              alt="이미지검색"
              @click="getBookFromSearch"
            />
          </v-col>

          <v-col cols="9">
            <v-text-field
              label="루피님의 리뷰입니다"
              solo
              v-model="review.title"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row justify="center">
          <v-rating
            v-model="review.bookScore"
            background-color="orange lighten-3"
            color="orange"
            size="25"
            id="rating"
          ></v-rating>
        </v-row>
        <v-row>
          <v-col>
            <v-textarea
              solo
              name="content"
              label="내용을 입력하세요"
              v-model="review.content"
              id="content"
            ></v-textarea>
          </v-col>
        </v-row>
        <v-row class="checkbox">
          <v-checkbox
            align="justify"
            label="비밀글로 작성하기"
            color="black"
            v-model="review.isSecret"
          ></v-checkbox>
        </v-row>

        <v-row>
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
        <br />

        <v-file-input
          class="input"
          type="file"
          label=" 사진 업로드"
          accept="image/png, image/jpeg, image/bmp"
          multiple
          prepend-icon="mdi-camera"
          style="width: 150px"
          @change="onImageChange"
          v-model="files"
        />
        <span v-for="(item, j) in review.hashtag" :key="j">
          #{{ item }}
          <v-btn icon color="red" @click="deleteHashtag(j)">x</v-btn></span
        >

        <v-row class="hash">
          <v-col cols="8"
            ><v-text-field
              v-model="hashtag"
              label="해시태그 입력"
              type="text"
            ></v-text-field>
          </v-col>
          <v-col cols="4">
            <v-btn icon @click="inputHashtag(hashtag)" class="add">Add</v-btn>
          </v-col>
        </v-row>

        <!--  -->
        <v-row>
          <v-col>
            <v-btn type="submit" v-if="this.type === 'modify'">
              리뷰 수정
            </v-btn>
            <v-btn type="submit" v-else> 리뷰 작성 </v-btn>
          </v-col>
        </v-row>
      </v-form>
    </v-container>
  </div>
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
      console.log(newreview);
    },
  },
  computed: {
    ...mapState(accountStore, ["user"]),
    ...mapState(reviewStore, ["book"]),
    ...mapMutations(reviewStore, ["CLEAR_BOOK_DATA"]),
  },
  created() {
    console.log(this.img);
    if (this.$route.params.reviewId != null) {
      this.reviewId = this.$route.params.reviewId;
      console.log(this.$route.params.reviewId);
      this.getReview(this.reviewId);
      this.type = "modify";
    } else {
      this.review.title = this.user.nickname + "님의 리뷰입니다.";
    }

    if (this.book.isbn != "") {
      console.log("비어있음");
      console.log(this.book);
      this.review.isbn = this.book.isbn;
      this.img = this.book.bookImgUrl;
      this.CLEAR_BOOK_DATA;
    }
    this.review.writer = this.user.userId;
    console.log(this.review.writer);
    console.log(this.img);
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

      console.log(this.review);
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
      this.review.hashtag.push(hashtag);
      console.log(this.review.hashtag);
      this.hashtag = "";
      console.log(this.hashtag);
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
.add {
  align-self: bottom;
}
.hash {
  vertical-align: middle;
}
.v-input--selection-controls {
  margin-top: 0px;
  padding-top: 0px;
}

.checkbox {
  justify-content: right;
  padding-right: 3vh;
}

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
