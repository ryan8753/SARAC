<template>
  <v-row align="center" justify="center">
    <v-col cols="3">
      <v-avatar>
        <img :src="this.$store.state.accountStore.user.imagePath" alt="John" />
      </v-avatar>
    </v-col>
    <v-col cols="9">
      <v-text-field
        v-model="inputs.contents"
        :label="text"
        @keyup.enter="createComment()"
      ></v-text-field>
    </v-col>
  </v-row>
</template>

<script>
import { mapActions } from "vuex";
const reviewStore = "reviewStore";
export default {
  props: ["reviewid", "parentId"],
  data() {
    return {
      inputs: {
        reviewId: this.reviewid,
        contents: null,
        depth: 0,
        parentId: 0,
      },
      text: "null",
    };
  },
  created() {
    if (this.parentId == 0) {
      this.text = "댓글을 입력해 주세요";
    } else {
      this.text = "답글을 입력해 주세요";
    }
  },

  methods: {
    ...mapActions(reviewStore, ["createCommentApi"]),

    async createComment() {
      // console.log(this.inputs.contents);
      if (this.parentId != 0) {
        this.inputs.depth = 1;
        this.inputs.parentId = this.parentId;
      }
      // console.log(this.inputs);
      await this.createCommentApi(this.inputs);
      this.$emit("commentChanged");
      await this.resetContent();
    },
    async resetContent() {
      this.inputs.contents = "";
    },
  },
};
</script>

<style></style>
