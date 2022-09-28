<template>
  <v-form>
    <v-container>
      <v-row>
        <v-col cols="2">
          <v-img src="../../../public/icons/logo.png"></v-img>
        </v-col>
        <v-col cols="9">
          <v-text-field
            hide-details
            v-model="keyword"
            append-icon="mdi-magnify"
            append-outer-icon="mdi-camera"
            filled
            clear-icon="mdi-close-circle"
            clearable
            type="text"
            placeholder="검색어 입력"
            @click:append="searchBook"
            @click:append-outer="getPicture"
            @click:clear="clearKeyword"
            color="rgba(170, 83, 14, 1)"
            dense
            @keydown.enter.prevent="searchBook"
          ></v-text-field>
        </v-col>
        <v-col cols="1"> </v-col>
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
  }),
  components: {
    StreamBarcodeReader,
  },
  computed: {
    ...mapState(["searchResults"]),
  },
  methods: {
    ...mapActions(searchStore, ["getBookResults"]),
    ...mapMutations(searchStore, { setTypeTrue: "SET_TEXT_TRUE" }),
    searchBook() {
      if (this.keyword === undefined || this.keyword.length < 2) {
        alert("2글자 이상 입력해주세요.");
        return;
      }
      this.setTypeTrue();
      this.getBookResults(this.keyword);
    },
    getPicture() {
      this.showCamera = !this.showCamera;
    },
    clearKeyword() {
      this.keyword = "";
    },
    onDecode(text) {
      this.keyword = text;
      this.showCamera = false;
      this.$router.replace({ path: "/book/detail/" + this.keyword });
    },
  },
};
</script>
