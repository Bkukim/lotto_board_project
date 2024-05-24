import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const routes = [
  {
    path: "/",
    component: HomeView,
  },
  {
    path: "/about",
    component: () => import("../views/AboutView.vue"),
  },
  // 공지사항 add
  {
    path: "/notice-add",
    component: () => import("../views/notice/AdminNoticeAdd.vue"),
  },
  // 로그인
  {
    path: "/member/login",
    component: () => import("../views/auth/LoginView.vue"),
  },
  // 회원가입 페이지
  {
    path: "/member/join",
    component: () => import("../views/auth/JoinView.vue"),
  },
  // 아이디 찾기 페이지
  {
    path: "/member/find-id",
    component: () => import("../views/auth/FindId.vue"),
  },
  // 비밀번호 찾기 페이지
  {
    path: "/member/find-pwd",
    component: () => import("../views/auth/FindPwd.vue"),
  },
  // 공지사항
  {
    path: "/notice/notice-board",
    component: () => import("../views/notice/noticeBoard.vue"),
  },
  // 자유 게시판
  {
    path: "/free/free-board",
    component: () => import("../views/free/freeBoard.vue"),
  },
    // 자유 게시판 -> 상세조회
    {
      path: "/free/free-boardDetail",
      component: () => import("../views/free/freeBoardDetail.vue"),
    },
  // 동아리 게시판
  {
    path: "/clud/clud-board",
    component: () => import("../views/clud/cludBoard.vue"),
  },
  // 동아리 게시판 -> 매칭글쓰기
  {
    path: "/clud/clud-boardDetail",
    component: () => import("../views/clud/cludBoardDetail.vue"),
  },
  // 동아리 게시판 -> 매칭 모집글 게시글
  {
    path: "/clud/clud-boardRecruitment",
    component: () => import("../views/clud/cludBorardRecruitment.vue"),
  },
  // 건의 게시판
  {
    path: "/complaint/complaint-board",
    component: () => import("../views/complaint/complaintBoard.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
