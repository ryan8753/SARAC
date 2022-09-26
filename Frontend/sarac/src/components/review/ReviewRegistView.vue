<template>
  <div>
    <v-container>
      <v-form @submit.prevent="onSubmit">
        <v-row>
          <v-col cols="2">
            <img
              class="reviewImage"
              fas
              fa-search
              src="https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/0e3f7785-e374-4013-b268-601cc2b5881a-50.png"
              alt=""
            />
          </v-col>
          <v-col cols="10">
            <v-text-field
              label="루피님의 리뷰입니다"
              solo
              :rules="[(v) => !!v || '제목은 필수입니다.']"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-rating
            v-model="rating"
            background-color="orange lighten-3"
            color="orange"
            large
          ></v-rating>
        </v-row>
        <v-row>
          <v-col>
            <v-textarea
              solo
              name="content"
              label="내용을 입력하세요"
              :rules="[(v) => !!v || '내용은 필수입니다.']"
            ></v-textarea>
          </v-col>
        </v-row>
        <v-checkbox label="비밀글로 작성하기" color="black"></v-checkbox>

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
        />

        <v-row>
          <v-col cols="5"> 태그 입력</v-col>
          <v-col cols="5">
            <v-text-field filled></v-text-field>
          </v-col>
          <v-icon large color="green darken-2"> mdi-check </v-icon>
        </v-row>
        <v-row>
          <v-col>
            <v-btn type="submit" v-if="this.type === 'register'"> 리뷰 작성 </v-btn>
            <v-btn type="submit" v-else> 리뷰 수정 </v-btn>
          </v-col>
        </v-row>
      </v-form>
    </v-container>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const reviewStore = "reviewStore";

export default {
  name: "ReviewRegist",

  data() {
    return {
      rating: 3,
      // data속성에서 추가되는 변수들
      uploadimageurl: [], // 업로드한 이미지의 미리보기 기능을 위해 url 저장하는 객체
      imagecnt: 0, // 업로드한 이미지 개수 => 제출버튼 클릭시 back서버와 axios 통신하게 되는데,
      // 이 때, 이 값도 넘겨줌
      review: {
        writer: "",
        isbn: "",
        title: "님의 리뷰입니다.",
        content: "",
        bookScore: "",
        isSecret: false,
        hashtag: [],
      },
    };
  },

 props: {
    type: { type: String },
  },
  computed: {
    ...mapState(["user"]),
  },
  created() {
     if (this.type === "modify") {
     this.udpdateReview();
      
    }
    this.review.title = this.user.nickname+"님의 리뷰입니다.";
    this.review.writer = this.user.nickname;
  },

  components: {},
  methods: {
    ...mapActions(reviewStore, ["registReview"]),

    onImageChange(file) {
      // v-file-input 변경시
      if (!file) {
        return;
      }
      const formData = new FormData(); // 파일을 전송할때는 FormData 형식으로 전송
      this.uploadimageurl = []; // uploadimageurl은 미리보기용으로 사용
      file.forEach((item) => {
        formData.append("filelist", item); // formData의 key: 'filelist', value: 이미지
        const reader = new FileReader();
        reader.onload = (e) => {
          this.uploadimageurl.push({ url: e.target.result });
          // e.target.result를 통해 이미지 url을 가져와서 uploadimageurl에 저장
        };
        reader.readAsDataURL(item);
      });
    },
    onSubmit() {
      this.registReview(review);
    },
  },
};
</script>

<style>
.reviewImage {
  width: 50px;
}
.preImage {
  width: 50px;
}
</style>
