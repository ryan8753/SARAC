<template>
  <div class="container">
    
    <form>
      <v-row>
        <v-col
       cols="2"
        md="4"
        >
        <img
          class="reviewImage"
          fas fa-search
          src="https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/0e3f7785-e374-4013-b268-601cc2b5881a-50.png"
          alt=""
        />
        </v-col>
         <v-col
          cols="10"
        sm=""
        md="8"
        >
        <v-text-field label="루피님의 리뷰입니다" solo></v-text-field>
         </v-col>
      </v-row>
   
      <v-rating
        v-model="rating"
        background-color="orange lighten-3"
        color="orange"
        large
      ></v-rating>
     
      <v-textarea solo name="content" label="내용을 입력하세요"></v-textarea>
     
      <v-checkbox label="비밀글로 작성하기" color="black"></v-checkbox>
      
      <v-row >
        
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
      <br/>
      <v-row>
      <v-file-input
        class="input"
        type="file"
      label=" 사진 업로드"
        accept="image/png, image/jpeg, image/bmp"
        multiple
        prepend-icon="mdi-camera"
        style="width: 140px"
        @change="onImageChange"
      />
      </v-row>
      <v-row>
  
    <v-col
    cols="2"
        md="4"> 태그 입력</v-col>
    <v-col cols="10"
        sm=""
        md="8">
      <v-text-field

            
            filled
          ></v-text-field>
    </v-col>
   <v-icon
      large
      color="green darken-2"
    >
      mdi-check
    </v-icon>
      </v-row>
<v-row>
    <v-col>
      <v-btn @click="submit"> submit </v-btn>
    </v-col>
    <v-col>
      <v-btn @click="clear"> clear </v-btn>
    </v-col>
</v-row>

    </form>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const reviewStore = "reviewStore";

export default {
  name: "ReviewRegist",
  data: () => ({
    rating: 3,
   // data속성에서 추가되는 변수들
    uploadimageurl: [],	// 업로드한 이미지의 미리보기 기능을 위해 url 저장하는 객체
    imagecnt: 0,		// 업로드한 이미지 개수 => 제출버튼 클릭시 back서버와 axios 통신하게 되는데, 
			// 이 때, 이 값도 넘겨줌
  }),

computed: {
        ...mapState(["user"]),
    },

  components: {},
  methods: {
     ...mapActions(reviewStore,['logout','signout']),
   
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
