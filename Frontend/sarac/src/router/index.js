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
import LibraryView from "@/views/LibraryView.vue";
import BookDetailView from "@/views/BookDetailView.vue";
// component
import UserActivity from "@/components/MyFeedView/UserActivity.vue";
import UserSearchResult from "@/components/MyFeedView/UserSearchResult.vue";
import UserReview from "@/components/MyFeedView/UserReview.vue";
import UserStatistics from "@/components/MyFeedView/UserStatistics.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/home",
    name: "home",
    component: HomeView,
  },
  {
    path: "/detailReview/:reviewId",
    name: "detailReview",
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
      {
        path: ":fromLocation",
        name: "reviewSearch",
        component: () => import("@/views/SearchView.vue"),
      },
      {
        path: "regist/:reviewId",
        name: "modifyRegist",
        component: () => import("@/components/review/ReviewRegistView.vue"),
      },
    ],
  },
  {
    path: "/myfeed",
    name: "myfeed",
    component: MyFeedView,
    redirect: "/myfeed/activity/review",
    children: [
      {
        path: "activity",
        name: "useractivity",
        component: UserActivity,
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
          },
        ],
      },
      {
        path: "search",
        name: "usersearch",
        component: UserSearchResult,
      },
    ],
  },
  {
    path: "/library",
    name: "library",
    component: LibraryView,
  },
  {
    path: "/book/detail/:bookId",
    name: "bookDetail",
    component: BookDetailView,
    children: [],
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
