import Vue from "vue";
import VueRouter from "vue-router";
// view
import HomeView from "../views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import DetailReview from "@/views/DetailReview.vue";
import KakaoRedirect from "@/views/KakaoLoginRedirect.vue";
import MypageView from "@/views/MypageView.vue";
import SearchView from "@/views/SearchView.vue";
import MyFeedView from "@/views/MyFeedView.vue";
// component
import UserReview from "@/components/MyFeedView/UserReview";
import UserStatistics from "@/components/MyFeedView/UserStatistics";

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
  {
    path: "/search",
    name: "search",
    component: SearchView,
  },
  {
    path: "/review",
    name: "review",
    component: () => import("@/views/ReviewView.vue"),
    redirect: "/review/regist",
    children: [
      {
        path: "regist",
        name: "reviewRegist",
        component: () => import("@/components/review/ReviewRegistView.vue"),
      },
    ],
  },
  {
    path: "/myfeed",
    name: "myfeed",
    component: MyFeedView,
    children: [
      {
        path: "review",
        name: "userreview",
        component: UserReview,
      },
      {
        path: "statistics",
        name: "userstatistics",
        component: UserStatistics,
      }
    ]
  }
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
