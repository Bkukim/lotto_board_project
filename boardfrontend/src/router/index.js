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
  // 로그인
  {
    path: "/member/login",
    component: () => import("../views/auth/LoginView.vue"),


  },  
  // 카카오 로그인
  {
    path: "/auth-redirect",
    component: () => import("../views/auth/KakaoRedirectView.vue"),
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
  // 비밀번호 재설정 페이지
  {
    path: "/member/new-pw",
    component: () => import("../views/auth/NewPwdView.vue"),
  },
  // 회원정보 수정 페이지
  {
    path: "/member/user-modify",
    component: () => import("../views/user/UserModifyView.vue"),
  },

  // 공지사항
  {
    path: "/notice/notice-board",
    component: () => import("../views/notice/noticeBoard.vue"),
  },
  // 공지사항 add
  {
    path: "/notice-add",
    component: () => import("../views/notice/AdminNoticeAdd.vue"),
  },
  // 자유 게시판
  {
    path: "/free/free-board",
    component: () => import("../views/free/freeBoard.vue"),
  },
  // 자유 게시판 -> 상세조회
  {
    path: "/free/free-boardDetail/:freeBoardId",
    component: () => import("../views/free/freeBoardDetail.vue"),
  },
  // 동아리 게시판
  {
    path: "/club/club-board",
    component: () => import("../views/club/clubBoard.vue"),
  },
  // 동아리 게시판 -> 매칭글쓰기
  {
    path: "/club/club-boardDetail",
    component: () => import("../views/club/clubBoardDetail.vue"),
  },
  // 동아리 게시판 -> 매칭 모집글 게시글
  {
    path: "/club/club-boardRecruitment",
    component: () => import("../views/club/clubBorardRecruitment.vue"),
  },
  // 건의 게시판
  {
    path: "/complaint/complaint-board",
    component: () => import("../views/complaint/complaintBoard.vue"),
  },
  // 각게시판별 공지사항 관리 ::
  {
    path: "/notice-master",
    component: () => import("../views/admin/MasterBoardNoticesAdmin.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;