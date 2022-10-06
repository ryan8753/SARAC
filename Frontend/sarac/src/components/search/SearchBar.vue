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
  </v-form>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import { StreamBarcodeReader } from "vue-barcode-reader";
const searchStore = "searchStore";

export default {
  data: () => ({
    keyword: "",
    showCamera: false,
    items: [{ title: "Click Me" }, { title: "Click Me 2" }],
    showMenu: false,
  }),
  props:["page"],
  components: {
    StreamBarcodeReader,
  },
  computed: {
    ...mapState(["searchResults"]),
  },
  watch: {
    page(newVal) {
      this.getBookResults({keywork: this.keyword, page: newVal});
    }
  },
  methods: {
    ...mapActions(searchStore, ["getBookResults"]),
    ...mapMutations(searchStore, { setTypeTrue: "SET_TEXT_TRUE" }),
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

      // 타입 추가!!!
      this.$router.push("/search?keyword=" + this.keyword + "&page=" + this.page).catch(() => {});
      this.setTypeTrue();
      this.getBookResults({keyword: this.keyword, page: "0"});
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
  },
};
</script>
