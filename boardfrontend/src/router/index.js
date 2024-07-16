import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const routes = [
  {
    path: "/",
    component: HomeView,
    meta: { showHeader: true ,shoewFoter: true }, // 기본적으로 헤더를 보이게 설정

  },
  // 어도민 홈
  {
    path: "/admin/home",
    component: () => import("../views/admin/AdminHome.vue"),

    meta: { showHeader: false }, // 관리자 페이지에서는 헤더를 숨김
  },

  // 로그인
  {
    path: "/member/login",
    component: () => import("../views/auth/LoginView.vue"),
    meta: { showHeader: false , shoewFoter: true },
  },
  // 마이페이지
  {
    path: "/member/mypage",
    component: () => import("../views/auth/MyPage.vue"),
    meta: { showHeader: true , shoewFoter: true }, // 기본적으로 헤더를 보이게 설정
  },
  // 알림 더 기
  {
    path: "/member/notification",
    component: () => import("../views/auth/NotificationAdd.vue"),
    meta: { showHeader: true , shoewFoter: true }, // 기본적으로 헤더를 보이게 설정
  },
  // 카카오 로그인
  {
    path: "/auth-redirect",
    component: () => import("../views/auth/KakaoRedirectView.vue"),
    meta: { showHeader: true ,shoewFoter: true }, // 기본적으로 헤더를 보이게 설정
  },
  // 카카오 로그인 시 추가 정보 기입
  {
    path: "/member/login/additional-info/:uuid",
    component: () => import("../views/auth/KakaoAdditionalInfo.vue"),
    meta: { showHeader: false }, // 기본적으로 헤더를 보이게 설정
  },
  // 회원가입 페이지
  {
    path: "/member/join",
    component: () => import("../views/auth/JoinView.vue"),
    meta: { showHeader: false }, // 기본적으로 헤더를 보이게 설정
  },
  // 회원가입 환영 페이지
  {
    path: "/member/join/welcome",
    component: () => import("../views/auth/WelcomeView.vue"),
    meta: { showHeader: false }, // 기본적으로 헤더를 보이게 설정
  },
  // 아이디 찾기 페이지
  {
    path: "/member/find-id",
    component: () => import("../views/auth/FindId.vue"),
    meta: { showHeader: false }, // 기본적으로 헤더를 보이게 설정
  },
  // 비밀번호 찾기 페이지
  {
    path: "/member/find-pwd",
    component: () => import("../views/auth/FindPwd.vue"),
    meta: { showHeader: false,shoewFoter: true  }, // 기본적으로 헤더를 보이게 설정
  },
  // 비밀번호 재설정 페이지
  {
    path: "/member/new-pw",
    component: () => import("../views/auth/NewPwdView.vue"),
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정

  },
  // 검색 페이지
  {
    path: "/member/search",
    component: () => import("../views/SearchView.vue"),
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정

  },

  
  // 회원정보 수정 페이지
  {
    path: "/member/user-modify",
    component: () => import("../views/user/UserModifyView.vue"),
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
  },

  // 공지사항
  {
    path: "/notice/notice-board",
    component: () => import("../views/notice/noticeBoard.vue"),
    meta: { showHeader: true ,shoewFoter: true  }, // 기본적으로 헤더를 보이게 설정
  },
 // 공지사항 -> 상세조회
 {
  path: "/notice/notice-check/:noticeId/:eventYN",
  component: () => import("../views/notice/NoticeCheck.vue"),
  meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
},

  // 공지사항 -> 글쓰기
  {
    path: "/admin/notice-add",
    meta: { showHeader: false }, // 관리자 페이지에서는 헤더를 숨김
    component: () => import("../views/admin/AdminNoticeAdd.vue"),
  },
  // 공지사항 수정 ::
  {
    path: "/admin/notice-update/:noticeId",
    meta: { showHeader: false }, // 관리자 페이지에서는 헤더를 숨김
    component: () => import("../views/admin/AdminNoticeUpdate.vue"),
  },
  // 자유 게시판
  {
    path: "/free/free-board",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/free/freeBoard.vue"),
  },
  // 자유 게시판 -> 상세조회
  {
    path: "/free/free-boardDetail/:freeBoardId",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/free/freeBoardDetail.vue"),
  },
  // 자유 게시판 -> 글쓰기
  {
    path: "/free/free-boardAdd",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/free/freeBoardAdd.vue"),
  },
  // 자유 게시판 -> 수정하기
  {
    path: "/free/free-board/Update/:freeBoardId",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/free/freeBoardUpdate.vue"),
  },
  // 동아리 게시판
  {
    path: "/club/club-board",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/club/ClubBoardList.vue"),
  },
  // 동아리 게시판 -> 매칭글쓰기
  {
    path: "/club/club-boardDetail",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/club/ClubBoardWrite.vue"),
  },
  // 동아리 게시판 -> 매칭글 수정
  {
    path: "/club/update/:clubBoardId",
    component: () => import("../views/club/ClubBoardUpdate.vue"),
  },
  // 동아리 게시판 -> 매칭 모집글 게시글
  {
    path: "/club/club-boardRecruitment/:clubBoardId",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/club/clubBoardDetail.vue"),
  },
  // 건의 게시판
  {
    path: "/complaint/complaint-board",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/complaint/complaintBoard.vue"),
  },
  // 건의 게시판 -> 상세조회
  {
    path: "/complaint/complaint-boardDetail/:complaintBoardId",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/complaint/complaintDetail.vue"),
  },
  // 건의 게시판 -> 글쓰기
  {
    path: "/complaint/complaint-boardAdd",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/complaint/complaintBoardAdd.vue"),
  },
  // 건의 게시판 -> 수정하기
  {
    path: "/complaint/complaint-board/Update/:complaintBoardId",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/complaint/complaintBoardUpdate.vue"),
  },
  // 각게시판별 공지사항 관리 ::
  {
    path: "/admin/notice-master",
    meta: { showHeader: false }, // 관리자 페이지에서는 헤더를 숨김
    component: () => import("../views/admin/MasterBoardNoticesAdmin.vue"),
  },
  // HOT 게시판
  {
    path: "/hot/hotBoard",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/hot/hotBoard.vue"),
  },
  // 부서 게시판
  {
    path: "/dept/:deptId",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/dept/DeptBoard.vue"),
  },
  
  {
    path: "/dept/board/detail/:deptBoardId",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/dept/DeptBoardDetail.vue"),
  },
  // 부서 게시판 : 글쓰기
  {
    path: "/dept/board/Add/:deptId",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/dept/DeptBoardAdd.vue"),
  },
  // 부서 게시판 : 수정하기
  {
    path: "/dept/board/Update/:deptBoardId",
    meta: { showHeader: true }, // 기본적으로 헤더를 보이게 설정
    component: () => import("../views/dept/DeptBoardUpdate.vue"),
  },
  // 관리자 회원 관리
  {
    path: "/admin/member/manage",
    component: () => import("../views/admin/AdminMemberManagement.vue"),
  },
  // 관리자 신고 게시판
  {
    path: "/admin/report",
    meta: { showHeader: false }, // 관리자 페이지에서는 헤더를 숨김
    component: () => import("../views/admin/AdminReportBoard.vue"),
  },
  // // 관리자 신고 게시판 : 상세페이지
  // {
  //   path: "/admin/report/:reportId",
  //   meta: { showHeader: false }, // 관리자 페이지에서는 헤더를 숨김
  //   component: () => import("../views/admin/AdminReportBoardDetail.vue"),
  // },

  
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
