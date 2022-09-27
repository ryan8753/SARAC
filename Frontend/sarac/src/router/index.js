import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import DetailReview from "@/views/DetailReview.vue";
import KakaoRedirect from "@/views/KakaoLoginRedirect.vue";
import MypageView from "@/views/MypageView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/home",
    name: "home",
    component: HomeView,
  },
  {
    path: "/detailReview/:reviewId",
    name: "detailReview/:reviewId",
    component: DetailReview,
    props: true,
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/redirect",
    name: "kakaoredirect",
    component: KakaoRedirect,
  },
  {
    path: "/mypage",
    name: "mypage",
    component: MypageView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach(function (to, from, next) {
  const accessToken = localStorage.getItem("accessToken");

  if (to.path === "/redirect" || to.path === "/login") {
    next();
  } else if (accessToken) {
    next();
  } else {
    next("/login");
  }
});

export default router;
