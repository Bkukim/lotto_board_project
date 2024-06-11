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


  // 마이페이지
  {
    path: "/member/mypage",
    component: () => import("../views/auth/MyPage.vue"),
  },

  // 카카오 로그인
  {
    path: "/auth-redirect",
    component: () => import("../views/auth/KakaoRedirectView.vue"),
  },
  // 카카오 로그인 시 추가 정보 기입
  {
    path: "/member/login/additional-info/:userId",
    component: () => import("../views/auth/KakaoAdditionalInfo.vue"),
  },
  // 회원가입 페이지
  {
    path: "/member/join",
    component: () => import("../views/auth/JoinView.vue"),
  },
  // 회원가입 환영 페이지
  {
    path: "/member/join/welcome",
    component: () => import("../views/auth/WelcomeView.vue"),
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
  // 공지사항 -> 상세조회
  {
    path: "/notice/notice-check/:noticeId",
    component: () => import("../views/notice/NoticeCheck.vue"),
  },
  // 공지사항 -> 글쓰기
  {
    path: "/admin/notice-add",
    component: () => import("../views/admin/AdminNoticeAdd.vue"),
  },
  // 공지사항 수정 ::
  {
    path: "/admin/notice-update/:noticeId",
    component: () => import("../views/admin/AdminNoticeUpdate.vue"),
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
  // 자유 게시판 -> 글쓰기
  {
    path: "/free/free-boardAdd",
    component: () => import("../views/free/freeBoardAdd.vue"),
  },
  // 자유 게시판 -> 수정하기
  {
    path: "/free/free-board/Update/:freeBoardId",
    component: () => import("../views/free/freeBoardUpdate.vue"),
  },
  // 동아리 게시판
  {
    path: "/club/club-board",
    component: () => import("../views/club/ClubBoardList.vue"),
  },
  // 동아리 게시판 -> 매칭글쓰기
  {
    path: "/club/club-boardDetail",
    component: () => import("../views/club/ClubBoardWrite.vue"),
  },
  // 동아리 게시판 -> 매칭 모집글 게시글
  {
    path: "/club/club-boardRecruitment/:clubBoardId",
    component: () => import("../views/club/clubBoardDetail.vue"),
  },
  // 건의 게시판
  {
    path: "/complaint/complaint-board",
    component: () => import("../views/complaint/complaintBoard.vue"),
  },
  // 건의 게시판 -> 상세조회
  {
    path: "/complaint/complaint-boardDetail/:complaintBoardId",
    component: () => import("../views/complaint/complaintDetail.vue"),
  },
  // 건의 게시판 -> 글쓰기
  {
    path: "/complaint/complaint-boardAdd",
    component: () => import("../views/complaint/complaintBoardAdd.vue"),
  },
  // 건의 게시판 -> 수정하기
  {
    path: "/complaint/complaint-board/Update/:complaintBoardId",
    component: () => import("../views/complaint/complaintBoardUpdate.vue"),
  },
  // 각게시판별 공지사항 관리 ::
  {
    path: "/admin/notice-master",
    component: () => import("../views/admin/MasterBoardNoticesAdmin.vue"),
  },
  // HOT 게시판
  {
    path: "/hot/hotBoard",
    component: () => import("../views/hot/hotBoard.vue"),
  },

  // 부서 게시판

  {
    path: "/dept/:deptId",
    component: () => import("../views/dept/DeptBoard.vue"),
  },

  // 부서 게시판 : 재정부
  {
    path: "/dept/B0002",
    component: () => import("../views/dept/FinanceBoard.vue"),
  },
  // 부서 게시판 : 기획부
  {
    path: "/dept/C0003",
    component: () => import("../views/dept/PlanningBoard.vue"),
  },
  // 부서 게시판 : 홍보부
  {
    path: "/dept/D0004",
    component: () => import("../views/dept/PromotionBoard.vue"),
  },

  // 부서 게시판 : 상세페이지
  {
    path: "/dept/board/detail/:deptBoardId",
    component: () => import("../views/dept/DeptBoardDetail.vue"),
  },
  // 부서 게시판 : 글쓰기
  {
    path: "/dept/board/Add/:deptId",
    component: () => import("../views/dept/DeptBoardAdd.vue"),
  },
  // 부서 게시판 : 수정하기
  {
    path: "/dept/board/Update/:deptBoardId",
    component: () => import("../views/dept/DeptBoardUpdate.vue"),
  },
  // 관리자 신고 게시판
  {
    path: "/admin/report",
    component: () => import("../views/admin/AdminReportBoard.vue"),
  },
   // 관리자 신고 게시판 : 상세페이지
  {
    path: "/admin/report/:reportId",
    component: () => import("../views/admin/AdminReportBoardDetail.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
