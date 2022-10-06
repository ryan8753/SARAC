<template>
  <v-form>
    <v-container>
      <v-row>
        <v-col>
          <v-text-field
            hide-details
            v-model="keyword"
            prepend-inner-icon="mdi-menu-down"
            @click:prepend-inner="dropdown"
            append-icon="mdi-magnify"
            append-outer-icon="mdi-camera"
            filled
            clearable
            placeholder="검색어 입력"
            @click:append="searchBook"
            @click:append-outer="getPicture"
            color="#E3984B"
            dense
            @keydown.enter.prevent="searchBook"
            id="bar"
          ></v-text-field>
          <v-menu offset-y v-model="showMenu">
            <v-list>
              <v-list-item
                v-for="(item, index) in items"
                :key="index"
                @click="selectType(item.value)"
                :class="item.value == type ? 'orange--text' : 'grey--text'"
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-col>
      </v-row>
      <v-row v-if="showCamera">
        <StreamBarcodeReader @decode="onDecode"></StreamBarcodeReader>
      </v-row>
    </v-container>
    <v-container>
      <v-row justify="end">
        <v-btn
          text
          color="#E3984B"
          x-small
          class="px-4 ma-0"
          @click="toggleCloud"
          >책 트렌드</v-btn
        >
      </v-row>
      <v-row justify="center" class="mx-auto px-1">
        <cloud
          v-if="showCloud"
          :data="wordCloud"
          :fontSizeMapper="fontSizeMapper"
          :onWordClick="onWordClick"
          width="250"
          height="150"
        />
      </v-row>
    </v-container>
  </v-form>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { StreamBarcodeReader } from "vue-barcode-reader";
import Cloud from "vue-d3-cloud";

const searchStore = "searchStore";

export default {
  data: () => ({
    keyword: "",
    showCamera: false,
    items: [
      { title: "통합 검색", value: "UNIFIED" },
      { title: "키워드 검색", value: "KEYWORD" },
    ],
    showMenu: false,
    showCloud: false,
    type: null,
    // 워드클라우드
    fontSizeMapper: (word) => word.value / 500,
  }),
  components: {
    StreamBarcodeReader,
    Cloud,
  },
  computed: {
    ...mapState(searchStore, ["searchResults", "wordCloud"]),
  },
  watch: {
    $route() {
      this.showMenu = false;
      this.showCloud = false;
      this.keyword = this.$route.query.keyword;
      this.type = this.$route.query.type ? this.$route.query.type : "UNIFIED";
    },
  },
  methods: {
    ...mapActions(searchStore, ["getBookResults", "getWordCloud"]),
    searchBook() {
      if (
        this.keyword == null ||
        this.keyword === undefined ||
        this.keyword.trim().length < 2
      ) {
        console.log(this.keyword.trim());
        alert("2글자 이상 입력해주세요.");
        return;
      }

      if (window.location.href.includes("/review/search")) {
        this.$router
          .push(
            "/review/search?keyword=" +
              this.keyword +
              "&page=" +
              0 +
              "&type=" +
              this.type
          )
          .catch(() => {});
      } else {
        this.$router
          .push(
            "/search?keyword=" +
              this.keyword +
              "&page=" +
              0 +
              "&type=" +
              this.type
          )
          .catch(() => {});
      }
      document.getElementById("bar").blur();
    },
    getPicture() {
      this.showCamera = !this.showCamera;
    },
    onDecode(text) {
      // TODO : isbn으로 백엔드 검색 후 책 결과가 없다면 페이지 이동 x

      this.keyword = text;
      this.showCamera = false;
      this.$router.push({ path: "/book/detail/" + this.keyword });
    },
    dropdown() {
      this.showMenu = true;
    },
    selectType(value) {
      this.type = value;
    },
    toggleCloud() {
      this.showCloud = !this.showCloud ? true : false;
    },
    onWordClick(word) {
      if (window.location.href.includes("/review/search"))
        this.$router
          .push(
            "/review/search?keyword=" +
              word.text +
              "&page=" +
              0 +
              "&type=KEYWORD"
          )
          .catch(() => {});
      else
        this.$router
          .push("/search?keyword=" + word.text + "&page=" + 0 + "&type=KEYWORD")
          .catch(() => {});
    },
  },
  created() {
    this.getWordCloud();
    this.keyword = this.$route.query.keyword;
    this.type = this.$route.query.type ? this.$route.query.type : "UNIFIED";
  },
};
</script>
